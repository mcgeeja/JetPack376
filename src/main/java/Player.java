import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player extends GravityGameObject{
	private boolean shieldActive = false;
	private static final int HeroHeight = 60;
	private static final int HeroWidth = 30;
	protected int lives;
	protected int speed;
	protected ArrayList<Bullets> bulletList = new ArrayList<>();
	protected ArrayList<Bullets> bulletListLeft = new ArrayList<>();
	protected int bulletCount;
	protected int reserveAmmo;
	protected Image image;
	private boolean right = false;
	private boolean left = false;
	private boolean up = false;
	private boolean down = false;
	private int pickUpCooldown;

	public void setDirectionToFace(int mouseX) {
		if(this.x >= mouseX){
			this.direction = Direction.LEFT;
		}else{
			this.direction = Direction.RIGHT;
		}
	}

	public enum Direction {LEFT, RIGHT};
	private Direction direction = Direction.LEFT;
	private boolean pickUpItem = false;
	private Sound reloadSound = new Sound(new File("src/main/resources/sounds/reload.wav"));

	public Player(int x, int y, int speed) {
		super(x, y, HeroWidth, HeroHeight);
		this.lives = 3;
		this.speed = speed;
		this.bulletCount = 25;
		this.reserveAmmo = 75;
		this.image = null;

		this.pickUpCooldown = 0;

	}//

	public void drawOn(Graphics2D g2d) {
		this.image = Toolkit.getDefaultToolkit().getImage("Astronaut.png");
		try {
			this.image = ImageIO.read(new File("Astronaut.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g2d.drawImage(image, x, y, width, height, null);

	}

	@Override
	public void leftEdgeHit() {
		this.x = 1920-20;
	}
  
	@Override
	public void rightEdgeHit() {

		this.x = 10;
	}

	@Override
	public void topEdgeHit() {
		this.y = 0;
	}

	@Override
	public void bottomEdgeHit() {

	}

	public void activateShield() {
    	this.shieldActive = true;
	}

	public void isHit() {
    	if (shieldActive) {
        	shieldActive = false; // Use up shield
    	} else {
        	this.lives = this.lives - 1;
        	this.x = 800;
        	this.y = 1920 / 2;
    	}
	}


	public int getPickUpCooldown() {
		return this.pickUpCooldown;
	}


	public void startCountDown() {
		this.pickUpCooldown = this.pickUpCooldown - 1;
		if (this.pickUpCooldown < 0) {
			this.pickUpCooldown = 0;
		}
	}


	public void move() {
		if (this.right) {
			this.x += this.speed;
		}
		if (this.left) {
			this.x -= this.speed;
		}

		if (this.up) {
			this.y -= this.speed;
		}
		if (this.down) {
			this.y += this.speed;
		}
	}


	public void shoot() {
		if (this.bulletCount != 0) {
			if(direction == Direction.RIGHT) {
				Bullets b = new Bullets(this.x + this.width, this.y + this.height / 2, Color.ORANGE, 10);
				bulletList.add(b);
			}
			else if(direction == Direction.LEFT) {
				Bullets b = new Bullets(this.x - this.width, this.y + this.height / 2, Color.ORANGE, 10);
				bulletListLeft.add(b);
			}
			this.bulletCount -= 1;
		}
	}

	public ArrayList<Bullets> getListOfBullets() {
		return bulletList;
	}


	public void reload() {
		int max = 25;
		int numBulletsToReload = Math.min(reserveAmmo, max - bulletCount);
		this.bulletCount += numBulletsToReload;
		this.reserveAmmo -= numBulletsToReload;
		reloadSound.playSoundOneShot();
	}

	public ArrayList<Bullets> getListOfLeftBullets() {
		return bulletListLeft;
	}

	public void setMoveRight(boolean b) {
		right = b;
	}

	public void setMoveLeft(boolean b) {
		left = b;
	}

	public void setMoveUp(boolean b) {
		up = b;
	}

	public void setMoveDown(boolean b) {
		down = b;
	}

	public boolean getPickUpItem(){
		return pickUpItem;
	}

	public void setPickUpItem(boolean b) {
		pickUpItem = b;
	}

	public Direction getDirection(){
		return direction;
	}
}
