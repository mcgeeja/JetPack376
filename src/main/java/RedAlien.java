import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class RedAlien extends Alien {
    public RedAlien(int x, int y, String direction) {
        super(x, y, direction);
    }

    void wrapAround() {
        if (direction.equals("-") && x < 0)
            x = Main.frameWidth;
        else if (direction.equals("+") && x > Main.frameWidth)
            x = 0;
        if (directNum == 1 && y > Main.frameHeight)
            y = 0;
        else if (directNum == 2 && y < 0)
            directNum = 1;
    }

    @Override
    public void drawOn(Graphics2D g) {
        this.image = Toolkit.getDefaultToolkit().getImage("RedAlien.png");
        try {
            this.image = ImageIO.read(new File("RedAlien.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image, x, y, width, height, null);
    }

    @Override
    public void move(ArrayList<Platform> plats) {
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
        System.out.println(y);
        wrapAround();
    }
}
