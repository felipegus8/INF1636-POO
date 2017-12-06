package controller;
import model.*;
import view.*;
import other.ObservadorIF;
import other.Suits;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;


public class GameController implements ObservadorIF{
	private ArrayList<Card> deck = new ArrayList<Card>();
	private TableFrame tf;
	private DealerController dc;
	private int numRound = 1;
	private int numPlayers;
	private ArrayList<GamblerFrame> pfs = new ArrayList<GamblerFrame>();
	private ArrayList<GamblerController> gcs = new ArrayList<GamblerController>();
	private Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	private int currentPlayer = 1;
	private boolean playerHasSurrendered = false;
	private BufferedReader bufferedReader;
	private static final GameController sharedInstance = new GameController();
 

	public static GameController getInstance() {
		return sharedInstance;
	}
	
	private GameController() {
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
		double width = this.screenSize.getWidth()/(numPlayers + 1) + 50;
		double height = this.screenSize.getHeight() / 3;
		
		double totalWidth = 0.0;
		double totalHeight = 0.0;
		
		
		tf = new TableFrame(0,height + 30);
		dc = new DealerController(new Dealer(),tf,this);
		tf.g = this;
		tf.registerObserver(this);
		
		for(int i=0;i<numPlayers;i++) {
			GamblerFrame gf = new GamblerFrame(width,height,totalWidth,totalHeight);
			gf.setTitle("Player " + String.valueOf(i + 1));
			GamblerController gc = new GamblerController(new Gambler(i + 1),gf,this);
			gf.gc = gc;
			gcs.add(gc);
			pfs.add(gf);
			totalWidth += width;
		}
		
		blockBet();

	//	blockPlayers();
		
	}
	
	private void blockPlayers() {
		for(int i=0;i<numPlayers;i++) {
			
			GamblerController c = gcs.get(i);
			if(this.currentPlayer != c.g.numGambler) {
				c.blockHitAndStand();
			} else {
				c.unblockHitAndStand();
			}
		}
	}
	
	public void blockBet() {
		for(int i=0;i<numPlayers;i++) {
			GamblerController c = gcs.get(i);
			if(this.currentPlayer != c.g.numGambler) {
				c.disableBet();
			} else {
				c.enableBet();
			}
		}
	}
	
	public void blockAllPlayers() {
		for(int i=0;i<numPlayers;i++) {	
			GamblerController c = gcs.get(i);
			c.blockHitAndStand();		
		}
	}
	
