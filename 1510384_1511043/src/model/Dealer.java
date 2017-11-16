package model;

public class Dealer extends Player{
	public boolean willHit() {
		if(this.totalPointCount < 17) {
			return true;
		}
		return false;
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