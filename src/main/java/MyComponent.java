import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;


public class MyComponent extends JComponent {
	protected Player player = new Player(1920 / 2, 800, 15);
	private Level levels;
	protected int num;
	protected String[] direction = new String[2];
	protected ArrayList<Platform> platforms;
	protected ArrayList<Alien> aliensType1 = new ArrayList<Alien>();
	protected ArrayList<Alien> aliensType2 = new ArrayList<Alien>();
	protected Graphics2D g;
	private ArrayList<Rocket> builtRocketPieces = new ArrayList<>();


	private int lives;
	protected int points;
	protected BuildingPiece rocketHolder;
	protected Rocket buildingRocket;
	protected int buildRocketNum = 0;
	protected int pieceCount = 3;
	protected int fuelCount = 0;
	protected AmmoCrate ammo;
	private boolean hasTakenOff=false;
	protected boolean endGame = false;
	private boolean levelChange = false;
	Random rand = new Random();

	public MyComponent() {
		this.direction[0] = "-";
		this.direction[1] = "+";
		this.direction[0] = "+";
		for (int i = 0; i < 1; i++) {
			Alien alienType1 = new BlueAlien(0, rand.nextInt(900),  this.direction[rand.nextInt(2)]);
			Alien redAlien = new RedAlien(500,500,"+");
			Alien alienType1_2 = new BlueAlien(0, rand.nextInt(900),  this.direction[rand.nextInt(2)]);
			Alien alienType2 = new GreenAlien(1920, rand.nextInt(900),  this.direction[rand.nextInt(2)]);
			Alien alien1 = new BlueAlien(0, 150,  this.direction[rand.nextInt(2)]);
			Alien alien2 = new GreenAlien(1920, 500,  this.direction[rand.nextInt(2)]);
			aliensType1.add(alienType1);
			aliensType1.add(alienType1_2);
			aliensType1.add(alien1);
			aliensType1.add(redAlien);
			
			aliensType2.add(alienType2);
			aliensType2.add(alien2);
		}
		this.levels = new Level(1);
		int xR = levels.getBottomRocketPiece().x;
		
		this.rocketHolder = new BuildingPiece( xR, 930);
		this.buildingRocket = levels.getBottomRocketPiece();
		this.buildingRocket.x = xR - 10;
		this.buildingRocket.y = levels.platforms.get(levels.platforms.size()-1).y - 120;
		num = rand.nextInt(20);
		this.ammo = new AmmoCrate(levels.platforms.get(num).x,levels.platforms.get(num).y -30);


	}

