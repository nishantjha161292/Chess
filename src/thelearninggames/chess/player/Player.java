package thelearninggames.chess.player;

import thelearninggames.chess.InputOutput.InputManager;
import thelearninggames.chess.InputOutput.OutputManager;
import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Move;

public class Player{

    InputManager in;
    OutputManager out;
    Color color;
    Move m;

    public Color getColor(){
        return color;
    }

    public Move getMove(final GameState state){

        int from = in.getFrom();
        int to = in.getTo();
        while(from == -1 || to == -1){
            from = in.getFrom();
            to = in.getTo();
        }
        m = new Move(from, to);

        if(out != in){
            out.setFrom(from);
            out.setTo(to);
        }

        return m;
    }

    public Player(InputManager i, Color c){
        in = i;
        color = c;
    }

    public Player(InputManager i, Color c, OutputManager o){
        in = i;
        color = c;
        out = o;
    }
}
