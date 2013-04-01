
public class Hanafuda {

	
	public static void main(String[] args){
		Deck deck = new Deck();
		Player player1 = new Player();
		Player player2 = new Player();
		deck.shuffle();
		deck.deal();
	}
}
class Deck {
	int[] deck = new int[48];
	Deck(){

		for (int loop = 1; loop < 49; loop++){
			deck[loop-1] = loop;
		}
	}
	void shuffle(){
		for (int i = 0; i < 48; i++) {
            int r = i + (int) (Math.random() * (48-i));
            int t = deck[r];
            deck[r] = deck[i];
            deck[i] = t;
        }
	}
	void deal(){
		for (int i = 0; i < 48; i++) {
            System.out.println(deck[i]);
        }
	}
	void drawcard(){
		
	}
}
class Player{
	Player(){
		Hand hand = new Hand();
	}
}
class Hand {
	int[] cards = new int[8];
	Hand(){
		for (int loop = 0; loop < 8; loop++){
			cards[loop] = 0;
		}
	}
}
