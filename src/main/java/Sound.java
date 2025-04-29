import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	public static boolean audioEnabled = true;

	private Clip audio;
	
	public Sound(String soundFile) {
		if(!audioEnabled)
			return;

		try {
			InputStream audioSrc = getClass().getResourceAsStream(soundFile);
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(new BufferedInputStream(audioSrc));
			this.audio = AudioSystem.getClip();
			this.audio.open(audioInput);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	public void playSoundOneShot() {
		if(audioEnabled) {
			audio.setFramePosition(0);
			this.audio.start();
		}
	}
	
	public void playSoundLoop() {
		if(audioEnabled) {
			this.audio.start();
			this.audio.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
}
