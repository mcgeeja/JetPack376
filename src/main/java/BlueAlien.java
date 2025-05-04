import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BlueAlien extends Alien{

    public BlueAlien(int x, int y,  String direction) {
        super(x, y,  direction);
    }

    @Override
    public void drawOn(Graphics2D g) {
        this.image = Toolkit.getDefaultToolkit().getImage("alien_type_1_2.0.png");
        try {
            this.image = ImageIO.read(new File("alien_type_1_2.0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.drawImage(this.image, x, y, width, height, null);
    }

    @Override
    public void leftEdgeHit() {
        super.leftEdgeHit();
        if(direction.equals("-"))
            this.x = 1920;
    }

    @Override
    public void rightEdgeHit() {
        super.rightEdgeHit();
        if(direction.equals("+"))
            this.x = 10;
        else
            this.x=1900;
    }

    @Override
    public void topEdgeHit() {
        super.topEdgeHit();
    }

    @Override
    public void bottomEdgeHit() {
        super.bottomEdgeHit();
    }

    @Override
    public void move(List<Platform> plats) {
        if (direction == "+") {
            this.x += 10;
        } else {
            this.x -= 15;
        }
    }
}
