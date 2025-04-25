import java.awt.Color;
import java.awt.Graphics2D;

public class AmmoCrate extends GameObject{
	private static final int size = 30;
	

	public AmmoCrate(int x, int y) {
		super(x, y, size, size);
	}

	public void drawOn(Graphics2D g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, size, size);
		g.setColor(Color.WHITE);
	}

	@Override
	public void leftEdgeHit() {
	}

	@Override
	public void rightEdgeHit() {
	}

	@Override
	public void topEdgeHit() {
	}

	@Override
	public void bottomEdgeHit() {
	}

	public void pickedUpAmmo(Player player) {
		if(this.intersects(player)) {
			player.reserveAmmo = 75;
			player.bulletCount = 25;
		}
	}
}
