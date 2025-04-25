import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.sql.Time;



public class GameOverScreen {
	MyComponent component;

	private static final Font controlsFont = new Font("arial", Font.BOLD, 50);
	private static final Font mainFont = new Font("arial", Font.BOLD, 100);

	public GameOverScreen(MyComponent component){
		this.component = component;
		component.addKeyListener(new GameOverKeyListener(component));
	}
	
	public void paintWinGame(){
		Graphics2D g = component.g;

		//Fill screen with black
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1920, 1080);

		//set font
		g.setFont(mainFont);

		//set Color and message to put on the screen
		g.setColor(Color.GREEN);
		g.drawString("SUCCESS", 1920 / 2 - 250, 1080 / 2);

		//set submessage on the screen
		g.setColor(Color.WHITE);
		g.drawString("Last score was: " + component.points, (1920 / 2) - 475, (1080 / 2) + 200);

		g.setFont(controlsFont);
		g.drawString("Press '1' for level 1 or '2' for level 2 " , (1920 / 2) - 420, (1080 / 2) + 300);
	}
	
	public void paintLoseGame(){
		Graphics2D g = component.g;
//		Time time = new Time(10);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1920, 1080);

		g.setFont(mainFont);
		g.setColor(Color.RED);
		g.drawString("GAME OVER", 1920 / 2 - 300, 1080 / 2);

		g.setColor(Color.WHITE);
		g.drawString("Last score was: " + component.points, (1920 / 2) - 475, (1080 / 2) + 200);

		g.setFont(controlsFont);
		g.drawString("Press '1' for level 1 or '2' for level 2 " , (1920 / 2) - 420, (1080 / 2) + 300);
		
	}

}
