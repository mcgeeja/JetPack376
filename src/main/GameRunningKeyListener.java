package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameRunningKeyListener implements KeyListener {
		private int moveRightKey =  KeyEvent.VK_RIGHT;
		private int moveLeftKey =  KeyEvent.VK_LEFT;
		private int downKey = KeyEvent.VK_DOWN;//I don't know if this is a duck or a move downwards key so we'll see and rename later
		private int moveUpKey = KeyEvent.VK_UP;
		
		private int shootKey = KeyEvent.VK_SPACE;
		private int reloadKey = KeyEvent.VK_R;
		private int pickupKey = KeyEvent.VK_X;
		
		private int changeToNextLevelKey = KeyEvent.VK_U;
		private int changeToPreviousLevelKey = KeyEvent.VK_D;
		
		private int selectLevelOneKey = KeyEvent.VK_1;
		private int selectLevelTwoKey = KeyEvent.VK_2;
		
		
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
			
			if (e.getKeyCode() == selectLevelTwoKey) {
				component.selectLevelTwoKeyPressResponse();
			}
			
			if (e.getKeyCode() == selectLevelOneKey) {
				component.selectLevelOneKeyPressResponse();
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
