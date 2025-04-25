import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestInteractable {
	@Test
	public void testInteractMethod() {
		AmmoCrate a = new AmmoCrate(1,1);
		Player p = new Player(1,1, 5);
		p.bulletCount = 2;
		a.interact(p);
		assertEquals(p.bulletCount, 25);
		assertEquals(p.reserveAmmo, 75);
		
	}
}
