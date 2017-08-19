package thelearninggames.chess.player;

import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Move;
import java.util.Scanner;

public class UIPlayer implements Player {

    Color color;

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Move getMove(final GameState state) {

        return new Move(0,0);
    }

    UIPlayer(Color c){
        this.color = c;
    }
}
