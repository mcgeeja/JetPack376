import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class ZombieAstronaut extends Player {
	private static final Image astronautImage = Toolkit.getDefaultToolkit().getImage(ZombieAstronaut.class.getResource("/images/zombie-astronaut.png"));
	private static final int bulletSpeed = 45;


	public ZombieAstronaut(int x, int y, int speed) {
		super(x, y, speed);
	}

	@Override
	public void drawOn(Graphics2D g2d){
		g2d.drawImage(astronautImage, x, y, width, height, null);

	}
	
	@Override
	public void shoot() {
		if (this.bulletCount != 0) {
			if(direction == Direction.RIGHT) {
				Bullets b = new Bullets(this.x + this.width, this.y + this.height / 2, Color.CYAN, 5, 45);
				bulletList.add(b);
			}
			else if(direction == Direction.LEFT) {
				Bullets b = new Bullets(this.x - this.width, this.y + this.height / 2, Color.CYAN, 5, 45);
				bulletListLeft.add(b);
			}
			this.bulletCount -= 1;
		}
	}

}
