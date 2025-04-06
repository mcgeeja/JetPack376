import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class GameRunningKeyListener implements KeyListener {
		
		static Map<String, Integer> controlsMap = new HashMap<>();
		
		
		private final MyComponent component;
		private final Player player;

		//this is called in the main method I think there is probably a better way to set this up
		//also possible future feature is making this generate based on a controls config file
		public static void initializeDefaultControlsMap() {
			controlsMap.put("Move Up", KeyEvent.VK_UP);
			controlsMap.put("Move Left", KeyEvent.VK_LEFT);
			controlsMap.put("Move Down", KeyEvent.VK_DOWN);
			controlsMap.put("Move Right", KeyEvent.VK_RIGHT);
			
			controlsMap.put("Shoot", KeyEvent.VK_SPACE);
			controlsMap.put("Reload", KeyEvent.VK_R);
			controlsMap.put("Pickup", KeyEvent.VK_X);
			
			controlsMap.put("Next Level", KeyEvent.VK_U);
			controlsMap.put("Previous Level", KeyEvent.VK_D);
		}
		
	
		public GameRunningKeyListener(MyComponent myComponent, Player player) {
			this.component = myComponent;
			this.player = player;
		}
		
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == controlsMap.get("Move Right")) {
				player.setMoveRight(true);
			}
			
			if (e.getKeyCode() == controlsMap.get("Move Left")) {
				player.setMoveLeft(true);
			}
			
			if(e.getKeyCode() == controlsMap.get("Shoot")) {
				player.shoot();
        	}
			
			if(e.getKeyCode() == controlsMap.get("Reload")) {
				player.reload();
        	}
			
			if(e.getKeyCode() == controlsMap.get("Pickup")) {
				player.setPickUpItem(true);
            }
			
			if (e.getKeyCode() == controlsMap.get("Move Up")) {
				player.setMoveUp(true);
			}
			
			if (e.getKeyCode() == controlsMap.get("Move Down")) {
				player.setMoveDown(true);
			}
			
			if (e.getKeyCode() ==  controlsMap.get("Next Level")) {
				component.changeToNextLevelKeyPressResponse();
			}
			
			if (e.getKeyCode() == controlsMap.get("Previous Level") ) {
				component.changeToPreviousLevelKeyPressResponse();
			}
			
		}
		
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == controlsMap.get("Move Right")) {
				player.setMoveRight(false);
        	}
			
			if (e.getKeyCode() == controlsMap.get("Move Left")) {
				player.setMoveLeft(false);
	        }
			
	        if(e.getKeyCode() == controlsMap.get("Move Up")) {
	        	player.setMoveUp(false);
	        }
	        
	        if(e.getKeyCode() == controlsMap.get("Move Down")) {
	        	player.setMoveDown(false);
	        }
	        
	        if(e.getKeyCode() == controlsMap.get("Pickup")) {
	        	player.setPickUpItem(false);
	        }
		}
		
		public void keyTyped(KeyEvent e) {
			
		}
			
}
