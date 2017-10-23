package other;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import view.TableFrame;
import javax.imageio.ImageIO;
import view.PlayerFrame;
import controller.GameController;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				Image i = null;
				
				try {
					i = ImageIO.read(new File("/Users/victornogueira/Documents/INF1636-POO/1510384_1511043/1510384_1511043/Resources/blackjackBKG.png"));
				} catch(IOException e) {
					System.out.println(e.getMessage());
					System.exit(1);
				}

				GameController g = new GameController();

	}

}
