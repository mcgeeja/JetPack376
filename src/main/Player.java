package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.color.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player {
	private int height = 60;
	private int width = 30;
	private static final int GRAVITY = 5;
	protected int lives;
	protected int x;
	protected int y;
	protected int speed;
	protected boolean spacePressed;
	protected boolean hasItem;
	protected Fuel heldFuel;
	protected ArrayList<Bullets> bulletlist = new ArrayList<>();
	protected ArrayList<Bullets> bulletlistleft = new ArrayList<>();
	private double count = 1;
	protected int bulletCount;
	protected int reserveAmmo;
	protected Image image;
	protected int wait = 0;
	protected static boolean right = false;
	protected static boolean left = false;
	protected static boolean up = false;
	protected static boolean down = false;
	protected boolean pickup;
	private int pickUpCooldown;

	public Player(int x, int y, int speed) {
		this.lives = 3;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.spacePressed = false;
		this.hasItem = false;
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

	public void setPickUpCooldown(int i) {
		this.pickUpCooldown = i;
	}

	public void startCountDown() {
		this.pickUpCooldown = this.pickUpCooldown - 1;
		if (this.pickUpCooldown < 0) {
			this.pickUpCooldown = 0;
		}
	}

	public Rectangle2D.Double getDimensions() {
		return new Rectangle2D.Double(this.x, this.y, this.width, this.height);
	}

	public void move() {
		if (this.right == true) {
			this.x += this.speed;
		}
		if (this.left == true) {
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

		if (this.up == true) {
			this.y -= this.speed;
		}
		if (this.down == true) {
			this.y += this.speed;
		}
	}

	public void gravity(ArrayList<Platform> plats) {
		this.y = this.y + GRAVITY;

		for (int i = 0; i < plats.size(); i++) {
			if (this.getDimensions().intersects(plats.get(i).getDimensions())) {
				this.y = plats.get(i).y - this.height;
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



	public void setPickUp(boolean b) {
		this.pickup = b;

	}

	public int getx() {
		return this.x;
	}

	public int gety() {
		return this.y;
	}

}