	@Override
	public void paintComponent(Graphics g) {
		this.g = (Graphics2D) g;
		
		this.g.setColor(Color.BLACK);
    	this.g.fillRect(0, 0, 1920, 1080);
		this.rocketHolder.drawOn(this.g);
		this.ammo.drawOn(this.g);
    	// this.buildingRocket.build(buildRocketNum, this.g);
		for (Rocket part : builtRocketPieces) {
			part.drawPiece(this.g, part.y);
		}
		
		
        levels.drawLevel(this.g);
        
     	for(int i = 0; i < aliensType1.size(); i++) {
       	aliensType1.get(i).drawOn(this.g);
      	
        }
     	for(int i = 0; i < aliensType2.size(); i++) {
     	aliensType2.get(i).drawOn(this.g);
     	}
        playerPickUp();
        onRocketHolder();
        updateFuelCount();
        if(fuelCount != 120) {
        player.drawOn(this.g);
        }
        for(Bullets bullet:player.getListOfBullets()) {
        	bullet.drawOn(this.g);
        }
	
		playerHit();
		Font font = new Font("arial", Font.BOLD, 50);
		this.g.setFont(font);
		this.g.setColor(Color.WHITE);
		this.g.drawString("Lives: " + this.player.lives, 100, 1030);
		this.g.drawString("Points:" + this.points, 400, 1030);
		this.g.drawString("Bullets:" + this.player.bulletCount + "/"
				+ this.player.reserveAmmo, 700, 1030);

		for (Bullets bullet : player.getListOfLeftBullets()) {
			bullet.drawOn(this.g);
		}

		for (Alien a : aliensType1) {
			for (Bullets bullet : a.getListOfRightBullets()) {
				bullet.drawOn(this.g);
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
		if(this.ammo.intersects(this.player)) {
			ammo.pickedUpAmmo(player);
		}
	}

	public void playerHit() {
		for(Alien a : this.aliensType1) {
			if(this.player.intersects(a) || a.shotPlayer(this.player)) {
				this.player.isHit();
			}
		}
		for(Alien a : this.aliensType2) {
			if(this.player.intersects(a)) {
				this.player.isHit();
			}
		}
		
	}

    public void updateFuelCount() {
    	if(pieceCount == 0) {
    		for(int i = 0 ; i < levels.fuels.size(); i++) {
    			if(this.levels.fuels.get(i).intersects(rocketHolder)){
    				this.levels.fuels.remove(i);
    				this.fuelCount += 40;
    				this.points += 300;
    			}
    		}	
    	}
    }

	public void onRocketHolder() {
		for (int i = 0; i < levels.rocketPieces.size(); i++) {
			Rocket piece = levels.rocketPieces.get(i);
			if (rocketHolder.intersects(piece)) {
				piece.x = rocketHolder.x - 10;
				piece.y = rocketHolder.y - (Rocket.PART_HEIGHT * builtRocketPieces.size() + Rocket.PART_HEIGHT); 
	
				boolean correctPiece = (pieceCount == 3 && piece instanceof BottomRocketPiece) ||
									   (pieceCount == 2 && piece instanceof MiddleRocketPiece) ||
									   (pieceCount == 1 && piece instanceof TopRocketPiece);
	
				if (correctPiece) {
					levels.rocketPieces.remove(i);
					builtRocketPieces.add(piece);
					buildRocketNum += 1;
					pieceCount -= 1;
					points += 300;
				}
				break;
			}
		}
	}
	
	
    public void takeOff() {
		if (this.pieceCount == 0 && this.fuelCount == 120) {
			for (Rocket part : builtRocketPieces) {
				part.takeOff(this.g);
			}
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

	public void updateleftsideBullets() {
		ArrayList<Bullets> bulletsToRemoveS = new ArrayList<>();
		for (Bullets b : player.getListOfLeftBullets()) {
			boolean shouldRemove = b.moveLeft(0);

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

	public void run() {

		KeyListener keylisten = new GameRunningKeyListener(this);
       
        this.addKeyListener(keylisten);
        this.setFocusable(true);
	}
	
	public void changeToNextLevelKeyPressResponse() {
    	if (levels.curLevel == 1) {
			levels = new Level(2);
			points = 0;
			player.lives = 3;
			player.reserveAmmo = 75;
			player.bulletCount = 25;
			levels.curLevel = 2;
			buildRocketNum = 0;
			pieceCount = 3;
			num = rand.nextInt(20);
			ammo = new AmmoCrate(levels.platforms.get(num).x,levels.platforms.get(num).y -30);
			buildingRocket.y = levels.platforms.get(levels.platforms.size()-1).y -120;
		}
		repaint();
    }

	public void changeToPreviousLevelKeyPressResponse() {
    	if (levels.curLevel == 2) {
			
			levels = new Level(1);
			points = 0;
			player.lives = 3;
			player.reserveAmmo = 75;
			player.bulletCount = 25;
			levels.curLevel = 1;
			buildRocketNum = 0;
			pieceCount = 3;
			num = rand.nextInt(20);
			ammo = new AmmoCrate(levels.platforms.get(num).x,levels.platforms.get(num).y -30);
			buildingRocket.y = levels.platforms.get(levels.platforms.size()-1).y -120;
			repaint();
		}
    }
    
    public void selectLevelOneKeyPressResponse() {
		if(endGame == true) {
			levels = new Level(1);
			points = 0;
			player.lives = 3;
			player.reserveAmmo = 75;
			player.bulletCount = 25;
			levels.curLevel = 1;
			endGame = false;
			buildRocketNum = 0;
			pieceCount = 3;
			num = rand.nextInt(20);
			ammo = new AmmoCrate(levels.platforms.get(num).x,levels.platforms.get(num).y -30);
			buildingRocket.y = levels.platforms.get(levels.platforms.size()-1).y -120;
		}
    }
    
    public void selectLevelTwoKeyPressResponse() {
    	if(endGame == true) {
			levels = new Level(2);
			points = 0;
			player.lives = 3;
			player.reserveAmmo = 75;
			player.bulletCount = 25;
			levels.curLevel = 2;
			endGame = false;
			buildRocketNum = 0;
			pieceCount = 3;
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