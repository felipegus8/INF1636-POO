package controller;
import model.Card;

import model.Dealer;
import view.TableFrame;

public class DealerController {
	Dealer d = null;
	TableFrame tf = null;
	GameController gc = null;
	public static boolean isFirstDealerCard = true;
	
	public DealerController(Dealer d, TableFrame tf, GameController gc) {
		this.d = d;
		this.tf = tf;
		this.gc = gc;
		
		//give2Cards();
	}
	
	public void give2Cards() {
		drawCardToDealer();
		paintCardOnScreen("deck1");
		hit();
	}
	
	public void restart() {
		tf.p.clearImgs();
		d.resetTotalPoints();
		d.playerCards.clear();
		//give2Cards();
		tf.disableRestart();
		tf.p.repaint();
	}
	
	public void enableRestart() {
		tf.enableRestart();
	}

	
	private Card drawCardToDealer() {
		Card drawed = gc.drawCard();

		if (drawed.cardNumber == 1) {
			drawed.cardName = 'A';
			d.addPoint(drawed);
			}
			else {
				if (drawed.cardNumber >= 10) {
					switch (drawed.cardNumber) {
					case 10:
						drawed.cardName = 'T';
						break;
					case 11:
						drawed.cardName = 'J';
						break;
					case 12:
						drawed.cardName = 'Q';
						break;
					case 13:
						drawed.cardName = 'K';
						break;
					}
					drawed.cardNumber = 10;
				}
				d.addPoint(drawed);
			}			
			return drawed;
		
	}
	
	private String getImageString(Card drawed) {
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
		return cardImageStr;
		
	}
	
	public void turnCard() {
		tf.p.clearImgs();
		for(Card c: d.playerCards) {
			String imgString = getImageString(c);
			paintCardOnScreen(imgString);
		}
	}
	
	public Boolean hitOrStand() {
		if(d.willHit()) {
			hit();
			return true;
		}
		return false;
	}
	
	private void paintCardOnScreen(String cardImageStr) {
		
		tf.p.paintCard(cardImageStr);
		tf.repaint();
		
	}
	
	public int totalPoints() {
		return this.d.getTotalPointCount();
	}
	
	public int totalPointsWithAce() {
		return this.d.getTotalPointCountWithAce();
	}
	
	public boolean checkIfPlayerWasBusted () {
		if (this.totalPoints() > 21) {
			return true;
		}
		return false;
	}
	
	public boolean checkIfPlayerHasAce() {
		if (d.getTotalPointCount() != d.getTotalPointCountWithAce()) {
			return true;
		}
		return false;
	}
	
	public boolean checkIfAceMaxBusts() {
		
		if (d.getTotalPointCountWithAce() > 21) {
			return true;
		}
		return false;
	}
	
	public int totalPointsFinal() {
		if(checkIfPlayerHasAce()) {
			if(checkIfAceMaxBusts()) {
				return this.d.getTotalPointCount();
			} else {
				return this.d.getTotalPointCountWithAce();
			}
		} else {
			return this.d.getTotalPointCount();
		}
	}
	
	public void hit() {
		Card drawed = drawCardToDealer();
		String imageString = getImageString(drawed);
		if (!this.checkIfPlayerHasAce() || this.checkIfAceMaxBusts()) {
			tf.cardValue.setText(String.valueOf(this.totalPoints()));
		}
		else {	
			tf.cardValue.setText(String.valueOf(this.totalPoints()) + "/" + String.valueOf(this.totalPointsWithAce()));
		}
		paintCardOnScreen(imageString);
	}
	
	public void updateDealerUI() {
		for (Card c:d.playerCards) {
			String s = null,n = null;
			switch (c.suit) {
			case hearts:
				s="h";
				break;
			case spades:
				s = "s";
				break;
			case diamonds:
				s = "d";
				break;
			case clubs:
				s = "c";
				break;
			}
			if (c.cardNumber < 10 && c.cardNumber != 1) {
				n = String.valueOf(c.cardNumber);
			}
			else {
				n = String.valueOf(c.cardName);
			}
			
		String cardImage =  n + s;
		if(isFirstDealerCard) {
			paintCardOnScreen("deck1");
		}
		else {
		paintCardOnScreen(cardImage);
		}
		isFirstDealerCard = false;
	}
}
}
