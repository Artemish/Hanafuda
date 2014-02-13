package hanafuda.server.networking;

import hanafuda.server.game.Game;
import hanafuda.server.game.Player;

import java.io.IOException;
import java.net.Socket;

public class GameFactory {
	
	synchronized static Game spawnGame() {
		Socket p1s = null, p2s = null;
		Player p1 = new Player(), p2 = new Player();
		Game current = new Game(p1, p2);
		
		ServerGUI.setStatus("Waiting for connection from player 1 . . .");
		p1s = ServerSocketLayer.getClient();
		ServerGUI.setStatus("Waiting for connection from player 2 . . .");
		p2s = ServerSocketLayer.getClient();
		
		Connection p1c = new Connection(current, p1s, p1);
		Connection p2c = new Connection(current, p2s, p2);
		try {
			p1c.initialize();
			p2c.initialize();
		} catch (IOException e) {
			System.err.println("Socket connections failed somehow.");
		}
		
		p1.connect(p1c);
		p2.connect(p2c);
		
		ServerGUI.setStatus("Connections established.");
		return current;
	}
	
}
