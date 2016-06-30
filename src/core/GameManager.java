package core;

import java.util.ArrayList;

public class GameManager {

	final private World world;
	private boolean update;

	private CarManagerHuman carManagerHuman;

	final ArrayList<CarManager> carManagerList;

	public GameManager() {

		this.update = true;
		this.world = new World();
		world.makeWorld();

		carManagerHuman = new CarManagerHuman(world, world.getCar());
		this.carManagerList = new ArrayList<>();
		carManagerList.add(carManagerHuman);
		switch (World.carToBeCreated) {
		case 2:
			carManagerList.add(new CarManagerDummyAi(world, world.getCar2()));
			break;
		case 3:
			carManagerList.add(new CarManagerDummyAi(world, world.getCar2()));
			carManagerList.add(new CarManagerDummyAi(world, world.getCar3()));

			break;
		case 4:
			carManagerList.add(new CarManagerReactiveAi(world, world.getCar2()));
			carManagerList.add(new CarManagerReactiveAi(world, world.getCar3()));
			carManagerList.add(new CarManagerDummyAi(world, world.getCar4()));
			break;
		}
		// switch (World.carToBeCreated) {
		// case 2:
		// carManagerList.add(new CarManagerDummyAi(world, world.getCar2(),
		// this));
		// break;
		// case 3:
		// carManagerList.add(new CarManagerDummyAi(world, world.getCar2(),
		// this));
		// carManagerList.add(new CarManagerDummyAi(world, world.getCar3(),
		// this));
		//
		// break;
		// case 4:
		// carManagerList.add(new CarManagerDummyAi(world, world.getCar2(),
		// this));
		// carManagerList.add(new CarManagerDummyAi(world, world.getCar3(),
		// this));
		// carManagerList.add(new CarManagerDummyAi(world, world.getCar4(),
		// this));
		// break;
		// }

		// carManagerList.add(new CarManagerAi(world, world.getCar2(), this));

		update();
		threadSpeedCar();
		settingPosition();

		// System.out.println(carManagerList.get(0).getCar().getID());
		// System.out.println(carManagerList.get(1).getCar().getID());
	}

	public ArrayList<CarManager> getCarManagerList() {
		return carManagerList;
	}

	public void init() {

		for (CarManager c : carManagerList) {
			c.makeCheckPoint();
		}
	}

	public void azzeraCheckPoint() {
		for (CarManager c : carManagerList) {
			c.getCheckpoints().setActualLaps(0);
			c.getCheckpoints().setFalseAllCheckPoint();
		}
	}

	public CarManagerHuman getCarManagerHuman() {
		return carManagerHuman;
	}

	public World getWorld() {
		return world;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public void update() {

		new Thread() {

			@Override
			public void run() {
				while (true) {

					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (update) {
						for (int i = 0; i < carManagerList.size(); i++) {
							carManagerList.get(i).updateCar(carManagerList, i);
						}
					}
				}
			}

		}.start();

	}

	public void settingPosition() {

		new Thread() {

			@Override
			public void run() {
				while (true) {

					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (update) {
						for (CarManager cm : carManagerList) {
							calculatePosition(cm);
						}
					}
				}
			}
		}.start();

	}

	public void threadSpeedCar() {

		new Thread() {

			@Override
			public void run() {
				while (true) {

					try {
						Thread.sleep(50);

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (update) {
						for (CarManager cm : carManagerList) {
							cm.speedHandler();
						}
					}
				}
			}
		}.start();
	}

	private void calculatePosition(final CarManager carManager) {
		int posCounter = 0;
		for (CarManager cm : carManagerList) {
			if (!cm.equals(carManager)) {
				if (carManager.getCheckpoints().getActualLaps() > cm.getCheckpoints().getActualLaps()) {
					posCounter++;
				} else if (carManager.getCheckpoints().getActualLaps() == cm.getCheckpoints().getActualLaps()) {
					if (carManager.getCheckpoints().numberCheckpointsHit() > cm.getCheckpoints()
							.numberCheckpointsHit()) {
						posCounter++;
					}
					if (carManager.getCheckpoints().numberCheckpointsHit() == cm.getCheckpoints()
							.numberCheckpointsHit()) {
						if (whichCarIsAhead(carManager, cm)) {
							posCounter++;
						}
					}
				}
			}
		}

		carManager.setPosition(carManagerList.size() - posCounter);

//		System.out.println("CAR : " + carManager.getCar().getID() + " POS : " + carManager.getPosition());
	}

	private boolean whichCarIsAhead(final CarManager carManager, final CarManager carManager2) {
		// THE FIRST CAR IS THE ALREADY IN THE IDS ARRAY.
		// FIXME AFTER MIDDLEPOINT ON THE WORLD.
		if (world.getMatrixWorld().whereAmI(carManager.getCar()) instanceof BlockRoadCurveLeftDown) {

		} else if (world.getMatrixWorld().whereAmI(carManager.getCar()) instanceof BlockRoadCurveLeftUp) {

		} else if (world.getMatrixWorld().whereAmI(carManager.getCar()) instanceof BlockRoadCurveRightUp) {

		} else if (world.getMatrixWorld().whereAmI(carManager.getCar()) instanceof BlockRoadCurveRightDown) {

		} else if (world.getMatrixWorld().whereAmI(carManager.getCar()) instanceof BlockRoadHorizontal) {
			if (carManager.getDirection() == Direction.RIGHT) {
				if (Math.max(carManager.getCar().getX3rot(), carManager.getCar().getX4rot()) > Math
						.max(carManager2.getCar().getX3rot(), carManager2.getCar().getX4rot())) {
					return true;
				}
			} else {
				if (Math.min(carManager.getCar().getX3rot(), carManager.getCar().getX4rot()) < Math
						.min(carManager2.getCar().getX3rot(), carManager2.getCar().getX4rot())) {
					return true;
				}
			}
		} else if (world.getMatrixWorld().whereAmI(carManager.getCar()) instanceof BlockRoadVertical) {
			if (carManager.getDirection() == Direction.DOWN) {
				if (Math.max(carManager.getCar().getY3rot(), carManager.getCar().getY4rot()) > Math
						.max(carManager2.getCar().getY3rot(), carManager2.getCar().getY4rot())) {
					return true;
				}
			} else {
				if (Math.min(carManager.getCar().getY3rot(), carManager.getCar().getY4rot()) < Math
						.min(carManager2.getCar().getY3rot(), carManager2.getCar().getY4rot())) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean endGame() {

		if (carManagerHuman.getCheckpoints().getActualLaps() != carManagerHuman.getCheckpoints().getTotalLaps()) {
			return false;
		}
		return true;

	}

	public String statusToString() {
		StringBuilder sb = new StringBuilder();
		for (CarManager cm : carManagerList) {
			sb.append(cm.getCar().getID() + ":" + cm.getCar().getX1() + ":" + cm.getCar().getX2() + ":"
					+ cm.getCar().getX3() + ":" + cm.getCar().getX4() + ":" + cm.getCar().getY1() + ":"
					+ cm.getCar().getY2() + ":" + cm.getCar().getY3() + ":" + cm.getCar().getY4() + ":"
					+ cm.getDirection() + ":" + cm.getPosition());
		}
		return sb.toString();
	}
}
