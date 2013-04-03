import java.util.ArrayList;

class Player{
	ArrayList<Card> hand;
	int points;
	
	
	Player(){
		hand = new ArrayList<Card>();
		points = 0;
	}
	
	void giveCard(Card card) {
		hand.add(card);
	}
	
}