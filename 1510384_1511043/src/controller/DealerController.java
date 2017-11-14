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
		drawCardToDealer();
		paintCardOnScreen("deck1");
		hit();
	}
	

	
	private Card drawCardToDealer() {
		Card drawed = gc.drawCard();

		if (drawed.cardNumber == 1) {
			d.addPoint(drawed.cardNumber,true);
			}
			else {
				if (drawed.cardNumber > 10) {
					drawed.cardNumber = 10;
				}
				d.addPoint(drawed.cardNumber, false);
			}
			d.playerCards.add(drawed);
			
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
	
	private void paintCardOnScreen(String cardImageStr) {
		
		tf.p.paintCard(cardImageStr);
		tf.repaint();
		
	}
	
	public void hit() {
		Card drawed = drawCardToDealer();
		String imageString = getImageString(drawed);
		paintCardOnScreen(imageString);
		
	}
}
