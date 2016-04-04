package core;

public class CarManagerHuman extends AbstractCarManager {

	public CarManagerHuman(World w, Car car) {
		super(w,car);
	}

	@Override
	void totalMove() {
		car.move(world);
	}	
}
