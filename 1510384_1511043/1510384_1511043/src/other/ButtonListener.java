package other;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.GameController;

public class ButtonListener implements ActionListener {

	GameController g = null;
	int numPlayers;
	
	public ButtonListener(GameController g, int numPlayers) {
		this.g = g;
		this.numPlayers = numPlayers;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		g.generateFrames(this.numPlayers);
	}

}
