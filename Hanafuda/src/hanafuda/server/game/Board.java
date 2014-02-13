package hanafuda.server.game;

public class Board {
	
	public Card[] field = new Card[8];
	
	public Board() {
		for (int i = 0; i < 8; i++) field[i] = Card.Null;		
	}
	
	void removeCard(int index) {
		field[index] = Card.Null;
	}
	
	
}
