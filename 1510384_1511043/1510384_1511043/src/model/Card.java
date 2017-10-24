package model;

import other.Suits;

public class Card {
	public Suits suit;
	public int cardNumber;
	
	public Card(Suits suit, int cardNumber) {
		this.suit = suit;
		this.cardNumber = cardNumber;
	}
	
}
