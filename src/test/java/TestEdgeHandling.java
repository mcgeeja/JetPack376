import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEdgeHandling {
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
    @Test
    public void HandleLeftEdge (){
        GameObject obj = new TestPlayer(1,1,1) {
        };
        obj.leftEdgeHit();
        assertEquals(1900, obj.x);
    }
    public void HandleRightEdge() {
        GameObject obj = new TestPlayer(1,1,1) ;
        obj.rightEdgeHit();
        assertEquals(10, obj.x);
    }
    }
