package thelearninggames.chess.player;

import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Move;

public interface Player {

    Color getColor();
    Move getMove(final GameState state);
}
