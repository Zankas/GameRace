package core;

public class CarManagerDummyAi extends AbstractCarManager {

	BlockRoadObject lastPiece;
	boolean alpha = true;
	boolean beta;
	Direction aiDirection;

	double lastX, lastY;
	final double AXEL = 2.0, BRAKE = 1.3;

	public CarManagerDummyAi(World w, Car car) {
		super(w, car);
		this.lastX = car.getX1rot();
		this.lastY = car.getY1rot();
		this.lastPiece = world.getMatrixWorld().whereAmI(car);
	}

	// START AI HANDLER

	private Direction reconDirection() {
		if (car.getAngle() == 0.0) { // 0.0
			return Direction.RIGHT;
		} else if (car.getAngle() == Math.PI) {
			return Direction.LEFT;
		} else if (car.getAngle() == Math.PI / 2) {
			return Direction.DOWN;
		}
		return Direction.UP;
	}

	public void autoMove() {
		if (alpha) {
			this.aiDirection = reconDirection();
			alpha = false;
		}

		if (car.getSpeed() <= BRAKE) {
			accel();
		} else if (car.getSpeed() >= AXEL) {
			brake();
		}

		if (world.getMatrixWorld().whereAmI(car) instanceof BlockRoadCurveLeftDown) {
			// System.out.println("LD");
			if (aiDirection == Direction.RIGHT) {
				steerRight();
				aiDirection = Direction.DOWN;
			} else if (aiDirection == Direction.UP) {
				steerLeft();
				aiDirection = Direction.LEFT;
			}

			if (aiDirection == Direction.DOWN) {
				if (car.getAngle() > Math.PI / 2)
					ahed(aiDirection);
			} else if (aiDirection == Direction.LEFT) {
				if (car.getAngle() < Math.PI)
					ahed(aiDirection);
			}

			lastPiece = world.getMatrixWorld().whereAmI(car);

		} else if (world.getMatrixWorld().whereAmI(car) instanceof BlockRoadCurveLeftUp) {
			// System.out.println("LU");
			if (car.getX1rot() == lastX && car.getY1rot() == lastY) {
				backward();
			}

			if (aiDirection == Direction.DOWN) {
				steerRight();
				aiDirection = Direction.LEFT;
			} else if (aiDirection == Direction.RIGHT) {
				steerLeft();
				aiDirection = Direction.UP;
			}

			if (aiDirection == Direction.LEFT) {
				if (car.getAngle() > Math.PI)
					ahed(aiDirection);
			} else if (aiDirection == Direction.UP) {
				if (car.getAngle() < Math.PI * 3 / 2 && car.getAngle() > Math.PI)
					ahed(aiDirection);
			}

			lastPiece = world.getMatrixWorld().whereAmI(car);

		} else if (world.getMatrixWorld().whereAmI(car) instanceof BlockRoadCurveRightUp) {
			// System.out.println("RU");
			if (aiDirection == Direction.LEFT) {
				steerRight();
				aiDirection = Direction.UP;
			} else if (aiDirection == Direction.DOWN) {
				steerLeft();
				aiDirection = Direction.RIGHT;
			}

			if (aiDirection == Direction.UP || aiDirection == Direction.RIGHT) {
				if (car.getAngle() > Math.PI * 3 / 2)
					ahed(aiDirection);
			}

			lastPiece = world.getMatrixWorld().whereAmI(car);

		} else if (world.getMatrixWorld().whereAmI(car) instanceof BlockRoadCurveRightDown) {
			// System.out.println("RD");
			// System.out.println(car.getSpeed());
			if (aiDirection == Direction.UP) {
				steerRight();
				aiDirection = Direction.RIGHT;
			} else if (aiDirection == Direction.LEFT) {
				steerLeft();
				aiDirection = Direction.DOWN;
			}

			if (aiDirection == Direction.DOWN) {
				if (car.getAngle() < Math.PI / 2)
					ahed(aiDirection);
			} else if (aiDirection == Direction.RIGHT) {
				if (car.getAngle() > 0.0)
					ahed(aiDirection);
			}

			lastPiece = world.getMatrixWorld().whereAmI(car);

		} else if (world.getMatrixWorld().whereAmI(car) instanceof BlockRoadHorizontal) {
			// System.out.println("H");
			die();
			if (aiDirection == Direction.LEFT) {
				ahed(aiDirection);
			} else if (aiDirection == Direction.RIGHT) {
				ahed(aiDirection);
			}

			lastPiece = world.getMatrixWorld().whereAmI(car);

		} else if (world.getMatrixWorld().whereAmI(car) instanceof BlockRoadVertical) {
			// System.out.println("V");
			die();
			if (aiDirection == Direction.UP) {
				ahed(aiDirection);
			} else if (aiDirection == Direction.DOWN) {
				ahed(aiDirection);
			}

			lastPiece = world.getMatrixWorld().whereAmI(car);
		}
	}

