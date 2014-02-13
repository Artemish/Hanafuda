package hanafuda.server.networking;

import hanafuda.server.game.Card;
import hanafuda.server.game.Game;
import hanafuda.server.game.Player;

public class Message {
	
	static final byte FULL_STATE_UPDATE_ID = 1;
	static final byte SCORE_UPDATE_ID = 2;
	static final byte HAND_UPDATE_ID = 3;
	static final byte SELECTION_ID = 4;
	static final byte STRING_MESSAGE_ID = 5;
	
	static final int FULL_STATE_UPDATE_LENGTH = 27;
	static final int SELECTION_MESSAGE_LENGTH = 3;
	
	byte[] payload;
	byte ID;
	
	static Message fullUpdate(Game game, Player p) {
		Message result = new Message();
		
		result.ID = FULL_STATE_UPDATE_ID;
		
		byte userSideLen = (byte) p.getSideBoard().size();
		byte opponentSideLen = (byte) p.getOpponent().getSideBoard().size();
		int len = 3 + FULL_STATE_UPDATE_LENGTH + userSideLen + opponentSideLen;
		byte[] fullState = new byte[len];
		// Player 1 score - Player 2 score - at index 1
		fullState[0] = (byte) p.getScore();
		fullState[1] = (byte) p.getOpponent().getScore();
		// Has the game ended yet? If so, 1, otherwise 0, at index 2
		fullState[2] = 0;
		// The contents of each player's hands, P1 first, then P2
		// Total size of 24 bytes, begins at 4, ends at 27
		for (int i = 3; i < 11; i++) {
			fullState[i] = p.getHand()[i-3].cardID;
			fullState[i+8] = Card.blindCard(p.getOpponent().getHand()[i-3]).cardID;
			fullState[i+16] = game.board.field[i-3].cardID;
		}
		// Begin sideboard packet region
		fullState[27] = userSideLen;
		fullState[28] = opponentSideLen;
		// Sideboard data begins at index 30 - fullState has total size of 30 bytes
		for (int i = 0; i < userSideLen; i++) {
			fullState[29+i] = p.getSideBoard().get(i).cardID;
		}
		for (int j = 0; j < opponentSideLen; j++) {
			fullState[29+userSideLen+j] = p.getOpponent().getSideBoard().get(j).cardID;
		}
		
		result.payload = fullState;
		
		return result;
	}
	
}
