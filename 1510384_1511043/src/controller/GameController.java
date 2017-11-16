package controller;
import model.*;
import view.*;
import other.Suits;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class GameController {
	private ArrayList<Card> deck = new ArrayList<Card>();
	private TableFrame tf;
	private DealerController dc;
	private int numRound = 1;
	private int numPlayers;
	private ArrayList<GamblerFrame> pfs = new ArrayList<GamblerFrame>();
	private ArrayList<GamblerController> gcs = new ArrayList<GamblerController>();
	private Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	private int currentPlayer = 1;
	
	public GameController(TableFrame tf) {
		this.tf = tf;
		
	}
	
	public GameController() {
		FirstScreen fs = new FirstScreen(this);
		fs.setTitle("First Screen");
		fs.setVisible(true);
		
	}
	
	public void insertCards() {
		//hearts
		for(int i = 0;i<13;i++) {
			deck.add(new Card(Suits.hearts,i+1));
		}
		
		//spades
		for(int i = 0;i<13;i++) {
			deck.add(new Card(Suits.spades,i+1));
		}
		
		//diamonds
		for(int i = 0;i<13;i++) {
			deck.add(new Card(Suits.diamonds,i+1));
		}
		
		//clubs
		for(int i = 0;i<13;i++) {
			deck.add(new Card(Suits.clubs,i+1));
		}
		
	}
	
	public void shuffleCards() {
		Collections.shuffle(deck);
	}
	
	public Card drawCard() {
		Card drawed = deck.get(0);
		deck.remove(0);
		return drawed;
	}
	
	public void generateFrames(int numPlayers) {
		this.numPlayers = numPlayers;
		double width = this.screenSize.getWidth()/(numPlayers + 1);
		double height = this.screenSize.getHeight() / 3;
		
		double totalWidth = 0.0;
		double totalHeight = 0.0;
		
		tf = new TableFrame(0,height + 30);
		dc = new DealerController(new Dealer(),tf,this);
		tf.g = this;
		
		for(int i=0;i<numPlayers;i++) {
			GamblerFrame gf = new GamblerFrame(width,height,totalWidth,totalHeight);
			gf.setTitle("Player " + String.valueOf(i + 1));
			GamblerController gc = new GamblerController(new Gambler(i + 1),gf,this);
			gf.gc = gc;
			gcs.add(gc);
			pfs.add(gf);
			totalWidth += width;
		}
		
		blockPlayers();
		
	}
	
	private void blockPlayers() {
		for(int i=0;i<numPlayers;i++) {
			
			GamblerController c = gcs.get(i);
			if(this.currentPlayer != c.g.numGambler) {
				c.blockHitAndStand();
			} else {
				c.unblockHitAndStand();
//				if(c.isStanded() == false) {
//					c.unblockHitAndStand();
//				} else {
//					decideWhoPlaysNext();
//				}
			}
		}
	}
	
	public void blockAllPlayers() {
		for(int i=0;i<numPlayers;i++) {	
			GamblerController c = gcs.get(i);
			System.out.println("no mitico block all players");
			c.blockHitAndStand();		
		}
	}
	
	void checkWinner() {
		for(int i=0;i<numPlayers;i++) {
			GamblerController c = gcs.get(i);
			if(dc.checkIfPlayerWasBusted() && c.checkIfPlayerWasBusted()) {
				//draw
				System.out.println("draw 1");
			} else if(dc.checkIfPlayerWasBusted()) {
				System.out.println("player wins 1");
				//player wins
			} else if(c.checkIfPlayerWasBusted()){
				//dealer wins
				System.out.println("dealer wins 1");
			} else {
				if(c.totalPointsFinal() > dc.totalPoints()) {
					//player wins
					System.out.println("player wins 2");
				} else if(c.totalPointsFinal() < dc.totalPoints()) {
					//dealer wins
					System.out.println("dealer wins 2");
				} else {
					System.out.println("draw 2");
					//draw
				} 
			}
			
			
		}
	}
	
	public void restartGame() {
		deck.clear();
		insertCards();
		shuffleCards();
		for(GamblerController c: gcs) {
			c.clearHand();
			c.restart();
		}
		dc.restart();
		blockPlayers();

		
	}
	
	public void decideWhoPlaysNext() {
		System.out.println("CURRENT PLAYER " + currentPlayer);
		this.currentPlayer = (currentPlayer%numPlayers) + 1;
		
		
		
		/* a round has passed*/
		if(this.currentPlayer  == 1) {
			if(this.numRound == 1) {
				dc.turnCard();
			}
			this.numRound++;
			
			System.out.println("NUM ROUND ONDE IMPORTA " + this.numRound);
			
			if(this.numRound == 2) {
				while(dc.hitOrStand());
				checkWinner();
				dc.enableRestart();
				blockAllPlayers();
				this.numRound = 1;
				return;
			}
			
		}
		
		if(this.numRound != 2) {
			blockPlayers();
		}
	}
	
	
	
	
}
