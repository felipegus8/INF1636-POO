package model;

import other.Suits;

public class Card {
	public Suits suit;
	public int cardNumber;
	public char cardName;
	public Card(Suits suit, int cardNumber) {
		this.suit = suit;
		this.cardNumber = cardNumber;
	}
	
	public Card(String suit,String cardNumberNew) {
		switch (suit) {
		case "spades":
			this.suit = Suits.spades;
			break;
		case "diamonds":
			this.suit = Suits.diamonds;
			break;
		case "clubs":
			this.suit = Suits.clubs;
			break;
		case "hearts":
			this.suit = Suits.hearts;
			break;
		}
		if (cardNumberNew.equals("T") || cardNumberNew.equals("J") || cardNumberNew.equals("Q") || cardNumberNew.equals("K")) {
			this.cardNumber = 10;
			this.cardName = cardNumberNew.charAt(0);
		}
		else if (cardNumberNew.equals("A")) {
			this.cardNumber = 1;
			this.cardName = 'A';
		}
		else {
			this.cardNumber = Integer.valueOf(cardNumberNew);
			this.cardName = cardNumberNew.charAt(0);
		}	
	}	
}