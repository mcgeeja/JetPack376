import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameOverKeyListener extends KeyAdapter {

	private static int selectLevelOneKey = KeyEvent.VK_1;
	private static int selectLevelTwoKey = KeyEvent.VK_2;
	
	private final MyComponent component;
	
	public GameOverKeyListener(MyComponent component) {
		this.component = component;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == selectLevelTwoKey) {
			component.selectLevelTwoKeyPressResponse();
		}
		
		if (e.getKeyCode() == selectLevelOneKey) {
			component.selectLevelOneKeyPressResponse();
		}
	}
}
