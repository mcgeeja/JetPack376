import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Graphics2D;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PowerUpTests {

    
    private Player player;
    private Shield shield;
    private SpeedBoost speedBoost;
    private class TestPlayer extends Player{

		public TestPlayer(int x, int y, int speed) {
			super(x, y, speed);
		}

		@Override
		public void drawOn(Graphics2D g2d) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void shoot() {
			// TODO Auto-generated method stub
			bulletList.add(new Bullets(1,1,null, 1,1));
		}
    	
    }
    @BeforeEach
    public void setup() {
        player = new TestPlayer(100, 100, 10);
        shield = new Shield(150, 150);
        speedBoost = new SpeedBoost(200, 200);
    }


    @Test
    public void testShieldBlocksDamage() {
        int initialLives = player.lives;
        shield.applyEffect(player);
        player.isHit();
        assertEquals(initialLives, player.lives);
    }

   
    @Test
    public void testAllPowerUpEffectsAtOnce() {
       

        int initialLives = player.lives;
        int initialSpeed = player.speed;

        shield.applyEffect(player);
        speedBoost.applyEffect(player);

        player.isHit();  

        assertEquals(initialLives, player.lives);
        assertTrue(player.speed > initialSpeed);
        assertNotNull(shield);
        assertNotNull(speedBoost);
        assertFalse(shield.collected);
    }

   
    @Test
    public void testSpeedBoostCollected() {
        speedBoost = new SpeedBoost(100, 100);
        player = new TestPlayer(100, 100, 10);

        assertFalse(speedBoost.collected);
        speedBoost.interact(player);
        assertTrue(speedBoost.collected);
    }
}
