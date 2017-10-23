package view;

import javax.swing.JFrame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class TableFrame extends JFrame {
	Graphics2D g;
	Image i;
	
	public final int LARG_DEFAULT=891; public final int ALT_DEFAULT=707;
	
	public TableFrame(Image param) {
		this.i = param;
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void paint(Graphics g) {
		g.drawImage(i, 0, 0, LARG_DEFAULT, ALT_DEFAULT, null);
	}

	
}
