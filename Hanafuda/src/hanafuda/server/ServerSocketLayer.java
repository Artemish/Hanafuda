package hanafuda.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketLayer {
	
	static final int PORT = 5533;
	
	static final byte FULL_STATE_UPDATE_ID = 1;
	static final byte SCORE_UPDATE_ID = 2;
	static final byte HAND_UPDATE_ID = 3;
	static final byte SELECTION_ID = 4;
	
	static final int FULL_STATE_UPDATE_LENGTH = 27;
	static final int SELECTION_UPDATE_LENGTH = 3;
	
	
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
	
	/* it fucking works don't question it */
	static int sendBytes(Socket s, byte[] message) {
		try { s.getOutputStream().write(message); }
		catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
	
}
