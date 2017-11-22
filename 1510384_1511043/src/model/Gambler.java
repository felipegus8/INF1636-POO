package model;

import java.util.ArrayList;

public class Gambler extends Player{
	int totalMoneyAvailable = 10000;
	public int numGambler;
	private ArrayList<Chip> betChips = new ArrayList<Chip>();
	
	public Gambler(int numGambler) {
		this.numGambler = numGambler;
	}
	
	public void betChip(int value) {
		if(this.totalMoneyAvailable >= value) {
			this.totalMoneyAvailable -= value;
			this.betChips.add(new Chip(value));
			System.out.println("value " + value);
		} else {
			System.out.println("i'm afraid i can't let you do that");
		}
	}
	
	public int totalBetted() {
		int totalMoneyBetted = 0;
		for(Chip c: betChips) {
			totalMoneyBetted += c.value;
		}
		return totalMoneyBetted;
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
