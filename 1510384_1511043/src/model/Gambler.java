package model;

import java.util.ArrayList;

public class Gambler extends Player{
	public int totalMoneyAvailable = 100;
	public int numGambler;
	private ArrayList<Chip> betChips = new ArrayList<Chip>();
	
	public Gambler(int numGambler) {
		this.numGambler = numGambler;
	}
	
	public void betChip(int value) {
		if(this.totalMoneyAvailable >= value) {
			if(this.totalBetted() + value <= 100) {
				this.totalMoneyAvailable -= value;
				this.betChips.add(new Chip(value));
				System.out.println("value " + value);
			} else {
				System.out.println("i'm afraid i can't let you do that");
			}
		} else {
			System.out.println("i'm afraid i can't let you do that");
		}
	}
	
	public void buyChip(int value) {
		this.totalMoneyAvailable += value;
	}
	
	public int totalBetted() {
		int totalMoneyBetted = 0;
		for(Chip c: betChips) {
			totalMoneyBetted += c.value;
		}
		return totalMoneyBetted;
	}
	
	public void clearCurrentBet() {
		this.betChips.clear();
	}
	
	public void playerWon() {
		this.totalMoneyAvailable += 2*this.totalBetted();
	}
	
	public void doubleBet() {
		int totalChipsBetted = this.betChips.size();
		if(totalMoneyAvailable >= totalBetted() * 2) {
			for(int i=0;i<totalChipsBetted;i++) {
				this.betChips.add(this.betChips.get(i));
			}
		this.totalMoneyAvailable -= totalBetted()/2;
		} else {
			System.out.println("i'm afraid i cant let you do that");
		}
	}
	
	public void playerDrawed() {
		this.totalMoneyAvailable += this.totalBetted();
	}
	
	@Override
	protected void hit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void stand() {
		// TODO Auto-generated method stub
		
	}
	
	
}
