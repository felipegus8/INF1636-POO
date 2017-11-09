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
		gf.p.paintCard(cardImageStr);
		g.addPoint(drawed.cardNumber);
		g.playerCards.add(drawed);
		gf.repaint();
	}
	public int totalPoints() {
		return this.g.getTotalPointCount();
	}
	
	public boolean checkIfPlayerWasBusted () {
		if (this.totalPoints() > 21) {
			return true;
		}
		return false;
	}
	
	
}
