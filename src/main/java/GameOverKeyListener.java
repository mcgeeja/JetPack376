import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameOverKeyListener implements KeyListener {

	private static int selectLevelOneKey = KeyEvent.VK_1;
	private static int selectLevelTwoKey = KeyEvent.VK_2;
	private static int exitKey = KeyEvent.VK_X;
	
	private final MyComponent component;
	
	public GameOverKeyListener(MyComponent component) {
		this.component = component;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
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
		
		if (e.getKeyCode() == exitKey) {
//			component
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
