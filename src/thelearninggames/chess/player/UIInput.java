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

    @Override
    public int getFrom() {
        return ui.getLastMove().fst;
    }

    @Override
    public int getTo() {
        return ui.getLastMove().snd;
    }
}
