package app;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	public Sound() { }
	
	public static synchronized void playMainSound(String fpath) throws Exception{
		File file = new File(fpath);
		Clip clip = AudioSystem.getClip();
		        // getAudioInputStream() also accepts a File or InputStream
		AudioInputStream ais = AudioSystem.getAudioInputStream( file );
		clip.open(ais);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void playSound(String fpath) throws Exception {
		Clip clip = AudioSystem.getClip();
		AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fpath));
		clip.open(ais);
		clip.loop(0);
	}
}
