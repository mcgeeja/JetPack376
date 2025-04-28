import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class ZombieAstronaut extends Player {
	private static final Image astronautImage = Toolkit.getDefaultToolkit().getImage("zombie-astronaut.png");


	public ZombieAstronaut(int x, int y, int speed) {
		super(x, y, speed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawOn(Graphics2D g2d){
		g2d.drawImage(astronautImage, x, y, width, height, null);

	}

}
