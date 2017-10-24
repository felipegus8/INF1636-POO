package view;

import javax.swing.JFrame;


import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import controller.GameController;
import other.ButtonListener;

public class FirstScreen extends JFrame {
	
	JButton onePlayer = new JButton("1");
	JButton twoPlayers = new JButton("2");
	JButton threePlayers = new JButton("3");
	JButton fourPlayers = new JButton("4");
	
	JPanel p = new JPanel();
	
	GameController g;
	
	private final int LARG_DEFAULT=891; public final int ALT_DEFAULT=707;
	
	public FirstScreen(GameController g) {
		
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		p.add(onePlayer);
		p.add(twoPlayers);
		p.add(threePlayers);
		p.add(fourPlayers);
		
		this.g= g;
		
		onePlayer.addActionListener(new ButtonListener(g,1,this));
		twoPlayers.addActionListener(new ButtonListener(g,2,this));
		threePlayers.addActionListener(new ButtonListener(g,3,this));
		fourPlayers.addActionListener(new ButtonListener(g,4,this));
		
		this.add(p);
		
		
	}
}
