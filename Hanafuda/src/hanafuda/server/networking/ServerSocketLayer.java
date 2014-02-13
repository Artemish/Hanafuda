package hanafuda.server.networking;

import hanafuda.server.game.Game;
import hanafuda.server.game.Player;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketLayer {
	
	static final int PORT = 5533;
	static private ServerSocket ss;
	
	static void initialize() {
		try {
			ss = new ServerSocket(PORT);
		} catch (IOException e) {
			System.err.println("Couldn't initialize SS layer.");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	static synchronized Socket getClient() {
		Socket client = null;
		try {
			client = ss.accept();
		} catch (IOException e) {
			System.err.println("Could not get client connection.");
			e.printStackTrace();
			System.exit(1);
		}
		
		return client;
	}
		
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
	
	public static boolean fullUpdate(Game g, Player p) {
		Message m = Message.fullUpdate(g, p);
		try {
			p.getConnection().sendMessage(m);
		} catch (IOException e) {
			System.out.println("Failed somehow.");
			return false;
		}
		return true;
	}
	
}
