package thelearninggames.chess.player;
import thelearninggames.chess.core.Pair;
import thelearninggames.chess.ui.GameBoardServices;


public class UIInput implements InputManager {

    int from = 0;
    int to = 0;

    public void update(){
        Pair<Integer, Integer> move = GameBoardServices.getObject().getLastMove();
        System.out.println("in update");
        from = move.fst;
        to = move.snd;
    }

    @Override
    public int getFrom() {
        System.out.println(from);
        return from;
    }

    @Override
    public int getTo() {
    	System.out.println(to);
        return to;
    }
}
