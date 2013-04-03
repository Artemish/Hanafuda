import java.util.ArrayList;


public class Board {
	ArrayList<Card> board;
	Board(){
		board = new ArrayList<Card>();
	}
	void giveCard(Card card) {
		board.add(card);
	}
}
