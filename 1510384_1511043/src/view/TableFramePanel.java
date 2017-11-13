package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TableFramePanel extends JPanel {
	Image i = null;
	public ArrayList<Image> cardImgs = new ArrayList<Image>();
	private int posX = 0, posY = 60;
	
	
	public TableFramePanel(Image i) {
		this.i = i;
	}
	public void paintCard(String cardImageStr) {
		Graphics g = this.getGraphics();
		Image i = null;
		
		System.out.println(cardImageStr);
		
		try {
			i = ImageIO.read(getClass().getResourceAsStream("/" + cardImageStr + ".gif"));
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		cardImgs.add(i);
		//g.drawImage(i, posX, posY, 50, 50, null);
		//posX += 50;
	
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(i, 0, 0, null);
		
		posX = 400;
		posY = 160;
		for(Image img: cardImgs) {
			System.out.println("passei aqui");
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
