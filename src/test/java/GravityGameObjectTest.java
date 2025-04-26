import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Graphics2D;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GravityGameObjectTest {
	@BeforeAll
	static void setUpBeforeClass() {
		Sound.audioEnabled=false;
	}

	public class TestGravObject extends GravityGameObject{
		
		public TestGravObject(int x, int y, int width, int height) {
			super(x, y, width, height);
		}

		@Override
		public void drawOn(Graphics2D g) {
			// TODO Auto-generated method stub
		}
		
	}
	public class TestPlatform extends Platform{

		public TestPlatform(int x, int y, int width, int height) {
			super(x, y, width, height);
			// TODO Auto-generated constructor stub
		}
		
	}
	@Test
	public void testGravity() {
		TestGravObject t1 = new TestGravObject(0, 0, 10, 5);
		ArrayList<Platform> ps = new ArrayList<>();
		TestPlatform p1 = new TestPlatform(0, 10, 500, 500);
		ps.add(p1);
		
		assertEquals(t1.y, 0);
		t1.gravity(ps);
		assertEquals(t1.y, 5);
		t1.gravity(ps);
		assertEquals(t1.y, 5);

		

	}
}
