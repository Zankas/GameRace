package core;

public class World {

	public final static int X_MATRIX_STRING = 5;
	public final static int Y_MATRIX_STRING = 7;

	private Car car = null;;
	private Car car2 = null; // CREATA A PARTIRE DALLA CAR.
	private Car car3 = null; // CREATA A PARTIRE DALLA CAR2.
	private Car car4 = null; // CREATA A PARTIRE DALLA CAR3.
	public static int carToBeCreated = 4;

	private String matrixString[][];

	private MatrixWorld worldMatrix;

	public World() {

		this.matrixString = new String[X_MATRIX_STRING][Y_MATRIX_STRING];
		this.initializes(matrixString);

		switch (carToBeCreated) {
		case 2:
			this.car = new Car(200, 140); // default position car
			this.car2 = new Car(car);
			break;
		case 3:
			this.car = new Car(200, 140); // default position car
			this.car2 = new Car(car);
			this.car3 = new Car(car2);
			break;
		case 4:
			this.car = new Car(200, 140); // default position car
			this.car2 = new Car(car);
			this.car3 = new Car(car2);
			this.car4 = new Car(car3);
			break;

		}
		// this.car = new Car(200, 140); // default position car
		// this.car2 = new Car(car);

		this.worldMatrix = new MatrixWorld(X_MATRIX_STRING, Y_MATRIX_STRING);

	}

	public Car getCar() {
		return car;
	}

	public Car getCar2() {
		return car2;
	}

	public Car getCar3() {
		return car3;
	}

	public Car getCar4() {
		return car4;
	}

	public String[][] getMatrixString() {
		return matrixString;
	}

	public MatrixWorld getMatrixWorld() {
		return worldMatrix;
	}

	private void initializes(String matrix[][]) {

		for (int i = 0; i < X_MATRIX_STRING; i++) {
			for (int j = 0; j < Y_MATRIX_STRING; j++) {
				matrix[i][j] = "grass";
			}
		}
	}

