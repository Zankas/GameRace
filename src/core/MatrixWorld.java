package core;

public class MatrixWorld {

	private BlockRoadObject matrixWorld[][];

	// private Map<Integer, Integer[][]> targetPoint;

	public MatrixWorld(final int x, final int y) {

		matrixWorld = new BlockRoadObject[x][y];

	}

	public void insertSubMatrix(final BlockRoadObject blockRoadObject, final int x, final int y) {

		matrixWorld[x][y] = blockRoadObject;
	}

	public int getValuePosition(final int x, final int y) {

		return matrixWorld[x / AbstractBlockRoadObject.SIZE][y / AbstractBlockRoadObject.SIZE].getMatrix()[x
				% AbstractBlockRoadObject.SIZE][y % AbstractBlockRoadObject.SIZE];
	}

	public BlockRoadObject whereAmI(final int y, final int x) {
		return matrixWorld[x / AbstractBlockRoadObject.SIZE][y / AbstractBlockRoadObject.SIZE];
	}

	public BlockRoadObject whereAmI(final Car car) {
		return matrixWorld[(int) (car.getY1() / AbstractBlockRoadObject.SIZE)][(int) (car.getX1()
				/ AbstractBlockRoadObject.SIZE)];
	}

	public int getXMatrixWorld(final int x) {
		return x / AbstractBlockRoadObject.SIZE;
	}

	public int getYMatrixWorld(final int y) {
		return y / AbstractBlockRoadObject.SIZE;
	}

