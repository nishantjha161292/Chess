package thelearninggames.chess.player;

import com.sun.tools.javac.util.Pair;
import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Move;
import thelearninggames.chess.ui.GameUI;
import thelearninggames.chess.ui.SwingUI;


public class UIInput implements InputManager {

    GameUI ui;

    UIInput(GameUI ui){
        this.ui = ui;
    }

    int from = -1;
    int to = -1;

    void update(){
        Pair<Integer, Integer> move = ui.getLastMove();
        from = move.fst;
        to = move.snd;
    }

    @Override
    public int getFrom() {
        update();
        return from;
    }

    @Override
    public int getTo() {
        return to;
    }
}
