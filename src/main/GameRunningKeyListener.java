package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameRunningKeyListener implements KeyListener {
//		private static int moveUpKey = KeyEvent.VK_UP;
//		private static int moveLeftKey =  KeyEvent.VK_LEFT;
//		private static int downKey = KeyEvent.VK_DOWN;//I don't know if this is a duck or a move downwards key so we'll see and rename later
//		private static int moveRightKey =  KeyEvent.VK_RIGHT;
		
		
//		private static int shootKey = KeyEvent.VK_SPACE;
//		private static int reloadKey = KeyEvent.VK_R;
//		private static int pickupKey = KeyEvent.VK_X;
		
//		private static int changeToNextLevelKey = KeyEvent.VK_U;
//		private static int changeToPreviousLevelKey = KeyEvent.VK_D;
		
		
		static Map<String, Integer> controlsMap = new HashMap<>();
		
		
		private MyComponent component;
	
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
	
		public GameRunningKeyListener(MyComponent myComponent) {
			this.component = myComponent;
		}
		
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == controlsMap.get("Move Right")) {
				component.player.moveRightKeyPressResponse();
			}
			
			if (e.getKeyCode() == controlsMap.get("Move Left")) {
				component.player.moveLeftKeyPressResponse();
			}
			
			if(e.getKeyCode() == controlsMap.get("Shoot")) {
				component.player.shootKeyPressResponse();
        	}
			
			if(e.getKeyCode() == controlsMap.get("Reload")) {
				component.player.reloadKeyPressResponse();
        	}
			
			if(e.getKeyCode() == controlsMap.get("Pickup")) {
            	component.player.pickupKeyPressResponse();
            }
			
			if (e.getKeyCode() == controlsMap.get("Move Up")) {
				component.player.moveUpKeyPressResponse();
			}
			
			if (e.getKeyCode() == controlsMap.get("Move Down")) {
				component.player.moveDownKeyPressResponse();
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
				component.player.moveRightKeyReleaseResponse();
        	}
			
			if (e.getKeyCode() == controlsMap.get("Move Left")) {
				component.player.moveLeftKeyReleaseResponse();
	        }
			
	        if(e.getKeyCode() == controlsMap.get("Move Up")) {
	        	component.player.moveUpKeyReleaseResponse();
	        }
	        
	        if(e.getKeyCode() == controlsMap.get("Move Down")) {
	        	component.player.moveDownKeyReleaseResponse();
	        }
	        
	        if(e.getKeyCode() == controlsMap.get("Pickup")) {
	        	component.player.pickupKeyReleaseResponse();
	        }
		}
		
		public void keyTyped(KeyEvent e) {
			
		}
			
}
