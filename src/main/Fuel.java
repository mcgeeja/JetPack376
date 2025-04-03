package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Fuel extends GameObject{

	private static final int size = 30;
	private static final int GRAVITY = 5;

	public Fuel(int x, int y) {
		super(x, y, size, size);
	}

	public void drawOn(Graphics2D g) {

		g.setColor(Color.PINK);
		g.fillRect(x, y, size, size);
	}

	public void pickedUp(Player player) {
		if (this.intersects(player)) {
			this.x = player.x;
			this.y = player.y;
		}
	}
	public void gravity(ArrayList<Platform> plats) {
		this.y = this.y + GRAVITY;
		for (int i = 0; i < plats.size(); i++) {
			if (this.intersects(plats.get(i))) {
				this.y = plats.get(i).y - size;
			}
		}
	}
		
}
