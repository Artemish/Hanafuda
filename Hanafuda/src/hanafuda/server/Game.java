package hanafuda.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Game {
	private Player player1, player2;
	private Board  board;
	private Deck   deck;	
	boolean running;
	
	Player current;
	
	public Game(Player p1, Player p2) {
		player1 = p1;
		player2 = p2;
		board = new Board();
		deck = new Deck();	
		deck.shuffle();
		deck.deal(player1, player2, board);
		running = false;
		current = p1;
	}
	
	public void start() {
		if (!running) {
			fullUpdate(player1);
			fullUpdate(player2);
			running = true;
		}
	}
	
	private void fullUpdate(Player p) {
		byte p1SideLen = (byte) player1.sideBoard.size();
		byte p2SideLen = (byte) player2.sideBoard.size();
		int len = 3 + ServerSocketLayer.FULL_STATE_UPDATE_LENGTH + p1SideLen + p2SideLen;
		byte[] fullState = new byte[len];
		
		fullState[0] = ServerSocketLayer.FULL_STATE_UPDATE_ID;
		// Player 1 score - Player 2 score - at index 1
		fullState[1] = fullState[2] = 0;
		// Has the game ended yet? If so, 1, otherwise 0, at index 3
		fullState[3] = 0;
		// The contents of each player's hands, P1 first, then P2
		// Total size of 24 bytes, begins at 4, ends at 27
		for (int i = 4; i < 12; i++) {
			fullState[i] = p.hand[i-4].cardID;
			fullState[i+8] = Card.blindCard(p.opponent.hand[i-4]).cardID;
			fullState[i+16] = board.board[i-4].cardID;
		}
		// Begin sideboard packet region
		fullState[28] = p1SideLen;
		fullState[29] = p2SideLen;
		// Sideboard data begins at index 30 - fullState has total size of 30 bytes
		for (int i = 0; i < p1SideLen; i++) {
			fullState[30+i] = player1.sideBoard.get(i).cardID;
		}
		for (int j = 0; j < p1SideLen; j++) {
			fullState[30+p1SideLen+j] = player1.sideBoard.get(j).cardID;
		}
		
		ServerSocketLayer.sendBytes(p.connection, fullState);
	}
	
	void makeSelection(Player p, Card handSelection, Card boardSelection) {
		
		
	}
	
}

class InputThread implements Runnable {
	Game game;
	InputStream iStream;
	final Player owner;
	
	public InputThread(Game g, Socket s, Player p) throws IOException {
		game = g;	
		iStream = s.getInputStream();
		owner = p;
	}
	
	public void run() {
		while (game.running) {
			getAndProcessMessage();
		}
	}
	
	protected void getAndProcessMessage() {
		byte b = -1;
		try { b = (byte) iStream.read(); }
		catch (IOException e) {
			// End of stream?
			System.err.println("IOException in input thread.");
			System.exit(1);
		}
		
		switch (b) {
		case ServerSocketLayer.SELECTION_ID:
			processSelection();
		default:
		}
		
		
	}
	
	private void processSelection() {
		byte[] command = new byte[ServerSocketLayer.SELECTION_UPDATE_LENGTH];
		if (MessageUtils.safeRead(iStream, command, command.length)) {
			int id = command[0] << 24 + command[1] << 16 + command[2] << 8 + command[3];
			if (id != owner.playerID) return;
			
			
		} else {
			
			
		}
		
		
		
	}
	
}

class OutputThread implements Runnable {
	public void run() {
		
	}
	
}

class MessageUtils {
	
	static boolean safeRead(InputStream iStream, byte[] dest, int len) {
		try { iStream.read(dest, 0, len); }
		catch (IOException e) { return false; }
		
		return true;
	}
	
	static boolean safeRead(InputStream iStream, byte[] dest, int offset, int len) {
		try { iStream.read(dest, offset, len); }
		catch (IOException e) { return false; }
		
		return true;
	}
	
}