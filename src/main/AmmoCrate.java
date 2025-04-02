package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class AmmoCrate extends GameObject{
//	protected int x;
//	protected int y;
	private static int size = 30;
	

	public AmmoCrate(int x, int y) {
		super(x, y, size, size);
//		this.x = x;
//		this.y = y;
//		this.size=30;
	}

	public void drawOn(Graphics2D g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, this.size, this.size);
		g.setColor(Color.WHITE);
	}

//	public Rectangle2D.Double getDimensions() {
//		return new Rectangle2D.Double(x, y, size, size);
//	}

//	public void pickedUpAmmo(Player player) {
//		if (this.getDimensions().intersects(player.getDimensions())) {
//			player.reserveAmmo = 75;
//		}
//	}
	public void pickedUpAmmo(Player player) {
		if(this.intersects(player)) {
			player.reserveAmmo = 75;
		}
	}
}
