
class Game {
	
	public Deck deck;
	public Player player1;
	public Player player2;
	public Board board;
	Game() {
		init();
	}
	
	void init() {
		deck = new Deck();
		player1 = new Player();
		player2 = new Player();
		board = new Board();
		deck.shuffle();
		deck.deal(player1);
		deck.deal(player2);
		deck.deal(board);
	}
	
	
	
	
	
}