package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class BuildingPiece extends GameObject{

	protected static final int size = 50;
	
	public BuildingPiece(int x, int y) {
		super(x, y, size, size);
	}

	public void drawOn(Graphics2D g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, width, height);
		g.setColor(Color.WHITE);
	}
}
