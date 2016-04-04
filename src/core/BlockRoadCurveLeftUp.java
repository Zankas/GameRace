package core;

public class BlockRoadCurveLeftUp extends AbstractBlockRoadObject {

	public BlockRoadCurveLeftUp() {
		super();
		this.createsRoad();
	}

	public void createsRoad() {

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < firstEdge; j++) {

				matrix[secondEdge + j][i] = GRASS;
				matrix[i][secondEdge + j] = GRASS;
				if (i <= firstEdge) {
					matrix[j][i] = GRASS;
				}
			}
		}
		for (int i = 0; i < firstEdge; i++) {
			for (int j = firstEdge; j >= firstEdge - i; j--) {
				matrix[i][j + firstEdge / 2] = ROAD;
				matrix[i + secondEdge - firstEdge][j + secondEdge - firstEdge] = GRASS;
			}
		}

	}

	public void setCheckPoint() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (i == j) {
					matrix[i][j] = CHECKPOINT;
				}
				for (int k = 0; k < 50; k++) {
					if (j == (i + k)) {
						matrix[i][j] = CHECKPOINT;
					}
				}

			}
		}
	}
}
