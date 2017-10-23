package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Point;

import javax.swing.JButton;

public class PlayerFrame extends JFrame{	
	JButton hit = new JButton("HIT");
	JButton stand = new JButton("STAND");
	
	JPanel p = new JPanel();
	
	public final int LARG_DEFAULT=300; public final int ALT_DEFAULT=300;
	
	public PlayerFrame(double x, double y,double posX, double posY) {
		setSize((int) x,(int) y);
		Point po = new Point((int) posX,(int) posY);
		this.setLocation(po);
		this.add(p);
		p.add(hit);
		p.add(stand);
		this.setTitle("teste");
		this.setVisible(true);
	}
}
