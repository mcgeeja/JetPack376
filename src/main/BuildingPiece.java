package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class BuildingPiece {
	protected int x;
	protected int y;
	protected int height = 50;
	protected int width = 50;;
	
	public BuildingPiece(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void draw(Graphics2D g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, width, height);
		g.setColor(Color.WHITE);
	}
	public Rectangle2D.Double getDimensions(){
		return new Rectangle2D.Double(x, y, width, height);
	}
}
