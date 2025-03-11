package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.color.*;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

public class Platform {
//
	protected int x;
	protected int y;
	protected int height;
	protected int width;

	public Platform(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	public void drawOn(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fill(getDimensions());

	}

	public Rectangle2D.Double getDimensions() {
		return new Rectangle2D.Double(x, y, width, height);
	}

}
