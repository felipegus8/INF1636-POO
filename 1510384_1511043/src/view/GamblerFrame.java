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
	public JButton doubleBet = new JButton ("DOUBLE");
	public JLabel cardValue = new JLabel("0");
	public JLabel stopHit = new JLabel("BUSTED");
	public JLabel checkResultWinLose = new JLabel ("You Won");
	public GamblerPanel p = new GamblerPanel();
	public GamblerController gc = null;
	public GameController g = null;
	public final int LARG_DEFAULT=400; public final int ALT_DEFAULT=400;
	public boolean isStanded = false;
	public GamblerFrame(double x, double y,double posX, double posY) {
		setSize((int) x,(int) y);
		Point po = new Point((int) posX,(int) posY);
		doubleBet.setEnabled(false);
		hit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("busted " + gc.checkIfPlayerWasBusted() + "standed " + isStanded);
				
				if(!gc.checkIfPlayerWasBusted() && (!isStanded)) {
				hit.setEnabled(true);
				gc.hit();
				cardValue.setText(gc.getCorrectTextForCardValue());
			}
				if (gc.checkIfPlayerWasBusted()) { 
					stopHit.setVisible(true);
					p.add(stopHit);
					hit.setEnabled(false);
					stand.setEnabled(false);
					gc.decideWhoPlaysNext();
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
		p.add(doubleBet);
		p.add(cardValue);
		this.setVisible(true);
	}
	
	public void addDidWonOrLostLabel() {
		p.add(checkResultWinLose);
	}
	
	
	
}
