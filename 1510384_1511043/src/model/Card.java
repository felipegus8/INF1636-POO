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
	
}
