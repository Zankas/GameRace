package core;

public class Car {

	private int ID;
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	private double x3;
	private double y3;
	private double x4;
	private double y4;

	private double x1rot;
	private double y1rot;
	private double x2rot;
	private double y2rot;
	private double x3rot;
	private double y3rot;
	private double x4rot;
	private double y4rot;

	private double angle;
	private double speed = 0;

	private boolean UP = false;
	private boolean DOWN = false;
	private boolean LEFT = false;
	private boolean RIGHT = false;

	public Car(int x, int y) {
		setX(x);
		setY(y);
		setXYrotate();
		setAngle(0);
		ID = 1;
	}

	public Car(Car car) {
		setX((int) car.getX1());
		setY((int) car.getY1());
		setXYrotate();
		setAngle(0);
		this.ID = car.ID + 1;
	}

	public Car(Car car, int value) {
		if (value == 1) {
			setX((int) car.getX1() + (int) (car.getX1() - car.getX3() / 2));
			setY((int) car.getY1() + (int) (car.getY2() - car.getY1() / 2));
			setXYrotate();
			setAngle(0);
			this.ID = car.ID + 1;
		}

	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	void moveXYrot(World world) {
		// rotazione dei 4 punti logici della macchina
		// (x3-x1)/3 punto di centro di rotazione sull'asse x
		// (y2-y1)/2 punto di centro di rotazione sull'asse y

		double tmpx1rot = (((x3 - x1) / 3) + x1) + (x1 - (((x3 - x1) / 3) + x1)) * Math.cos(angle)
				- (y1 - (((y2 - y1) / 2) + y1)) * Math.sin(angle);
		double tmpx2rot = (((x3 - x1) / 3) + x1) + (x2 - (((x3 - x1) / 3) + x1)) * Math.cos(angle)
				- (y2 - (((y2 - y1) / 2) + y1)) * Math.sin(angle);
		double tmpx3rot = (((x3 - x1) / 3) + x1) + (x3 - (((x3 - x1) / 3) + x1)) * Math.cos(angle)
				- (y3 - (((y2 - y1) / 2) + y1)) * Math.sin(angle);
		double tmpx4rot = (((x3 - x1) / 3) + x1) + (x4 - (((x3 - x1) / 3) + x1)) * Math.cos(angle)
				- (y4 - (((y2 - y1) / 2) + y1)) * Math.sin(angle);
		double tmpy1rot = (((y2 - y1) / 2) + y1) + (x1 - (((x3 - x1) / 3) + x1)) * Math.sin(angle)
				+ (y1 - (((y2 - y1) / 2) + y1)) * Math.cos(angle);
		double tmpy2rot = (((y2 - y1) / 2) + y1) + (x2 - (((x3 - x1) / 3) + x1)) * Math.sin(angle)
				+ (y2 - (((y2 - y1) / 2) + y1)) * Math.cos(angle);
		double tmpy3rot = (((y2 - y1) / 2) + y1) + (x3 - (((x3 - x1) / 3) + x1)) * Math.sin(angle)
				+ (y3 - (((y2 - y1) / 2) + y1)) * Math.cos(angle);
		double tmpy4rot = (((y2 - y1) / 2) + y1) + (x4 - (((x3 - x1) / 3) + x1)) * Math.sin(angle)
				+ (y4 - (((y2 - y1) / 2) + y1)) * Math.cos(angle);

		if (tmpx1rot >= 0 && tmpx1rot <= AbstractBlockRoadObject.getSize() * World.Y_MATRIX_STRING && tmpx2rot >= 0
				&& tmpx2rot <= AbstractBlockRoadObject.getSize() * World.Y_MATRIX_STRING && tmpx3rot >= 0
				&& tmpx3rot <= AbstractBlockRoadObject.getSize() * World.Y_MATRIX_STRING && tmpx4rot >= 0
				&& tmpx4rot <= AbstractBlockRoadObject.getSize() * World.Y_MATRIX_STRING && tmpy1rot >= 0
				&& tmpy1rot <= AbstractBlockRoadObject.getSize() * World.X_MATRIX_STRING && tmpy2rot >= 0
				&& tmpy2rot <= AbstractBlockRoadObject.getSize() * World.X_MATRIX_STRING && tmpy3rot >= 0
				&& tmpy3rot <= AbstractBlockRoadObject.getSize() * World.X_MATRIX_STRING && tmpy4rot >= 0
				&& tmpy4rot <= AbstractBlockRoadObject.getSize() * World.X_MATRIX_STRING) {

			x1rot = (((x3 - x1) / 3) + x1) + (x1 - (((x3 - x1) / 3) + x1)) * Math.cos(angle)
					- (y1 - (((y2 - y1) / 2) + y1)) * Math.sin(angle);
			x2rot = (((x3 - x1) / 3) + x1) + (x2 - (((x3 - x1) / 3) + x1)) * Math.cos(angle)
					- (y2 - (((y2 - y1) / 2) + y1)) * Math.sin(angle);
			x3rot = (((x3 - x1) / 3) + x1) + (x3 - (((x3 - x1) / 3) + x1)) * Math.cos(angle)
					- (y3 - (((y2 - y1) / 2) + y1)) * Math.sin(angle);
			x4rot = (((x3 - x1) / 3) + x1) + (x4 - (((x3 - x1) / 3) + x1)) * Math.cos(angle)
					- (y4 - (((y2 - y1) / 2) + y1)) * Math.sin(angle);

			y1rot = (((y2 - y1) / 2) + y1) + (x1 - (((x3 - x1) / 3) + x1)) * Math.sin(angle)
					+ (y1 - (((y2 - y1) / 2) + y1)) * Math.cos(angle);
			y2rot = (((y2 - y1) / 2) + y1) + (x2 - (((x3 - x1) / 3) + x1)) * Math.sin(angle)
					+ (y2 - (((y2 - y1) / 2) + y1)) * Math.cos(angle);
			y3rot = (((y2 - y1) / 2) + y1) + (x3 - (((x3 - x1) / 3) + x1)) * Math.sin(angle)
					+ (y3 - (((y2 - y1) / 2) + y1)) * Math.cos(angle);
			y4rot = (((y2 - y1) / 2) + y1) + (x4 - (((x3 - x1) / 3) + x1)) * Math.sin(angle)
					+ (y4 - (((y2 - y1) / 2) + y1)) * Math.cos(angle);
		}

	}

	public int getID() {
		return ID;
	}

	public double getX1() {
		return x1;
	}

	public double getY1() {
		return y1;
	}

	public double getX2() {
		return x2;
	}

	public double getY2() {
		return y2;
	}

	public double getX3() {
		return x3;
	}

	public double getY3() {
		return y3;
	}

	public double getX4() {
		return x4;
	}

	public double getY4() {
		return y4;
	}

	public double getX1rot() {
		return x1rot;
	}

	public double getY1rot() {
		return y1rot;
	}

	public double getX2rot() {
		return x2rot;
	}

	public double getY2rot() {
		return y2rot;
	}

	public double getX3rot() {
		return x3rot;
	}

	public double getY3rot() {
		return y3rot;
	}

	public double getX4rot() {
		return x4rot;
	}

	public double getY4rot() {
		return y4rot;
	}

	public void setX(int x) {
		x1 = x;
		x2 = x1;
		x3 = x + 60;
		x4 = x3;
	}

	public void setY(int y) {
		y1 = y;
		y2 = 30 + y;
		y3 = y1;
		y4 = y2;
	}

	private void setXYrotate() {
		x1rot = x1;
		x2rot = x2;
		x3rot = x3;
		x4rot = x4;

		y1rot = y1;
		y2rot = y2;
		y3rot = y3;
		y4rot = y4;
	}

	void move(World world) {

		double cos = Math.cos(angle);
		double sin = Math.sin(angle);

		if (x1 + cos * speed >= 0 && x1 + cos * speed < AbstractBlockRoadObject.getSize() * World.Y_MATRIX_STRING
				&& x2 + cos * speed >= 0 && x2 + cos * speed < AbstractBlockRoadObject.getSize() * World.Y_MATRIX_STRING
				&& x3 + cos * speed >= 0 && x3 + cos * speed < AbstractBlockRoadObject.getSize() * World.Y_MATRIX_STRING
				&& x4 + cos * speed >= 0 && x4 + cos * speed < AbstractBlockRoadObject.getSize() * World.Y_MATRIX_STRING
				&&

		y1 + sin * speed >= 0 && y1 + sin * speed < AbstractBlockRoadObject.getSize() * World.X_MATRIX_STRING
				&& y2 + sin * speed >= 0 && y2 + sin * speed < AbstractBlockRoadObject.getSize() * World.X_MATRIX_STRING
				&& y3 + sin * speed >= 0 && y3 + sin * speed < AbstractBlockRoadObject.getSize() * World.X_MATRIX_STRING
				&& y4 + sin * speed >= 0
				&& y4 + sin * speed < AbstractBlockRoadObject.getSize() * World.X_MATRIX_STRING) {

			x1 = x1 + cos * speed;
			x2 = x2 + cos * speed;
			x3 = x3 + cos * speed;
			x4 = x4 + cos * speed;

			y1 = y1 + sin * speed;
			y2 = y2 + sin * speed;
			y3 = y3 + sin * speed;
			y4 = y4 + sin * speed;
		} else {

			speed = 0;

		}
	}

	public void setUpDownLeftRightFalse() {
		UP = false;
		DOWN = false;
		LEFT = false;
		RIGHT = false;
	}

	public boolean isUP() {
		return UP;
	}

	public void setUP(boolean up) {
		UP = up;
	}

	public boolean isDOWN() {
		return DOWN;
	}

	public boolean isRotate() {
		return RIGHT || LEFT;
	}

	public void setDOWN(boolean down) {
		DOWN = down;
	}

	public boolean isLEFT() {
		return LEFT;
	}

	public void setLEFT(boolean left) {
		LEFT = left;
	}

	public boolean isRIGHT() {
		return RIGHT;
	}

	public void setRIGHT(boolean right) {
		RIGHT = right;
	}

	public void setX1(double x1) {
		this.x1 = x1;
	}

	public void setY1(double y1) {
		this.y1 = y1;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	public void setY2(double y2) {
		this.y2 = y2;
	}

	public void setX3(double x3) {
		this.x3 = x3;
	}

	public void setY3(double y3) {
		this.y3 = y3;
	}

	public void setX4(double x4) {
		this.x4 = x4;
	}

	public void setY4(double y4) {
		this.y4 = y4;
	}

	public void setX1rot(double x1rot) {
		this.x1rot = x1rot;
	}

	public void setY1rot(double y1rot) {
		this.y1rot = y1rot;
	}

	public void setX2rot(double x2rot) {
		this.x2rot = x2rot;
	}

	public void setY2rot(double y2rot) {
		this.y2rot = y2rot;
	}

	public void setX3rot(double x3rot) {
		this.x3rot = x3rot;
	}

	public void setY3rot(double y3rot) {
		this.y3rot = y3rot;
	}

	public void setX4rot(double x4rot) {
		this.x4rot = x4rot;
	}

	public void setY4rot(double y4rot) {
		this.y4rot = y4rot;
	}
}
