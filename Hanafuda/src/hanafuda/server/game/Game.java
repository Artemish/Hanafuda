package hanafuda.server.game;

import hanafuda.server.networking.ServerSocketLayer;

public class Game {
	private Player player1, player2;
	public Board  board;
	private Deck   deck;
	public boolean running;
	
	Player current;
	
	public Game(Player p1, Player p2) {
		player1 = p1;
		player2 = p2;
		board = new Board();
		deck = new Deck();	
		deck.shuffle();
		deck.deal(player1, player2, board);
		running = false;
		current = p1;
		p1.opponent = p2;
		p2.opponent = p1;
	}
	
	public void start() {
		if (!running) {
			running = true;
			sendUpdates();
		}
	}
	
	void sendUpdates() {
		ServerSocketLayer.fullUpdate(this, player1);
		ServerSocketLayer.fullUpdate(this, player2);
	}
	
	public void makeSelection(Player p, Card handSelection, Card boardSelection) {
		if (Card.combos(handSelection, boardSelection)) {
			
			
			
		} else {
			
			
		}
		
	}
	
}