	public int pointBlock(final CarManager carM, TargetPoint targetPoint) {
		int x = getXMatrixWorld((int) (carM.getCar().getX4rot() + carM.getCar().getX3rot()) / 2);
		int y = getXMatrixWorld((int) (carM.getCar().getY3rot() + carM.getCar().getY4rot()) / 2);

		// System.out.println(carM.getCar().getID() + " x matrice: " + x + " y
		// matrice: " + y);
		BlockRoadObject blockRoadObject = matrixWorld[y][x];

		if (blockRoadObject instanceof BlockRoadCurveLeftDown) {
			if (((CarManagerReactiveAi) carM).getAiDirection() == Direction.RIGHT) {
				for (int i = 0; i < AbstractBlockRoadObject.SIZEROW; i++) {
					targetPoint.add(blockRoadObject.getPointBlock(i, 1) + AbstractBlockRoadObject.SIZE * x,
							blockRoadObject.getPointBlock(i, 0) + AbstractBlockRoadObject.SIZE * y, i);
				}
				return 1;
			} else if (((CarManagerReactiveAi) carM).getAiDirection() == Direction.UP) {
				for (int i = AbstractBlockRoadObject.SIZEROW - 1; i >= 0; i--) {
					targetPoint.add(blockRoadObject.getPointBlock(i, 1) + AbstractBlockRoadObject.SIZE * x,
							blockRoadObject.getPointBlock(i, 0) + AbstractBlockRoadObject.SIZE * y, i);
				}
				return 2;

			}
		} else if (blockRoadObject instanceof BlockRoadCurveLeftUp) {
			if (((CarManagerReactiveAi) carM).getAiDirection() == Direction.RIGHT) {
				for (int i = 0; i < AbstractBlockRoadObject.SIZEROW; i++) {
					targetPoint.add(blockRoadObject.getPointBlock(i, 1) + AbstractBlockRoadObject.SIZE * x,
							blockRoadObject.getPointBlock(i, 0) + AbstractBlockRoadObject.SIZE * y, i);
				}

				return 3;
			} else if (((CarManagerReactiveAi) carM).getAiDirection() == Direction.DOWN) {
				for (int i = AbstractBlockRoadObject.SIZEROW - 1; i >= 0; i--) {
					targetPoint.add(blockRoadObject.getPointBlock(i, 1) + AbstractBlockRoadObject.SIZE * x,
							blockRoadObject.getPointBlock(i, 0) + AbstractBlockRoadObject.SIZE * y, i);
				}

				return 4;
			}
		} else if (blockRoadObject instanceof BlockRoadCurveRightDown) {
			if (((CarManagerReactiveAi) carM).getAiDirection() == Direction.LEFT) {
				for (int i = 0; i < AbstractBlockRoadObject.SIZEROW; i++) {
					targetPoint.add(blockRoadObject.getPointBlock(i, 1) + AbstractBlockRoadObject.SIZE * x,
							blockRoadObject.getPointBlock(i, 0) + AbstractBlockRoadObject.SIZE * y, i);
				}
				return 5;

			} else if (((CarManagerReactiveAi) carM).getAiDirection() == Direction.UP) {
				for (int i = AbstractBlockRoadObject.SIZEROW - 1; i >= 0; i--) {
					targetPoint.add(blockRoadObject.getPointBlock(i, 1) + AbstractBlockRoadObject.SIZE * x,
							blockRoadObject.getPointBlock(i, 0) + AbstractBlockRoadObject.SIZE * y, i);
				}
				System.out.println("DIR " + ((CarManagerReactiveAi) carM).getAiDirection() + " Blocco RD");
				return 6;

			}
		} else if (blockRoadObject instanceof BlockRoadCurveRightUp) {
			if (((CarManagerReactiveAi) carM).getAiDirection() == Direction.LEFT) {
				for (int i = 0; i < AbstractBlockRoadObject.SIZEROW; i++) {
					targetPoint.add(blockRoadObject.getPointBlock(i, 1) + AbstractBlockRoadObject.SIZE * x,
							blockRoadObject.getPointBlock(i, 0) + AbstractBlockRoadObject.SIZE * y, i);
				}
				return 7;
			} else if (((CarManagerReactiveAi) carM).getAiDirection() == Direction.DOWN) {
				for (int i = AbstractBlockRoadObject.SIZEROW - 1; i >= 0; i--) {
					targetPoint.add(blockRoadObject.getPointBlock(i, 1) + AbstractBlockRoadObject.SIZE * x,
							blockRoadObject.getPointBlock(i, 0) + AbstractBlockRoadObject.SIZE * y, i);
				}
				return 8;
			}
		} else if (blockRoadObject instanceof BlockRoadHorizontal) {
			if (((CarManagerReactiveAi) carM).getAiDirection() == Direction.RIGHT) {
				for (int i = 0; i < AbstractBlockRoadObject.SIZEROW; i++) {
					targetPoint.add(blockRoadObject.getPointBlock(i, 1) + AbstractBlockRoadObject.SIZE * x,
							blockRoadObject.getPointBlock(i, 0) + AbstractBlockRoadObject.SIZE * y, i);
				}
				return 9;
			} else if (((CarManagerReactiveAi) carM).getAiDirection() == Direction.LEFT) {
				for (int i = AbstractBlockRoadObject.SIZEROW - 1; i >= 0; i--) {
					targetPoint.add(blockRoadObject.getPointBlock(i, 1) + AbstractBlockRoadObject.SIZE * x,
							blockRoadObject.getPointBlock(i, 0) + AbstractBlockRoadObject.SIZE * y, i);
					// System.out.println("X " +
					// (blockRoadObject.getPointBlock(i, 1) +
					// AbstractBlockRoadObject.SIZE * (x))
					// + " Y " + (blockRoadObject.getPointBlock(i, 0) +
					// AbstractBlockRoadObject.SIZE * (y)));
				}
				return 10;

			}
		} else if (blockRoadObject instanceof BlockRoadVertical) {
			if (((CarManagerReactiveAi) carM).getAiDirection() == Direction.DOWN) {
				for (int i = 0; i < AbstractBlockRoadObject.SIZEROW; i++) {
					targetPoint.add(blockRoadObject.getPointBlock(i, 1) + AbstractBlockRoadObject.SIZE * x,
							blockRoadObject.getPointBlock(i, 0) + AbstractBlockRoadObject.SIZE * y, i);
				}
				return 11;

			} else if (((CarManagerReactiveAi) carM).getAiDirection() == Direction.UP) {
				for (int i = AbstractBlockRoadObject.SIZEROW - 1; i >= 0; i--) {
					targetPoint.add(blockRoadObject.getPointBlock(i, 1) + AbstractBlockRoadObject.SIZE * x,
							blockRoadObject.getPointBlock(i, 0) + AbstractBlockRoadObject.SIZE * y, i);
				}
				return 12;
			}
		}
		return -1;
	}
}
