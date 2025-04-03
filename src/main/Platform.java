package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.color.*;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

public class Platform extends GameObject{

	public Platform(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public void drawOn(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fill(new Rectangle2D.Double(x, y, width, height));

	}

}
