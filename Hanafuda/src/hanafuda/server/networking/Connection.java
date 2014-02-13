package hanafuda.server.networking;

import hanafuda.server.game.Card;
import hanafuda.server.game.Game;
import hanafuda.server.game.Player;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connection {
	
	final Socket socket;
	final Game game;
	final Player owner;
	private InputStream inStream;
	private OutputStream outStream;
	PlayerInputThread IOThread;
	
	public Connection(Game g, Socket s, Player p) {
		socket = s;
		game = g;
		owner = p;
	}
	
	final void initialize() throws IOException {
		inStream = socket.getInputStream();
		outStream = socket.getOutputStream();
		IOThread = new PlayerInputThread(game, inStream, owner);
		IOThread.start();
	}
	
	public void readNBytes() throws IOException {
		
		
	}
	
	public void sendMessage(Message m) throws IOException {
		outStream.write(m.ID);
		outStream.write(m.payload);
	}
	
	
}

class PlayerInputThread implements Runnable {
	private InputStream iStream;
	private final Player owner;
	private Thread instance;
	Game game;
	
	public PlayerInputThread(Game g, InputStream is, Player p) throws IOException {
		game = g;
		iStream = is;
		owner = p;
		instance = new Thread(this);
	}
	
	public void start() {
		instance.start();
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
		case Message.SELECTION_ID:
			processSelection();
		default:
		}
		
	}
	
	private void processSelection() {
		byte[] command = new byte[Message.SELECTION_MESSAGE_LENGTH];
		if (ServerSocketLayer.safeRead(iStream, command, command.length)) {
			int id = command[0] << 24 + command[1] << 16 + command[2] << 8 + command[3];
			if (id != owner.getID()) return;
			game.makeSelection(owner, Card.getByID(command[4]), Card.getByID(command[5]));
		} else {
			
			
		}
		
		
		
	}
	
}
