import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;


/**
 * The main class for your arcade game.
 * You can design your game any way you like, but make the game start by running
 * main here.
 * Also don't forget to write javadocs for your classes and functions!
 * 
 * @author Josiah McGee, Tim Harris
 *
 */
public class Main {


	public static final int frameWidth = 1920;
	public static final int frameHeight = 1080;

	public static final Random rand = new Random();
	static JFrame titleFrame;
	static JFrame gameFrame;
	static MyComponent component;
	static Player player;
	static List<Alien> aliensType1 = new ArrayList<>();
	static List<Alien> aliensType2 = new ArrayList<>();
	static Level level;
	static List<PowerUp> powerUps = new ArrayList<>();
	static AmmoCrate ammoCrate;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameRunningKeyListener.initializeDefaultControlsMap();
		titleFrame = new JFrame();

		TitleScreen titleScreen = new TitleScreen();
		titleScreen.setFocusable(true);
		titleScreen.requestFocusInWindow();
		titleScreen.addKeyListener(new TitleScreenKeyListener());
		titleFrame.add(titleScreen);
		titleFrame.setVisible(true);
		titleFrame.setSize(frameWidth, frameHeight);
		titleFrame.setTitle("Title Screen");
		titleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		gameFrame = new JFrame();
		gameFrame.setSize(frameWidth, frameHeight);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		Sound gameTheme = new Sound("/sounds/finalgametheme.wav");
		gameTheme.playSoundLoop();
	}

	private static void setUpComponent() {
		component = new MyComponent(player, aliensType1, aliensType2, level, powerUps, ammoCrate);
		gameFrame.add(component);
		titleFrame.setVisible(false);
	}
	private static void setUpAliens() {
		aliensType1.add(new BlueAlien(0, rand.nextInt(900),  "+"));
		aliensType1.add(new BlueAlien(0, rand.nextInt(900),  "+"));
		aliensType1.add(new BlueAlien(0, 150,  "+"));
		aliensType1.add(new RedAlien(500,500,"+"));
		aliensType2.add(new GreenAlien(frameWidth, rand.nextInt(900),  "+"));
		aliensType2.add(new GreenAlien(frameWidth, 500,  "+"));
	}

	private static void setUpPowerUps() {
		// Create a random powerup on a platform
		PowerUp speedBoost = new SpeedBoost(level.platforms.get(rand.nextInt(level.platforms.size())).x, 100);
		PowerUp shield = new Shield(level.platforms.get(rand.nextInt(level.platforms.size())).x, 300);
		powerUps.add(speedBoost);
		powerUps.add(shield);
	}

	private static void setUpAmmoCrate() {
		int num = rand.nextInt(20);
		ammoCrate = new AmmoCrate(level.platforms.get(num).x, level.platforms.get(num).y -30);
	}

	private static void run() {
		component.run();
		GameAdvanceListener advancelistener = new GameAdvanceListener(component);
		Timer timer = new Timer(60, advancelistener);
		timer.start();
		gameFrame.setVisible(true);
		
	}

	public static void startGameWithPlayerTypeOne(){

		player = new Astronaut(frameWidth / 2, 800, 15);
		level = new Level(1);
		setUpAliens();
		setUpPowerUps();
		setUpAmmoCrate();
		setUpComponent();
		run();
	}

	public static void startGameWithPlayerTypeTwo(){
		player = new ZombieAstronaut(frameWidth / 2, 800, 15);
		level = new Level(1);
		setUpAliens();
		setUpPowerUps();
		setUpAmmoCrate();
		setUpComponent();
		run();
	}

	public static void showControlRemappingMenu(){
		JFrame controlRemappingFrame = new JFrame("Control Settings");
		controlRemappingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		controlRemappingFrame.setSize(400, 1000);
		ControlRemappingComponent controlMappingComponent = new ControlRemappingComponent();

		controlRemappingFrame.add(controlMappingComponent.panel);
		controlRemappingFrame.setVisible(true);
	}


}
