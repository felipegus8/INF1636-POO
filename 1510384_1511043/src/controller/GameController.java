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
	public int numRound = 1;
	private int numPlayers;
	private ArrayList<GamblerFrame> pfs = new ArrayList<GamblerFrame>();
	private ArrayList<GamblerController> gcs = new ArrayList<GamblerController>();
	private Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	private int currentPlayer = 1;
	private boolean playerHasSurrendered = false;
	private BufferedReader reader;
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
	
	public int getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public void blockAllPlayers() {
		for(int i=0;i<numPlayers;i++) {	
			GamblerController c = gcs.get(i);
			c.blockHitAndStand();		
		}
	}
	
	void checkWinner() {
		dc.tf.saveGame.setEnabled(false);
		for(int i=0;i<numPlayers;i++) {
			GamblerController c = gcs.get(i);
			if(dc.checkIfPlayerWasBusted() && c.checkIfPlayerWasBusted()) {
				//NOT A DRAW HERE.PLAYER LOSES IF BOTH ARE BUSTED.
		//		c.gf.checkResultWinLose.setText("Draw");
		//		c.gf.addDidWonOrLostLabel();
				c.playerLost();
			} else if(dc.checkIfPlayerWasBusted()) {
				c.gf.checkResultWinLose.setText("You Won");
				c.playerWon();
				c.gf.addDidWonOrLostLabel();
				//player wins
			} else if(c.checkIfPlayerWasBusted()){
				//dealer wins
				c.playerLost();
			} else {
				if(c.totalPointsFinal() > dc.totalPoints()) {
					//player wins
					c.gf.checkResultWinLose.setText("You Won");
					c.playerWon();
					c.gf.addDidWonOrLostLabel();
				} else if(c.totalPointsFinal() < dc.totalPoints()) {
					//dealer wins
					c.playerLost();
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
		}
		
		
		if(this.numRound == 2) {
			blockPlayers();
		}
	}

	@Override
	public void update(int valor, Object obj) {
		GamblerController c = gcs.get(this.currentPlayer - 1);
		if(c.gf.buyChips.isEnabled()) {
			c.buyChip(valor);
		}
		else {
		c.betChip(valor);	
		}
	}	
	
	
	public void save(String filePath) {		
		try {
			PrintWriter file = new PrintWriter(filePath + ".txt", "UTF-8");
			file.printf("Dealer");
			file.println();
			if (dc.checkIfPlayerHasAce() && !dc.checkIfAceMaxBusts()) {
				file.printf("Points "+ dc.totalPoints() + "/" + dc.totalPointsWithAce());
			}
			else {
			file.printf("Points " + dc.totalPoints());
			}
			file.println();
			file.printf("Cards ");
			for(Card card : dc.d.playerCards) {
				if (card.cardNumber < 10 && card.cardNumber != 1) {
				file.printf(card.cardNumber + " " + card.suit + " ");
				}
				else {
					file.printf(card.cardName + " " + card.suit + " ");
				}
			}	
			file.println();
			file.println();
			file.printf("Gamblers " + numPlayers);
			file.println();
			file.printf("Current Player " + currentPlayer);
			file.println();
			file.println("Current Round " + numRound);
			for (GamblerController gambler: gcs) {				
				file.printf("Chips " + gambler.g.totalMoneyAvailable);
				file.println();
				file.printf("Bet " + gambler.totalBetted());
				file.println();
				file.printf("Points " + gambler.getCorrectTextForCardValue());
				file.println();
				file.printf("Cards");
				for(Card card : gambler.g.playerCards) {
					if (card.cardNumber < 10 && card.cardNumber != 1) {
						file.printf(" " + card.cardNumber + " " + card.suit);
						}
						else {
							file.printf(" " + card.cardName + " " + card.suit);
						}
				}
				file.println();
				file.println();
			}
			file.close();
		} 
		catch (IOException e) {
			System.out.println("GameController : save : error = " + e.getMessage());
			System.exit(1);
		}
		
	}
	
	public void retrieveGame(String filePath) {
		try {
			reader = new BufferedReader(new FileReader(filePath));
		    String currentLine = reader.readLine();
		    String[] lineComp = currentLine.split(" ");
		    
		    while (currentLine != null) {
		    		switch (lineComp[0]) {
		    		case "Dealer":
		    		double height = this.screenSize.getHeight() / 3;
				tf = new TableFrame(0,height + 30);
				dc = new DealerController(new Dealer(),tf,this);
				dc.d = new Dealer();
				tf.g = this;
				tf.registerObserver(this);
		    		currentLine = reader.readLine();
				lineComp = currentLine.split(" ");
				    if (lineComp[0].compareTo("Points") == 0)  {
				    		tf.cardValue.setText(lineComp[1]);
				    	    if (lineComp[1].indexOf('/') > 0) {
				    		    String [] bothPoints = lineComp[1].split("/");
				    		    dc.d.setTotalPoints(Integer.parseInt(bothPoints[0]));
				    		    dc.d.setTotalPointsWithAce(Integer.parseInt(bothPoints[1]));
				         }
				    	    else {
				    	    		dc.d.setTotalPoints(Integer.parseInt(lineComp[1]));
				    	    		dc.d.setTotalPointsWithAce(Integer.parseInt(lineComp[1]));
				    	    }
				    }
				    else
				    		break;
				    
				    currentLine = reader.readLine();
				    lineComp = currentLine.split(" ");
				    if (lineComp[0].compareTo("Cards") == 0) {
				   
				    		for(int i = 1; i < lineComp.length; i += 2) {
				    			 Card newCard = new Card(lineComp[i + 1],lineComp[i]);
				    			 dc.d.addPoint(newCard);				    			
				    			 deck.remove(newCard);
				    		}
				    }
				    dc.updateDealerUI();
		    			break;
		    			
		    		case "Gamblers":
		    		this.numPlayers = Integer.parseInt(lineComp[1]);
				double width = this.screenSize.getWidth()/(numPlayers + 1) + 50;	
				double totalWidth = 0.0;
				double totalHeight = 0.0;
				height = this.screenSize.getHeight() / 3;
		    		for(int i=0;i<numPlayers;i++) {
		    				GamblerFrame gf = new GamblerFrame(width,height,totalWidth,totalHeight);
		    				gf.setTitle("Player " + String.valueOf(i + 1));
		    				GamblerController gc = new GamblerController(new Gambler(i + 1),gf,this);
		    				gf.gc = gc;
		    				gcs.add(gc);
		    				pfs.add(gf);
		    				totalWidth += width;
		    			}
		    			currentLine = reader.readLine();
					lineComp = currentLine.split(" ");
					currentPlayer = Integer.parseInt(lineComp[2]);
					currentLine = reader.readLine();
					lineComp = currentLine.split(" ");
					numRound = Integer.parseInt(lineComp[2]);
		    			for (int i = 0; i < numPlayers; i++) {
		    				currentLine = reader.readLine();
						lineComp = currentLine.split(" ");
						
						GamblerController current = gcs.get(i);
						current.g = new Gambler(i + 1);
						for (int j = 0; j < 4; j++) {
							//4 porque é a QTD de informacoes a ser lida do aquivo
							switch (lineComp[0]) {
							case "Chips":
								current.g.totalMoneyAvailable = (Integer.parseInt(lineComp[1]));
								break;
							case "Bet":
								current.g.setTotalMoneyBetted(Integer.parseInt(lineComp[1]));
								break;
								
							case "Points":
								 if (lineComp[1].indexOf('/') > 0) {
						    		    String [] bothPoints = lineComp[1].split("/");
						    		    current.g.setTotalPoints(Integer.parseInt(bothPoints[0]));
						    		    current.g.setTotalPointsWithAce(Integer.parseInt(bothPoints[1]));
						         }
						    	    else {
						    	    		current.g.setTotalPoints(Integer.parseInt(lineComp[1]));
						    	    		current.g.setTotalPointsWithAce(Integer.parseInt(lineComp[1]));
						    	    }
								break;
								
							case "Cards":							
								for(int k = 1; k < lineComp.length; k += 2) {
									Card newCard = new Card(lineComp[k + 1],lineComp[k]);
									current.g.playerCards.add(newCard);
					    				deck.remove(newCard);
								}
								break;
							}
							current.updateGamblerUI();
							currentLine = reader.readLine();
							lineComp = currentLine.split(" ");
						}
		    			}
		    			
		    			break;
		    			
		    		default:
		    			System.out.println("GameController : retrieveGame : error");
		    			System.exit(1);
		    		}
		    		reader.readLine();
		    		currentLine = reader.readLine();
		    		if (currentLine != null)
		        		lineComp = currentLine.split(" ");
		    }
		}
		catch (IOException e) {
			System.out.println("GameController : retrieveGame : ioException = " + e.getMessage());
			System.exit(1);			
		}

	
	}
}