package thelearninggames.chess.player;

import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Move;

public class CPUPlayer implements Player {

    Color color;

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Move getMove(final GameState state) {
        return null;
    }

    CPUPlayer(Color c){
        color = c;
    }
}
