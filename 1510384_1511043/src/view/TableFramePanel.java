package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TableFramePanel extends JPanel {
	Image i = null;
	private ArrayList<Image> cardImgs = new ArrayList<Image>();
	private int posX = 0, posY = 60;
	
	
	public TableFramePanel(Image i) {
		this.i = i;
	}
		
	public void clearImgs() {
		cardImgs.clear();
	}
	
	public void paintCard(String cardImageStr) {
		Graphics g = this.getGraphics();
		Image i = null;		
		try {
			i = ImageIO.read(getClass().getResourceAsStream("/" + cardImageStr + ".gif"));
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		cardImgs.add(i);
	
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(i, 0, 0, null);
		Image i = null;
		try {
			i = ImageIO.read(getClass().getResourceAsStream("/ficha_1.png"));
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		g.drawImage(i,750,200,50,50, null);
		try {
			i = ImageIO.read(getClass().getResourceAsStream("/ficha_5.png"));
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		g.drawImage(i,810,200,50,50, null);
		try {
			i = ImageIO.read(getClass().getResourceAsStream("/ficha_10.png"));
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		g.drawImage(i,750,260,50,50, null);
		try {
			i = ImageIO.read(getClass().getResourceAsStream("/ficha_20.png"));
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		g.drawImage(i,810,260,50,50, null);
		try {
			i = ImageIO.read(getClass().getResourceAsStream("/ficha_50.png"));
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		g.drawImage(i,750,320,50,50, null);
		try {
			i = ImageIO.read(getClass().getResourceAsStream("/ficha_100.png"));
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		g.drawImage(i,810,320,50,50, null);
		posX = 400;
		posY = 160;
		for(Image img: cardImgs) {
			g.drawImage(img, posX, posY, 50, 50, null);
			int panelSize = this.getSize().width;
			if (panelSize < posX){
				posX = 0;
				posY += 50;
			}
			else {
			//	posY = 40;
				posX += 50;
			}
		}
	}
}
