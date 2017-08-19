package thelearninggames.chess.player;

import thelearninggames.chess.Color;
import thelearninggames.chess.GameState;
import thelearninggames.chess.Move;

public interface Player {

    Color getColor();
    Move getMove(final GameState state);
}
