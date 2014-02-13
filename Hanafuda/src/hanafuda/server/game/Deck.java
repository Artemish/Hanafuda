package hanafuda.server.game;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
	
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	public Deck() {
		for (Card c : Card.values()) {
			if (c != Card.Null && c != Card.FaceDown) cards.add(c);
		}
	}
	
	void shuffle() {
		Collections.shuffle(cards, new Random(System.nanoTime()));
	}
	
	Card pop() {
		if (cards.size() > 0) {
			Card c = cards.get(0);
			cards.remove(0);
			return c;
		} else {
			System.err.println("The deck ran out of cards, somehow.");
			return null;
		}
	}
	
	void deal(Player p1, Player p2, Board b) {
		for (int i = 0; i < 8; i++) {
			p1.hand[i] = pop();
			p2.hand[i] = pop();
			b.field[i] = pop();
		}		
	}
	
	
}
