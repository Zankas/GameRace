package core;

public class TargetPoint {
	// int[][] targetPoint;
	int targetPointX[];
	int targetPointY[];
	final int dim;
	// final int dimX;
	// final int dimY;
	private int freePosition;

	public TargetPoint() {
		// targetPoint = new int[1][1];
		// dimX = 1;
		// dimY = 1;
		this.targetPointX = new int[AbstractBlockRoadObject.SIZEROW];
		this.targetPointY = new int[AbstractBlockRoadObject.SIZEROW];
		dim = AbstractBlockRoadObject.SIZEROW;
		freePosition = 0;
	}

	public TargetPoint(final int x) {
		// this.dimX = x;
		// this.dimY = y;
		this.dim = x;
		// this.targetPoint = new int[x][y];
		this.targetPointX = new int[x];
		this.targetPointY = new int[x];
		this.freePosition = 0;
	}

	public void add(final int x, final int y, final int index) {
		// if (freePosition < dim) {
		// targetPoint[freePosition][0] = x;
		// targetPoint[freePosition][1] = y;
		targetPointX[index] = x;
		targetPointY[index] = y;
		// freePosition++;
		// }

	}

	@Override
	public boolean equals(Object obj) {
		return ((((TargetPoint) obj).targetPointX == this.targetPointX)
				&& (((TargetPoint) obj).targetPointY == this.targetPointY));
	}

	public int getPointX(final int i) {
		return targetPointX[i];
	}

	public int getPointY(final int i) {
		return targetPointY[i];
	}

}
