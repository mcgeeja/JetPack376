import java.awt.*;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBullet {
    public void setupBullet(){

    }
    @Test
    public void testBulletMove(){
        Bullets bullet = new Bullets(50,0, Color.BLACK,1);
        assertFalse(bullet.move(99));
        assertTrue(bullet.move(99));

    }
    @Test
    public void testMoveLeft(){

        Bullets bullet = new Bullets(50,0, Color.BLACK,1);
        bullet.x=50;
        assertFalse(bullet.moveLeft(1));
        assertTrue(bullet.moveLeft(1));
    }
}
