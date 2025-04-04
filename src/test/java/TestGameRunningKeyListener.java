import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

public class TestGameRunningKeyListener {

    @BeforeAll
    public static void setUpDefaultControlMap() {
        GameRunningKeyListener.initializeDefaultControlsMap();//this sets up a static variable
    }

    KeyEvent mockedEvent;
    Player mockedPlayer;
    MyComponent mockedComponent;

    private void setupMockedClasses(){
        mockedEvent = EasyMock.mock(KeyEvent.class);
        mockedPlayer = EasyMock.mock(Player.class);
        mockedComponent = EasyMock.mock(MyComponent.class);
    }

    private void expectKeyEvent(String controlMapKey){
        EasyMock.expect(mockedEvent.getKeyCode()).andReturn(GameRunningKeyListener.controlsMap.get(controlMapKey)).anyTimes();
    }

    private void replayAndConstructUUT(){
        EasyMock.replay(mockedEvent, mockedPlayer, mockedComponent);
        GameRunningKeyListener unitUnderTest = new GameRunningKeyListener(mockedComponent, mockedPlayer);
        unitUnderTest.keyPressed(mockedEvent);
    }

    @Test
    public void testKeyPressedUpKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Move Up");
        mockedPlayer.moveUpKeyPressResponse();

        //replay section
        replayAndConstructUUT();

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer, mockedComponent);
    }

    @Test
    public void testKeyPressedLeftKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Move Left");
        mockedPlayer.moveLeftKeyPressResponse();

        //replay section
        replayAndConstructUUT();

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer, mockedComponent);
    }

    @Test
    public void testKeyPressedDownKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Move Down");
        mockedPlayer.moveDownKeyPressResponse();

        //replay section
        replayAndConstructUUT();

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer, mockedComponent);
    }

    @Test
    public void testKeyPressedRightKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Move Right");
        mockedPlayer.moveRightKeyPressResponse();

        //replay section
        replayAndConstructUUT();

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer, mockedComponent);
    }

    @Test
    public void testKeyPressedShootKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Shoot");
        mockedPlayer.shootKeyPressResponse();

        //replay section
        replayAndConstructUUT();

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer, mockedComponent);
    }

    @Test
    public void testKeyPressedReloadKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Reload");
        mockedPlayer.reloadKeyPressResponse();

        //replay section
        replayAndConstructUUT();

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer, mockedComponent);
    }

    @Test
    public void testKeyPressedPickupKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Pickup");
        mockedPlayer.pickupKeyPressResponse();

        //replay section
        replayAndConstructUUT();

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer, mockedComponent);
    }

    @Test
    public void testKeyPressedChangeToNextLevelKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Next Level");
        mockedComponent.changeToNextLevelKeyPressResponse();

        //replay section
        replayAndConstructUUT();

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer, mockedComponent);
    }

    @Test
    public void testKeyPressedChangeToPreviousLevelKey() {
        //setup
        setupMockedClasses();

        //record section
        expectKeyEvent("Previous Level");
        mockedComponent.changeToPreviousLevelKeyPressResponse();

        //replay section
        replayAndConstructUUT();

        //verify
        EasyMock.verify(mockedEvent, mockedPlayer, mockedComponent);
    }
}
