import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class GameRunningKeyListener implements KeyListener {
		
		static Map<String, Integer> controlsMap = new HashMap<>();
		
		
		private MyComponent component;
		private Player player;
	
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
				player.moveRightKeyPressResponse();
			}
			
			if (e.getKeyCode() == controlsMap.get("Move Left")) {
				player.moveLeftKeyPressResponse();
			}
			
			if(e.getKeyCode() == controlsMap.get("Shoot")) {
				player.shootKeyPressResponse();
        	}
			
			if(e.getKeyCode() == controlsMap.get("Reload")) {
				player.reloadKeyPressResponse();
        	}
			
			if(e.getKeyCode() == controlsMap.get("Pickup")) {
            	player.pickupKeyPressResponse();
            }
			
			if (e.getKeyCode() == controlsMap.get("Move Up")) {
				player.moveUpKeyPressResponse();
			}
			
			if (e.getKeyCode() == controlsMap.get("Move Down")) {
				player.moveDownKeyPressResponse();
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
				player.moveRightKeyReleaseResponse();
        	}
			
			if (e.getKeyCode() == controlsMap.get("Move Left")) {
				player.moveLeftKeyReleaseResponse();
	        }
			
	        if(e.getKeyCode() == controlsMap.get("Move Up")) {
	        	player.moveUpKeyReleaseResponse();
	        }
	        
	        if(e.getKeyCode() == controlsMap.get("Move Down")) {
	        	player.moveDownKeyReleaseResponse();
	        }
	        
	        if(e.getKeyCode() == controlsMap.get("Pickup")) {
	        	player.pickupKeyReleaseResponse();
	        }
		}
		
		public void keyTyped(KeyEvent e) {
			
		}
			
}
