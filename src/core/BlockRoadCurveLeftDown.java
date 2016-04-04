package core;

public class BlockRoadCurveLeftDown extends AbstractBlockRoadObject {

	public BlockRoadCurveLeftDown() {
		super();
		this.createsRoad();
	}

	public void createsRoad() {

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < firstEdge; j++) {
				matrix[j][i] = 1;
				matrix[i][secondEdge + j] = GRASS;

				if (i <= firstEdge) {
					matrix[secondEdge + j][i] = GRASS;
				}

			}
		}
		for (int i = 0; i < firstEdge; i++) {
			for (int j = i; j <= firstEdge; j++) {
				matrix[i + secondEdge][j + firstEdge / 2] = ROAD;
				matrix[i + firstEdge][j + secondEdge - firstEdge] = GRASS;
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
