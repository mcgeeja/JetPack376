import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;
import javax.swing.event.MouseInputListener;


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
	private static JFrame titleFrame;
	private static JFrame gameFrame;
	private static JFrame gameOverFrame;


	private static MyComponent component;
	private static GameOverScreen gameOverScreen;


	private static Player player;
	private static int playerNum;
	private static List<Alien> aliensType1;
	private static List<Alien> aliensType2;
	private static Level level;
	private static int levelNum = 1;//default of 1 so when we select just player it gives you a place to start
	private static List<PowerUp> powerUps;
	private static AmmoCrate ammoCrate;

	private static Timer timer;
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

		gameOverFrame = new JFrame();
		gameOverFrame.setSize(frameWidth, frameHeight);
		gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		Sound gameTheme = new Sound("/sounds/finalgametheme.wav");
		gameTheme.playSoundLoop();
	}

	private static void setUpComponent() {
		component = new MyComponent(player, aliensType1, aliensType2, level, powerUps, ammoCrate);
		gameFrame.add(component);
		titleFrame.setVisible(false);
		gameOverFrame.setVisible(false);
	}
	private static void setUpAliens() {
		aliensType1 = new ArrayList<>();
		aliensType2 = new ArrayList<>();

		aliensType1.add(new BlueAlien(0, rand.nextInt(900),  "+"));
		aliensType1.add(new BlueAlien(0, rand.nextInt(900),  "+"));
		aliensType1.add(new BlueAlien(0, 150,  "+"));
		aliensType1.add(new RedAlien(500,500,"+"));
		aliensType2.add(new GreenAlien(frameWidth, rand.nextInt(900),  "+"));
		aliensType2.add(new GreenAlien(frameWidth, 500,  "+"));
	}

	private static void setUpPowerUps() {
		powerUps = new ArrayList<>();
		PowerUp speedBoost = new SpeedBoost(level.getPlatforms().get(rand.nextInt(level.getPlatforms().size())).x, 100);
		PowerUp shield = new Shield(level.getPlatforms().get(rand.nextInt(level.getPlatforms().size())).x, 300);
		powerUps.add(speedBoost);
		powerUps.add(shield);
	}

	private static void setUpAmmoCrate() {
		int num = rand.nextInt(20);
		ammoCrate = new AmmoCrate(level.getPlatforms().get(num).x, level.getPlatforms().get(num).y -30);
	}

	private static void setUpPlayer(){
		if(playerNum == 1) {
			player = new Astronaut(frameWidth / 2, 800, 15);
		} else if(playerNum == 2) {
			player = new ZombieAstronaut(frameWidth / 2, 800, 15);
		}
	}

	private static void run() {
		component.setFocusable(true);
		component.requestFocusInWindow();
		component.addKeyListener(new GameRunningKeyListener(player));
		MouseInputListener mouseListen = new GameRunningMouseListener(player);
		component.addMouseListener(mouseListen);
		component.addMouseMotionListener(mouseListen);

		GameAdvanceListener advancelistener = new GameAdvanceListener(component);
		timer = new Timer(60, advancelistener);
		timer.start();
		gameFrame.setVisible(true);
		
	}

	public static void setupGameOverScreen(int score, boolean gameWon){
		gameFrame.remove(component);
		timer.stop();

		if(gameOverScreen != null) {//remove old GameOverScreen
			gameOverFrame.remove(gameOverScreen);
		}
		gameOverScreen = new GameOverScreen(score, gameWon);
		gameOverScreen.setFocusable(true);
		gameOverScreen.requestFocusInWindow();
		gameOverScreen.addKeyListener(new GameOverKeyListener());
		gameOverFrame.add(gameOverScreen);

		titleFrame.setVisible(false);
		gameFrame.setVisible(false);

		gameOverFrame.setVisible(true);
		gameOverFrame.setSize(frameWidth, frameHeight);
		gameOverFrame.setTitle("Game Over Screen");
		gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private static void startGame(){
		setUpPlayer();
		level = new Level(levelNum);
		setUpAliens();
		setUpPowerUps();
		setUpAmmoCrate();
		setUpComponent();
		run();
	}

	public static void startGameWithPlayerTypeOne(){
		playerNum = 1;
		startGame();
	}

	public static void startGameWithPlayerTypeTwo(){
		playerNum = 2;
		startGame();
	}

	public static void startGameLevelOne(){
		levelNum = 1;
		startGame();
	}

	public static void startGameLevelTwo(){
		levelNum = 2;
		startGame();
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
