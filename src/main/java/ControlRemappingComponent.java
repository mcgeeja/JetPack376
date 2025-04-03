import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ControlRemappingComponent extends JComponent{
	protected JPanel panel;

	public ControlRemappingComponent() {
		// Create a panel with a grid layout
	    panel = new JPanel();
	    panel.setLayout(new GridLayout(0, 2, 10, 10)); // Two columns: one for labels, one for buttons

		Set<Entry<String, Integer>> controlsSet = GameRunningKeyListener.controlsMap.entrySet();
		for (Entry<String, Integer> entry : controlsSet) {
			panel.add(new JLabel(entry.getKey() + " :")); // Label
			JButton button = new JButton(KeyEvent.getKeyText(entry.getValue()));

			panel.add(button); // Button
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//when button pressed indicate button that is waiting for key Input
					button.setText("Waiting For Key Input!");
					//set this button as focus so the key inputs will definitely be directed at the button
					button.requestFocusInWindow();

					// get new button mapping using the key listener
					// (also only creates key listener inside the button action handler meaning it will only listen for key input after being pressed)
					button.addKeyListener(new KeyListener() {

						@Override
						public void keyTyped(KeyEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void keyPressed(KeyEvent e) {
							entry.setValue(e.getKeyCode());
							button.setText(KeyEvent.getKeyText(GameRunningKeyListener.controlsMap.get(entry.getKey())));
							button.removeKeyListener(this);
						}

						@Override
						public void keyReleased(KeyEvent e) {
							// TODO Auto-generated method stub

						}

					});
				}
			});

		}
	}
	
}
