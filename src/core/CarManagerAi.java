package core;

public class CarManagerAi extends AbstractCarManager {

	BlockRoadObject lastPiece;
	GameManager game;
	boolean alpha = true;
	boolean beta;
	double lastX, lastY;
	final double AXEL = 2.0, BRAKE = 1.3;

	public CarManagerAi(World w, Car car, GameManager game) {
		super(w, car);
		this.lastX = car.getX1rot();
		this.lastY = car.getY1rot();
		this.game = game;
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
			this.direction = reconDirection();
			alpha = false;
		}

		if (car.getSpeed() <= BRAKE) {
			accel();
		} else if (car.getSpeed() >= AXEL) {
			brake();
		}

		if (world.getMatrixWorld().whereAmI(car) instanceof BlockRoadCurveLeftDown) {
			// System.out.println("LD");
			if (direction == Direction.RIGHT) {
				steerRight();
				direction = Direction.DOWN;
			} else if (direction == Direction.UP) {
				steerLeft();
				direction = Direction.LEFT;
			}

			if (direction == Direction.DOWN) {
				if (car.getAngle() > Math.PI / 2)
					ahed(direction);
			} else if (direction == Direction.LEFT) {
				if (car.getAngle() < Math.PI)
					ahed(direction);
			}

			lastPiece = world.getMatrixWorld().whereAmI(car);

		} else if (world.getMatrixWorld().whereAmI(car) instanceof BlockRoadCurveLeftUp) {
			// System.out.println("LU");
			if (car.getX1rot() == lastX && car.getY1rot() == lastY) {
				backward();
			}

			if (direction == Direction.DOWN) {
				steerRight();
				direction = Direction.LEFT;
			} else if (direction == Direction.RIGHT) {
				steerLeft();
				direction = Direction.UP;
			}

			if (direction == Direction.LEFT) {
				if (car.getAngle() > Math.PI)
					ahed(direction);
			} else if (direction == Direction.UP) {
				if (car.getAngle() < Math.PI * 3 / 2 && car.getAngle() > Math.PI)
					ahed(direction);
			}

			lastPiece = world.getMatrixWorld().whereAmI(car);

		} else if (world.getMatrixWorld().whereAmI(car) instanceof BlockRoadCurveRightUp) {
			// System.out.println("RU");
			if (direction == Direction.LEFT) {
				steerRight();
				direction = Direction.UP;
			} else if (direction == Direction.DOWN) {
				steerLeft();
				direction = Direction.RIGHT;
			}

			if (direction == Direction.UP || direction == Direction.RIGHT) {
				if (car.getAngle() > Math.PI * 3 / 2)
					ahed(direction);
			}

			lastPiece = world.getMatrixWorld().whereAmI(car);

		} else if (world.getMatrixWorld().whereAmI(car) instanceof BlockRoadCurveRightDown) {
			// System.out.println("RD");
			// System.out.println(car.getSpeed());
			if (direction == Direction.UP) {
				steerRight();
				direction = Direction.RIGHT;
			} else if (direction == Direction.LEFT) {
				steerLeft();
				direction = Direction.DOWN;
			}

			if (direction == Direction.DOWN) {
				if (car.getAngle() < Math.PI / 2)
					ahed(direction);
			} else if (direction == Direction.RIGHT) {
				if (car.getAngle() > 0.0)
					ahed(direction);
			}

			lastPiece = world.getMatrixWorld().whereAmI(car);

		} else if (world.getMatrixWorld().whereAmI(car) instanceof BlockRoadHorizontal) {
			// System.out.println("H");
			die();
			if (direction == Direction.LEFT) {
				ahed(direction);
			} else if (direction == Direction.RIGHT) {
				ahed(direction);
			}

			lastPiece = world.getMatrixWorld().whereAmI(car);

		} else if (world.getMatrixWorld().whereAmI(car) instanceof BlockRoadVertical) {
			// System.out.println("V");
			die();
			if (direction == Direction.UP) {
				ahed(direction);
			} else if (direction == Direction.DOWN) {
				ahed(direction);
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

	private void ahed(Direction direction) {
		accel();
		// checkBOOL();
		if (direction == Direction.RIGHT) {
			// System.out.println("R1\t" + car.getAngle());
			if (car.getAngle() > (0.0) && car.getAngle() < (Math.PI / 2)) {
				car.setAngle(car.getAngle() - 0.011);
				// System.out.println("r2\t" + car.getAngle());
			} else if (car.getAngle() < (Math.PI * 2) && car.getAngle() > (Math.PI / 2 * 3)) {
				car.setAngle(car.getAngle() + 0.011);
				// System.out.println("r3\t" + car.getAngle());
			}
		} else if (direction == Direction.LEFT) {

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
		} else if (direction == Direction.UP) {
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
		} else if (direction == Direction.DOWN) {

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
}