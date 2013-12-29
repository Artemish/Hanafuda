package hanafuda.server;

public class Board {
	
	Card[] board = new Card[8];
	
	public Board() {
		for (int i = 0; i < 8; i++) board[i] = Card.Null;		
	}
	
	void removeCard(int index) {
		board[index] = Card.Null;
	}
	
	
}
