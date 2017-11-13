package view;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

public class TableFrame extends JFrame {
	//Graphics2D g;
	Image i;
	public TableFramePanel p = null;
	int posX, posY;
	
	public final int LARG_DEFAULT=891; public final int ALT_DEFAULT=400;
	
	
	
	public TableFrame(double posX,double posY) {
		
		try {
			i = ImageIO.read(getClass().getResourceAsStream("/blackjackBKG.png"));
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		p = new TableFramePanel(i);
		
		this.posX = (int)posX;
		this.posY = (int)posY;
		Point po = new Point(this.posX,this.posY);
		setLocation(po);
		this.add(p);
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Table");
		this.setVisible(true);
	}
	
}
