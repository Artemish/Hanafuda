package hanafuda.server;

import java.net.Socket;

public class GameFactory {
	
	synchronized static Game spawnGame() {
		Socket p1s = null, p2s = null;
		
		ServerGUI.setStatus("Waiting for connection from player 1 . . .");
		p1s = ServerSocketLayer.getClient();
		Player p1 = new Player(p1s, ServerMain.getID());
		ServerGUI.setStatus("Waiting for connection from player 2 . . .");
		p2s = ServerSocketLayer.getClient();
		Player p2 = new Player(p2s, ServerMain.getID());
		p1.opponent = p2;
		p2.opponent = p1;
		ServerGUI.setStatus("Connections established.");
		return new Game(p1, p2);
	}
	
}
