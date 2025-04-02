package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameRunningKeyListener implements KeyListener {
		private static int moveRightKey =  KeyEvent.VK_RIGHT;
		private static int moveLeftKey =  KeyEvent.VK_LEFT;
		private static int downKey = KeyEvent.VK_DOWN;//I don't know if this is a duck or a move downwards key so we'll see and rename later
		private static int moveUpKey = KeyEvent.VK_UP;
		
		private static int shootKey = KeyEvent.VK_SPACE;
		private static int reloadKey = KeyEvent.VK_R;
		private static int pickupKey = KeyEvent.VK_X;
		
		private static int changeToNextLevelKey = KeyEvent.VK_U;
		private static int changeToPreviousLevelKey = KeyEvent.VK_D;
		
		private MyComponent component;
	
	
		public GameRunningKeyListener(MyComponent myComponent) {
			this.component = myComponent;
		}
		
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == moveRightKey) {
				component.moveRightKeyPressResponse();
			}
			
			if (e.getKeyCode() == moveLeftKey) {
				component.moveLeftKeyPressResponse();
			}
			
			if(e.getKeyCode() == shootKey) {
				component.shootKeyPressResponse();
        	}
			
			if(e.getKeyCode() == reloadKey) {
				component.reloadKeyPressResponse();
        	}
			
			if(e.getKeyCode() == pickupKey) {
            	component.pickupKeyPressResponse();
            }
			
			if (e.getKeyCode() == moveUpKey) {
				component.moveUpKeyPressResponse();
			}
			
			if (e.getKeyCode() == downKey) {
				component.moveDownKeyPressResponse();
			}
			
			if (e.getKeyCode() ==  changeToNextLevelKey) {
				component.changeToNextLevelKeyPressResponse();
			}
			
			if (e.getKeyCode() == changeToPreviousLevelKey ) {
				component.changeToPreviousLevelKeyPressResponse();
			}
			
		}
		
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == moveRightKey) {
				component.moveRightKeyReleaseResponse();
        	}
			
			if (e.getKeyCode() == moveLeftKey) {
				component.moveLeftKeyReleaseResponse();
	        }
			
	        if(e.getKeyCode() == moveUpKey) {
	        	component.moveUpKeyReleaseResponse();
	        }
	        
	        if(e.getKeyCode() == downKey) {
	        	component.moveDownKeyReleaseResponse();
	        }
	        
	        if(e.getKeyCode() == pickupKey) {
	        	component.pickupKeyReleaseResponse();
	        }
		}
		
		public void keyTyped(KeyEvent e) {
			
		}
			
}
