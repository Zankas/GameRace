package core;

public class BlockRoadVertical extends AbstractBlockRoadObject {
	
	public BlockRoadVertical() {
		super();
		this.createsRoad();
	}

	public void setStart() {
		super.initializes();
		createsRoad();
		int sizestart=10;
		for (int i = 0; i < SIZE; i++) {
			
				for (int j = 0; j < sizestart; j++) {
					matrix[((SIZE/2)-(sizestart/2))+j][i] = START;
				}
		}
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
		int sizecheck=40;
		for (int i = 0; i < SIZE; i++) {
			
				for (int j = 0; j < sizecheck; j++) {
					matrix[((SIZE/2)-(sizecheck/2))+j][i] = CHECKPOINT;
				}
		}
	}
}