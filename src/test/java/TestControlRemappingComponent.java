import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Map.Entry;
import java.util.Set;

public class TestControlRemappingComponent {
    @BeforeAll
    static void setUpBeforeClass() {
        GameRunningKeyListener.initializeDefaultControlsMap();
        Sound.audioEnabled=false;
    }

    @Test
    void testControlRemappingComponent() {
        //setup
        ControlRemappingComponent controlRemappingComponent = new ControlRemappingComponent();
        int testKeyCode = KeyEvent.VK_E;
        Entry<String,Integer> mockedKeyEntry = EasyMock.mock(Entry.class);
        JButton mockedButton = EasyMock.mock(JButton.class);
        //record
        EasyMock.expect(mockedKeyEntry.setValue(testKeyCode)).andReturn(testKeyCode);
        mockedButton.setText(KeyEvent.getKeyText(testKeyCode));

        //replay
        EasyMock.replay(mockedKeyEntry, mockedButton);
        controlRemappingComponent.changeKeyMapping(mockedKeyEntry, mockedButton, testKeyCode);

        //verify
        EasyMock.verify(mockedKeyEntry, mockedButton);

    }
}
