package hanafuda.server;

import java.util.ArrayList;

public class ServerMain {
	
	private static ArrayList<Game> games = new ArrayList<Game>();
	
	public static void main(String[] args) {
		ServerGUI.initialize();
		ServerSocketLayer.initialize();
		while (true) {
			spawnGame();
		}
	}
	
	static void spawnGame() {
		Game g = GameFactory.spawnGame();
		games.add(g);
		g.initialize();
	}
	
	
	
}
