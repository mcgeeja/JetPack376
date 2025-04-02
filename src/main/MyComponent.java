package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;


public class MyComponent extends JComponent {
	protected Player player = new Player(1920 / 2, 800, 15);
	private LevelReader levels;
	protected int num;
	protected String[] direction = new String[2];
	protected ArrayList<Platform> platforms;
	protected ArrayList<Alien> aliensType1 = new ArrayList<Alien>();
	protected ArrayList<Alien> aliensType2 = new ArrayList<Alien>();
	protected Graphics2D g;

	private int lives;
	protected int points;
	protected BuildingPiece rocketHolder;
	protected Rocket buildingRocket;
	protected int buildRocketNum = 0;
	protected int PieceCount = 3;
	protected int fuelCount = 0;
	protected AmmoCrate ammo;
	private boolean hasTakenOff=false;
	private boolean endGame = false;
	private boolean levelChange = false;
//	protected int xR;

	public MyComponent() {
		this.direction[0] = "-";
		this.direction[1] = "+";
		this.direction[0] = "+";
		Random random = new Random();
		for (int i = 0; i < 1; i++) {
			Alien alienType1 = new Alien(0, random.nextInt(900), 1, this.direction[random.nextInt(2)]);
			Alien alienType1_2 = new Alien(0, random.nextInt(900), 1, this.direction[random.nextInt(2)]);
			Alien alienType2 = new Alien(1920, random.nextInt(900), 2, this.direction[random.nextInt(2)]);
			Alien alien1 = new Alien(0, 150, 1, this.direction[random.nextInt(2)]);
			Alien alien2 = new Alien(1920, 500, 2, this.direction[random.nextInt(2)]);
			aliensType1.add(alienType1);
			aliensType1.add(alienType1_2);
			aliensType1.add(alien1);
			
			aliensType2.add(alienType2);
			aliensType2.add(alien2);
		}
		this.levels = new LevelReader(1);
		int xR = levels.rocketPieces.get(2).x;
		
		this.rocketHolder = new BuildingPiece( xR, 930);
		this.buildingRocket = new Rocket(xR - 10,levels.platforms.get(levels.platforms.size()-1).y -120);
		Random rand = new Random();
		num = rand.nextInt(20);
		this.ammo = new AmmoCrate(levels.platforms.get(num).x,levels.platforms.get(num).y -30);


	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	
		Graphics2D g2d = (Graphics2D) g;
		this.g = (Graphics2D) g;
		
		this.g.setColor(Color.BLACK);
    	this.g.fillRect(0, 0, 1920, 1080);
		this.rocketHolder.draw(g2d);
		this.ammo.drawOn(g2d);
    	this.buildingRocket.build(buildRocketNum, g2d);
        levels.drawFile(this.g);
        
     	for(int i = 0; i < aliensType1.size(); i++) {
       	aliensType1.get(i).drawOn(g2d);
      	
        }
     	for(int i = 0; i < aliensType2.size(); i++) {
     	aliensType2.get(i).drawOn(g2d);
     	}
        playerPickUp();
        onRocketHolder();
        updateFuelCount();
        if(fuelCount != 120) {
        player.drawOn(g2d);
        }
        for(Bullets bullet:player.getListOfBullets()) {
        	bullet.drawOn(g2d);
        }
	
		playerHit();
		Font font = new Font("arial", Font.BOLD, 50);
		this.g.setFont(font);
		this.g.setColor(Color.WHITE);
		this.g.drawString("Lives: " + Integer.toString(this.player.lives), 100, 1030);
		this.g.drawString("Points:" + Integer.toString(this.points), 400, 1030);
		this.g.drawString("Bullets:" + Integer.toString(this.player.bulletCount) + "/"
				+ Integer.toString(this.player.reserveAmmo), 700, 1030);

		for (Bullets bullet : player.getListOfLeftBullets()) {
			bullet.drawOn(g2d);
		}

		for (Alien a : aliensType1) {
			for (Bullets bullet : a.getListOfRightBullets()) {
				bullet.drawOn(g2d);
			}
		}


		takeOff();
		gameOver();
		
	}

	public void gameOver() {
		if (this.player.lives <= 0) {
			GameOverScreen gameOverScreen = new GameOverScreen(this);
			gameOverScreen.paintLoseGame();
			endGame = true;
		}
		if(buildingRocket.y <= 0) {
			GameOverScreen gameOverScreen = new GameOverScreen(this);
			gameOverScreen.paintWinGame();
			endGame = true;
		}
	}

