import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	private Clip audio;
	
	public Sound(String soundFile) {
		try {
			InputStream audioSrc = getClass().getResourceAsStream(soundFile);
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(new BufferedInputStream(audioSrc));
			this.audio = AudioSystem.getClip();
			this.audio.open(audioInput);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void playSoundOneShot() {
		audio.setFramePosition(0);
		this.audio.start();
	}
	
	public void playSoundLoop() {
		this.audio.start();
		this.audio.loop(Clip.LOOP_CONTINUOUSLY);
	}
}
