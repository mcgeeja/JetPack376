import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	private Clip audio;
	
	public Sound(File soundFile) {
		try {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
			this.audio = AudioSystem.getClip();
			this.audio.open(audioInput);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void playSoundOneShot() {
		this.audio.start();
	}
	
	public void playSoundLoop() {
		this.audio.start();
		this.audio.loop(Clip.LOOP_CONTINUOUSLY);
	}
}
