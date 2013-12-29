package hanafuda.server;

import java.io.IOException;
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
	
	
}
