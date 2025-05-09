import java.awt.*;
import java.util.List;

public class GreenAlien extends  Alien{
    public GreenAlien(int x, int y, String direction) {
        super(x, y, direction);
    }

    @Override
    public void drawOn(Graphics2D g) {
        this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/Alien_type_2_2.0.png"));
        g.drawImage(image, x, y, width, height, null);
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

    @Override
    public void move(List<Platform> plats) {
        this.contactWith(plats);
        if (direction == "-") {
            if (directNum == 1) {
                this.y += 10;
            } else {
                this.y -= 10;
            }
            this.x -= 15;
        } else {

            if (directNum == 2) {
                this.y -= 10;
            } else {
                this.y += 10;
            }
            this.x += 15;
        }
    }
}
