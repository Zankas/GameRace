package core;

import java.awt.Polygon;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractCarManager implements CarManager {
	Car car;
	World world;
	Checkpoints checkpoints;

	// POSITION OF THE CAR RESPECT TO THE OTHERS.
	int position;
	// DIRECTION FOR CAR COLLISION.
	private Direction direction;
	// ANGLE FOR STEERING.
	final double angle = 0.011;
	// ANGLE FOR COLLISION.
	final double collisionAngle = angle + 0.001;
	// SPEED FOR ACCELLERATION.
	final double speed = 0.04;
	// SPEED FOR COLLISION.
	final double collisionSpeed = speed + 0.01;

	final Lock lock = new ReentrantLock();

	public AbstractCarManager(World w, Car car) {
		this.world = w;
		this.car = car;

		this.checkpoints = new Checkpoints();
		this.position = car.getID();
		checkpoints.setTotalLaps(3);// default laps track
	}

	public void makeCheckPoint() {

		for (int i = 0; i < World.X_MATRIX_STRING; i++) {
			for (int j = 0; j < World.Y_MATRIX_STRING; j++) {

				if ("horizontal".equals(world.getMatrixString()[i][j])
						|| "vertical".equals(world.getMatrixString()[i][j])
						|| "curveleftdown".equals(world.getMatrixString()[i][j])
						|| "curveleftup".equals(world.getMatrixString()[i][j])
						|| "curverightup".equals(world.getMatrixString()[i][j])
						|| "curverightdown".equals(world.getMatrixString()[i][j])) {

					checkpoints.insertCheckPoint(i, j);
				}

			}
		}
	}

	public Checkpoints getCheckpoints() {
		return checkpoints;
	}

	@Override
	public void updateCar(final ArrayList<CarManager> carManagerList, final int i) {
		totalMove();
		check();
		moving();
		setDirection();
		for (int j = i + 1; j < carManagerList.size(); j++) {
			if (collisionDetection(carManagerList.get(j).getCar())) {
				applyCollision(carManagerList.get(j).getCar(), aheadCar(carManagerList.get(j)));
			}
		}
	}

	private void applyCollision(Car car2, int aheadCar) {
		switch (aheadCar) {
		// 0, 1, 2 ARE COMMON FOR ALL.
		// 3 FOR DOWN TO LEFT.
		// 4 FOR UP TO LEFT.
		// 5, 6 FOR UP TO LEFT/RIGHT.
		// 7 FOR UP TO RIGHT.
		// 8, 9 FOR DOWN TO LEFT.
		case 0: // FACE TO FACE.
			if (car.getSpeed() > 0) {
				car.setSpeed(car.getSpeed() - collisionSpeed);
				car2.setSpeed(car2.getSpeed() - collisionSpeed);
			} else {
				car.setSpeed(car.getSpeed() + collisionSpeed);
				car2.setSpeed(car2.getSpeed() + collisionSpeed);
			}
			break;
		case 1: // SAME DIRECTION, TAMPONATO.
			car2.setSpeed(car2.getSpeed() - collisionSpeed);
			car.setSpeed(car.getSpeed() + collisionSpeed);
			break;
		case 2: // SAME DIRECTION, TAMPONATORE.
			car.setSpeed(car.getSpeed() - collisionSpeed);
			car2.setSpeed(car2.getSpeed() + collisionSpeed);
			break;
		case 3:
			if (car2.getSpeed() > 0) {
				car2.setSpeed(car2.getSpeed() - collisionSpeed);
				if (findCenter(car.getY3rot(), car.getY1rot()) < findCenter(car2.getY3rot(), car2.getY4rot())) {
					car.setAngle(car.getAngle() + collisionAngle);
				} else {
					car.setAngle(car.getAngle() - collisionAngle);
				}
			} else {
				car2.setSpeed(car2.getSpeed() + collisionSpeed);
				if (findCenter(car.getY3rot(), car.getY1rot()) < findCenter(car2.getY3rot(), car2.getY4rot())) {
					car.setAngle(car.getAngle() - collisionAngle);
				} else {
					car.setAngle(car.getAngle() + collisionAngle);
				}
			}
			break;
		case 4:
			if (car2.getSpeed() > 0) {
				car2.setSpeed(car2.getSpeed() - collisionSpeed);
				if (findCenter(car.getY4rot(), car.getY2rot()) > findCenter(car2.getY3rot(), car2.getY4rot())) {
					car.setAngle(car.getAngle() - collisionAngle);
				} else {
					car.setAngle(car.getAngle() + collisionAngle);
				}
			} else {
				car2.setSpeed(car2.getSpeed() + collisionSpeed);
				if (findCenter(car.getY4rot(), car.getY2rot()) > findCenter(car2.getY3rot(), car2.getY4rot())) {
					car.setAngle(car.getAngle() + collisionAngle);
				} else {
					car.setAngle(car.getAngle() - collisionAngle);
				}
			}
			// if (findCenter(car.getY4rot(), car.getY2rot()) >
			// findCenter(car2.getY3rot(), car2.getY4rot())) {
			// car.setAngle(car.getAngle() + collisionAngle);
			// car2.setAngle(car.getAngle() - collisionAngle);
			// } else {
			// car.setAngle(car.getAngle() - collisionAngle);
			// car2.setAngle(car.getAngle() + collisionAngle);
			// }
			break;
		case 5:
			car.setSpeed(car.getSpeed() - collisionSpeed);
			if (findCenter(car.getX4rot(), car.getX3rot()) > findCenter(car2.getX3rot(), car2.getX1rot())) {
				car2.setAngle(car2.getAngle() - collisionAngle);
			} else {
				car2.setAngle(car2.getAngle() + collisionAngle);
			}
		case 6:
			car.setSpeed(car.getSpeed() + collisionSpeed);
			if (findCenter(car.getX4rot(), car.getX3rot()) > findCenter(car2.getX3rot(), car2.getX1rot())) {
				car2.setAngle(car2.getAngle() + collisionAngle);
			} else {
				car2.setAngle(car2.getAngle() - collisionAngle);
			}
			break;
		case 7:
			if (car2.getSpeed() <= 0) {
				car2.setSpeed(car2.getSpeed() - collisionSpeed);
				if (findCenter(car.getY4rot(), car.getY2rot()) > findCenter(car2.getY3rot(), car2.getY4rot())) {
					car.setAngle(car.getAngle() - collisionAngle);
				} else {
					car.setAngle(car.getAngle() + collisionAngle);
				}
			} else {
				car2.setSpeed(car2.getSpeed() + collisionSpeed);
				if (findCenter(car.getY4rot(), car.getY2rot()) > findCenter(car2.getY3rot(), car2.getY4rot())) {
					car.setAngle(car.getAngle() + collisionAngle);
				} else {
					car.setAngle(car.getAngle() - collisionAngle);
				}
			}
			break;
		case 8:
			car.setSpeed(car.getSpeed() - collisionSpeed);
			if (findCenter(car.getX4rot(), car.getX3rot()) < findCenter(car2.getX4rot(), car2.getX2rot())) {
				car2.setAngle(car2.getAngle() - collisionAngle);
			} else {
				car2.setAngle(car2.getAngle() + collisionAngle);
			}
			break;
		case 9:
			car.setSpeed(car.getSpeed() + collisionSpeed);
			if (findCenter(car.getX4rot(), car.getX3rot()) < findCenter(car2.getX4rot(), car2.getX2rot())) {
				car2.setAngle(car2.getAngle() + collisionAngle);
			} else {
				car2.setAngle(car2.getAngle() - collisionAngle);
			}
			break;
		case 10:
			if (car2.getSpeed() > 0) {
				car2.setSpeed(car2.getSpeed() - collisionSpeed);
				if (findCenter(car2.getY4rot(), car2.getY3rot()) < findCenter(car.getY4rot(), car.getY2rot())) {
					car.setAngle(car.getAngle() + collisionAngle);
				} else {
					car.setAngle(car.getAngle() - collisionAngle);
				}
			} else {
				car2.setSpeed(car2.getSpeed() + collisionSpeed);
				if (findCenter(car2.getY4rot(), car2.getY3rot()) < findCenter(car.getY4rot(), car.getY2rot())) {
					car.setAngle(car.getAngle() - collisionAngle);
				} else {
					car.setAngle(car.getAngle() + collisionAngle);
				}
			}
			break;
		case 11:
			car.setSpeed(car.getSpeed() - collisionSpeed);
			if (findCenter(car.getY3rot(), car.getY4rot()) > findCenter(car2.getY3rot(), car.getY1rot())) {
				car2.setAngle(car2.getAngle() - collisionAngle);
			} else {
				car2.setAngle(car2.getAngle() + collisionAngle);
			}
			break;
		case 12:
			car.setSpeed(car.getSpeed() + collisionSpeed);
			if (findCenter(car.getY3rot(), car.getY4rot()) > findCenter(car2.getY3rot(), car.getY1rot())) {
				car2.setAngle(car2.getAngle() + collisionAngle);
			} else {
				car2.setAngle(car2.getAngle() - collisionAngle);
			}
			break;
		case 13:
			if (car2.getSpeed() >= 0) {
				car2.setSpeed(car2.getSpeed() - collisionSpeed);
				if (findCenter(car.getX2rot(), car.getX4rot()) > findCenter(car2.getX3rot(), car.getX4rot())) {
					car.setAngle(car.getAngle() + collisionAngle);
				}
			} else {
				car2.setSpeed(car2.getSpeed() + collisionSpeed);
				if (findCenter(car.getX2rot(), car.getX4rot()) > findCenter(car2.getX3rot(), car.getX4rot())) {
					car.setAngle(car.getAngle() - collisionAngle);
				}

			}
			break;
		case 14:
			if (car2.getSpeed() >= 0) {
				car2.setSpeed(car2.getSpeed() - collisionSpeed);
				if (findCenter(car.getX2rot(), car.getX4rot()) > findCenter(car2.getX3rot(), car.getX4rot())) {
					car.setAngle(car.getAngle() - collisionAngle);
				}
			} else {
				car2.setSpeed(car2.getSpeed() + collisionSpeed);
				if (findCenter(car.getX2rot(), car.getX4rot()) > findCenter(car2.getX3rot(), car.getX4rot())) {
					car.setAngle(car.getAngle() + collisionAngle);
				}

			}
			break;
		case 15:
			car.setSpeed(car.getSpeed() - collisionSpeed);
			if (findCenter(car.getY3rot(), car.getY4rot()) > findCenter(car2.getY4rot(), car.getY2rot())) {
				car2.setAngle(car2.getAngle() + collisionAngle);
			} else {
				car2.setAngle(car2.getAngle() - collisionAngle);
			}
			break;
		case 16:
			car.setSpeed(car.getSpeed() + collisionSpeed);
			if (findCenter(car.getY3rot(), car.getY4rot()) > findCenter(car2.getY3rot(), car.getY1rot())) {
				car2.setAngle(car2.getAngle() - collisionAngle);
			} else {
				car2.setAngle(car2.getAngle() + collisionAngle);
			}
			break;
		case 17:
			if (car2.getSpeed() >= 0) {
				car2.setSpeed(car2.getSpeed() - collisionSpeed);
				if (findCenter(car2.getX3rot(), car2.getX4rot()) > findCenter(car.getX3rot(), car.getX1rot())) {
					car.setAngle(car.getAngle() - collisionAngle);
				} else {
					car.setAngle(car.getAngle() + collisionAngle);
				}
			} else {
				car2.setSpeed(car2.getSpeed() + collisionSpeed);
				if (findCenter(car2.getX1rot(), car2.getX2rot()) > findCenter(car.getX3rot(), car.getX1rot())) {
					car.setAngle(car.getAngle() + collisionAngle);
				} else {
					car.setAngle(car.getAngle() - collisionAngle);
				}
			}
			break;
		case 18:
			if (car2.getSpeed() >= 0) {
				car2.setSpeed(car2.getSpeed() - collisionSpeed);
				if (findCenter(car2.getX3rot(), car2.getX4rot()) > findCenter(car.getX4rot(), car.getX2rot())) {
					car.setAngle(car.getAngle() + collisionAngle);
				} else {
					car.setAngle(car.getAngle() - collisionAngle);
				}
			} else {
				car2.setSpeed(car2.getSpeed() + collisionSpeed);
				if (findCenter(car2.getX1rot(), car2.getX2rot()) > findCenter(car.getX3rot(), car.getX1rot())) {
					car.setAngle(car.getAngle() - collisionAngle);
				} else {
					car.setAngle(car.getAngle() + collisionAngle);
				}
			}
			break;
		}

	}

	private double findCenter(double firstVar, double secondVar) {
		return Math.abs((firstVar + secondVar) / 2);
	}

	private int aheadCar(CarManager carManager) {
		if (this.direction == Direction.UP)
			return aheadUp(carManager);
		else if (direction == Direction.DOWN) {
			return aheadDown(carManager);
		} else if (direction == Direction.LEFT) {
			return aheadLeft(carManager);
		}
		// if (direction == Direction.RIGHT)
		return aheadRight(carManager);
	}

	final private int aheadUp(final CarManager carManager) {
		if (carManager.getDirection() == Direction.DOWN)
			return 0;
		else if (carManager.getDirection() == Direction.UP) {
			if (findCenter(this.getCar().getY1rot(), this.getCar().getY4rot()) < findCenter(
					carManager.getCar().getY1rot(), carManager.getCar().getY4rot())) {
				return 1;
			} else
				return 2;
		} else if (carManager.getDirection() == Direction.RIGHT) {
			if (car.getSpeed() >= 0) {
				if (carManager.getCar().getY4rot() <= Math.min(car.getY3rot(), car.getY4rot())) {
					return 5;
				}
			} else if (carManager.getCar().getY3rot() >= Math.max(car.getY1rot(), car.getY2rot()))
				return 6;
			return 7;
		}
		// DIRECTION.RIGHT
		// if (car.getSpeed() >= 0) {
		if (carManager.getCar().getY3rot() <= Math.min(car.getY3rot(), car.getY4rot())) {
			return 5;
		} else if (carManager.getCar().getY4rot() >= Math.max(car.getY1rot(), car.getY2rot())) {
			return 6;
		}
		return 4;
	}

	final private int aheadDown(final CarManager carManager) {
		if (carManager.getDirection() == Direction.UP)
			return 0;
		else if (carManager.getDirection() == Direction.DOWN) {
			if (findCenter(this.getCar().getY1rot(), this.getCar().getY4rot()) < findCenter(
					carManager.getCar().getY1rot(), carManager.getCar().getY4rot())) {
				return 2;
			} else
				return 1;
		} else if (carManager.getDirection() == Direction.LEFT) {
			// if (car.getSpeed() >= 0) {
			if (carManager.getCar().getY4rot() >= Math.max(car.getY3rot(), car.getY4rot()))
				return 8;
			else if (carManager.getCar().getY3rot() <= Math.min(car.getY2rot(), car.getY1rot()))
				return 9;
			return 3;
		}
		// DIRECTION.RIGHT
		if (car.getSpeed() >= 0) {
			if (carManager.getCar().getY3rot() >= Math.max(car.getY3rot(), car.getY4rot())) {
				return 8;
			}
		} else if (carManager.getCar().getY4rot() <= Math.min(car.getY2rot(), car.getY1rot()))
			return 9;

		return 10;
	}

	final private int aheadRight(final CarManager carManager) {
		if (carManager.getDirection() == Direction.LEFT)
			return 0;
		else if (carManager.getDirection() == Direction.RIGHT) {
			if (findCenter(this.getCar().getX1rot(), this.getCar().getX4rot()) <= findCenter(
					carManager.getCar().getX1rot(), carManager.getCar().getX4rot())) {
				return 2;
			} else
				return 1;
		} else if (carManager.getDirection() == Direction.UP) {
			if (carManager.getCar().getX3rot() >= Math.max(car.getX3rot(), car.getX4rot()))
				return 11;
			else if (carManager.getCar().getX4rot() <= Math.min(car.getX1rot(), car.getX2rot())) {
				return 12;
			}
			return 13;
		}
		if (carManager.getCar().getX4rot() >= Math.max(car.getX3rot(), car.getX4rot())) {
			return 11;
		} else if (carManager.getCar().getX3rot() <= Math.min(car.getX1rot(), car.getX2rot())) {
			return 12;
		}
		return 14;
	}

	final private int aheadLeft(final CarManager carManager) {
		if (carManager.getDirection() == Direction.RIGHT)
			return 0;
		else if (carManager.getDirection() == Direction.LEFT) {
			if (findCenter(this.getCar().getX1rot(), this.getCar().getX4rot()) < findCenter(
					carManager.getCar().getX1rot(), carManager.getCar().getX4rot())) {
				return 1;
			} else {
				return 2;
			}
		} else if (carManager.getDirection() == Direction.UP) {
			if (carManager.getCar().getX4rot() <= Math.min(car.getX4rot(), car.getX3rot())) {
				return 15;
			} else if (carManager.getCar().getX3rot() >= Math.max(car.getX1rot(), car.getX2rot())) {
				return 16;
			}
			return 17;
		}
		// Direction.DOWN
		if (carManager.getCar().getX3rot() <= Math.min(car.getX4rot(), car.getX3rot())) {
			return 15;
		} else if (carManager.getCar().getX4rot() >= Math.max(car.getX1rot(), car.getX2rot())) {
			return 16;
		}
		return 17;
	}

	private void moving() {
		car.moveXYrot(world);

		if (car.isLEFT()) {
			if (car.getSpeed() != 0) {
				if (car.getSpeed() < 0) {
					car.setAngle(car.getAngle() + angle);
				} else {
					car.setAngle(car.getAngle() - angle);
				}
			}

		}
		if (car.isRIGHT()) {
			if (car.getSpeed() != 0) {
				if (car.getSpeed() < 0) {
					car.setAngle(car.getAngle() - angle);
				} else {
					car.setAngle(car.getAngle() + angle);
				}
			}
		}
		if (car.getAngle() < 0)
			car.setAngle(car.getAngle() + Math.PI * 2); // 6.283185307179586
		if (car.getAngle() >= Math.PI * 2) {
			car.setAngle(0);
		}
	}

	abstract void totalMove();

	// Collision Detection.
	@Override
	public boolean collisionDetection(Car car2) {
		int[] xpoints = { (int) this.car.getX1rot(), (int) this.car.getX2rot(), (int) this.car.getX3rot(),
				(int) this.car.getX4rot() };
		int[] ypoints = { (int) this.car.getY1rot(), (int) this.car.getY2rot(), (int) this.car.getY3rot(),
				(int) this.car.getY4rot() };
		int npoints = 4;

		Area areaA = new Area(new Polygon(xpoints, ypoints, npoints));

		xpoints[0] = (int) car2.getX1rot();
		xpoints[1] = (int) car2.getX2rot();
		xpoints[2] = (int) car2.getX3rot();
		xpoints[3] = (int) car2.getX4rot();

		ypoints[0] = (int) car2.getY1rot();
		ypoints[1] = (int) car2.getY2rot();
		ypoints[2] = (int) car2.getY3rot();
		ypoints[3] = (int) car2.getY4rot();

		areaA.intersect(new Area(new Polygon(xpoints, ypoints, npoints)));

		return !areaA.isEmpty();
	}

	void check() {
		collisionGrass();
		collisionCheckPoint();
		collisionStart();
	}

	@Override
	public void collisionGrass() {

		if (checking_off_the_track())
			return;

		double cos = Math.cos(car.getAngle());
		double sin = Math.sin(car.getAngle());

		if ((world.getMatrixWorld().getValuePosition((int) (car.getY1rot() + (sin * car.getSpeed())),
				(int) (car.getX1rot() + (cos * car.getSpeed()))) == BlockRoadObject.GRASS)
				|| (world.getMatrixWorld().getValuePosition((int) (car.getY2rot() + (sin * car.getSpeed())),
						(int) (car.getX2rot() + (cos * car.getSpeed()))) == BlockRoadObject.GRASS)
				|| (world.getMatrixWorld().getValuePosition((int) (car.getY3rot() + (sin * car.getSpeed())),
						(int) (car.getX3rot() + (cos * car.getSpeed()))) == BlockRoadObject.GRASS)
				|| (world.getMatrixWorld().getValuePosition((int) (car.getY4rot() + (sin * car.getSpeed())),
						(int) (car.getX4rot() + (cos * car.getSpeed()))) == BlockRoadObject.GRASS)) {

			if (car.getSpeed() > 0.75)
				car.setSpeed(car.getSpeed() - 0.03);

			if (car.getSpeed() < 0)
				if (car.getSpeed() < -0.75)
					car.setSpeed(car.getSpeed() + 0.03);
		}
	}

	public void collisionCheckPoint() {
		if (checking_off_the_track())
			return;

		double cos = Math.cos(car.getAngle());
		double sin = Math.sin(car.getAngle());

		if (world.getMatrixWorld().getValuePosition((int) (car.getY1rot() + (sin * car.getSpeed())),
				(int) (car.getX1rot() + (cos * car.getSpeed()))) == BlockRoadObject.CHECKPOINT) {

			checkpoints.setCheckpoint((int) (car.getY1rot() + (sin * car.getSpeed())) / AbstractBlockRoadObject.SIZE,
					(int) (car.getX1rot() + (cos * car.getSpeed())) / AbstractBlockRoadObject.SIZE, true);

		}

		if (world.getMatrixWorld().getValuePosition((int) (car.getY2rot() + (sin * car.getSpeed())),
				(int) (car.getX2rot() + (cos * car.getSpeed()))) == BlockRoadObject.CHECKPOINT) {

			checkpoints.setCheckpoint((int) (car.getY2rot() + (sin * car.getSpeed())) / AbstractBlockRoadObject.SIZE,
					(int) (car.getX2rot() + (cos * car.getSpeed())) / AbstractBlockRoadObject.SIZE, true);

		}

		if (world.getMatrixWorld().getValuePosition((int) (car.getY3rot() + (sin * car.getSpeed())),
				(int) (car.getX3rot() + (cos * car.getSpeed()))) == BlockRoadObject.CHECKPOINT) {

			checkpoints.setCheckpoint((int) (car.getY3rot() + (sin * car.getSpeed())) / AbstractBlockRoadObject.SIZE,
					(int) (car.getX3rot() + (cos * car.getSpeed())) / AbstractBlockRoadObject.SIZE, true);

		}

		if (world.getMatrixWorld().getValuePosition((int) (car.getY4rot() + (sin * car.getSpeed())),
				(int) (car.getX4rot() + (cos * car.getSpeed()))) == BlockRoadObject.CHECKPOINT) {

			checkpoints.setCheckpoint((int) (car.getY4rot() + (sin * car.getSpeed())) / AbstractBlockRoadObject.SIZE,
					(int) (car.getX4rot() + (cos * car.getSpeed())) / AbstractBlockRoadObject.SIZE, true);

		}
	}

	public boolean checking_off_the_track() {
		double cos = Math.cos(car.getAngle());
		double sin = Math.sin(car.getAngle());

		if (((int) (car.getY1rot() + (sin * car.getSpeed())) < 0)
				|| ((int) (car.getX1rot() + (cos * car.getSpeed())) < 0)
				|| ((int) (car.getY1rot() + (sin * car.getSpeed())) >= (AbstractBlockRoadObject.getSize()
						* World.X_MATRIX_STRING))
				|| ((int) (car.getX1rot() + (cos * car.getSpeed())) >= (AbstractBlockRoadObject.getSize()
						* World.Y_MATRIX_STRING))
				||

				((int) (car.getY2rot() + (sin * car.getSpeed())) < 0)
				|| ((int) (car.getX2rot() + (cos * car.getSpeed())) < 0)
				|| ((int) (car.getY2rot() + (sin * car.getSpeed())) >= (AbstractBlockRoadObject.getSize()
						* World.X_MATRIX_STRING))
				|| ((int) (car.getX2rot() + (cos * car.getSpeed())) >= (AbstractBlockRoadObject.getSize()
						* World.Y_MATRIX_STRING))
				||

				((int) (car.getY3rot() + (sin * car.getSpeed())) < 0)
				|| ((int) (car.getX3rot() + (cos * car.getSpeed())) < 0)
				|| ((int) (car.getY3rot() + (sin * car.getSpeed())) >= (AbstractBlockRoadObject.getSize()
						* World.X_MATRIX_STRING))
				|| ((int) (car.getX3rot() + (cos * car.getSpeed())) >= (AbstractBlockRoadObject.getSize()
						* World.Y_MATRIX_STRING))
				||

				((int) (car.getY4rot() + (sin * car.getSpeed())) < 0)
				|| ((int) (car.getX4rot() + (cos * car.getSpeed())) < 0)
				|| ((int) (car.getY4rot() + (sin * car.getSpeed())) >= (AbstractBlockRoadObject.getSize()
						* World.X_MATRIX_STRING))
				|| ((int) (car.getX4rot() + (cos * car.getSpeed())) >= (AbstractBlockRoadObject.getSize()
						* World.Y_MATRIX_STRING))) {

			return true;
		}
		return false;
	}

	public void collisionStart() {
		if (checking_off_the_track())
			return;

		double cos = Math.cos(car.getAngle());
		double sin = Math.sin(car.getAngle());

		if ((world.getMatrixWorld().getValuePosition((int) (car.getY1rot() + (sin * car.getSpeed())),
				(int) (car.getX1rot() + (cos * car.getSpeed()))) == BlockRoadObject.START)
				|| (world.getMatrixWorld().getValuePosition((int) (car.getY2rot() + (sin * car.getSpeed())),
						(int) (car.getX2rot() + (cos * car.getSpeed()))) == BlockRoadObject.START)
				|| (world.getMatrixWorld().getValuePosition((int) (car.getY3rot() + (sin * car.getSpeed())),
						(int) (car.getX3rot() + (cos * car.getSpeed()))) == BlockRoadObject.START)
				|| (world.getMatrixWorld().getValuePosition((int) (car.getY4rot() + (sin * car.getSpeed())),
						(int) (car.getX4rot() + (cos * car.getSpeed()))) == BlockRoadObject.START)) {

			if (checkpoints.allCheckpointsPassed()) {
				checkpoints.setFalseAllCheckPoint();

				checkpoints.increaseLaps();

			}

		}
	}

	public Car getCar() {
		return car;
	}

	public void speedHandler() {
		// gestione velocita' per marcia avanti
		if (car.isUP()) {
			if (car.getSpeed() < 4)
				car.setSpeed(car.getSpeed() + speed);
		}
		if (!car.isUP()) {
			if (car.getSpeed() >= collisionSpeed)
				car.setSpeed(car.getSpeed() - collisionSpeed);
		}
		if (car.isDOWN()) {
			if (car.getSpeed() >= 0.15)
				car.setSpeed(car.getSpeed() - 0.15);
		}

		// gestione velocita' per marcia indietro
		if (car.isDOWN()) {
			if (car.getSpeed() > -2)
				car.setSpeed(car.getSpeed() - speed);
		}
		if (!car.isDOWN()) {
			if (car.getSpeed() <= -collisionSpeed)
				car.setSpeed(car.getSpeed() + collisionSpeed);
		}
		if (car.isUP()) {
			if (car.getSpeed() <= -0.15)
				car.setSpeed(car.getSpeed() + 0.15);
		}

		if ((car.getSpeed() > -0.2) && (car.getSpeed() < 0.2) && (!car.isUP() && !car.isDOWN())) {
			car.setSpeed(0);
		}
	}

	@Override
	public Direction getDirection() {
		try {
			lock.lock();
			return direction;
		} finally {
			lock.unlock();
		}

	}

	@Override
	public void setDirection() {
		lock.lock();
		if (car.getAngle() >= Math.PI / 4 && car.getAngle() <= Math.PI - Math.PI / 4) {
			direction = Direction.DOWN;
		} else if (car.getAngle() >= Math.PI + Math.PI / 4 && car.getAngle() <= Math.PI * 2 - Math.PI / 4) {
			direction = Direction.UP;
		} else if (car.getAngle() > Math.PI - Math.PI / 4 && car.getAngle() < Math.PI + Math.PI / 4) {
			direction = Direction.LEFT;
		} else {
			direction = Direction.RIGHT;
		}
		lock.unlock();
	}

	@Override
	public void setPosition(final int position) {
		lock.lock();
		this.position = position;
		lock.unlock();
	}

	@Override
	public int getPosition() {
		try {
			lock.lock();
			return this.position;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean equals(final Object obj) {
		if (car.getID() == ((AbstractCarManager) obj).getCar().getID())
			return true;
		return false;
		// return super.equals(obj);
	}
}