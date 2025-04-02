package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class GameAdvanceListener implements ActionListener {

	private MyComponent component;

	public GameAdvanceListener(MyComponent component) {
		this.component = component;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			advanceOneTick();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void advanceOneTick() throws FileNotFoundException {
		this.component.repaint();
		this.component.repaint();
		this.component.updateState();
	}

}