	public void playerPickUp() {
		if(player.dropItem != false) {
			for (int i = 0; i < levels.fuels.size(); i++) {
				levels.fuels.get(i).pickedUp(this.player);
			}
			for (int i = 0; i < levels.rocketPieces.size(); i++) {
				levels.rocketPieces.get(i).pickedUp(this.player);
			}
		}
		if(this.ammo.getDimensions().intersects(this.player.getDimensions())) {
			ammo.pickedUpAmmo(player);
		}
	}

	public void playerHit() {
		for(int i = 0; i < aliensType2.size(); i++) {
			if (this.player.getDimensions().intersects(aliensType2.get(i).getDimensions())) {
				player.isHit();

			}
		}
		for (int i = 0; i < aliensType1.size(); i++) {
			if (this.player.getDimensions().intersects(aliensType1.get(i).getDimensions())) {
				player.isHit();

			}
			
			for (int j = 0; j < aliensType1.get(i).rightbulletlist.size(); j++) {
				if (aliensType1.get(i).rightbulletlist.get(j).getDimensions().intersects(this.player.getDimensions())) {
					player.isHit();
				}
			}
			
			
		}
		
	}

    public void updateFuelCount() {
    	if(PieceCount == 0) {
    		for(int i = 0 ; i < levels.fuels.size(); i++) {
    			if(this.levels.fuels.get(i).getDimensions().intersects(rocketHolder.getDimensions())){
    				this.levels.fuels.remove(i);
    				this.fuelCount += 40;
    				this.points += 300;
    				
    			}
    		}	
    	}
    }
    
    public void onRocketHolder() {
    	for(int i = 0 ; i < this.levels.rocketPieces.size(); i++) {
    		if(this.rocketHolder.getDimensions().intersects(levels.rocketPieces.get(i).getDimensions())){
    		levels.rocketPieces.get(i).x = this.rocketHolder.x-10;	
    		if(levels.rocketPieces.get(i).type == PieceCount) {
    			this.levels.rocketPieces.remove(i);
    			this.buildRocketNum += 1;
    			this.PieceCount -= 1;
    			this.points += 300;
    		}
    		}

    		}
    }
    public void takeOff(){
    	if(this.PieceCount == 0 && this.fuelCount == 120) {
    	this.buildingRocket.takeOff(this.g);
    	
    	
    	}
    	
    }
    
    public void updateBullets() {
    ArrayList<Bullets>	bulletsToRemove= new ArrayList<>();
    for(Bullets b: player.getListOfBullets()) {
    	boolean shouldRemove= b.move(this.getWidth());
    	if(shouldRemove) {
    		bulletsToRemove.add(b);
    	}
    }
    
    for(Bullets b: bulletsToRemove) {
    	player.getListOfBullets().remove(b);
    	
    	}
    
    }

	public void drawScreen() {
		this.repaint();
	}

	public void updateleftsideBullets() {
		ArrayList<Bullets> bulletsToRemoveS = new ArrayList<>();
		for (Bullets b : player.getListOfLeftBullets()) {
			boolean shouldRemove = b.moveleft(0);

			if (shouldRemove) {
				bulletsToRemoveS.add(b);
			}
		}

		for (Bullets b : bulletsToRemoveS) {
			player.getListOfLeftBullets().remove(b);

		}
	}


	public void updateAllAlienBullets() {
		ArrayList<Bullets> bulletsToRemove = new ArrayList<>();
		for (Bullets b : aliensType1.get(0).getListOfRightBullets()) {
			boolean shouldRemove = b.move(this.getWidth());
			if (shouldRemove) {
				bulletsToRemove.add(b);
			}
		}		
		for (Bullets b : bulletsToRemove) {
			aliensType1.get(0).getListOfRightBullets().remove(b);
		}

	}

	public void updateGrav(){
		for (Rocket r : levels.rocketPieces) {
			r.gravity(levels.platforms);
		}
		player.gravity(levels.platforms);
		for (Fuel f : levels.fuels) {
			f.gravity(levels.platforms);
		}
	}

	public void updateAlienReload() {
		aliensType1.get(0).shotTimer();
		aliensType2.get(0).shotTimer();

	}

