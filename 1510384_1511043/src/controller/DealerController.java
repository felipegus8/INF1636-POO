package controller;
import model.Card;
import model.Dealer;
import model.Gambler;
import view.GamblerFrame;
import view.TableFrame;

public class DealerController {
	Dealer d = null;
	TableFrame tf = null;
	GameController gc = null;
	
	
	public DealerController(Dealer d, TableFrame tf, GameController gc) {
		this.d = d;
		this.tf = tf;
		this.gc = gc;
	}
	
	public void hit() {
		if (d.willHit()) {
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
			//TODO:Ajeitar isso aqui
//			gf.p.paintCard(cardImageStr);
//			g.addPoint(drawed.cardNumber);
//			g.playerCards.add(drawed);
//			gf.repaint();
		}
	}
}
