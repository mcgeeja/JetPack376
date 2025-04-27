import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

	public static final Random rand = new Random();

	public static void main(String[] args) {
		GameRunningKeyListener.initializeDefaultControlsMap();

		JFrame frame = new JFrame();

		TitleScreen title = new TitleScreen(0);
		frame.add(title);

		JFrame frame2 = new JFrame();
		frame2.setSize(frameWidth, frameHeight);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		List<Alien> aliensType1 = new ArrayList<Alien>();
		List<Alien> aliensType2 = new ArrayList<Alien>();
		aliensType1.add(new BlueAlien(0, rand.nextInt(900),  "+"));
		aliensType1.add(new BlueAlien(0, rand.nextInt(900),  "+"));
		aliensType1.add(new BlueAlien(0, 150,  "+"));
		aliensType1.add(new RedAlien(500,500,"+"));

		aliensType2.add(new GreenAlien(frameWidth, rand.nextInt(900),  "+"));
		aliensType2.add(new GreenAlien(frameWidth, 500,  "+"));

		Player player = new Player(frameWidth / 2, 800, 15);

		MyComponent component = new MyComponent(player, aliensType1, aliensType2);
		frame2.add(component);

		KeyListener keyListen = new KeyAdapter() {

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

		};

		frame.addKeyListener(keyListen);
		
		Sound gameTheme = new Sound("/sounds/finalgametheme.wav");
		gameTheme.playSoundLoop();

		frame.setVisible(true);
		frame.setSize(frameWidth, frameHeight);
		frame.setTitle("An Empty Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}


}
