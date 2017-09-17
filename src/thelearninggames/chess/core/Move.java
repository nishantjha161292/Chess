package thelearninggames.chess.core;

public class Move {

    public Move(int f, int t){
        if(f < 0 || f > 63 || t < 0 || t > 63)
            throw new IllegalArgumentException("move out of board");
        from = f;
        to = t;
    }

    public int getFrom() {
        return from;
    }

    private int from;

    public int getTo() {
        return to;
    }

    private int to;

    public Move getInverseMove(){
        return new Move(to, from);
    }

}
