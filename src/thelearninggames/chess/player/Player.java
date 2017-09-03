package thelearninggames.chess.player;

import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Move;

public class Player{

    InputManager in;
    Color color;

    public Color getColor(){
        return color;
    }

    public Move getMove(final GameState state){
        return new Move(in.getFrom(),in.getTo());
    }

    public Player(InputManager i, Color c){
        in = i;
        color = c;
    }
}
