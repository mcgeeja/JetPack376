import static org.junit.jupiter.api.Assertions.*;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestInteractable {
	@BeforeAll
	static void setUpBeforeClass() {
		Sound.audioEnabled=false;
	}

	boolean success = false;
	private class InteractableObject extends GameObject implements Interactable{
		
		public InteractableObject(int x, int y, int width, int height) {
			super(x, y, width, height);
		}

		@Override
		public void interact(Player player) {
			if(this.intersects(player)){
				success = true;
			}
			
		}

		@Override
		public void drawOn(Graphics2D g) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class TestPlayer extends Player{

		public TestPlayer(int x, int y, int speed) {
			super(x, y, speed);
		}
		
	}
	@Test
	public void testInteractMethod() {
		TestPlayer p = new TestPlayer(1,1, 5);
		InteractableObject ob = new InteractableObject(1,1,100,100);
		ob.interact(p);
		assertTrue(success);
		
	}
}
