import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class GameRunningKeyListener extends KeyAdapter {
		
		static Map<String, Integer> controlsMap = new HashMap<>();

		private final Player player;

		//this is called in the main method I think there is probably a better way to set this up
		//also possible future feature is making this generate based on a controls config file
		public static void initializeDefaultControlsMap() {
			controlsMap.put("Move Up", KeyEvent.VK_UP);
			controlsMap.put("Move Left", KeyEvent.VK_LEFT);
			controlsMap.put("Move Down", KeyEvent.VK_DOWN);
			controlsMap.put("Move Right", KeyEvent.VK_RIGHT);

			controlsMap.put("Reload", KeyEvent.VK_R);
			controlsMap.put("Pickup", KeyEvent.VK_X);
		}
		
	
		public GameRunningKeyListener(Player player) {
			this.player = player;
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == controlsMap.get("Move Right")) {
				player.setMoveRight(true);
			}
			
			if (e.getKeyCode() == controlsMap.get("Move Left")) {
				player.setMoveLeft(true);
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
			
		}

		@Override
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
			
}
