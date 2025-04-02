package main;

import java.awt.Button;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class TitleScreen extends JComponent {
	protected Graphics2D g;
	protected String title = "JETPACK";
	protected static boolean onTitle;
	protected Button button;
	protected int lastScore;

	public TitleScreen(int score) {
		this.onTitle = true;
		this.lastScore = score;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		this.g = (Graphics2D) g;
		Font font = new Font("arial", Font.BOLD, 100);
		this.g.setFont(font);
		this.g.drawString(title, 1920 / 2 - 250, 1080 / 2);

		font = new Font("arial", Font.BOLD, 50);
		this.g.setFont(font);
		this.g.drawString("Press ENTER to start", 1920 / 2 - 265, 1080 / 2 + 200);

//		
//	    
	}
}
