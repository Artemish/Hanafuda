package hanafuda.server.game;

import hanafuda.server.networking.Connection;

import java.util.ArrayList;

public class Player {
	int score;
	Card[] hand;
	
	ArrayList<Card> sideBoard;
	
	private int playerID;
	
	Player opponent;
	Game currentGame;
	private Connection playerConnection;
	
	public Player() {
		score = 0;
		hand = new Card[8];
		
		for (int i = 0; i < 8; i++) hand[i] = Card.Null;
		
		sideBoard = new ArrayList<Card>();
	}
	
	public void connect(Connection c) {
		playerConnection = c;
	}
	
	public Player getOpponent() {
		return opponent;
	}
	
	public int getID() {
		return playerID;
	}
	
	public ArrayList<Card> getSideBoard() {
		return sideBoard;
	}
	
	public Card[] getHand() {
		return hand;
	}
	
	public int getScore() {
		return score;
	}
	
	public Connection getConnection() {
		return playerConnection;
	}
	
}