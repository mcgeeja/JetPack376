import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public abstract class GameObject {
	protected int x, y, height, width;
	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
	public boolean intersects(GameObject ob) {
		return ob.getDimensions().intersects(this.getDimensions());
	}
	private Rectangle2D.Double getDimensions() {
		return new Rectangle2D.Double(x, y, width, height);
	}
	public abstract void drawOn(Graphics2D g);
}
