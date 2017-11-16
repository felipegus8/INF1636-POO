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
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
