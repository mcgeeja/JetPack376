package main;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;

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
	    
	    Set<String> labels= GameRunningKeyListener.controlsMap.keySet();
	    
	    for (String text : labels) {
	        panel.add(new JLabel(text + " :")); // Label
	    	JButton button = new JButton(KeyEvent.getKeyText(GameRunningKeyListener.controlsMap.get(text)));
	    	
	        panel.add(button); // Button
	        button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    button.setText("Waiting For Key Input!");
                    button.requestFocusInWindow();
                    
                    button.addKeyListener(new KeyListener() {

        				@Override
        				public void keyTyped(KeyEvent e) {
        					// TODO Auto-generated method stub
        					
        				}

        				@Override
        				public void keyPressed(KeyEvent e) {
        					// TODO Auto-generated method stub
        					GameRunningKeyListener.controlsMap.put(text, e.getKeyCode());
        					button.setText(KeyEvent.getKeyText(GameRunningKeyListener.controlsMap.get(text)));
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
