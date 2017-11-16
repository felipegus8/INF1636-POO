package controller;


import model.Card;
import model.Gambler;
import view.GamblerFrame;

public class GamblerController {
	Gambler g = null;
	GamblerFrame gf = null;
	GameController gc = null;
	
	public GamblerController(Gambler g, GamblerFrame gf, GameController gc) {
		this.g = g;
		this.gf = gf;
		this.gc = gc;
		
		give2Cards();
		
	}
	
	public void restart() {
		gf.p.clearImgs();
		gf.isStanded = false;
		gf.stopHit.setVisible(false);
		gf.checkResultWinLose.setVisible(false);
		g.resetTotalPoints();
		give2Cards();
	}
	

	
	private void give2Cards() {
		hit();
		hit();
		gf.cardValue.setText(getCorrectTextForCardValue());
	}
	
	public void hit() {
		Card drawed = gc.drawCard();
		String cardImageStr = "";
		if(drawed.cardNumber > 9 || drawed.cardNumber == 1) {
			switch(drawed.cardNumber) {
				case 10:
					cardImageStr += "t";
					break;
				case 11:
					cardImageStr += "j";
					break;
				case 12:
					cardImageStr += "q";
					break;
				case 13:
					cardImageStr += "k";
					break;
				case 1:
					cardImageStr += "a";
					break;
			}
		} else {
			cardImageStr += Integer.toString(drawed.cardNumber);
		}
		
		switch(drawed.suit) { 
		case clubs:
			cardImageStr += "c";
			break;
		case hearts:
			cardImageStr += "h";
			break;
		case spades:
			cardImageStr += "s";
			break;
		case diamonds:
			cardImageStr += "d";
			break;
			
		}
		
		if (drawed.cardNumber == 1) {
		g.addPoint(drawed);
		}
		else {
			if (drawed.cardNumber > 10) {
				drawed.cardNumber = 10;
			}
			g.addPoint(drawed);
		}
		gf.p.paintCard(cardImageStr);
		gf.repaint();
	}
	public int totalPoints() {
		return this.g.getTotalPointCount();
	}
	
	public int totalPointsFinal() {
		if(checkIfPlayerHasAce()) {
			if(checkIfAceMaxBusts()) {
				return this.g.getTotalPointCount();
			} else {
				return this.g.getTotalPointCountWithAce();
			}
		} else {
			return this.g.getTotalPointCount();
		}
	}
	
	public void clearHand() {
		this.g.playerCards.clear();
	}
	
	public int totalPointsWithAce() {
		return this.g.getTotalPointCountWithAce();
	}
	
	public boolean checkIfPlayerWasBusted () {
		if (this.totalPoints() > 21) {
			//decideWhoPlaysNext();
			return true;
		}
		return false;
	}
	
	public boolean checkIfPlayerHasAce() {
		for(Card c : g.playerCards) {
			if (c.cardNumber == 1) {
				return true;
			}
		}
		return false;
}
	
	public boolean checkIfAceMaxBusts() {
		
		if (g.getTotalPointCountWithAce() > 21) {
			//decideWhoPlaysNext();
			return true;
		}
		return false;
	}
	
	public String getCorrectTextForCardValue() {
		if (checkIfPlayerHasAce() && !checkIfAceMaxBusts()) {
			return String.valueOf(totalPoints()) + "/" + String.valueOf(totalPointsWithAce());
		}
		return String.valueOf(totalPoints());
	}
	
	public void unblockHitAndStand() {
		gf.hit.setEnabled(true);
		gf.stand.setEnabled(true);
		
	}
	
	public void blockHitAndStand() {
		gf.hit.setEnabled(false);
		gf.stand.setEnabled(false);
		
	}
	
	public void decideWhoPlaysNext() {
		gc.decideWhoPlaysNext();
		
	}
	
	public Boolean isStanded() {
		return gf.isStanded;
	}
	
}
