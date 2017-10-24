package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class TableFramePanel extends JPanel {
	Image i = null;
	
	public TableFramePanel(Image i) {
		this.i = i;
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(i, 0, 0, null);
	}
}
