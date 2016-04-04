package core;

public class BlockProvider {

	private static Grass grass;
	private static BlockRoadHorizontal roadHorizontal;
	private static BlockRoadVertical roadVertical;
	private static BlockRoadCurveRightDown roadCurveRightDown;
	private static BlockRoadCurveLeftDown roadCurveLeftDown;
	private static BlockRoadCurveRightUp roadCurveRightUp;
	private static BlockRoadCurveLeftUp roadCurveLeftUp;
	private static BlockRoadHorizontal roadHorizontalStart;
	private static BlockRoadVertical roadVerticalStart;

	public static BlockRoadObject getGrass() {
		if (grass == null)
			grass = new Grass();

		return grass;
	}

	public static BlockRoadObject getRoadHorizontal() {
		if (roadHorizontal == null) {

			roadHorizontal = new BlockRoadHorizontal();
			roadHorizontal.setCheckPoint();
		}
		return roadHorizontal;
	}

	public static BlockRoadObject getRoadCurveRightDown() {
		if (roadCurveRightDown == null) {

			roadCurveRightDown = new BlockRoadCurveRightDown();
			roadCurveRightDown.setCheckPoint();
		}
		return roadCurveRightDown;
	}

	public static BlockRoadObject getRoadVertical() {
		if (roadVertical == null) {

			roadVertical = new BlockRoadVertical();
			roadVertical.setCheckPoint();
		}
		return roadVertical;
	}

	public static BlockRoadObject getRoadCurveLeftDown() {
		if (roadCurveLeftDown == null) {

			roadCurveLeftDown = new BlockRoadCurveLeftDown();
			roadCurveLeftDown.setCheckPoint();
		}
		return roadCurveLeftDown;
	}

	public static BlockRoadObject getRoadCurveRightUp() {
		if (roadCurveRightUp == null) {

			roadCurveRightUp = new BlockRoadCurveRightUp();
			roadCurveRightUp.setCheckPoint();
		}
		return roadCurveRightUp;
	}

	public static BlockRoadObject getRoadCurveLeftUp() {
		if (roadCurveLeftUp == null) {

			roadCurveLeftUp = new BlockRoadCurveLeftUp();
			roadCurveLeftUp.setCheckPoint();
		}
		return roadCurveLeftUp;
	}

	public static BlockRoadObject getRoadHorizontalStart() {
		if (roadHorizontalStart == null) {

			roadHorizontalStart = new BlockRoadHorizontal();
			roadHorizontalStart.setStart();
		}
		return roadHorizontalStart;
	}

	public static BlockRoadObject getRoadVerticalStart() {
		if (roadVerticalStart == null) {

			roadVerticalStart = new BlockRoadVertical();
			roadVerticalStart.setStart();
		}
		return roadVerticalStart;
	}

}
