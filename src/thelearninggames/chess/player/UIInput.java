package thelearninggames.chess.player;
import thelearninggames.chess.core.Pair;
import thelearninggames.chess.ui.ChessBoard;


public class UIInput implements InputManager {

    int from = 0;
    int to = 0;
   
    public void update(ChessBoard board){
        Pair<Integer, Integer> move = board.getLastMove();
        System.out.println("Moving from: "+ from+ "  to: "+ to);
        from = move.fst;
        to = move.snd;
    }

    @Override
    public int getFrom() {
        return from;
    }

    @Override
    public int getTo() {
        return to;
    }
}
