package other;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import controller.GameController;

public class ButtonListener implements ActionListener {

	GameController g = null;
	int numPlayers;
	JFrame fs;
	
	public ButtonListener(GameController g, int numPlayers, JFrame f) {
		this.g = g;
		this.numPlayers = numPlayers;
		this.fs = f;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		fs.setVisible(false);
		g.insertCards();
		g.shuffleCards();
		g.generateFrames(this.numPlayers);
	}

}
