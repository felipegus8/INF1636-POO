package model;
import java.util.ArrayList;

import other.Status;

public abstract class Player {
	public ArrayList<Card> playerCards = new ArrayList<Card>();
	protected Chip[] playerChips;
	protected int totalPointCount = 0;
	protected int totalPointCountWithAce = 0;
	protected int totalMoneyBetted;
	protected Status s;
	
	protected abstract void hit();
	
	protected abstract void stand();
	
	public void addPoint(Card card) {
		int qtdAces = 0;
		playerCards.add(card);
		if (card.cardNumber != 1)  {
		this.totalPointCount += card.cardNumber;
		this.totalPointCountWithAce += card.cardNumber;
		}
		else {
			for(Card c : playerCards) {
				if(c.cardNumber == 1) {
					qtdAces ++;
				}
			}
			this.totalPointCount += 1;
			if (qtdAces == 1) {
			this.totalPointCountWithAce += 11;
			}
			else {
				this.totalPointCountWithAce += 1;
			}
		}
	}
	
	public int getTotalPointCount() {
		return this.totalPointCount;
	}
	
	public void resetTotalPoints() {
		this.totalPointCount = 0;
		this.totalPointCountWithAce = 0;
	}
	
	public int getTotalPointCountWithAce() {
		return this.totalPointCountWithAce;
	}
}
