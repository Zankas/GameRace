package core;

public interface CarManager {
	void collisionGrass();

	void collisionCheckPoint();

	boolean checking_off_the_track();
	
	void collisionStart();

	void updateCar();

	Car getCar();
	
	public void makeCheckPoint();
	
	void speedHandler();
	
	public Checkpoints getCheckpoints();
}
