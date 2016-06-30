package core;

public class CarManagerHuman extends AbstractCarManager {

	public CarManagerHuman(World w, Car car) {
		super(w, car);
	}

	@Override
	void totalMove() {
		car.move(world);

//		debug();
	}

	void debug() {

		// System.out.println("X1 :" + car.getX1());
		// System.out.println("X2 :" + car.getX2());
		// System.out.println("X3 :" + car.getX3());
		// System.out.println("X4 :" + car.getX4());
		// System.out.println("Y1 :" + car.getY1());
		// System.out.println("Y2 :" + car.getY2());
		// System.out.println("Y3 :" + car.getY3());
		// System.out.println("Y4 :" + car.getY4());

		System.out.println("X1rot :" + car.getX1rot());
		System.out.println("X2rot :" + car.getX2rot());
		System.out.println("X3rot :" + car.getX3rot());
		System.out.println("X4rot :" + car.getX4rot());

		System.out.println("Y1rot :" + car.getY1rot());
		System.out.println("Y2rot :" + car.getY2rot());
		System.out.println("Y3rot :" + car.getY3rot());
		System.out.println("Y4rot :" + car.getY4rot());
	}
}
