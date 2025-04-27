import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PowerUpTests {

    
    private Player player;
    private Shield shield;
    private SpeedBoost speedBoost;
    private BlueAlien blueAlien;

    @BeforeEach
    public void setup() {
        player = new Player(100, 100, 10);
        blueAlien = new BlueAlien(0, 0, "+");
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
    public void testSpeedBoostDetails() {

        assertEquals(30, speedBoost.width);
        assertEquals(30, speedBoost.height);
        assertEquals(100, speedBoost.x);
        assertEquals(100, speedBoost.y);
        assertFalse(speedBoost.collected);
        assertEquals(java.awt.Color.BLUE, speedBoost.color);
    }
}
