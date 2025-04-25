import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class Rocket extends GravityGameObject implements Interactable{
    protected static final int WIDTH = 60;
    protected static final int HEIGHT = 120;
    protected static final int PART_HEIGHT = 40;
    protected static final int GRAVITY = 5;

    public Rocket(int x, int y) {
        super(x, y, WIDTH, PART_HEIGHT);
    }

    public abstract void drawPiece(Graphics2D g, int offsetY);

    public void build(int num, Graphics2D g) {
        for (int i = 0; i < num; i++) {
            int offsetY = y + i * PART_HEIGHT;
            drawPiece(g, offsetY);
        }
    }

    public void takeOff(Graphics2D g) {
        g.setColor(Color.ORANGE);
        g.fillOval(x, y + 120, 15, 20);
        g.setColor(Color.RED);
        g.fillOval(x + (width / 2) - 7, y + 120, 15, 20);
        g.setColor(Color.YELLOW);
        g.fillOval(x + 45, y + 120, 15, 20);
        this.y -= 10;
    }

    public void interact(Player player) {
        if (this.intersects(player) && player.getPickUpCooldown() == 0) {
            this.x = player.x;
            this.y = player.y;
        }
    }

}
