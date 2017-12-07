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
		System.out.println("O QUE CHEGOU" + cardNumberNew);
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
		System.out.println(cardNumberNew);
		if (cardNumberNew !=  "T" && cardNumberNew != "J" && cardNumberNew != "Q" && cardNumberNew != "K") {
			this.cardNumber = Integer.valueOf(cardNumberNew);
			this.cardName = cardNumberNew.charAt(0);
		}
		else {
			this.cardNumber = 10;
		}
		
	}
	
}
