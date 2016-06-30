package core;

import java.util.ArrayList;

public interface CarManager {
	void collisionGrass();

	void collisionCheckPoint();

	boolean checking_off_the_track();

	void collisionStart();

	void updateCar(final ArrayList<CarManager> carManagerList, final int i);

	Car getCar();

	Direction getDirection();

	void makeCheckPoint();

	void speedHandler();

	Checkpoints getCheckpoints();

	boolean collisionDetection(final Car car);

	void setDirection();

	void setPosition(final int i);

	int getPosition();
}
