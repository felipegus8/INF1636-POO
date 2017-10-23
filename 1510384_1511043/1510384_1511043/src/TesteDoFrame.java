import java.awt.Image;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

import javax.imageio.ImageIO;

public class TesteDoFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Image i = null;
		
		try {
			i = ImageIO.read(new File("/Users/victornogueira/Documents/INF1636-POO/1510384_1511043/1510384_1511043/Resources/blackjackBKG.png"));
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}

		Frame f = new Frame();

		f.setTitle("Teste Frame");
		f.setContentPane(new ImagePanel(i));
		f.setVisible(true);
	}

}
