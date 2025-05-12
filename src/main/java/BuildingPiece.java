import java.awt.Color;
import java.awt.Graphics2D;

public class BuildingPiece extends GameObject{
	
	public BuildingPiece(int x, int y) {
		super(x, y, 40, 60);
	}

	public void drawOn(Graphics2D g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y-10, width, height);
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
}
