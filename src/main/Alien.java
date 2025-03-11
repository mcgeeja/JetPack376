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

public class Alien {

	protected int x;
	protected int y;
	private int type;
	private int alienreload;
	private int width = 25;
	private int height = 25;
	protected String direction;
	private int num;
	protected int directNum;
	protected ArrayList<Platform> platforms;
	protected ArrayList<Bullets> rightbulletlist = new ArrayList<>();
	protected Image Alien1;
	protected Image Alien2;

	public Alien(int x, int y, int type, String direction) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.direction = direction;
		this.alienreload = 1;
		this.Alien1 = null;
		this.Alien2 = null;
	}

	public void drawOn(Graphics2D g) {
		if (this.type == 1) {

			this.Alien1 = Toolkit.getDefaultToolkit().getImage("alien_type_1_2.0.png");
			try {
				this.Alien1 = ImageIO.read(new File("alien_type_1_2.0.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			g.drawImage(Alien1, x, y, width, height, null);
		}
		if (this.type == 2) {

			this.Alien2 = Toolkit.getDefaultToolkit().getImage("Alien_type_2_2.0.png");
			try {
				this.Alien2 = ImageIO.read(new File("Alien_type_2_2.0.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(Alien2, x, y, width, height, null);
		}
	}

	public void contactWith(ArrayList<Platform> plats) {
		for (int i = 0; i < plats.size(); i++) {
			if (this.getDimensions().intersects(plats.get(i).getDimensions())) {

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

	public boolean bulletHit(ArrayList<Bullets> bullets) {
		Random rand = new Random();
		for (int i = 0; i < bullets.size(); i++) {
			if (this.getDimensions().intersects(bullets.get(i).getDimensions())) {
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

	public Rectangle2D.Double getDimensions() {
		return new Rectangle2D.Double(this.x, this.y, this.width, this.height);
	}

	public void move(ArrayList<Platform> plats) {
		this.platforms = plats;
		if (this.type == 1) {
			if (direction == "+") {
				if (this.x <= 1920) {
					this.x += 10;
				} else {
					this.x = 0;
				}
			} else {
				if (this.x > 0) {
					this.x -= 15;
				} else {
					this.x = 1920;
				}
			}
		} else {
			this.contactWith(plats);
			if (direction == "-") {
				if (directNum == 1) {
					this.y += 10;
				} else {
					this.y -= 10;
				}
				this.x -= 15;
			} else {

				if (directNum == 2) {
					this.y -= 10;
				} else {
					this.y += 10;
				}

				this.x += 15;

			}
		}

	}

}
