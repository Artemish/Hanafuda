package hanafuda.server;

import java.net.Socket;

public class Game {
	private Player player1, player2;
	private Board  board;
	private Deck   deck;
	
	public Game(Socket p1s, Socket p2s) {
		
	}
	
	void initialize() {
		player1 = new Player();
		player2 = new Player();
		board = new Board();
		deck = new Deck();
		
		deck.shuffle();
		deck.deal(player1, player2, board);
	}
	
	
	
}
