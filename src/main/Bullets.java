package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Bullets extends GameObject{
//	private double x;
//	private double y;
	private int xVelocity;
	private double size;
	private Color bulletcolor;

	//

	public Bullets(int startx, int starty, Color bulletcolor, int size) {
		super(startx, starty, size, size);
//		this.x = startx;
//		this.y = starty;
		this.xVelocity = 25;
		this.bulletcolor = bulletcolor;
		this.size = size;

	}

	public boolean move(int boundary) {
		this.x = this.x + this.xVelocity;
		return this.x > boundary;
	}

	public boolean moveleft(int boundary) {
		this.x = this.x - this.xVelocity;
		return this.x < boundary;
	}

	public void drawOn(Graphics2D g) {
		g.setColor(this.bulletcolor);
		Rectangle2D.Double bullet = new Rectangle2D.Double(this.x, this.y, this.size, this.size);
		g.fill(bullet);
	}



	public void setSpeedX(int speedX) {
		this.xVelocity = speedX;
	}

	public Rectangle2D.Double getDimensions() {
		return new Rectangle2D.Double(this.x, this.y, this.size, this.size);
	}
}
