import java.awt.Color;
import java.awt.Graphics2D;

public class MiddleRocketPiece extends Rocket {
    public MiddleRocketPiece(int x, int y) {
        super(x, y);
    }

    @Override
    public void drawOn(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, WIDTH, HEIGHT / 3);
        g.setColor(Color.BLACK);
        g.fillRect(x + (WIDTH / 3), y + (HEIGHT / 9), 20, 20);
    }
}