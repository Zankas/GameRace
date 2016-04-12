package core;

import java.util.ArrayList;

public interface CarManager {
	void collisionGrass();

	void collisionCheckPoint();

	boolean checking_off_the_track();

	void collisionStart();

	void updateCar(ArrayList<CarManager> carManagerList);

	Car getCar();

	void makeCheckPoint();

	void speedHandler();

	Checkpoints getCheckpoints();
	
	void intersect(ArrayList<CarManager> carManagerList);

}
