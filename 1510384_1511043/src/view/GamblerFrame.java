package view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.GamblerController;
import controller.GameController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import java.awt.Point;

import javax.swing.JButton;

public class GamblerFrame extends JFrame{	
	public JButton hit = new JButton("HIT");
	public JButton stand = new JButton("STAND");
	public JLabel cardValue = new JLabel("0");
	JLabel stopHit = new JLabel("BUSTED");
	public GamblerPanel p = new GamblerPanel();
	public GamblerController gc = null;
	public GameController g = null;
	public final int LARG_DEFAULT=300; public final int ALT_DEFAULT=300;
	public boolean isStanded = false;
	public GamblerFrame(double x, double y,double posX, double posY) {
		setSize((int) x,(int) y);
		Point po = new Point((int) posX,(int) posY);
		hit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!gc.checkIfPlayerWasBusted() && (!isStanded)) {
				hit.setEnabled(true);
				gc.hit();
				cardValue.setText(gc.getCorrectTextForCardValue());
			}
				if (gc.checkIfPlayerWasBusted()) { 
					p.add(stopHit);
					hit.setEnabled(false);
					stand.setEnabled(false);
			}
			}
			
		});
		
		stand.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				  if (gc.checkIfPlayerHasAce() && !gc.checkIfAceMaxBusts()) {
					  cardValue.setText(String.valueOf(gc.totalPointsWithAce()));
				  }
				  else {
					  cardValue.setText(String.valueOf(gc.totalPoints()));
				  }
				  
				  gc.decideWhoPlaysNext();
			      isStanded = true;
			      stand.setEnabled(false);
			      hit.setEnabled(false);
			      
			      
				
			}
			
		});
		this.setLocation(po);
		this.add(p);
		p.add(hit);
		p.add(stand);
		p.add(cardValue);
		this.setVisible(true);
	}
	
	public void drawCard() {
		
	}
}
