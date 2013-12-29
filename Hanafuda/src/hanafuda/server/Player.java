package hanafuda.server;

public class Player {
	int score;
	Card[] hand;
	
	public Player() {
		score = 0;
		hand = new Card[8];
		for (int i = 0; i < 8; i++) hand[i] = Card.Null;
	}
	
	
	
	
}
