import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEdgeHandling {
    @Test
    public void HandleLeftEdge (){
        GameObject obj = new GameObject(1,1,10,10) {
            @Override
            public void drawOn(Graphics2D g) {

            }

            @Override
            public void leftEdgeHit() {
                x = 1920-10;
            }
        };
        obj.leftEdgeHit();
        assertEquals(1920-10, obj.x);
    }
    public void HandleRightEdge() {
        GameObject obj = new GameObject(1919, 1, 10, 10) {
            @Override
            public void drawOn(Graphics2D g) {

            }

            @Override
            public void rightEdgeHit() {
                x = 0;
            }
        };
        obj.rightEdgeHit();
        assertEquals(0, obj.x);
    }
    }
