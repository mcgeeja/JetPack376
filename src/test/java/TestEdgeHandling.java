import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEdgeHandling {
    @Test
    public void HandleLeftEdge (){
        GameObject obj = new Player(1,1,1) {
        };
        obj.leftEdgeHit();
        assertEquals(1900, obj.x);
    }
    public void HandleRightEdge() {
        GameObject obj = new Player(1,1,1) ;
        obj.rightEdgeHit();
        assertEquals(10, obj.x);
    }
    }
