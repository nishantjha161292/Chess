package thelearninggames.chess.player;

import com.sun.tools.javac.util.Pair;
import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Move;
import thelearninggames.chess.ui.GameUI;


public class UIPlayer implements Player {

    Color color;

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Move getMove(final GameState state) {
        int first = -1, second = -1;
        while(first == -1 || second == -1) {
            Pair<Integer, Integer> p = GameUI.getLast2Clicks();
            first = p.fst;
            second = p.snd;
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new Move(first,second);
    }

    UIPlayer(Color c){
        this.color = c;
    }
}
