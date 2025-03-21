package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.color.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Rocket {
	private int height = 120;
	private int width = 60;
	private int fuelCount = 0;
	protected int x;
	private static final int GRAVITY = 5;
	protected int y;
	private int partHeight = 40;
	private Graphics2D g;
	protected int type;
	protected boolean isRocketPickedUp;
	protected boolean notStacked = false;
	private boolean rocketOffScreen=false;

	public Rocket(int x, int y, int type) {
		this.fuelCount = 0;
		this.x = x;
		this.y = y;
		this.g = g;
		this.type = type;
		this.isRocketPickedUp = false;

	}
	public Rocket(int x, int y) {
		this.fuelCount = 0;
		this.x = x;
		this.y = y;
		this.g = g;
		this.type = type;
		this.isRocketPickedUp = false;

	}
	public void build(int num, Graphics2D g) {
		if(num > 0) {
		if(num == 3) {
		g.setColor(Color.WHITE);
		g.drawLine(x, y, x + (width / 2), y - (height / 4));
		g.drawLine(x + (width / 2), y - (height / 4), x + (width), y);
		g.drawLine(x, y, x + width, y);
		
		
		
		g.fillRect(x, y, width, partHeight);
		
		g.fillRect(x, y + partHeight, width, partHeight);
		g.setColor(Color.BLACK);
		g.fillRect(x + (width / 3), y + (height / 3)-20, 20, 20);
		
		g.setColor(Color.WHITE);
		g.fillRect(x, y + 2*partHeight, width, partHeight);
		g.drawLine(x, y + partHeight*2, x - (width / 2), y + height );
		g.drawLine(x + (width), y + partHeight*2, x + (3 * height / 4), y + height );
		g.drawLine(x - (width / 2), y + height , x + (3 * width / 2), y + height );
		}
		if(num == 2) {
			
			
			g.fillRect(x, y + partHeight, width, partHeight);
			g.setColor(Color.BLACK);
			g.fillRect(x + (width / 3), y + (height / 3) + 10, 20, 20);
			
			g.setColor(Color.WHITE);
			g.fillRect(x, y + 2*partHeight, width, partHeight);
			g.drawLine(x, y + partHeight*2, x - (width / 2), y + height );
			g.drawLine(x + (width), y + partHeight*2, x + (3 * height / 4), y + height );
			g.drawLine(x - (width / 2), y + height , x + (3 * width / 2), y + height );
			}
		if(num == 1) {
			g.setColor(Color.WHITE);
			g.fillRect(x, y + 2*partHeight, width, partHeight);
			g.drawLine(x, y + partHeight*2, x - (width / 2), y + height );
			g.drawLine(x + (width), y + partHeight*2, x + (3 * height / 4), y + height );
			g.drawLine(x - (width / 2), y + height , x + (3 * width / 2), y + height );
			}
		}
	
	}

	public void draw(Graphics2D g) {
		if (this.type == 1) {
			g.setColor(Color.WHITE);
			g.fillRect(x, y, width, height / 3);
			g.drawLine(x, y, x + (width / 2), y - (height / 4));
			g.drawLine(x + (width / 2), y - (height / 4), x + (width), y);
			g.drawLine(x, y, x + width, y);

		}
		if (this.type == 2) {
			g.setColor(Color.WHITE);
			g.fillRect(x, y, width, height / 3);
			g.setColor(Color.BLACK);
			g.fillRect(x + (width / 3), y + (height / 9), 20, 20);
		}
		if (this.type == 3) {
			g.setColor(Color.WHITE);
			g.fillRect(x, y, width, height / 3);
			g.drawLine(x, y, x - (width / 2), y + height / 3);
			g.drawLine(x + (width), y, x + (3 * height / 4), y + height / 3);
			g.drawLine(x - (width / 2), y + height / 3, x + (3 * width / 2), y + height / 3);

		}
	}


	public void takeOff(Graphics2D g) {
		g.setColor(Color.ORANGE);
		g.fillOval(x, y + 120, 15, 20);
		g.setColor(Color.RED);
		g.fillOval(x + (width / 2) - 7, y + 120, 15, 20);
		g.setColor(Color.YELLOW);
		g.fillOval(x + 45, y + 120, 15, 20);
		this.y -= 10;
		System.out.println("Y = " + this.y);
	}

	public Rectangle2D.Double getDimensions() {
		if (this.type == 1) {
			return new Rectangle2D.Double(x, y, width, height / 3);
		}
		if (this.type == 2) {
			return new Rectangle2D.Double(x, y, width, height / 3);
		}
		if (this.type == 3) {
			return new Rectangle2D.Double(x, y, width, height / 3);
		}
		return null;
	}

	public void pickedUp(Player player) {
		if (this.getDimensions().intersects(player.getDimensions()) && player.getPickUpCooldown() == 0) {
			this.x = player.x;
			this.y = player.y;
			this.isRocketPickedUp = true;
		}

	}


	public void gravity(ArrayList<Platform> plats) {
		if(notStacked = true) {
		this.y = this.y + GRAVITY;
		for (int i = 0; i < plats.size(); i++) {
			if (this.getDimensions().intersects(plats.get(i).getDimensions())) {
				this.y = plats.get(i).y - this.height / 3;
			}

		}
		}
	}

//	public int getRocketType() {
//		return this.type;
//	}

}
