package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Fuel extends GameObject{

//	protected int x;
//	protected int y;
	private static int size = 30;
	private static final int GRAVITY = 5;
	private boolean haveGrav = true;

	public Fuel(int x, int y) {
		super(x, y, size, size);
//		this.x = x;
//		this.y = y;
//		this.size=20;
	}

	public void drawOn(Graphics2D g) {

		g.setColor(Color.PINK);
		g.fillRect(x, y, this.size, this.size);
	}

//	public Rectangle2D.Double getDimensions() {
//		return new Rectangle2D.Double(x, y, size, size);
//	}

//	public void pickedUp(Player player) {
//		if (this.getDimensions().intersects(player.getDimensions())) {
//			this.x = player.x;
//			this.y = player.y;
//			this.haveGrav = false;
//		}
//	}
	public void pickedUp(Player player) {
		if (this.intersects(player)) {
			this.x = player.x;
			this.y = player.y;
			this.haveGrav = false;
		}
	}
	public void gravity(ArrayList<Platform> plats) {
		this.y = this.y + GRAVITY;
		for (int i = 0; i < plats.size(); i++) {
			if (this.getDimensions().intersects(plats.get(i).getDimensions())) {
				this.y = plats.get(i).y - this.size;
			}
		}
	}
		
}
