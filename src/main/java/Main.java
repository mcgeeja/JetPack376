import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.Timer;


/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start by running
 * main here.
 * 
 * Also don't forget to write javadocs for your classes and functions!
 * 
 * @author Josiah McGee, Tim Harris
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static final int frameWidth = 1920;
	public static final int frameHeight = 1080;

	public static void main(String[] args) {
		GameRunningKeyListener.initializeDefaultControlsMap();

		JFrame frame = new JFrame();

		TitleScreen title = new TitleScreen(0);
		frame.add(title);

		JFrame frame2 = new JFrame();
		frame2.setSize(frameWidth, frameHeight);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyComponent component = new MyComponent();
		frame2.add(component);

		KeyListener keylisten = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					frame.setVisible(false);
					component.run();
					GameAdvanceListener advancelistener = new GameAdvanceListener(component);
					Timer timer = new Timer(60, advancelistener);
					timer.start();
					frame2.setVisible(true);
				}
				
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					
					JFrame controlRemappingFrame = new JFrame("Control Settings");
			        controlRemappingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        controlRemappingFrame.setSize(400, 1000);
			        ControlRemappingComponent controlMappingComponent = new ControlRemappingComponent();
			        
			        controlRemappingFrame.add(controlMappingComponent.panel);
			        controlRemappingFrame.setVisible(true);					
				}

			}

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		};
		frame.addKeyListener(keylisten);
		
		Sound gameTheme = new Sound(new File("finalgametheme.wav"));
		gameTheme.playSoundLoop();

		frame.setVisible(true);
		frame.setSize(frameWidth, frameHeight);
		frame.setTitle("An Empty Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}


}
