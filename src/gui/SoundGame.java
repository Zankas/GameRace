package gui;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundGame {
	
	private File soundBottonClick;
	private File soundBottonPop;
	public SoundGame() {
		soundBottonClick = new File("resource/fileSound/pong.wav");
		soundBottonPop=new File("resource/fileSound/pop2.wav");
	}
	public void soundPlay(File file){
		try {
	        
	         AudioInputStream audioIn = AudioSystem.getAudioInputStream(file); 
	         
	         Clip clip = AudioSystem.getClip();
	         clip.open(audioIn);
	         clip.start();
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
		
	}
	public File getSoundBottonClick() {
		return soundBottonClick;
	}
	public File getSoundBottonPop() {
		return soundBottonPop;
	}
	
	
}
