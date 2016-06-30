package core;

public abstract class AbstractBlockRoadObject implements BlockRoadObject {
	protected final static int SIZEROW = 3;
	protected final static int SIZECOL = 2;
	protected final static int SIZE = 250;
	protected final int percentRoad = 62;
	protected int percentEdge;
	protected int road_width;
	protected int firstEdge;
	protected int secondEdge;
	protected int[][] targetPoint;

	protected int matrix[][];

	protected AbstractBlockRoadObject() {
		percentEdge = (100 - percentRoad) / 2;
		try {
			road_width = percentage(percentRoad, SIZE);
			firstEdge = percentage(percentEdge, SIZE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		secondEdge = road_width + firstEdge;
		matrix = new int[SIZE][SIZE];
		initializes();
		this.targetPoint = new int[SIZEROW][SIZECOL];
	}

	@Override
	public abstract void createsRoad();

	@Override
	public void initializes() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				matrix[i][j] = 0;
			}
		}
	}

	protected int percentage(int percent, int number) throws Exception {
		if ((number * percent) == 0) {
			throw new Exception("Error: divide by 0");
		}
		return number * percent / 100;
	}

	public static int getSize() {
		return SIZE;
	}

	public void stampa() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(matrix[i][j]);
				System.out.print(" ");
			}
			System.out.println("\n");
		}
	}

	@Override
	public int[][] getMatrix() {
		return matrix;
	}

	@Override
	public int[][] getPointBlock() {
		return targetPoint;
	}
	
	public int getPointBlock(int i, int j) {
		return targetPoint[i][j];
	}
}
