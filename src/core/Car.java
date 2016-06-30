package core;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car {

	final private int ID;

	private static final int WIDTH = 60;
	private static final int LENGHT = 30;

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

	public Lock lock = new ReentrantLock();

	public Car(int x, int y) {
		lock.lock();

		setX(x);
		setY(y);
		setXYrotate();
		setAngle(0);
		ID = 1;

		lock.unlock();
	}

	public Car(Car car) {
		lock.lock();

		setX((int) car.getX1());
		setY((int) car.getY1());
		setXYrotate();
		setAngle(0);
		this.ID = car.ID + 1;

		lock.unlock();
	}
<<<<<<< HEAD
=======
	//
	// public Car(Car car, int value) {
	// if (value == 1) {
	// setX((int) car.getX1() + (int) (car.getX1() - car.getX3() / 2));
	// setY((int) car.getY1() + (int) (car.getY2() - car.getY1() / 2));
	// setXYrotate();
	// setAngle(0);
	// this.ID = car.ID + 1;
	// }
	//
	// }
>>>>>>> origin/master

	public double getSpeed() {
		try {
			lock.lock();
			return speed;
		} finally {
			lock.unlock();
		}

	}

	public void setSpeed(double speed) {
		lock.lock();
		this.speed = speed;
		lock.unlock();
	}

	public double getAngle() {
		try {
			lock.lock();
			return angle;
		} finally {
			lock.unlock();
		}

	}

	public void setAngle(double angle) {
		lock.lock();

		this.angle = angle;

		lock.unlock();
	}

	void moveXYrot(World world) {
		lock.lock();
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
		} else {
			// System.out.println("CAR " + ID + " BLOCCATTOOOOOO");
		}

		lock.unlock();
	}

	public int getID() {
		try {
			lock.lock();
			return ID;
		} finally {
			lock.unlock();
		}

	}

	public double getX1() {
		try {
			lock.lock();
			return x1;
		} finally {
			lock.unlock();
		}
	}

	public double getY1() {
		try {
			lock.lock();
			return y1;
		} finally {
			lock.unlock();
		}
	}

	public double getX2() {
		try {
			lock.lock();
			return x2;
		} finally {
			lock.unlock();
		}
	}

	public double getY2() {
		try {
			lock.lock();
			return y2;
		} finally {
			lock.unlock();
		}
	}

	public double getX3() {
		try {
			lock.lock();
			return x3;
		} finally {
			lock.unlock();
		}
	}

	public double getY3() {
		try {
			lock.lock();
			return y3;
		} finally {
			lock.unlock();
		}
	}

	public double getX4() {
		try {
			lock.lock();
			return x4;
		} finally {
			lock.unlock();
		}
	}

	public double getY4() {
		try {
			lock.lock();
			return y4;
		} finally {
			lock.unlock();
		}
	}

	public double getX1rot() {
		try {
			lock.lock();
			return x1rot;
		} finally {
			lock.unlock();
		}
	}

	public double getY1rot() {
		try {
			lock.lock();
			return y1rot;
		} finally {
			lock.unlock();
		}
	}

	public double getX2rot() {
		try {
			lock.lock();
			return x2rot;
		} finally {
			lock.unlock();
		}
	}

	public double getY2rot() {
		try {
			lock.lock();
			return y2rot;
		} finally {
			lock.unlock();
		}
	}

	public double getX3rot() {
		try {
			lock.lock();
			return x3rot;
		} finally {
			lock.unlock();
		}
	}

	public double getY3rot() {
		try {
			lock.lock();
			return y3rot;
		} finally {
			lock.unlock();
		}
	}

	public double getX4rot() {
		try {
			lock.lock();
			return x4rot;
		} finally {
			lock.unlock();
		}
	}

	public double getY4rot() {
		try {
			lock.lock();
			return y4rot;
		} finally {
			lock.unlock();
		}
	}

	public static int getWidth() {
		return WIDTH;
	}

	public static int getLenght() {
		return LENGHT;
	}

	public void setX(int x) {
		lock.lock();
		x1 = x;
		x2 = x1;
		x3 = x + WIDTH;
		x4 = x3;
		lock.unlock();
	}

	public void setY(int y) {
		lock.lock();
		y1 = y;
		y2 = y + LENGHT;
		y3 = y1;
		y4 = y2;
		lock.unlock();
	}

	private void setXYrotate() {
		lock.lock();
		x1rot = x1;
		x2rot = x2;
		x3rot = x3;
		x4rot = x4;

		y1rot = y1;
		y2rot = y2;
		y3rot = y3;
		y4rot = y4;
		lock.unlock();
	}

	void move(World world) {
		lock.lock();
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
		lock.unlock();
	}

	public void setUpDownLeftRightFalse() {
		lock.lock();
		UP = false;
		DOWN = false;
		LEFT = false;
		RIGHT = false;
		lock.unlock();
	}

	public boolean isUP() {
		try {
			lock.lock();
			return UP;
		} finally {
			lock.unlock();
		}
	}

	public void setUP(boolean up) {
		lock.lock();
		UP = up;
		lock.unlock();
	}

	public boolean isDOWN() {
		try {
			lock.lock();
			return DOWN;
		} finally {
			lock.unlock();
		}
	}

	public boolean isRotate() {
		try {
			lock.lock();
			return RIGHT || LEFT;
		} finally {
			lock.unlock();
		}
	}

	public void setDOWN(boolean down) {
		lock.lock();
		DOWN = down;
		lock.unlock();
	}

	public boolean isLEFT() {
		try {
			lock.lock();
			return LEFT;
		} finally {
			lock.unlock();
		}
	}

	public void setLEFT(boolean left) {
		lock.lock();
		LEFT = left;
		lock.unlock();
	}

	public boolean isRIGHT() {
		try {
			lock.lock();
			return RIGHT;
		} finally {
			lock.unlock();
		}
	}

	public void setRIGHT(boolean right) {
		lock.lock();
		RIGHT = right;
		lock.unlock();
	}

	public void setX1(double x1) {
		lock.lock();
		this.x1 = x1;
		lock.unlock();
	}

	public void setY1(double y1) {
		lock.lock();
		this.y1 = y1;
		lock.lock();
	}

	public void setX2(double x2) {
		lock.lock();
		this.x2 = x2;
		lock.unlock();
	}

	public void setY2(double y2) {
		lock.lock();
		this.y2 = y2;
		lock.unlock();
	}

	public void setX3(double x3) {
		lock.lock();
		this.x3 = x3;
		lock.unlock();
	}

	public void setY3(double y3) {
		lock.lock();
		this.y3 = y3;
		lock.unlock();
	}

	public void setX4(double x4) {
		lock.lock();
		this.x4 = x4;
		lock.unlock();
	}

	public void setY4(double y4) {
		lock.lock();
		this.y4 = y4;
		lock.unlock();
	}

	public void setX1rot(double x1rot) {
		lock.lock();
		this.x1rot = x1rot;
		lock.unlock();
	}

	public void setY1rot(double y1rot) {
		lock.lock();
		this.y1rot = y1rot;
		lock.unlock();
	}

	public void setX2rot(double x2rot) {
		lock.lock();
		this.x2rot = x2rot;
		lock.unlock();
	}

	public void setY2rot(double y2rot) {
		lock.lock();
		this.y2rot = y2rot;
		lock.unlock();

	}

	public void setX3rot(double x3rot) {
		lock.lock();
		this.x3rot = x3rot;
		lock.unlock();

	}

	public void setY3rot(double y3rot) {
		lock.lock();
		this.y3rot = y3rot;
		lock.unlock();
	}

	public void setX4rot(double x4rot) {
		lock.lock();
		this.x4rot = x4rot;
		lock.unlock();

	}

	public void setY4rot(double y4rot) {
		lock.lock();
		this.y4rot = y4rot;
		lock.unlock();
	}
}
