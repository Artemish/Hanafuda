package hanafuda.server;

import java.net.Socket;
import java.util.ArrayList;

public class Player {
	int score;
	Card[] hand;
	
	ArrayList<Card> sideBoard;
	
	Socket connection;
	
	final int playerID;
	
	public Player opponent;
	
	public Player(Socket s, int playerID) {
		connection = s;
		score = 0;
		hand = new Card[8];
		for (int i = 0; i < 8; i++) hand[i] = Card.Null;
		sideBoard = new ArrayList<Card>();
		this.playerID = playerID;
	}
	
	
	
	
}


