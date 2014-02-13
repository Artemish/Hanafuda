package hanafuda.server.networking;

import hanafuda.server.game.Game;

import java.util.ArrayList;

public class ServerMain {
	
	private static ArrayList<Game> games = new ArrayList<Game>();
	
	static int currentID = 0;
	
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
		g.start();
	}
	
	static int getID() {
		return currentID++;
	}
	
}
