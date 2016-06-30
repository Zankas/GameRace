package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import core.CarManager;
import core.GameManager;
import core.World;

public class LevelLoadSave {
	
	//FIXME CLASSE DA METTERE IN CORE
	
	private static final String PATH_RECORD ="resource/record/";
	private static final String EXTENSION_RECORD =".rec";
	
	public static long saveRecord(long time, String nameTrack) {
		
		long recordTrack=0;
		try {

			File folder = new File(PATH_RECORD);

			String[] array = folder.list();

			boolean itIsPresent = false;
			boolean isBetter = false;

			for (int a = 0; a < array.length; a++) {

				String tmp = nameTrack + EXTENSION_RECORD;

				if (tmp.equals(array[a])) {
					itIsPresent = true;
					File file = new File(PATH_RECORD + nameTrack + EXTENSION_RECORD);
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);

					if (time < Long.parseLong(br.readLine())) {
						isBetter = true;
					}
					br.close();
				}
			}

			if (isBetter || !itIsPresent) {
				File file = new File(PATH_RECORD + nameTrack + EXTENSION_RECORD);
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);

				bw.write(Long.toString(time) + "\n");
				recordTrack = time;
				bw.flush();
				bw.close();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return recordTrack;
	}
	
	public static long loadRecord(String path,String nameTrack) {
		long timeTrack = 0;
		try {

			File folder = new File(PATH_RECORD);

			String[] array = folder.list();

			for (int a = 0; a < array.length; a++) {

				String tmp = nameTrack + EXTENSION_RECORD;

				if (tmp.equals(array[a])) {

					File file = new File(path);
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					timeTrack = Long.parseLong(br.readLine());
					br.close();
					return timeTrack;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return timeTrack;
	}
	
	public static long load(String pathTrack, String nameTrack, GameManager game) {
		
		long recordTrack=0;
		String path = pathTrack;
		recordTrack = loadRecord(PATH_RECORD + nameTrack + EXTENSION_RECORD,nameTrack);
		try {
			File file = new File(path);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			for (int i = 0; i < World.X_MATRIX_STRING; i++) {
				for (int j = 0; j < World.Y_MATRIX_STRING; j++) {
					game.getWorld().getMatrixString()[i][j] = br.readLine();
				}
			}
			String laps = br.readLine();
			if (laps != null) {
				for (CarManager c : game.getCarManagerList()) {

					c.getCheckpoints().setTotalLaps(Integer.parseInt(laps));
				}
			}
			String enemies=br.readLine();
			if(enemies != null){
				// settare i nemici nel GameManager
				//TODO
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return recordTrack;
	}
}
