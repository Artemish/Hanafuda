package hanafuda.server;

import java.net.Socket;

public class GameFactory {
	
	synchronized static Game spawnGame() {
		Socket p1s = null, p2s = null;
		
		ServerGUI.setStatus("Waiting for connection from player 1 . . .");
		p1s = ServerSocketLayer.getClient();
		ServerGUI.setStatus("Waiting for connection from player 2 . . .");
		p2s = ServerSocketLayer.getClient();
		
		return new Game(p1s, p2s);
	}
	
}
