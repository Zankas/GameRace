package core;

public class MatrixWorld {

	private BlockRoadObject matrixWorld[][];
	
	
	public MatrixWorld(int x,int y) {
		
		matrixWorld=new BlockRoadObject [x] [y];
		
	}

	public void insertSubMatrix (BlockRoadObject blockRoadObject,int x,int y){
		
		matrixWorld[x][y]=blockRoadObject;
	}

	public int getValuePosition(int x,int y){
		
		return matrixWorld[x/AbstractBlockRoadObject.SIZE][y/AbstractBlockRoadObject.SIZE].
				getMatrix()[x%AbstractBlockRoadObject.SIZE][y%AbstractBlockRoadObject.SIZE];
	}
	public BlockRoadObject whereAmI(int y,int x) {
		return matrixWorld[x/AbstractBlockRoadObject.SIZE][y/AbstractBlockRoadObject.SIZE];
	}

	public BlockRoadObject whereAmI(Car artiCar) {
		return matrixWorld[(int) (artiCar.getY1()/AbstractBlockRoadObject.SIZE)][(int) (artiCar.getX1()/AbstractBlockRoadObject.SIZE)];
	}
	public int getXMatrixWorld(int x){
		return x/AbstractBlockRoadObject.SIZE;
	}
	public int getYMatrixWorld(int y){
		return y/AbstractBlockRoadObject.SIZE;
	}
}
