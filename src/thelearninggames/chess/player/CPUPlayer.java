package thelearninggames.chess.player;

import thelearninggames.chess.Color;
import thelearninggames.chess.GameState;
import thelearninggames.chess.Move;

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
