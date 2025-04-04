import java.awt.Color;
import java.awt.Graphics2D;

public class TopRocketPiece extends Rocket {
    public TopRocketPiece(int x, int y) {
        super(x, y);
    }

    @Override
    public void drawOn(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, WIDTH, HEIGHT / 3);
        g.drawLine(x, y, x + (WIDTH / 2), y - (HEIGHT / 4));
        g.drawLine(x + (WIDTH / 2), y - (HEIGHT / 4), x + WIDTH, y);
        g.drawLine(x, y, x + WIDTH, y);
    }
}
