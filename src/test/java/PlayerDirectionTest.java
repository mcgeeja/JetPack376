import java.awt.Graphics2D;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class PlayerDirectionTest {
    @BeforeAll
    static void setUpBeforeClass() {
        Sound.audioEnabled=false;
    }
    
    private class TestPlayer extends Player{

		public TestPlayer(int x, int y, int speed) {
			super(x, y, speed);
		}

		@Override
		public void drawOn(Graphics2D g2d) {
			// TODO Auto-generated method stub
			
		}
    	
    }

    @Test
    public void testDefaultDirectionLeft() {
        Player player = new TestPlayer(10, 10, 5);

        Assertions.assertEquals(Player.Direction.LEFT, player.getDirection());
    }

    @Test
    public void testSetMoveRightMakesDirectionRight() {
        Player player = new TestPlayer(10, 10, 5);

        player.setDirectionToFace(11);
        Assertions.assertEquals(Player.Direction.RIGHT, player.getDirection());
    }

    @Test
    public void testSetDirectionToFaceMakesDirectionLeft() {
        Player player = new TestPlayer(10, 10, 5);

        player.setDirectionToFace(9);
        Assertions.assertEquals(Player.Direction.LEFT, player.getDirection());
    }

    @Test
    public void testShootAddsBulletsToFacedDirection() {
        Player player = new TestPlayer(10, 10, 5);

        player.setDirectionToFace(9);//sets the direction for me
        Assertions.assertEquals(0,  player.bulletListLeft.size());
        player.shoot();
        Assertions.assertEquals(1,  player.bulletListLeft.size());

        player.setDirectionToFace(11);//sets the direction for me
        Assertions.assertEquals(0,  player.bulletList.size());
        player.shoot();
        Assertions.assertEquals(1,  player.bulletList.size());
    }
}
