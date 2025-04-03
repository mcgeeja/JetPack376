import static org.junit.Assert.assertTrue;

import java.awt.Graphics2D;

import org.junit.Test;


public class TestGameObject {
	public class TestObject extends GameObject{

		public TestObject(int x, int y, int width, int height) {
			super(x, y, width, height);
		}

		@Override
		public void drawOn(Graphics2D g) {
			System.out.println("Don't need to test drawing");
		}
		
	}
	@Test
	public void testGameObjectIntersects() {
		TestObject t1 = new TestObject(0,0,100,100);
	}
	
}
