package view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.GamblerController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import java.awt.Point;

import javax.swing.JButton;

public class GamblerFrame extends JFrame{	
	JButton hit = new JButton("HIT");
	JButton stand = new JButton("STAND");
	JLabel cardValue = new JLabel("0");
	JLabel stopHit = new JLabel("BUSTED");
	public GamblerPanel p = new GamblerPanel();
	public GamblerController gc = null;
	public final int LARG_DEFAULT=300; public final int ALT_DEFAULT=300;
	public boolean isStanded = false;
	public GamblerFrame(double x, double y,double posX, double posY) {
		setSize((int) x,(int) y);
		Point po = new Point((int) posX,(int) posY);
		hit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!gc.checkIfPlayerWasBusted() && (!isStanded)) {
				gc.hit();
				cardValue.setText(String.valueOf(gc.totalPoints()));
				}
				if (gc.checkIfPlayerWasBusted()) { 
					p.add(stopHit);
			}
			}
			
		});
		
		stand.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			      isStanded = true;
				
			}
			
		});
		this.setLocation(po);
		this.add(p);
		p.add(hit);
		p.add(stand);
		p.add(cardValue);
		this.setTitle("teste");
		this.setVisible(true);
	}
	
	public void drawCard() {
		
	}
}