	public void updateAliens() throws FileNotFoundException {
		Random rand = new Random();
		for (int i = 0; i < this.aliensType1.size(); i++) {

			aliensType1.get(i).move(levels.platforms);
			if (aliensType1.get(i).bulletHit(player.bulletlist)
					|| aliensType1.get(i).bulletHit(player.bulletlistleft)) {
				this.points += 100;

			}
		}
//	    	
		for (int i = 0; i < this.aliensType2.size(); i++) {
			if (aliensType2.get(i).direction == "-") {
				if (aliensType2.get(i).x < 0) {
					aliensType2.get(i).x = 1920;
				}
			} else {
				if (aliensType2.get(i).x > 1920) {
					if (aliensType2.get(i).y > 950) {
						aliensType2.get(i).y = rand.nextInt(800);
					}
					aliensType2.get(i).x = 0;
				}
			}
			if (aliensType2.get(i).y <= 0) {
				if (aliensType2.get(i).directNum == 1) {
					aliensType2.get(i).directNum = 2;
				} else {
					aliensType2.get(i).directNum = 1;

				}

			}
			aliensType2.get(i).move(levels.platforms);
			if (aliensType2.get(i).bulletHit(player.bulletlist)
					|| aliensType2.get(i).bulletHit(player.bulletlistleft)) {
				this.points += 100;

			}
		}
	}

	public void updatePickUpTimer() {
		player.startCountDown();
	}

	public void Run() {

		KeyListener keylisten = new GameRunningKeyListener(this);
       
        this.addKeyListener(keylisten);
        this.setFocusable(true);
	}
	
	public void changeToNextLevelKeyPressResponse() {
    	if (levels.on == 1) {
			levels = new LevelReader(2);
			points = 0;
			player.lives = 3;
			player.reserveAmmo = 75;
			player.bulletCount = 25;
			levels.on = 2;
			buildRocketNum = 0;
			PieceCount = 3;
			Random rand = new Random();
			num = rand.nextInt(20);
			ammo = new AmmoCrate(levels.platforms.get(num).x,levels.platforms.get(num).y -30);
			buildingRocket.y = levels.platforms.get(levels.platforms.size()-1).y -120;
		}
		repaint();
    }

	public void changeToPreviousLevelKeyPressResponse() {
    	if (levels.on == 2) {
			
			levels = new LevelReader(1);
			points = 0;
			player.lives = 3;
			player.reserveAmmo = 75;
			player.bulletCount = 25;
			levels.on = 1;
			buildRocketNum = 0;
			PieceCount = 3;
			Random rand = new Random();
			num = rand.nextInt(20);
			ammo = new AmmoCrate(levels.platforms.get(num).x,levels.platforms.get(num).y -30);
			buildingRocket.y = levels.platforms.get(levels.platforms.size()-1).y -120;
			repaint();
		}
    }
    
    public void selectLevelOneKeyPressResponse() {
		if(endGame == true) {
			levels = new LevelReader(1);
			points = 0;
			player.lives = 3;
			player.reserveAmmo = 75;
			player.bulletCount = 25;
			levels.on = 1;
			endGame = false;
			buildRocketNum = 0;
			PieceCount = 3;
			Random rand = new Random();
			num = rand.nextInt(20);
			ammo = new AmmoCrate(levels.platforms.get(num).x,levels.platforms.get(num).y -30);
			buildingRocket.y = levels.platforms.get(levels.platforms.size()-1).y -120;
		}
    }
    
    public void selectLevelTwoKeyPressResponse() {
    	if(endGame == true) {
			levels = new LevelReader(2);
			points = 0;
			player.lives = 3;
			player.reserveAmmo = 75;
			player.bulletCount = 25;
			levels.on = 2;
			endGame = false;
			buildRocketNum = 0;
			PieceCount = 3;
			Random rand = new Random();
			num = rand.nextInt(20);
			ammo = new AmmoCrate(levels.platforms.get(num).x,levels.platforms.get(num).y -30);
			buildingRocket.y = levels.platforms.get(levels.platforms.size()-1).y -120;
		}
    }

    
    public void updateState() throws FileNotFoundException {
		updateAlienReload();
		updateleftsideBullets();
		updateBullets();
		updateGrav();
		updateAliens();
		player.move();
		updateAllAlienBullets();
		updatePickUpTimer();

	}

}