	// END AI HANDLER

	// START SUPPORT FUNCTIONS

	private void backward() {
		int speed = -2;
		car.setSpeed(speed);
	}

	private void die() {
		car.setLEFT(false);
		car.setRIGHT(false);
		car.setDOWN(false);
		car.setUP(false);
	}

	private void accel() {
		car.setUP(true);
		car.setDOWN(false);

	}

	private void brake() {
		car.setUP(false);
		car.setDOWN(true);
	}

	private void ahed(Direction aiDirection) {
		accel();
		// checkBOOL();
		if (aiDirection == Direction.RIGHT) {
			// System.out.println("R1\t" + car.getAngle());
			if (car.getAngle() > (0.0) && car.getAngle() < (Math.PI / 2)) {
				car.setAngle(car.getAngle() - 0.011);
				// System.out.println("r2\t" + car.getAngle());
			} else if (car.getAngle() < (Math.PI * 2) && car.getAngle() > (Math.PI / 2 * 3)) {
				car.setAngle(car.getAngle() + 0.011);
				// System.out.println("r3\t" + car.getAngle());
			}
		} else if (aiDirection == Direction.LEFT) {

			if (car.getAngle() > (Math.PI)) {
				car.setAngle(car.getAngle() - 0.011);
			} else if (car.getAngle() < (Math.PI)) {
				car.setAngle(car.getAngle() + 0.011);
			}
			// System.out.println("l\t" + car.getAngle());
			// if (car.getAngle() > (Math.PI)) {
			// System.out.println("steerLeft");
			// steerLeft();
			// } else if (car.getAngle() < (Math.PI)) {
			// System.out.println("steerRight");
			// steerRight();
			// }
		} else if (aiDirection == Direction.UP) {
			// if (car.getAngle() > (Math.PI / 36 * 55)) {
			// car.setAngle(car.getAngle() - 0.011);
			// } else if (car.getAngle() < (Math.PI / 36 * 53)) {
			// car.setAngle(car.getAngle() + 0.011);
			// }
			if (car.getAngle() > (Math.PI / 2 * 3)) {
				car.setAngle(car.getAngle() - 0.011);
			} else if (car.getAngle() < (Math.PI / 2 * 3)) {
				car.setAngle(car.getAngle() + 0.011);
			}
		} else if (aiDirection == Direction.DOWN) {

			if (car.getAngle() > (Math.PI / 2)) {
				car.setAngle(car.getAngle() - 0.011);
			} else if (car.getAngle() < (Math.PI / 2)) {
				car.setAngle(car.getAngle() + 0.011);
			}
		}

	}

	private void steerLeft() {
		car.setLEFT(true);
		car.setRIGHT(false);
		if (car.getSpeed() > AXEL) {
			brake();
		}
		if (car.getSpeed() < BRAKE) {
			accel();
		}
	}

	private void steerRight() {
		car.setRIGHT(true);
		car.setLEFT(false);
		if (car.getSpeed() > AXEL) {
			brake();
		}
		if (car.getSpeed() < BRAKE) {
			accel();
		}
	}

	// END SUPPORT

	@Override
	void totalMove() {

		if (checkpoints.getActualLaps() != checkpoints.getTotalLaps()) {
			autoMove();
			car.move(world);
		}
	}

	public Direction getAiDirection() {
		try {
			lock.lock();
			return aiDirection;
		} finally {
			lock.unlock();
		}
	}

	public void setAiDirection(final Direction dir) {
		lock.lock();
		this.aiDirection = dir;
		lock.unlock();
	}
}