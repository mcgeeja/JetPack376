import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.List;
import java.util.Random;

public class RedAlien extends Alien {
    public RedAlien(int x, int y, String direction) {
        super(x, y, direction);
    }


    @Override
    public void topEdgeHit() {
        if (y < 0)
            directNum = 1;
    }

    @Override
    public void drawOn(Graphics2D g) {
        this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/RedAlien.png"));
        g.drawImage(image, x, y, width, height, null);
    }

    @Override
    public void move(List<Platform> plats) {
        this.contactWith(plats);
        Random random = new Random();
        boolean changeDir = random.nextInt(21) == 20;
        if (changeDir)
            direction = direction.equals("-") ? "+" : "-";
        if (direction == "-") {
            if (directNum == 1) {
                this.y += 10;
            } else {
                this.y -= 10;
            }
            this.x -= 15;
        } else {

            if (directNum == 1) {
                this.y += 10;
            } else {
                this.y -= 10;
            }
            this.x += 15;
        }

    }
}
