import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public abstract class GravityGameObject extends GameObject{
	private static final int GRAVITY = 5;

	public GravityGameObject(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void gravity(List<Platform> plats) {
		this.y = this.y + GRAVITY;

        for (Platform plat : plats) {
            if (this.intersects(plat)) {
                this.y = plat.y - this.height;
            }
        }
	}
}

