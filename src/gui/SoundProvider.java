package gui;

import java.io.File;

import jaco.mp3.player.MP3Player;

public class SoundProvider {

	private static MP3Player pop;
	
	private static MP3Player click;
	
	private static MP3Player music;
	
	static {		
		pop=new MP3Player(new File("resource/fileSound/pop2.mp3"));
		
		click=new MP3Player(new File("resource/fileSound/pong.mp3"));
		
		music=new MP3Player(new File("resource/fileSound/Avenged Sevenfold - A Little Piece Of Heaven [Official Music Video].mp3"));

	}


	public static MP3Player getPop() {
		return pop;
	}
	
	public static MP3Player getClick() {
		return click;
	}

	public static MP3Player getMusic() {
		return music;
	}
	
	
	
}
