import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;


public class MyComponent extends JComponent {
	protected Player player;
	private Level levels;
	protected int num;
	protected List<Alien> aliensType1;
	protected List<Alien> aliensType2;
	protected Graphics2D g;
	private ArrayList<Rocket> builtRocketPieces = new ArrayList<>();


	protected int points;
	protected BuildingPiece rocketHolder;
	LocalDateTime time;
	protected Rocket buildingRocket;
	protected int buildRocketNum = 0;
	protected int piecesInLevel;
	protected int pieceCount;
	protected int fuelCount = 0;
	protected AmmoCrate ammo;
	protected boolean endGame = false;
	private ArrayList<PowerUp> powerUps = new ArrayList<>();

	Random r = new Random();
	public MyComponent(Player player, List<Alien> aliensType1,List<Alien>  aliensType2) {
		time =LocalDateTime.now();

		this.player = player;

		this.aliensType1 = aliensType1;
		this.aliensType2 = aliensType2;

		this.levels = new Level(1);
		piecesInLevel = levels.rocketPieces.size();
		pieceCount = levels.rocketPieces.size(); ;
		int xR = levels.getBottomRocketPiece().x;
		
		this.rocketHolder = new BuildingPiece( xR, 930);
		this.buildingRocket = levels.getBottomRocketPiece();
		this.buildingRocket.x = xR - 10;
		this.buildingRocket.y = levels.platforms.get(levels.platforms.size()-1).y - 120;
		num = r.nextInt(20);
		this.ammo = new AmmoCrate(levels.platforms.get(num).x,levels.platforms.get(num).y -30);
		// Create a random powerup on a platform
		PowerUp speedBoost = new SpeedBoost(levels.platforms.get(r.nextInt(levels.platforms.size())).x, 100);
		PowerUp shield = new Shield(levels.platforms.get(r.nextInt(levels.platforms.size())).x, 300);

		powerUps.add(speedBoost);
		powerUps.add(shield);
	}
	public void setPlayer(Player player) {
		this.player = player;
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

        for (Alien alien : aliensType1) {
            alien.drawOn(this.g);
        }
        for (Alien alien : aliensType2) {
            alien.drawOn(this.g);
        }
        interactionHandler();
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

		for (PowerUp p : powerUps) {
			p.drawOn(this.g);
		}



		takeOff();
		gameOver();
		
	}
	public void writeResultsToFile(boolean didWin){
		LocalDateTime end = LocalDateTime.now();
		Duration diff = Duration.between(time,end );
		long minutes = diff.toMinutesPart();
		long seconds = diff.toSecondsPart();
		LinkedList<String> summaryLines = new LinkedList<>();
		summaryLines.add((didWin)?"You won!":"You lost!");
		summaryLines.add("Score: "+points);
		summaryLines.add("Time: "+minutes+" minutes and "+seconds+" seconds");
		summaryLines.add("Played Level: "+levels.curLevel);

		FileWriter writer = null;
        try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			String formattedDateTime = end.format(formatter); // "1986-04-08 12:30"
			File resultsFolder = new File("./results/");
			resultsFolder.mkdir();

			writer= new FileWriter("results/"+formattedDateTime+" game.txt");
			for(String line : summaryLines){
				writer.write(line+"\n");
			}
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
		finally {
			if(writer!=null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
		}
    }
	public void gameOver() {
		if (this.player.lives <= 0) {
			GameOverScreen gameOverScreen = new GameOverScreen(this);
			gameOverScreen.paintLoseGame();
			endGame = true;
			writeResultsToFile(false);
		}
		if(buildingRocket.y <= 0) {
			GameOverScreen gameOverScreen = new GameOverScreen(this);
			gameOverScreen.paintWinGame();

			endGame = true;
			writeResultsToFile(true);
		}

	}

	public void interactionHandler() {
		if(player.getPickUpItem()) {
			for (int i = 0; i < levels.fuels.size(); i++) {
				levels.fuels.get(i).interact(this.player);
			}
			for (int i = 0; i < levels.rocketPieces.size(); i++) {
				levels.rocketPieces.get(i).interact(this.player);
			}
		}
		ammo.interact(player);
		
		for (PowerUp p : powerUps) {
			if(p.intersects(player)){
				p.pickedUp(player);
			}
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
	
				boolean correctPiece = (pieceCount == piecesInLevel && piece instanceof BottomRocketPiece) ||
									   (pieceCount < piecesInLevel && pieceCount > 1 && piece instanceof MiddleRocketPiece) ||
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

	public void updateAliens() {
        for (Alien alien : this.aliensType1) {

            alien.move(levels.platforms);
            if (alien.bulletHit(player.bulletList)
                    || alien.bulletHit(player.bulletListLeft)) {
                this.points += 100;

            }
        }
//	    	
        for (Alien alien : this.aliensType2) {
            alien.move(levels.platforms);
            if (alien.bulletHit(player.bulletList)
                    || alien.bulletHit(player.bulletListLeft)) {
                this.points += 100;

            }
        }
	}

	public void updatePickUpTimer() {
		player.startCountDown();
	}

	public void run() {

		KeyListener keyListen = new GameRunningKeyListener( this.player);
		MouseInputListener mouseListen = new GameRunningMouseListener(this.player);
       
        this.addKeyListener(keyListen);
		this.addMouseListener(mouseListen);
		this.addMouseMotionListener(mouseListen);

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
			piecesInLevel = levels.rocketPieces.size();
			pieceCount = levels.rocketPieces.size(); ;
			num = Main.rand.nextInt(20);
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
			num = Main.rand.nextInt(20);
			ammo = new AmmoCrate(levels.platforms.get(num).x,levels.platforms.get(num).y -30);
			buildingRocket.y = levels.platforms.get(levels.platforms.size()-1).y -120;
			repaint();
		}
    }

	public void handleComponentOnEdge(GameObject gameObject){
		int bottomEdge = gameObject.y+gameObject.height;
		int rightEdge = gameObject.x+gameObject.width;
		int leftEdge = gameObject.x;
		int topEdge = gameObject.y;
		if(leftEdge<0)
			gameObject.leftEdgeHit();
		else if(rightEdge>getWidth())
			gameObject.rightEdgeHit();
		if(topEdge<0)
			gameObject.topEdgeHit();
		else if(bottomEdge>getHeight())
			gameObject.bottomEdgeHit();
	}
	public void processGameComponentsOnEdge(){
		handleComponentOnEdge(player);
		for(Alien alien:aliensType1){
			handleComponentOnEdge(alien);
		}
		for(Alien alien:aliensType2){
			handleComponentOnEdge(alien);
		}
	}
    public void selectLevelOneKeyPressResponse() {
		if(endGame) {
			levels = new Level(1);
			points = 0;
			player = new Astronaut(1920 / 2, 800, 15);
			this.addKeyListener(new GameRunningKeyListener(this.player));
			levels.curLevel = 1;
			endGame = false;
			buildRocketNum = 0;
			pieceCount = 3;
			num = Main.rand.nextInt(20);
			ammo = new AmmoCrate(levels.platforms.get(num).x,levels.platforms.get(num).y -30);
			buildingRocket.y = levels.platforms.get(levels.platforms.size()-1).y -120;
		}
    }
    
    public void selectLevelTwoKeyPressResponse() {
    	if(endGame) {
			levels = new Level(2);
			points = 0;
			player = new Astronaut(1920 / 2, 800, 15);
			this.addKeyListener(new GameRunningKeyListener(this.player));
			levels.curLevel = 2;
			endGame = false;
			buildRocketNum = 0;
			pieceCount = 3;
			num = Main.rand.nextInt(20);
			ammo = new AmmoCrate(levels.platforms.get(num).x,levels.platforms.get(num).y -30);
			buildingRocket.y = levels.platforms.get(levels.platforms.size()-1).y -120;
		}
    }

    
    public void updateState() throws FileNotFoundException {
		updateAlienReload();
		updateleftsideBullets();
		processGameComponentsOnEdge();
		updateBullets();
		updateGrav();
		updateAliens();
		player.move();
		updateAllAlienBullets();
		updatePickUpTimer();

	}

}