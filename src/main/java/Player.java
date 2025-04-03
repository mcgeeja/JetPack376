import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player extends GameObject{
	private static int HeroHeight = 60;
	private static int HeroWidth = 30;
	private static final int GRAVITY = 5;
	protected int lives;
	protected int speed;
	protected ArrayList<Bullets> bulletlist = new ArrayList<>();
	protected ArrayList<Bullets> bulletlistleft = new ArrayList<>();
	protected int bulletCount;
	protected int reserveAmmo;
	protected Image image;
	protected boolean right = false;
	protected boolean left = false;
	protected boolean up = false;
	protected boolean down = false;
	private int pickUpCooldown;
	private boolean faceLeft;
	private boolean faceRight;
	protected boolean dropItem = false;

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

	public void isHit() {
		this.lives = this.lives - 1;
		this.x = 800;
		this.y = 1920 / 2;
		if (this.lives == 0) {

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
		if (this.x < 0) {
			this.x = 1920 - this.width;
		}
		if (this.x > 1920) {
			this.x = 0;
		}
		if (this.y < 0) {
			this.y = 0;
		}

		if (this.up) {
			this.y -= this.speed;
		}
		if (this.down) {
			this.y += this.speed;
		}
	}

	public void gravity(ArrayList<Platform> plats) {
		this.y = this.y + GRAVITY;

        for (Platform plat : plats) {
            if (this.intersects(plat)) {
                this.y = plat.y - this.height;
            }
        }
	}

	public void shoot() {
		if (this.bulletCount != 0) {
			Bullets b = new Bullets(this.x + this.width, this.y + this.height / 2, Color.ORANGE, 10);
			bulletlist.add(b);
			this.bulletCount -= 1;
		}
	}

	public ArrayList<Bullets> getListOfBullets() {
		return bulletlist;
	}

	public void shootleft() {
		if (this.bulletCount != 0) {
			Bullets b = new Bullets(this.x - this.width, this.y + this.height / 2, Color.ORANGE, 10);
			bulletlistleft.add(b);
			this.bulletCount -= 1;
		}
	}

	public void reload() {
		int max = 25;
		if (this.bulletCount >= 25) {
			return;
		}
		if (this.reserveAmmo <= 0) {
			this.reserveAmmo = 0;
		} else if (this.reserveAmmo < 25) {
			int num = Math.min(this.reserveAmmo, max - bulletCount);
			this.bulletCount += num;
			this.reserveAmmo -= num;

		} else {
			int num = Math.min(max, max - bulletCount);
			this.bulletCount += num;
			this.reserveAmmo -= num;
		}
	}

	public ArrayList<Bullets> getListOfLeftBullets() {
		return bulletlistleft;
	}

	public void moveRightKeyReleaseResponse() {
		right = false;
	}

	public void moveLeftKeyReleaseResponse() {
		left = false;
	}

	public void moveUpKeyReleaseResponse() {
		up = false;
	}

	public void moveDownKeyReleaseResponse() {
		down = false;
	}

	public void reloadKeyPressResponse() {
		reload();
	}

	public void shootKeyPressResponse() {
		if(faceRight) {
			shoot();
		}
	
		if(faceLeft) {
	    	shootleft();
		}
	}

	public void pickupKeyPressResponse() {
		dropItem = true;
	}

	public void moveRightKeyPressResponse() {
		faceRight = true;
		faceLeft = false;
		right = true;
	
	}

	public void moveLeftKeyPressResponse() {
		faceRight = false;
		faceLeft = true;
		left = true;
	}

	public void moveUpKeyPressResponse() {
		up = true;
	}

	public void moveDownKeyPressResponse() {
		down = true;
	}

	public void pickupKeyReleaseResponse() {
		dropItem = false;
	}

}
