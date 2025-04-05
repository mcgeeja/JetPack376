import java.awt.Color;
import java.awt.Graphics2D;

public class MiddleRocketPiece extends Rocket {
    public MiddleRocketPiece(int x, int y) {
        super(x, y);
    }

    @Override
public void drawPiece(Graphics2D g, int offsetY) {
    g.setColor(Color.WHITE);
    g.fillRect(x, offsetY, WIDTH, PART_HEIGHT);
    g.setColor(Color.BLACK);
    g.fillRect(x + (WIDTH / 3), offsetY + (PART_HEIGHT / 4), 20, 20);
}


    @Override
    public void drawOn(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, WIDTH, HEIGHT / 3);
        g.setColor(Color.BLACK);
        g.fillRect(x + (WIDTH / 3), y + (HEIGHT / 9), 20, 20);
    }

}