package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class GameOverScreen extends JComponent {
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, 1920, 1080);
	
		Font font = new Font("arial", Font.BOLD, 100);
		g2d.setFont(font);
		g2d.setColor(Color.RED);
		g2d.drawString("GAME OVER", 1920/2 -250 , 1080/2);

	}
}
