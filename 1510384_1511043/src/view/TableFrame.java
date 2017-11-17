package view;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.GameController;
import other.ObservadoIF;
import other.ObservadorIF;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

public class TableFrame extends JFrame implements MouseListener, ObservadoIF{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Graphics2D g;
	Image i;
	
	private ArrayList<ObservadorIF> observers = new ArrayList<ObservadorIF>();

	public TableFramePanel p = null;
	private JButton restart = new JButton("RESTART");
	public JLabel cardValue = new JLabel("0");
	public GameController g = null;
	
	
	int posX, posY;
	
	public final int LARG_DEFAULT=891; public final int ALT_DEFAULT=400;
	
	
	
	public TableFrame(double posX,double posY) {
		
		this.addMouseListener(this);
		
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
		this.cardValue.setLocation(50,20);
		this.cardValue.setForeground(Color.white);
		this.cardValue.setFont(new Font("Helvetica", Font.PLAIN, 30));
		this.cardValue.setVisible(false);
		this.p.add(cardValue);
		restart.setEnabled(false);
		this.add(p);
		restart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardValue.setVisible(false);
				g.restartGame();		
			}
			
			
		});
		this.p.add(restart);
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Table");
		this.setVisible(true);		
		
	}
	
	public void enableRestart() {
		this.cardValue.setVisible(true);
		restart.setEnabled(true);
		
	}
	
	public void disableRestart() {
		restart.setEnabled(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		Point p = e.getLocationOnScreen();
//		if (p.getX() > 750 && p.getX() < 810 && p.getY() > 200 && p.getY() < 260) {
//			System.out.println("FICHA 1");
//		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	//	System.out.println("funcionou por algum motivo");
		System.out.println(e.getX());
		System.out.println(e.getY());
//		if (e.getX() > 725 && e.getX() < 775 && e.getY() > 200 && e.getY() < 250) {
//			System.out.println("FICHA 1");
//		}
//		
//		if (e.getX() > 785 && e.getX() < 835 && e.getY() > 200 && e.getY() < 250) {
//			System.out.println("FICHA 5");
//		}
//		
//		if (e.getX() > 725 && e.getX() < 775 && e.getY() > 260 && e.getY() < 310) {
//			System.out.println("FICHA 10");
//		}
//		
//		if (e.getX() > 785 && e.getX() < 835 && e.getY() > 260 && e.getY() < 310) {
//			System.out.println("FICHA 20");
//		}
//		
//		if (e.getX() > 725 && e.getX() < 775 && e.getY() > 320 && e.getY() < 370) {
//			System.out.println("FICHA 50");
//		}
//		
//		if (e.getX() > 785 && e.getX() < 835 && e.getY() > 320 && e.getY() < 370) {
//			System.out.println("FICHA 100");
//		}
	
		

		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void registerObserver(ObservadorIF observer) {
		observers.add(observer);
		
	}

	@Override
	public void removeObserver(ObservadorIF observer) {
		for(ObservadorIF o: observers) {
			if(o == observer) {
				observers.remove(o);
			}
		}
		
	}

	@Override
	public void notifyObservers(String mensagem, Object obj) {
		// TODO Auto-generated method stub
		
	}

	
}
