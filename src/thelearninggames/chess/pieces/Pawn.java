package thelearninggames.chess.pieces;

import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Move;

import java.util.ArrayList;

public class Pawn implements Piece {

    Color color;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    int pos;

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public ArrayList<Integer> getValidMoves(int row, int col, boolean isToEmpty) {
        ArrayList<Integer> moves = new ArrayList<>();
        Direction d = new Direction(row, col);

        //Move forward/backward when empty
        if(this.getColor() == Color.WHITE && row < 7 && isToEmpty)
            moves.add( d.DOWN );
        if(this.getColor() == Color.BLACK && row < 7 && isToEmpty)
            moves.add( d.UP );
        //Forward Diagonal for killing enemy
        if(this.getColor() == Color.WHITE && row < 7 && col < 7 && !isToEmpty)
            moves.add( d.DOWNRIGHT );
        if(this.getColor() == Color.WHITE && row < 7 && col > 0 && !isToEmpty)
            moves.add( d.DOWNLEFT );
        //Backward Diagonal for killing enemy
        if(this.getColor() == Color.BLACK && row > 0 && col < 7 && !isToEmpty)
            moves.add( d.UPRIGHT );
        if(this.getColor() == Color.BLACK && row > 0  && col > 0 && !isToEmpty)
            moves.add( d.UPLEFT );
        // move 2 steps from init location
        if(this.getColor() == Color.WHITE && row == 1)
            moves.add( d.getDOWN(2) );
        if(this.getColor() == Color.BLACK && row == 6)
            moves.add( d.getUP(2) );

        return moves;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.Pawn;
    }

    @Override
    public String toString(){
        return "P";
    }

    Pawn(Color color){
        this.color = color;
    }
}
