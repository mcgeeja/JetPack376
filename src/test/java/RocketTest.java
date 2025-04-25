import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

public class RocketTest {

    @Test
    public void testPickedUp_SuccessfulPickup() {
        Rocket rocket = new BottomRocketPiece(106, 105);
        Player player = new Player(100, 100, 5);  // Overlapping position
        rocket.interact(player);

        assertEquals(player.x, rocket.x);
        assertEquals(player.y, rocket.y);
    }

@Test
public void testPickedUp_CooldownBlocksPickup() {
    Rocket rocket = new BottomRocketPiece(100, 110);

    // Use subclass to override cooldown method
    Player mockPlayer = new Player(150, 100, 5) {
        @Override
        public int getPickUpCooldown() {
            return 5; // Simulate active cooldown
        }
    };

    rocket.interact(mockPlayer);

    // Should NOT move to player's position
    assertNotEquals(mockPlayer.x, rocket.x);
    assertNotEquals(mockPlayer.y, rocket.y);
}


    @Test
    public void testGravity_NoPlatformCollision() {
        Rocket rocket = new BottomRocketPiece(100, 100);
        ArrayList<Platform> platforms = new ArrayList<>();

        // Rocket will fall with gravity (y + 5)
        rocket.gravity(platforms);
        assertEquals(105, rocket.y);
    }

    @Test
    public void testGravity_HitsPlatformAndStops() {
        Rocket rocket = new BottomRocketPiece(100, 100);
        Platform platform = new Platform(100, 105, 60, 10); // Touching platform just below
        ArrayList<Platform> platforms = new ArrayList<>();
        platforms.add(platform);

        rocket.gravity(platforms);

        // Should land right on top of platform
        assertEquals(platform.y - rocket.height, rocket.y);
    }
}
