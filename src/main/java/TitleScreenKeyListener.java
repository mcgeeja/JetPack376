import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TitleScreenKeyListener extends KeyAdapter {

    private static int selectPlayerTypeOneKey = KeyEvent.VK_1;
    private static int selectPlayerTypeTwoKey = KeyEvent.VK_2;
    private static int controlRemappingMenuKey = KeyEvent.VK_BACK_SPACE;


    public TitleScreenKeyListener() {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyCode() == selectPlayerTypeOneKey) {
            Main.startGameWithPlayerTypeOne();
        }

        if (e.getKeyCode() == selectPlayerTypeTwoKey) {
            Main.startGameWithPlayerTypeTwo();
        }

        if (e.getKeyCode() == controlRemappingMenuKey) {
            Main.showControlRemappingMenu();
        }
    }
}
