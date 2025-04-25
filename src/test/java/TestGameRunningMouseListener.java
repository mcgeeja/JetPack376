import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.awt.event.MouseEvent;

public class TestGameRunningMouseListener {

    @Test
    public void testMouseClickedShoots() {
        //setup
        MouseEvent mockedEvent = EasyMock.mock(MouseEvent.class);
        Player mockedPlayer = EasyMock.mock(Player.class);

        //record section
        EasyMock.expect(mockedEvent.getButton()).andReturn(MouseEvent.BUTTON1);
        mockedPlayer.shoot();

        //replay section
        EasyMock.replay(mockedEvent, mockedPlayer);
        GameRunningMouseListener unitUnderTest = new GameRunningMouseListener(mockedPlayer);
        unitUnderTest.mousePressed(mockedEvent);

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer);
    }

    @Test
    public void testMouseMovedSetsDirection() {
        //setup
        MouseEvent mockedEvent = EasyMock.mock(MouseEvent.class);
        Player mockedPlayer = EasyMock.mock(Player.class);

        //record section
        EasyMock.expect(mockedEvent.getX()).andReturn(0);
        mockedPlayer.setDirectionToFace(0);

        //replay section
        EasyMock.replay(mockedEvent, mockedPlayer);
        GameRunningMouseListener unitUnderTest = new GameRunningMouseListener(mockedPlayer);
        unitUnderTest.mousePressed(mockedEvent);

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer);
    }
}
