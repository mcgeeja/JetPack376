import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

public class TestGameRunningKeyListener {

    @BeforeAll
    public static void setUpDefaultControlMap() {
        GameRunningKeyListener.initializeDefaultControlsMap();//this sets up a static variable
        Sound.audioEnabled=false;
    }

    KeyEvent mockedEvent;
    Player mockedPlayer;

    private void setupMockedClasses(){
        mockedEvent = EasyMock.mock(KeyEvent.class);
        mockedPlayer = EasyMock.mock(Player.class);
    }

    private void expectKeyEvent(String controlMapKey){
        EasyMock.expect(mockedEvent.getKeyCode()).andReturn(GameRunningKeyListener.controlsMap.get(controlMapKey)).anyTimes();
    }

    @Test
    public void testKeyPressedUpKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Move Up");
        mockedPlayer.setMoveUp(true);

        //replay section
        EasyMock.replay(mockedEvent, mockedPlayer);
        GameRunningKeyListener unitUnderTest = new GameRunningKeyListener(mockedPlayer);
        unitUnderTest.keyPressed(mockedEvent);

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer);
    }

    @Test
    public void testKeyPressedLeftKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Move Left");
        mockedPlayer.setMoveLeft(true);

        //replay section
        EasyMock.replay(mockedEvent, mockedPlayer);
        GameRunningKeyListener unitUnderTest = new GameRunningKeyListener(mockedPlayer);
        unitUnderTest.keyPressed(mockedEvent);

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer);
    }

    @Test
    public void testKeyPressedDownKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Move Down");
        mockedPlayer.setMoveDown(true);

        //replay section
        EasyMock.replay(mockedEvent, mockedPlayer);
        GameRunningKeyListener unitUnderTest = new GameRunningKeyListener(mockedPlayer);
        unitUnderTest.keyPressed(mockedEvent);

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer);
    }

    @Test
    public void testKeyPressedRightKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Move Right");
        mockedPlayer.setMoveRight(true);

        //replay section
        EasyMock.replay(mockedEvent, mockedPlayer);
        GameRunningKeyListener unitUnderTest = new GameRunningKeyListener(mockedPlayer);
        unitUnderTest.keyPressed(mockedEvent);

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer);
    }

    @Test
    public void testKeyPressedReloadKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Reload");
        mockedPlayer.reload();

        //replay section
        EasyMock.replay(mockedEvent, mockedPlayer);
        GameRunningKeyListener unitUnderTest = new GameRunningKeyListener(mockedPlayer);
        unitUnderTest.keyPressed(mockedEvent);

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer);
    }

    @Test
    public void testKeyPressedPickupKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Pickup");
        mockedPlayer.setPickUpItem(true);

        //replay section
        EasyMock.replay(mockedEvent, mockedPlayer);
        GameRunningKeyListener unitUnderTest = new GameRunningKeyListener(mockedPlayer);
        unitUnderTest.keyPressed(mockedEvent);

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer);
    }

    @Test
    public void testKeyReleasedUpKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Move Up");
        mockedPlayer.setMoveUp(false);

        //replay section
        EasyMock.replay(mockedEvent, mockedPlayer);
        GameRunningKeyListener unitUnderTest = new GameRunningKeyListener(mockedPlayer);
        unitUnderTest.keyReleased(mockedEvent);

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer);
    }

    @Test
    public void testKeyReleasedLeftKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Move Left");
        mockedPlayer.setMoveLeft(false);

        //replay section
        EasyMock.replay(mockedEvent, mockedPlayer);
        GameRunningKeyListener unitUnderTest = new GameRunningKeyListener(mockedPlayer);
        unitUnderTest.keyReleased(mockedEvent);

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer);
    }

    @Test
    public void testKeyReleasedDownKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Move Down");
        mockedPlayer.setMoveDown(false);

        //replay section
        EasyMock.replay(mockedEvent, mockedPlayer);
        GameRunningKeyListener unitUnderTest = new GameRunningKeyListener(mockedPlayer);
        unitUnderTest.keyReleased(mockedEvent);

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer);
    }

    @Test
    public void testKeyReleasedRightKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Move Right");
        mockedPlayer.setMoveRight(false);

        //replay section
        EasyMock.replay(mockedEvent, mockedPlayer);
        GameRunningKeyListener unitUnderTest = new GameRunningKeyListener(mockedPlayer);
        unitUnderTest.keyReleased(mockedEvent);

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer);
    }

    @Test
    public void testKeyReleasedPickupKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Pickup");
        mockedPlayer.setPickUpItem(false);

        //replay section
        EasyMock.replay(mockedEvent, mockedPlayer);
        GameRunningKeyListener unitUnderTest = new GameRunningKeyListener(mockedPlayer);
        unitUnderTest.keyReleased(mockedEvent);

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer);
    }
}
