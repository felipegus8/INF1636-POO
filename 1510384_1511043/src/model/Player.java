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
	
	public void addPoint(int numPoints,boolean isAce) {
		int qtdAces = 0;
		if (!isAce)  {
		this.totalPointCount += numPoints;
		this.totalPointCountWithAce = this.totalPointCount;
		}
		else {
			for(Card c : playerCards) {
				if(c.cardNumber == 1) {
					qtdAces ++;
				}
			}
			this.totalPointCount += 1;
			if (qtdAces == 0) {
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