	public void makeWorld() {

		for (int i = 0; i < X_MATRIX_STRING; i++) {
			for (int j = 0; j < Y_MATRIX_STRING; j++) {

				if ("horizontal".equals(matrixString[i][j])) {

					setBlock(Block.ROAD_HORIZZONTAL, i, j);

				}
				if ("vertical".equals(matrixString[i][j])) {

					setBlock(Block.ROAD_VERTICAL, i, j);

				}
				if ("curveleftup".equals(matrixString[i][j])) {

					setBlock(Block.ROAD_LEFT_UP, i, j);

				}
				if ("curveleftdown".equals(matrixString[i][j])) {

					setBlock(Block.ROAD_LEFT_DOWN, i, j);

				}
				if ("curverightup".equals(matrixString[i][j])) {

					setBlock(Block.ROAD_RIGHT_UP, i, j);

				}
				if ("curverightdown".equals(matrixString[i][j])) {

					setBlock(Block.ROAD_RIGHT_DOWN, i, j);

				}
				if ("grass".equals(matrixString[i][j])) {

					setBlock(Block.BLOCK_GRASS, i, j);

				}
				if ("starthorizontalleft".equals(matrixString[i][j])) {

					setBlock(Block.ROAD_HORIZZONTAL_START, i, j);

					final int x = j * AbstractBlockRoadObject.getSize() + AbstractBlockRoadObject.getSize() / 2
							- (int) (car.getX1() - car.getX3()) / 2;

					final int y = i * AbstractBlockRoadObject.getSize() + AbstractBlockRoadObject.getSize() / 2
							- (int) (car.getY1() / 6) - (int) car.getY1() / 6;

					switch (carToBeCreated) {
					case 2:
						car.setX(x);
						car.setY(y);

						car2.setX(x + Car.getWidth() - (int) (Car.getWidth() * 0.5));
						car2.setY(y + Car.getLenght() + (int) (Car.getLenght() * 0.5));

						car.setAngle(Math.PI);
						car2.setAngle(Math.PI);
						break;
					case 3:
						car.setX(x);
						car.setY(y);

						car2.setX(x + Car.getWidth() - (int) (Car.getWidth() * 0.5));
						car2.setY(y + Car.getLenght() + (int) (Car.getLenght() * 0.5));

						car3.setX(x + Car.getWidth() + (int) (Car.getWidth() * 0.5));
						car3.setY(y);

						car.setAngle(Math.PI);
						car2.setAngle(Math.PI);
						car3.setAngle(Math.PI);
						break;
					case 4:
						car.setX(x);
						car.setY(y);

						car2.setX(x + Car.getWidth() - (int) (Car.getWidth() * 0.5));
						car2.setY(y + Car.getLenght() + (int) (Car.getLenght() * 0.5));

						car3.setX(x + Car.getWidth() + (int) (Car.getWidth() * 0.5));
						car3.setY(y);

						car4.setX(x + Car.getWidth() * 2);
						car4.setY(y + Car.getLenght() + (int) (Car.getLenght() * 0.5));

						car.setAngle(Math.PI);
						car2.setAngle(Math.PI);
						car3.setAngle(Math.PI);
						car4.setAngle(Math.PI);
						break;
					}

				}
				if ("starthorizontalright".equals(matrixString[i][j])) {

					setBlock(Block.ROAD_HORIZZONTAL_START, i, j);

					final int x = j * AbstractBlockRoadObject.getSize() + AbstractBlockRoadObject.getSize() / 2
							- (int) (car.getX3() - car.getX1()) - 12;

					final int y = (i * AbstractBlockRoadObject.getSize()) + (AbstractBlockRoadObject.getSize() / 2)
							- (int) ((car.getY2() - car.getY1()) * 1.25);
					// final int y = (i * AbstractBlockRoadObject.getSize()) +
					// (AbstractBlockRoadObject.getSize() / 2)
					// - (int) ((car.getY2() - car.getY1()) / 2);

					switch (carToBeCreated) {
					case 2:
						car.setX(x);
						car.setY(y);

						car2.setX(x - Car.getWidth() + (int) (Car.getWidth() * 0.5));
						car2.setY(y + Car.getLenght() + (int) (Car.getLenght() * 0.5));

						car.setAngle(0);
						car2.setAngle(0);
						car3.setAngle(0);
						break;
					case 3:
						car.setX(x);
						car.setY(y);

						car2.setX(x - Car.getWidth() + (int) (Car.getWidth() * 0.5));
						car2.setY(y + Car.getLenght() + (int) (Car.getLenght() * 0.5));

						car3.setX(x - Car.getWidth() - (int) (Car.getWidth() * 0.5));
						car3.setY(y);

						car.setAngle(0);
						car2.setAngle(0);
						car3.setAngle(0);
						break;
					case 4:
						car.setX(x);
						car.setY(y);

						car2.setX(x - Car.getWidth() + (int) (Car.getWidth() * 0.5));
						car2.setY(y + Car.getLenght() + (int) (Car.getLenght() * 0.5));

						car3.setX(x - Car.getWidth() - (int) (Car.getWidth() * 0.5));
						car3.setY(y);

						car4.setX(x - Car.getWidth() * 2);
						car4.setY(y + Car.getLenght() + (int) (Car.getLenght() * 0.5));

						car.setAngle(0);
						car2.setAngle(0);
						car3.setAngle(0);
						car4.setAngle(0);
						break;
					}
				}
				if ("startverticalup".equals(matrixString[i][j])) {

					setBlock(Block.ROAD_VERTICAL_START, i, j);

					car.setX(j * AbstractBlockRoadObject.getSize() + AbstractBlockRoadObject.getSize() / 2
							- (int) (car.getX2() - car.getX1()));
					car.setY(i * AbstractBlockRoadObject.getSize() + AbstractBlockRoadObject.getSize() / 2
							- (int) (car.getY1() - car.getY2()) + 6);
					car.setAngle(Math.PI + Math.PI / 2);

				}
				if ("startverticaldown".equals(matrixString[i][j])) {

					setBlock(Block.ROAD_VERTICAL_START, i, j);

					car.setX(j * AbstractBlockRoadObject.getSize() + AbstractBlockRoadObject.getSize() / 2
							- (int) ((car.getX3() - car.getX1()) / 4));
					car.setY(i * AbstractBlockRoadObject.getSize() + AbstractBlockRoadObject.getSize() / 2
							- (int) (car.getY2() - car.getY1()) - ((int) (car.getY2() - car.getY1()) + 6));
					car.setAngle(Math.PI / 2);
				}
			}
		}
	}

	private void setBlock(Block block, int x, int y) {

		switch (block) {
		case BLOCK_GRASS:
			worldMatrix.insertSubMatrix(BlockProvider.getGrass(), x, y);
			break;

		case ROAD_HORIZZONTAL:
			worldMatrix.insertSubMatrix(BlockProvider.getRoadHorizontal(), x, y);
			break;

		case ROAD_LEFT_DOWN:
			worldMatrix.insertSubMatrix(BlockProvider.getRoadCurveLeftDown(), x, y);
			break;

		case ROAD_LEFT_UP:
			worldMatrix.insertSubMatrix(BlockProvider.getRoadCurveLeftUp(), x, y);
			break;

		case ROAD_RIGHT_DOWN:
			worldMatrix.insertSubMatrix(BlockProvider.getRoadCurveRightDown(), x, y);
			break;

		case ROAD_RIGHT_UP:
			worldMatrix.insertSubMatrix(BlockProvider.getRoadCurveRightUp(), x, y);
			break;

		case ROAD_VERTICAL:
			worldMatrix.insertSubMatrix(BlockProvider.getRoadVertical(), x, y);
			break;

		case ROAD_HORIZZONTAL_START:
			worldMatrix.insertSubMatrix(BlockProvider.getRoadHorizontalStart(), x, y);
			break;

		case ROAD_VERTICAL_START:
			worldMatrix.insertSubMatrix(BlockProvider.getRoadVerticalStart(), x, y);
			break;

		default:
			break;
		}

	}

	public void setMatrixString(String[][] matrixString) {
		this.matrixString = matrixString;
	}

}
