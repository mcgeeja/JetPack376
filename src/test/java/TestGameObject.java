import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Graphics2D;

import org.junit.jupiter.api.Test;

public class TestGameObject {
	public boolean ranDrawOn = false;
	
	public class TestObject extends GameObject{
		
		public TestObject(int x, int y, int width, int height) {
			super(x, y, width, height);
		}

		@Override
		public void drawOn(Graphics2D g) {
			ranDrawOn = true;
		}

		
	}
	@Test
	public void testGameObjectIntersectsTrue() {
		TestObject t1 = new TestObject(0,0,100,100);
		TestObject t2 = new TestObject(0,0,100,100);
		assertTrue(t1.intersects(t2));
	}
	@Test
	public void testGameObjectIntersectsFalse() {
		TestObject t1 = new TestObject(0,0,100,100);
		TestObject t2 = new TestObject(200,200,100,100);
		assertFalse(t1.intersects(t2));
	}
	@Test
	public void testGameObjectGameOn() {
		Graphics2D g = null;
		TestObject t1 = new TestObject(0,0,100,100);
		assertFalse(ranDrawOn);
		t1.drawOn(g);
		assertTrue(ranDrawOn);
	}
	
}