	void checkWinner() {
		for(int i=0;i<numPlayers;i++) {
			GamblerController c = gcs.get(i);
			if(dc.checkIfPlayerWasBusted() && c.checkIfPlayerWasBusted()) {
				//NOT A DRAW HERE.PLAYER LOSES IF BOTH ARE BUSTED.
		//		c.gf.checkResultWinLose.setText("Draw");
		//		c.gf.addDidWonOrLostLabel();
			} else if(dc.checkIfPlayerWasBusted()) {
				c.gf.checkResultWinLose.setText("You Won");
				c.playerWon();
				c.gf.addDidWonOrLostLabel();
				//player wins
			} else if(c.checkIfPlayerWasBusted()){
				//dealer wins
			} else {
				if(c.totalPointsFinal() > dc.totalPoints()) {
					//player wins
					c.gf.checkResultWinLose.setText("You Won");
					c.playerWon();
					c.gf.addDidWonOrLostLabel();
				} else if(c.totalPointsFinal() < dc.totalPoints()) {
					//dealer wins
					c.gf.checkResultWinLose.setText("You Lost");
					c.gf.addDidWonOrLostLabel();
				} else {
					c.gf.checkResultWinLose.setText("Draw");
					c.playerDrawed();
					c.gf.addDidWonOrLostLabel();
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
			c.clearCurrentBet();
		}
		dc.restart();
		blockBet();
		//blockPlayers();

		
	}
	
	public void surrenderPlayer(int numPlayer) {
		gcs.remove(numPlayer - 1);
		GamblerFrame removed = pfs.remove(numPlayer - 1);
		removed.setVisible(false);
		for(GamblerController c: gcs) {
			if(c.g.numGambler > numPlayer) {
				c.g.numGambler--;
			}
		}
		
		
		/* if current player goes back to one, the program will that 
		 * one round has been passed and that's why we need this if
		 *  */
		if(numPlayer == 1 || (this.numPlayers -1 > 1 && this.currentPlayer != numPlayer)) {
			this.playerHasSurrendered = true;
		}
		this.numPlayers--;
		if(this.currentPlayer == numPlayer) {
			this.currentPlayer--;
			decideWhoPlaysNext();
		}
	}
	
	public void give2CardsToEveryone() {
		for(GamblerController c: gcs) {
			c.give2Cards();
		}
		dc.give2Cards();
	}
	
	public void decideWhoPlaysNext() {
		this.currentPlayer = (currentPlayer%numPlayers) + 1;
		
		/* a round has passed*/
		if(this.currentPlayer  == 1) {
			
			if(this.numRound == 1 && this.playerHasSurrendered == false) {
				give2CardsToEveryone();
				
			}
			if(this.numRound == 2) {
				dc.turnCard();
			}
			if(this.playerHasSurrendered == false) {
				this.numRound++;
			} else {
				this.playerHasSurrendered = false;
			}
						
			if(this.numRound == 3) {
				while(dc.hitOrStand());
				checkWinner();
				dc.enableRestart();
				blockAllPlayers();
				this.numRound = 1;
				return;
			}
			
			
		}
		
		this.playerHasSurrendered = false;
		if(this.numRound == 1) {
			blockBet();
			System.out.println("bin ich hier");
		}
		
		
		if(this.numRound == 2) {
			blockPlayers();
		}
	}

	@Override
	public void update(int valor, Object obj) {
		GamblerController c = gcs.get(this.currentPlayer - 1);
		c.betChip(valor);	
	}	
	
	
	public void save(String filePath) {		
		try {
			PrintWriter writer = new PrintWriter(filePath + ".txt", "UTF-8");
			
			// Store table
			
			writer.printf("Dealer");
			writer.println();
			if (dc.checkIfPlayerHasAce() && !dc.checkIfAceMaxBusts()) {
				writer.printf("Points  "+ dc.totalPoints() + "/" + dc.totalPointsWithAce());
			}
			else {
			writer.printf("Points " + dc.totalPoints());
			}
			writer.println();
			
			writer.printf("Cards");
			for(Card card : dc.d.playerCards) {
				if (card.cardNumber < 10) {
				writer.printf(" " + card.cardNumber + " " + card.suit);
				}
				else {
					writer.printf(" " + card.cardName + " " + card.suit);
				}
			}
			
			writer.println();
			writer.println();
			
			// Store players
			
			writer.printf("Gamblers " + numPlayers);
			writer.println();
			writer.printf("Current Player " + currentPlayer);
			writer.println();
			for (GamblerController gambler: gcs) {				
				writer.printf("Chips " + gambler.g.totalMoneyAvailable);
				writer.println();
				
				writer.printf("Bet " + gambler.totalBetted());
				writer.println();
				
				writer.printf("Points " + gambler.getCorrectTextForCardValue());
				writer.println();
				if (gambler.isStanded()) {
					writer.printf("Standed");
				}
				if (gambler.checkIfPlayerWasBusted()) {
					writer.printf("Busted");
				}
				
				writer.println();
				
				writer.printf("Cards");
				for(Card card : gambler.g.playerCards) {
					if (card.cardNumber < 10) {
						writer.printf(" " + card.cardNumber + " " + card.suit);
						}
						else {
							writer.printf(" " + card.cardName + " " + card.suit);
						}
				}
				writer.println();
				writer.println();
			}
			
			writer.printf("End");
			writer.close();
		} 
		catch (IOException e) {
			System.out.println("GameController : save : error = " + e.getMessage());
			System.exit(1);
		}
		
	}
	/*
	public void retrieveSavedGame(String filePath) {
		try {
			bufferedReader = new BufferedReader(new FileReader(filePath));
		    String currentLine = bufferedReader.readLine();
		    String[] currentComponents = currentLine.split(" ");
		    
		    while (currentLine != null) {
		    		switch (currentComponents[0]) {
		    		case "Dealer":
		    			table = new Table();

		    			tableView = new TableScreen(screenSize.getWidth()/2, 200);
		    			tableView.setListeners(this);
		    			tableView.register(this);
		    			tableView.setVisible(true);
		    			initializeDeck();
		    			
		    			currentLine = bufferedReader.readLine();
				    currentComponents = currentLine.split(" ");
				    
				    if (currentComponents[0].compareTo("Points") == 0)
				    		table.addPoints(Integer.parseInt(currentComponents[1]));
				    else
				    		break;
				    
				    currentLine = bufferedReader.readLine();
				    currentComponents = currentLine.split(" ");
				    
				    if (currentComponents[0].compareTo("Cards") == 0) {
				    		for(int i = 1; i < currentComponents.length; i += 2) {
				    			Card newCard = new Card(Suit.getSuitWith(currentComponents[i + 1]), Integer.parseInt(currentComponents[i]));
				    			table.cards.add(newCard);
				    			
				    			deck.remove(newCard);
				    		}
				    }
				    
		    			break;
		    			
		    		case "Gamblers":
		    			numberOfPlayers = Integer.parseInt(currentComponents[1]);
		    		    currentPlayer = -1;
		    			gamblersControllers = new ArrayList<GamblerController>();
		    			
		    			for (int i = 0; i < numberOfPlayers; i++) {
		    				gamblersControllers.add(new GamblerController(i));
		    				GamblerController currentGambler = gamblersControllers.get(i);
		    				
		    				currentLine = bufferedReader.readLine();
						currentComponents = currentLine.split(" ");
						
						for (int j = 0; j < 5; j++) {
							switch (currentComponents[0]) {
							case "Chips":
								currentGambler.setPlayerChips(Integer.parseInt(currentComponents[1]));;
								break;
								
							case "Bet":
								currentGambler.setPlayerBet(Integer.parseInt(currentComponents[1]));
								break;
								
							case "Points":
								currentGambler.setPlayerPoints(Integer.parseInt(currentComponents[1]));
								break;
								
							case "State":
								currentGambler.setPlayerState(PlayerState.getStateWith(Integer.parseInt(currentComponents[1])));
								break;
								
							case "Cards":
								ArrayList<Card> cards = new ArrayList<Card>();
								
								for(int k = 1; k < currentComponents.length; k += 2) {
									Card newCard = new Card(Suit.getSuitWith(currentComponents[k + 1]), Integer.parseInt(currentComponents[k]));
									cards.add(newCard);
									
					    				deck.remove(newCard);
								}
								
								currentGambler.setPlayerCards(cards);
								break;
								
							default:
				    				System.out.println("GameController : retrieveSavedGame : invalid content");
				    				System.exit(1);
							}
							
							currentLine = bufferedReader.readLine();
							currentComponents = currentLine.split(" ");
						}
		    			}
		    			
		    			break;
		    			
		    		default:
		    			System.out.println("GameController : retrieveSavedGame : invalid content");
		    			System.exit(1);
		    		}
		        
		    		bufferedReader.readLine();
		        if (( currentLine = bufferedReader.readLine()) != null)
		        		currentComponents = currentLine.split(" ");
		    }
		}
		catch (IOException e) {
			System.out.println("GameController : retrieveSavedGame : error = " + e.getMessage());
			System.exit(1);			
		}
		
		updateUI();		
		
		boolean isEndOfRound = true;
		for (int i = 0; i < numberOfPlayers; i++) {
			gamblersControllers.get(i).updateUI();
			
			PlayerState playerState = gamblersControllers.get(i).getPlayerState();
			isEndOfRound = playerState != PlayerState.Playing || playerState != PlayerState.Waiting;
		}
		
		if (gamblersControllers.get(0).getPlayerState() == PlayerState.Betting)
			nextPlayerToBet();
		
		if (isEndOfRound)
			tableView.showResetOption();
	}
	*/
	
}
