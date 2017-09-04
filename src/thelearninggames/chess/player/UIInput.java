package thelearninggames.chess.player;
import thelearninggames.chess.core.Pair;
import thelearninggames.chess.ui.GameBoardServices;


public class UIInput implements InputManager {

    int from = -1;
    int to = -1;

    void update(){
        Pair<Integer, Integer> move = GameBoardServices.getObject().getLastMove();
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
