package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public abstract class Alien extends GameObject{

	private int type;
	private int alienreload;
	protected String direction;
	private int num;
	protected int directNum;
	protected ArrayList<Platform> platforms;
	protected ArrayList<Bullets> rightbulletlist = new ArrayList<>();
	protected Image Alien1;
	protected Image Alien2;

	public Alien(int x, int y, String direction) {
		super(x, y, 25, 25);
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.alienreload = 1;
		this.Alien1 = null;
		this.Alien2 = null;
	}

	public abstract void drawOn(Graphics2D g);
	public boolean shotPlayer(Player player) {
		for(Bullets b : this.rightbulletlist) {
			if(player.intersects(b)) {
				return true;
			}
		}
		return false;
	}

	public void contactWith(ArrayList<Platform> plats) {
		for (int i = 0; i < plats.size(); i++) {
			if (this.intersects(plats.get(i))) {

				if (this.directNum == 1) {
					directNum = 2;
				} else {
					directNum = 1;
				}

			}
		}
	}

	public void shootright() {
		Bullets b = new Bullets(this.x + this.width, this.y + this.height / 2, Color.RED, 5);
		rightbulletlist.add(b);
	}

	public ArrayList<Bullets> getListOfRightBullets() {
		return rightbulletlist;
	}

	public void shotTimer() {
		this.alienreload -= 1;
		if (this.alienreload == 0) {
			this.shootright();
			this.alienreload = 25;
		}
	}

//	public boolean bulletHit(ArrayList<Bullets> bullets) {
//		Random rand = new Random();
//		for (int i = 0; i < bullets.size(); i++) {
//			if (this.getDimensions().intersects(bullets.get(i).getDimensions())) {
//				if (direction == "+") {
//					this.x = 0;
//					this.y = rand.nextInt(600);
//					this.direction = "-";
//					return true;
//				} else {
//					this.x = 1920;
//					this.y = rand.nextInt(600);
//					this.direction = "+";
//					return true;
//				}
//
//			}
//		}
//		return false;
//	}
	public boolean bulletHit(ArrayList<Bullets> bullets) {
		Random rand = new Random();
		for (int i = 0; i < bullets.size(); i++) {
			if (this.intersects(bullets.get(i))) {
				if (direction == "+") {
					this.x = 0;
					this.y = rand.nextInt(600);
					this.direction = "-";
					return true;
				} else {
					this.x = 1920;
					this.y = rand.nextInt(600);
					this.direction = "+";
					return true;
				}

			}
		}
		return false;
	}

//	public Rectangle2D.Double getDimensions() {
//		return new Rectangle2D.Double(this.x, this.y, this.width, this.height);
//	}

	public abstract  void move(ArrayList<Platform> plats) ;

}
