package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GreenAlien extends  Alien{
    public GreenAlien(int x, int y, String direction) {
        super(x, y, direction);
    }

    @Override
    public void drawOn(Graphics2D g) {
        this.image = Toolkit.getDefaultToolkit().getImage("Alien_type_2_2.0.png");
        try {
            this.image = ImageIO.read(new File("Alien_type_2_2.0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image, x, y, width, height, null);
    }

    @Override
    public void move(ArrayList<Platform> plats) {
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
