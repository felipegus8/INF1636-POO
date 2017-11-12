package model;
import java.util.ArrayList;
import java.util.Collections;
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
		if (!isAce)  {
		this.totalPointCount += numPoints;
		this.totalPointCountWithAce = this.totalPointCount;
		}
		else {
			this.totalPointCount += 1;
			this.totalPointCountWithAce += 11;
		}
	}
	
	public int getTotalPointCount() {
		return this.totalPointCount;
	}
	public int getTotalPointCountWithAce() {
		return this.totalPointCountWithAce;
	}
}
