
class Game {
	
	public Deck deck;
	public Player player1;
	public Player player2;
	
	Game() {
		init();
	}
	
	void init() {
		player1 = new Player();
		player2 = new Player();
		deck.shuffle();
		deck.deal(player1);
		deck.deal(player2);
	}
	
	
	
	
	
}