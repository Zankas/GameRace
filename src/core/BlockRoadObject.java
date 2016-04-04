package core;

public interface BlockRoadObject {

	public abstract void createsRoad();

	public abstract void initializes();

	public abstract int[][] getMatrix();

	public static int ROAD = 0, GRASS = 1, START = 2, CHECKPOINT = 3;

}