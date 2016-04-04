package core;

public class BlockRoadCurveRightDown extends AbstractBlockRoadObject {

	public BlockRoadCurveRightDown() {
		super();
		this.createsRoad();
	}

	public void createsRoad() {

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < firstEdge; j++) {

				matrix[j][i] = 1;
				if (i < firstEdge) {
					matrix[secondEdge + j][i + secondEdge] = GRASS;
					matrix[i + secondEdge][secondEdge + j] = GRASS;
				}
				matrix[i][j] = GRASS;
			}
		}
		for (int i = 0; i < firstEdge; i++) {
			for (int j = 0; j < firstEdge - i; j++) {
				matrix[i + secondEdge - (firstEdge / 4)][j + secondEdge
						- (firstEdge / 4)] = ROAD;
				matrix[i + firstEdge][j + firstEdge] = GRASS;
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
