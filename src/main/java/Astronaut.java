import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Astronaut extends Player{
	private static final Image astronautImage = Toolkit.getDefaultToolkit().getImage("Astronaut.png");
	private static final int bulletSpeed = 25;

	public Astronaut(int x, int y, int speed) {
		super(x, y, speed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawOn(Graphics2D g2d){
		g2d.drawImage(astronautImage, x, y, width, height, null);

	}
	@Override
	public void shoot() {
		if (this.bulletCount != 0) {
			if(direction == Direction.RIGHT) {
				Bullets b = new Bullets(this.x + this.width, this.y + this.height / 2, Color.ORANGE, 10, bulletSpeed);
				bulletList.add(b);
			}
			else if(direction == Direction.LEFT) {
				Bullets b = new Bullets(this.x - this.width, this.y + this.height / 2, Color.ORANGE, 10, bulletSpeed);
				bulletListLeft.add(b);
			}
			this.bulletCount -= 1;
		}
	}
	

}
