import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class TitleScreen extends JComponent {
	protected String title = "JETPACK";
	protected int lastScore;

	public TitleScreen(int score) {
		this.lastScore = score;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font font = new Font("arial", Font.BOLD, 100);
		g2d.setFont(font);
		g2d.drawString(title, 1920 / 2 - 250, 1080 / 2);

		font = new Font("arial", Font.BOLD, 50);
		g2d.setFont(font);
		g2d.drawString("Press 1 to play as Astronaut", 1920 / 2 - 265, 1080 / 2 + 200);
		g2d.drawString("Press 2 to play as Zombie Astronaut", 1920 / 2 - 265, 1080 / 2 + 300);

	}
}
