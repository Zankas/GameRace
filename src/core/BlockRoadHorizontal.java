package core;

public class BlockRoadHorizontal extends AbstractBlockRoadObject {

	public BlockRoadHorizontal() {
		super();
		this.createsRoad();
	}

	public void setStart() {
		super.initializes();
		createsRoad();
		
		int sizestart=10;
		for (int i = 0; i <sizestart ; i++) {
			
				for (int j = 0; j < SIZE; j++) {
					matrix[j][((SIZE/2)-(sizestart/2))+i] = START;
				}
		}
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
		
		int sizecheck=40;
		for (int i = 0; i <sizecheck ; i++) {
			
				for (int j = 0; j < SIZE; j++) {
					matrix[j][((SIZE/2)-(sizecheck/2))+i] = CHECKPOINT;
				}
		}
	}
}
