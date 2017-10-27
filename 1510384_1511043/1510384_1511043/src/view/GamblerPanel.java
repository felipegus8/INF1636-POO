package view;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import other.Suits;

import model.Card;

public class GamblerPanel extends JPanel {
	private int posX = 0, posY = 60;
	
	public ArrayList<Image> cardImgs = new ArrayList<Image>();
	
	public GamblerPanel() {
		
	}
	
	public void drawCard(String cardImageStr) {
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
		super.paintComponent(g);
		posX = 0;
		posY = 40;
		for(Image img: cardImgs) {
			g.drawImage(img, posX, posY, 50, 50, null);
			System.out.println("size xaxando " + this.getSize().width);
			System.out.println("POSX:" + posX);
			int mito = this.getSize().width;
			if (mito < posX){
				System.out.println("POSX:" + posX);
				System.out.println("MITO:" + mito);
				System.out.println("POSY:" + posY);
				posX = 0;
				posY += 50;
			}
			else {
				System.out.println("POSX:" + posX);
			//	posY = 40;
				posX += 50;
			}
		}
		
		
	}
	
}
