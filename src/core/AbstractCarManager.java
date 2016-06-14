package core;

import java.awt.Polygon;
import java.awt.geom.Area;
import java.util.ArrayList;

public abstract class AbstractCarManager implements CarManager {
	Car car;
	World world;
	Direction direction;
	protected Checkpoints checkpoints;

	private double xCenter;
	private double yCenter;

	public AbstractCarManager(World w, Car car) {
		this.world = w;
		this.car = car;

		this.checkpoints = new Checkpoints();

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
	public void updateCar(ArrayList<CarManager> carManagerList, int i) {
		moving(true);
		check();
		moving(false);
		for (int j = i + 1; j < carManagerList.size(); j++)
			if (collisionDetection(carManagerList.get(j).getCar())) {
				// System.out.println(
				// "car: " + car.getID() + " collide with car :" +
				// carManagerList.get(j).getCar().getID());
				// final int behaviour = aheadCar(carManagerList.get(j));
				// System.out.println(behaviour);
				// responseCollision(this.car, behaviour);
				// responseCollision(carManagerList.get(j).getCar(), behaviour +
				// 1);

				applyCollision(carManagerList.get(j).getCar());
			}
	}

	private void applyCollision(Car car2) {
		if (inRange(car2.getAngle()) == 0) {

		}
	}

	private int inRange(double angle) {
		// SAME RANGE OF ANGLE == 1 , OPPOSITE RANGE == 0 , NEXT UP = 1, NEXT
		// DOWN = 2.

		return -1;
	}

	private int aheadCar(CarManager carManager) {

		// TODO
		// DA FARE LA CHECK CON IL RITORNO NEL CASO UNA CAR SFONDI UN'ALTRA CAR.

		if (this.direction == Direction.UP) {
			if (carManager.getDirection() == Direction.DOWN)
				return 4;
			else if (carManager.getDirection() == Direction.UP) {
				if (findCenter(this.getCar().getY1rot(), this.getCar().getY4rot()) < findCenter(
						carManager.getCar().getY1rot(), carManager.getCar().getY4rot())) {
					return 0;
				} else
					return 2;
			}
		} else if (direction == Direction.DOWN) {
			if (carManager.getDirection() == Direction.UP)
				return 4;
			else if (carManager.getDirection() == Direction.DOWN) {
				if (findCenter(this.getCar().getY1rot(), this.getCar().getY4rot()) < findCenter(
						carManager.getCar().getY1rot(), carManager.getCar().getY4rot())) {
					return 2;
				} else
					return 0;
			}
		} else if (direction == Direction.LEFT) {
			if (carManager.getDirection() == Direction.RIGHT)
				return 4;
			else if (carManager.getDirection() == Direction.LEFT) {
				if (findCenter(this.getCar().getX1rot(), this.getCar().getX4rot()) < findCenter(
						carManager.getCar().getX1rot(), carManager.getCar().getX4rot())) {
					return 0;
				} else {
					return 2;
				}
			}
		} else if (direction == Direction.RIGHT) {
			if (carManager.getDirection() == Direction.LEFT)
				return 4;
			else if (carManager.getDirection() == Direction.RIGHT) {
				if (findCenter(this.getCar().getX1rot(), this.getCar().getX4rot()) < findCenter(
						carManager.getCar().getX1rot(), carManager.getCar().getX4rot())) {
					return 2;
				} else
					return 0;
			}
		}

		return -1;

	}

	private double findCenter(double firstVar, double secondVar) {
		return Math.abs((firstVar + secondVar) / 2);
	}

	private void moving(boolean condition) {
		if (condition) {
			totalMove();
		} else {
			car.moveXYrot(world);

			if (car.isLEFT()) {
				if (car.getSpeed() != 0) {
					if (car.getSpeed() < 0) {
						car.setAngle(car.getAngle() + 0.011);
					} else {
						car.setAngle(car.getAngle() - 0.011);
					}
				}

			}
			if (car.isRIGHT()) {
				if (car.getSpeed() != 0) {
					if (car.getSpeed() < 0) {
						car.setAngle(car.getAngle() - 0.011);
					} else {
						car.setAngle(car.getAngle() + 0.011);
					}
				}
			}
			if (car.getAngle() < 0)
				car.setAngle(car.getAngle() + 6.283185307179586);
			if (car.getAngle() >= 6.283185307179586) {
				car.setAngle(0);
			}
		}
	}

	abstract void totalMove();

	// Collision Detection.
	@Override
	public boolean collisionDetection(Car car) {

		int[] xpoints = { (int) this.car.getX1rot(), (int) this.car.getX2rot(), (int) this.car.getX3rot(),
				(int) this.car.getX4rot() };
		int[] ypoints = { (int) this.car.getY1rot(), (int) this.car.getY2rot(), (int) this.car.getY3rot(),
				(int) this.car.getY4rot() };
		int npoints = 4;

		Area areaA = new Area(new Polygon(xpoints, ypoints, npoints));

		xpoints[0] = (int) car.getX1rot();
		xpoints[1] = (int) car.getX2rot();
		xpoints[2] = (int) car.getX4rot();
		xpoints[3] = (int) car.getX4rot();

		ypoints[0] = (int) car.getY1rot();
		ypoints[1] = (int) car.getY2rot();
		ypoints[2] = (int) car.getY4rot();
		ypoints[3] = (int) car.getY4rot();

		areaA.intersect(new Area(new Polygon(xpoints, ypoints, npoints)));

		return !areaA.isEmpty();

	}

	void responseCollision(Car car, int behaviour) {
		// FIXME

		if (behaviour == 0 || behaviour == 3) { // CAR STA ANDANDO AVANTI E
												// VIENE COLPITA DA DIETRO.
			// gestione velocita' per marcia avanti
			if (car.isUP()) {
				car.setSpeed(car.getSpeed() + 0.01);
			} else if (car.isDOWN()) {
				car.setSpeed(-car.getSpeed() + 0.01);
			} else {
				car.setSpeed(car.getSpeed() + 0.03);
			}

		} else if (behaviour == 1 || behaviour == 2) {
			// CAR
			// STA
			// ANDANDO
			// AVANTI
			// E COLPISCE DAVANTI.
			if (car.isUP() || car.isDOWN()) {
				car.setSpeed(-car.getSpeed());
			} else {
				car.setSpeed(car.getSpeed() + 0.04);
			}
		} else if (behaviour == 4 || behaviour == 5) {
			// CAR FANNO FACE TO FACE.
			if (car.isUP() || car.isDOWN()) {
				car.setSpeed(-car.getSpeed());
			} else {
				car.setSpeed(car.getSpeed() - 0.04);
			}

		}
		if (car.isLEFT()) {
			if (car.getSpeed() != 0) {
				if (car.getSpeed() < 0) {
					car.setAngle(car.getAngle() - 0.012);
				} else {
					car.setAngle(car.getAngle() + 0.012);
				}
			}

		} else if (car.isRIGHT()) {
			if (car.getSpeed() != 0) {
				if (car.getSpeed() < 0) {
					car.setAngle(car.getAngle() + 0.012);
				} else {
					car.setAngle(car.getAngle() - 0.012);
				}
			}
		}

		if (car.getAngle() < 0)
			car.setAngle(car.getAngle() + 6.283185307179586);
		if (car.getAngle() >= 6.283185307179586) {
			car.setAngle(0);
		}

		// else {
		// // gestione velocita' per marcia avanti
		// if (car.isUP()) {
		// if (car.getSpeed() < 4)
		// car.setSpeed(car.getSpeed() - 0.05);
		// }
		// if (!car.isUP()) {
		// if (car.getSpeed() >= 0.05)
		// car.setSpeed(car.getSpeed() + 0.06);
		// }
		// if (car.isDOWN()) {
		// if (car.getSpeed() >= 0.15)
		// car.setSpeed(car.getSpeed() + 0.16);
		// }
		//
		// // gestione velocita' per marcia indietro
		// if (car.isDOWN()) {
		// if (car.getSpeed() > -2)
		// car.setSpeed(car.getSpeed() + 0.05);
		// }
		// if (!car.isDOWN()) {
		// if (car.getSpeed() <= -0.05)
		// car.setSpeed(car.getSpeed() - 0.06);
		// }
		// if (car.isUP()) {
		// if (car.getSpeed() <= -0.15)
		// car.setSpeed(car.getSpeed() - 0.16);
		// }
		//
		// if ((car.getSpeed() > -0.2) && (car.getSpeed() < 0.2) && (!car.isUP()
		// && !car.isDOWN())) {
		// car.setSpeed(0);
		// }
		// }
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
				car.setSpeed(car.getSpeed() + 0.04);
		}
		if (!car.isUP()) {
			if (car.getSpeed() >= 0.05)
				car.setSpeed(car.getSpeed() - 0.05);
		}
		if (car.isDOWN()) {
			if (car.getSpeed() >= 0.15)
				car.setSpeed(car.getSpeed() - 0.15);
		}

		// gestione velocita' per marcia indietro
		if (car.isDOWN()) {
			if (car.getSpeed() > -2)
				car.setSpeed(car.getSpeed() - 0.04);
		}
		if (!car.isDOWN()) {
			if (car.getSpeed() <= -0.05)
				car.setSpeed(car.getSpeed() + 0.05);
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
		return direction;
	}
}
