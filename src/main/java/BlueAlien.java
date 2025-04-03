import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
    public void move(ArrayList<Platform> plats) {
        if (direction == "+") {
            if (this.x <= 1920) {
                this.x += 10;
            } else {
                this.x = 0;
            }
        } else {
            if (this.x > 0) {
                this.x -= 15;
            } else {
                this.x = 1920;
            }
        }
    }
}
