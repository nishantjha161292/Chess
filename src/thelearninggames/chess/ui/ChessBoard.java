package thelearninggames.chess.ui;

import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Pair;
import thelearninggames.chess.player.Player;

public interface ChessBoard {

	 void startGame();
	 boolean moveDidHappen();
	 Pair<Integer,Integer> getLastMove();
	 void updateBoard(Player currentPlayer, GameState state);
}
