import java.awt.Color;
import java.awt.Graphics2D;

public abstract class PowerUp extends GameObject implements Interactable{
    protected boolean collected = false;
    protected Color color;

    public PowerUp(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;
    }

    public abstract void applyEffect(Player player);

    @Override
    public void drawOn(Graphics2D g) {
        if (!collected) {
            g.setColor(color);
            g.fillOval(x, y, width, height);
        }
    }

    public void interact(Player player) {
        if (!collected && this.intersects(player)) {
            collected = true;
            applyEffect(player);
        }
    }
}
