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
			carManagerList.add(new CarManagerAi(world, world.getCar2(), this));
			break;
		case 3:
			carManagerList.add(new CarManagerAi(world, world.getCar2(), this));
			carManagerList.add(new CarManagerAi(world, world.getCar3(), this));

			break;
		case 4:
			carManagerList.add(new CarManagerAi(world, world.getCar2(), this));
			carManagerList.add(new CarManagerAi(world, world.getCar3(), this));
			carManagerList.add(new CarManagerAi(world, world.getCar4(), this));
			break;
		}

//		carManagerList.add(new CarManagerAi(world, world.getCar2(), this));

		update();
		threadSpeedCar();

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
						for (CarManager cm : carManagerList) {
							cm.updateCar();
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

	public boolean endGame() {

		if (carManagerHuman.getCheckpoints().getActualLaps() != carManagerHuman.getCheckpoints().getTotalLaps()) {
			return false;
		}
		return true;

	}
}
