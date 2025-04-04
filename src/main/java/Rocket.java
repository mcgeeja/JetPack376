import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class Rocket extends GameObject{

	protected static final int WIDTH = 60;
    protected static final int HEIGHT = 120;
    protected static final int PART_HEIGHT = 40;
    protected static final int GRAVITY = 5;

	public Rocket(int x, int y) {
        super(x, y, WIDTH, HEIGHT / 3);
    }

	public abstract void drawOn(Graphics2D g);

	public void pickedUp(Player player) {
		if (this.intersects(player) && player.getPickUpCooldown() == 0) {
			this.x = player.x;
			this.y = player.y;
		}
	}

	public void gravity(ArrayList<Platform> plats) {
			this.y = this.y + GRAVITY;
			for (int i = 0; i < plats.size(); i++) {
				if (this.intersects(plats.get(i))) {
					this.y = plats.get(i).y - this.height / 3;
				}
			}
	}

}
