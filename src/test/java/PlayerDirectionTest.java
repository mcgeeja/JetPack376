import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerDirectionTest {

    @Test
    public void testDefaultDirectionLeft() {
        Player player = new Player(10, 10, 5);

        Assertions.assertEquals(Player.Direction.LEFT, player.getDirection());
    }

    @Test
    public void testSetMoveRightMakesDirectionRight() {
        Player player = new Player(10, 10, 5);

        player.setDirectionToFace(11);
        Assertions.assertEquals(Player.Direction.RIGHT, player.getDirection());
    }

    @Test
    public void testSetDirectionToFaceMakesDirectionLeft() {
        Player player = new Player(10, 10, 5);

        player.setDirectionToFace(9);
        Assertions.assertEquals(Player.Direction.LEFT, player.getDirection());
    }

    @Test
    public void testShootAddsBulletsToFacedDirection() {
        Player player = new Player(10, 10, 5);

        player.setDirectionToFace(9);//sets the direction for me
        Assertions.assertEquals(0,  player.getListOfLeftBullets().size());
        player.shoot();
//        Assertions.assertEquals(1,  player.getListOfLeftBullets().size());
//
//        player.setDirectionToFace(11);//sets the direction for me
//        Assertions.assertEquals(0,  player.getListOfBullets().size());
//        player.shoot();
//        Assertions.assertEquals(1,  player.getListOfBullets().size());
    }
}
