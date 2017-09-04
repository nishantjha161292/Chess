package thelearninggames.chess.ui;

import thelearninggames.chess.core.GameState;
import thelearninggames.chess.player.Player;

public interface ChessBoard {

	void update(Player currentPlayer, GameState state);
}
