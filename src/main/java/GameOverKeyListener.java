import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameOverKeyListener extends KeyAdapter {

	private static int selectLevelOneKey = KeyEvent.VK_1;
	private static int selectLevelTwoKey = KeyEvent.VK_2;

	
	public GameOverKeyListener() {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == selectLevelOneKey) {
			Main.startGameLevelOne();
		}

		if (e.getKeyCode() == selectLevelTwoKey) {
			Main.startGameLevelTwo();
		}
	}
}
