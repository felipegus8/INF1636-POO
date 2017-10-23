package controller;
import model.*;
import view.*;
import java.awt.*;
import java.util.ArrayList;

public class GameController {
	private Card[] deck;
	private TableFrame tf;
	private int numPlayers;
	private ArrayList<PlayerFrame> pfs = new ArrayList<PlayerFrame>();
	private Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	
	
	public GameController(TableFrame tf) {
		this.tf = tf;
		
	}
	
	public GameController() {
		FirstScreen fs = new FirstScreen(this);
		fs.setTitle("First Screen");
		fs.setVisible(true);
		
	}
	
	public void generateFrames(int numPlayers) {
		this.numPlayers = numPlayers;
		double width = this.screenSize.getWidth()/(numPlayers + 1);
		double height = this.screenSize.getHeight()/(numPlayers + 1);
		
		double totalWidth = 0.0;
		double totalHeight = 0.0;
		
		for(int i=0;i<numPlayers;i++) {
			pfs.add(new PlayerFrame(width,height,totalWidth,totalHeight));
			totalWidth += width + 5;
		}
	}
	
	
}
