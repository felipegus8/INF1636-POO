package view;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GameController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class TableFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Graphics2D g;
	Image i;
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
		this.p.add(cardValue);
		restart.setEnabled(false);
		this.add(p);
		restart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
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
		restart.setEnabled(true);
		
	}
	
	public void disableRestart() {
		restart.setEnabled(false);
	}
	
}
