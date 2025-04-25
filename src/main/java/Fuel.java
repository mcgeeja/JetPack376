import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Fuel extends GravityGameObject implements Interactable{

	private static final int size = 30;

	public Fuel(int x, int y) {
		super(x, y, size, size);
	}

	public void drawOn(Graphics2D g) {

		g.setColor(Color.PINK);
		g.fillRect(x, y, size, size);
	}

	public void interact(Player player) {
		if (this.intersects(player)) {
			this.x = player.x;
			this.y = player.y;
		}
	}

		
}
