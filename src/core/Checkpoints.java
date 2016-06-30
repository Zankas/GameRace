package core;

import java.util.HashMap;

public class Checkpoints {

	private int totalLaps;
	private int actualLaps;

	private HashMap<String, Boolean> hashMap;

	public Checkpoints() {

		hashMap = new HashMap<String, Boolean>();
		totalLaps = 0;
		actualLaps = 0;
	}

	public int getTotalLaps() {
		return totalLaps;
	}

	public void setTotalLaps(int totalLaps) {
		this.totalLaps = totalLaps;
	}

	public int getActualLaps() {
		return actualLaps;
	}

	public void setActualLaps(int actualLaps) {
		this.actualLaps = actualLaps;
	}

	public void increaseLaps() {

		if (actualLaps < totalLaps) {
			actualLaps++;
		}
	}

	public void insertCheckPoint(int i, int j) {
		String key = Integer.toString(i) + Integer.toString(j);

		hashMap.put(key, false);

	}

	public void setCheckpoint(int i, int j, Boolean value) {
		String key = Integer.toString(i) + Integer.toString(j);

		hashMap.replace(key, value);
	}

	public boolean verifyCheckpoint(int i, int j) {
		String key = Integer.toString(i) + Integer.toString(j);

		return hashMap.get(key);
	}

	public boolean allCheckpointsPassed() {

		Object[] valueHashMap = hashMap.values().toArray();

		for (Object object : valueHashMap) {

			if (!(boolean) object) {
				return false;
			}
		}

		return true;
	}

	public void stampa() { /* debug */

		Object[] keyHashMap = hashMap.keySet().toArray();
		Object[] valueHashMap = hashMap.values().toArray();

		for (int i = 0; i < keyHashMap.length; i++) {

			System.out.println("CheckPoint " + keyHashMap[i] + " " + (Boolean) valueHashMap[i]);
		}

	}

	public void setFalseAllCheckPoint() {

		Object[] keyHashMap = hashMap.keySet().toArray();
		Object[] valueHashMap = hashMap.values().toArray();

		for (int i = 0; i < keyHashMap.length; i++) {

			hashMap.replace((String) keyHashMap[i], (boolean) valueHashMap[i], false);
		}
	}

	public int numberCheckpointsHit() {
		int counter = 0;
		Object[] valueHashMap = hashMap.values().toArray();

		for (int i = 0; i < valueHashMap.length; i++) {
			if ((Boolean) valueHashMap[i])
				counter++;
		}
		return counter;
	}
}
