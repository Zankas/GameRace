package core;

public class BlockRoadVertical extends AbstractBlockRoadObject {

	public BlockRoadVertical() {
		super();
		this.createsRoad();
	}

	public void setStart() {
		super.initializes();
		createsRoad();
		int sizestart = 10;
		for (int i = 0; i < SIZE; i++) {

			for (int j = 0; j < sizestart; j++) {
				matrix[((SIZE / 2) - (sizestart / 2)) + j][i] = START;
			}
		}

		// START THE TARGET POINT FROM UP TO DOWN.
		int mediumPoint = (firstEdge + secondEdge) / 2;
		targetPoint[0][0] = SIZE - SIZE;
		targetPoint[0][1] = mediumPoint;
		targetPoint[1][0] = SIZE / 2;
		targetPoint[1][1] = mediumPoint;
		targetPoint[2][0] = SIZE - 1;
		targetPoint[2][1] = mediumPoint;
//		matrix[SIZE - SIZE][mediumPoint] = FOLLOWCAR;
//		matrix[SIZE / 2][mediumPoint] = FOLLOWCAR;
//		matrix[SIZE - 1][mediumPoint] = FOLLOWCAR;
	}

	public void createsRoad() {

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < firstEdge; j++) {
				matrix[i][j] = GRASS;
				matrix[i][secondEdge + j] = GRASS;
			}
		}
	}

	public void setCheckPoint() {
		super.initializes();
		createsRoad();
		int sizecheck = 40;
		for (int i = 0; i < SIZE; i++) {

			for (int j = 0; j < sizecheck; j++) {
				matrix[((SIZE / 2) - (sizecheck / 2)) + j][i] = CHECKPOINT;
			}
		}
		int mediumPoint = (firstEdge + secondEdge) / 2;
		targetPoint[0][0] = SIZE - SIZE;
		targetPoint[0][1] = mediumPoint;
		targetPoint[1][0] = SIZE / 2;
		targetPoint[1][1] = mediumPoint;
		targetPoint[2][0] = SIZE - 1;
		targetPoint[2][1] = mediumPoint;
		// matrix[SIZE - SIZE][mediumPoint] = FOLLOWCAR;
		// matrix[SIZE / 2][mediumPoint] = FOLLOWCAR;
		// matrix[SIZE-1][mediumPoint] = FOLLOWCAR;
	}
}