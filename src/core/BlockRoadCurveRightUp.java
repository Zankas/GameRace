package core;

public class BlockRoadCurveRightUp extends AbstractBlockRoadObject {

	public BlockRoadCurveRightUp() {
		super();
		this.createsRoad();
	}

	public void createsRoad() {

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < firstEdge; j++) {

				matrix[secondEdge + j][i] = GRASS;

				if (i < firstEdge) {
					matrix[j][i] = GRASS;
					matrix[i][secondEdge + j] = GRASS;
				}

				if (i <= secondEdge) {
					matrix[i][j] = GRASS;
				}
			}
		}
		for (int i = 0; i < firstEdge; i++) {
			for (int j = i; j < firstEdge; j++) {
				matrix[j + secondEdge - firstEdge][i + firstEdge] = GRASS;
				matrix[j + firstEdge / 2][i + secondEdge] = ROAD;
			}
		}

	}

	public void setCheckPoint() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (i == j) {
					matrix[i][SIZE - j - 1] = CHECKPOINT;
				}
				for (int k = 0; k < 50; k++) {
					if (j == (i + k)) {
						matrix[i][SIZE - j - 1] = CHECKPOINT;
					}
				}

			}
		}
	}

}
