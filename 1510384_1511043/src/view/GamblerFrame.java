package view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.GamblerController;
import controller.GameController;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.JButton;

public class GamblerFrame extends JFrame{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton hit = new JButton("HIT");
	public JButton stand = new JButton("STAND");
	public JButton doubleBet = new JButton ("DOUBLE");
	public JButton bet = new JButton("BET");
	public JButton surrender = new JButton ("SURRENDER");
	public JButton buyChips = new JButton("BUY");
	public JLabel cardValue = new JLabel("0");
	public JLabel stopHit = new JLabel("BUSTED");
	public JLabel checkResultWinLose = new JLabel ("You Won");
	public JLabel totalCoinsGambler = new JLabel("Total Money:10000");
	public JLabel totalMoneyBetted = new JLabel("Money Betted:0");
	public GamblerPanel p = new GamblerPanel();
	public GamblerController gc = null;
	public GameController g = null;
	public final int LARG_DEFAULT=400; public final int ALT_DEFAULT=400;
	public boolean isStanded = false;
	public GamblerFrame(double x, double y,double posX, double posY) {
		setSize((int) x,(int) y);
		hit.setEnabled(false);
		stand.setEnabled(false);
		doubleBet.setEnabled(false);
		Point po = new Point((int) posX,(int) posY);
		checkResultWinLose.setVisible(false);
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
		doubleBet.addActionListener(new ActionListener() {
			//TODO:DOUBLE THE BET HERE.This only makes a Hit followed by a Stand.
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!gc.checkIfPlayerWasBusted() && (!isStanded)) {
					doubleBet.setEnabled(true);
					gc.hit();
					int total = gc.totalBetted();
					gc.doubleBet();
					cardValue.setText(gc.getCorrectTextForCardValue());
				}
				if (gc.checkIfPlayerWasBusted()) { 
					stopHit.setVisible(true);
					p.add(stopHit);		
				}
				hit.setEnabled(false);
				stand.setEnabled(false);
				doubleBet.setEnabled(false);
				  if (gc.checkIfPlayerHasAce() && !gc.checkIfAceMaxBusts()) {
					  cardValue.setText(String.valueOf(gc.totalPointsWithAce()));
				  }
				  else {
					  cardValue.setText(String.valueOf(gc.totalPoints()));
				  }
				isStanded = true;
				gc.decideWhoPlaysNext();
			}
		});
		bet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				  gc.didFinishBet();
				  //bet.setEnabled(false);
				  
			}
			
		});
		
		buyChips.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Aqui avisar que acabou de comprar as fichas.				  
			}
			
		});
		
		surrender.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gc.playerSurrendered();
				
			}
			
		});
		
		this.setLocation(po);

		GridBagLayout borderL = new GridBagLayout();
		p.setLayout(borderL);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		p.add(stand,c);
		c.gridx = 1;
		c.gridy = 1;
		p.add(hit,c);
		c.gridx = 2;
		c.gridy = 1;
		p.add(doubleBet,c);
		c.gridx = 0;
		c.gridy = 4;
		p.add(bet,c);
		c.gridx = 1;
		c.gridy = 4;
		p.add(surrender,c);
		c.gridx = 2;
		c.gridy = 4;
		p.add(buyChips,c);
		p.add(cardValue);
		
		c.gridx = 1;
		c.gridy = 8;
	//	c.anchor = GridBagConstraints.PAGE_END;
		p.add(totalCoinsGambler,c);
		c.gridx = 1;
		c.gridy = 12;
		p.add(totalMoneyBetted,c);
		this.add(p);
		this.setVisible(true);
	}
	
	public void addDidWonOrLostLabel() {
		checkResultWinLose.setVisible(true);
		p.add(checkResultWinLose);
		p.validate();
	}
	
	public void alterTotalMoneyLabel(int totalMoneyPlayerHas) {
		this.totalCoinsGambler.setText("Total Money:" + Integer.toString(totalMoneyPlayerHas));
		totalCoinsGambler.validate();
		p.validate();
	}
	
	public void alterCurrentBetLabel(int currentBet) {
		this.totalMoneyBetted.setText("Money Betted:" + Integer.toString(currentBet));
		totalMoneyBetted.validate();
		p.validate();
	}
	
	public void hideScoreLabel() {
		cardValue.setVisible(false);
		cardValue.validate();
	}
	
	public void showScoreLabel() {
		cardValue.setVisible(true);
		cardValue.validate();
	}
	
	public void disableBetButton() {
		bet.setEnabled(false);
		bet.validate();
	}
	
	public void enableBetButton() {
		bet.setEnabled(true);
		bet.validate();
	}
}
