import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Deck {
	
	ArrayList<Card> deck = new ArrayList<Card>();
	
	Deck(){
		for (Card c : Card.values()){
			deck.add(c);
		}
	}
	
	int cardsRemaining() {
		return deck.size();
	}
	
	void shuffle(){
		Collections.shuffle(deck, new Random(System.nanoTime()));
	}
	
	void deal(Player player){
		for (int i = 0; i < 8; i++) {
            player.giveCard(deck.get(0));
            deck.remove(0);
        }
	}
	void deal(Board board){
		for (int i = 0; i < 8; i++) {
            board.giveCard(deck.get(0));
            deck.remove(0);
        }
	}

	void drawcard(){
		
	}
}