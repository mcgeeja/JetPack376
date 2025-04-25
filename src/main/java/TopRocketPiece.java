import java.awt.Color;
import java.awt.Graphics2D;

public class TopRocketPiece extends Rocket {
    public TopRocketPiece(int x, int y) {
        super(x, y);
    }

    @Override
public void drawPiece(Graphics2D g, int offsetY) {
    g.setColor(Color.WHITE);
    g.fillRect(x, offsetY, WIDTH, PART_HEIGHT);
    g.drawLine(x, offsetY, x + (WIDTH / 2), offsetY - (PART_HEIGHT / 2));
    g.drawLine(x + (WIDTH / 2), offsetY - (PART_HEIGHT / 2), x + WIDTH, offsetY);
    g.drawLine(x, offsetY, x + WIDTH, offsetY);
}


    @Override
    public void drawOn(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, WIDTH, HEIGHT / 3);
        g.drawLine(x, y, x + (WIDTH / 2), y - (HEIGHT / 4));
        g.drawLine(x + (WIDTH / 2), y - (HEIGHT / 4), x + WIDTH, y);
        g.drawLine(x, y, x + WIDTH, y);
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
