package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class GameOverScreen extends JComponent {
	protected int finalScore;
	protected boolean gameWin = false;

	public GameOverScreen(int score) {
		this.finalScore = score;
	}
	
//	@Override
//	protected void paintComponent(Graphics g) {
//		Graphics2D g2d = (Graphics2D)g;
//		g2d.setColor(Color.BLACK);
//		g2d.fillRect(0, 0, 1920, 1080);
//	
//		Font font = new Font("arial", Font.BOLD, 100);
//		g2d.setFont(font);
//		g2d.setColor(Color.RED);
//		g2d.drawString("GAME OVER", 1920/2 -250 , 1080/2);
//
//	}
	public void setSuccess(boolean success) {
		gameWin = success;
	}
	@Override
	protected void paintComponent(Graphics g) {
		if(gameWin) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1920, 1080);

			Font font = new Font("arial", Font.BOLD, 100);
			g.setFont(font);
			g.setColor(Color.GREEN);
			g.drawString("SUCCESS", 1920 / 2 - 250, 1080 / 2);

			g.setColor(Color.WHITE);
			g.drawString("Last score was: " + Integer.toString(this.finalScore), (1920 / 2) - 475, (1080 / 2) + 200);
			font = new Font("arial", Font.BOLD, 50);
			g.setFont(font);
			g.drawString("Press the X to exit" , (1920 / 2) - 230, (1080 / 2) + 300);
			
		}else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1920, 1080);

			Font font = new Font("arial", Font.BOLD, 100);
			g.setFont(font);
			g.setColor(Color.RED);
			g.drawString("GAME OVER", 1920 / 2 - 300, 1080 / 2);

			g.setColor(Color.WHITE);
			g.drawString("Last score was: " + Integer.toString(this.finalScore), (1920 / 2) - 475, (1080 / 2) + 200);
			
			font = new Font("arial", Font.BOLD, 50);
			g.setFont(font);
			g.drawString("Press '1' for level 1 or '2' for level 2 " , (1920 / 2) - 420, (1080 / 2) + 300);
		}
		
	}

}
