package core;

public class BlockRoadHorizontal extends AbstractBlockRoadObject {

	public BlockRoadHorizontal() {
		super();
		this.createsRoad();
	}

	public void setStart() {
		super.initializes();
		createsRoad();

		int sizestart = 10;
		for (int i = 0; i < sizestart; i++) {

			for (int j = 0; j < SIZE; j++) {
				matrix[j][((SIZE / 2) - (sizestart / 2)) + i] = START;
			}
		}

		// START THE TARGET POINT FROM LEFT TO RIGHT.
		int mediumPoint = (firstEdge + secondEdge) / 2;
		targetPoint[0][0] = mediumPoint;
		targetPoint[0][1] = SIZE - SIZE;
		targetPoint[1][0] = mediumPoint;
		targetPoint[1][1] = SIZE / 2;
		targetPoint[2][0] = mediumPoint;
		targetPoint[2][1] = SIZE - 1;
		// matrix[mediumPoint][SIZE - SIZE] = FOLLOWCAR;
		// matrix[mediumPoint][SIZE / 2] = FOLLOWCAR;
		// matrix[mediumPoint][SIZE - 1] = FOLLOWCAR;
	}

	public void createsRoad() {

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < firstEdge; j++) {
				matrix[j][i] = GRASS;
				matrix[secondEdge + j][i] = GRASS;
			}
		}
	}

	public void setCheckPoint() {
		super.initializes();
		createsRoad();

		int sizecheck = 40;
		for (int i = 0; i < sizecheck; i++) {
			for (int j = 0; j < SIZE; j++) {
				matrix[j][((SIZE / 2) - (sizecheck / 2)) + i] = CHECKPOINT;
			}
		}

		int mediumPoint = (firstEdge + secondEdge) / 2;
		targetPoint[0][0] = mediumPoint;
		targetPoint[0][1] = SIZE - SIZE;
		targetPoint[1][0] = mediumPoint;
		targetPoint[1][1] = SIZE / 2;
		targetPoint[2][0] = mediumPoint;
		targetPoint[2][1] = SIZE - 1;
//		matrix[mediumPoint][SIZE - SIZE] = FOLLOWCAR;
//		matrix[mediumPoint][SIZE / 2] = FOLLOWCAR;
//		matrix[mediumPoint][SIZE - 1] = FOLLOWCAR;
	}

}
