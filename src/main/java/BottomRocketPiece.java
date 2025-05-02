import java.awt.Color;
import java.awt.Graphics2D;

public class BottomRocketPiece extends Rocket {
    public BottomRocketPiece(int x, int y) {
        super(x, y);
    }

    @Override
public void drawPiece(Graphics2D g, int offsetY) {
    g.setColor(Color.WHITE);
    g.fillRect(x, offsetY, WIDTH, PART_HEIGHT);

    g.drawLine(x, offsetY + PART_HEIGHT/2, x - (WIDTH / 2), offsetY + PART_HEIGHT/2 + PART_HEIGHT/2);
    g.drawLine(x + WIDTH, offsetY + PART_HEIGHT/2, x + (3 * HEIGHT / 4), offsetY + PART_HEIGHT/2 + PART_HEIGHT/2);
    g.drawLine(x - (WIDTH / 2), offsetY + PART_HEIGHT/2 + PART_HEIGHT/2, x + (3 * WIDTH / 2), offsetY + PART_HEIGHT/2 + PART_HEIGHT/2);
}


    @Override
    public void drawOn(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, WIDTH, HEIGHT / 3);
        g.drawLine(x, y + HEIGHT / 3, x - (WIDTH / 2), y + HEIGHT);
        g.drawLine(x + WIDTH, y + HEIGHT / 3, x + (3 * HEIGHT / 4), y + HEIGHT);
        g.drawLine(x - (WIDTH / 2), y + HEIGHT, x + (3 * WIDTH / 2), y + HEIGHT);
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
