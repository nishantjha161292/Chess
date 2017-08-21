package thelearninggames.chess.pieces;

import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Move;

import java.util.ArrayList;

public class King implements Piece {

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
        Direction d = new Direction(row,col);

        if(row > 0 && row < 7){
            if(col == 0){
                moves.add( d.RIGHT);
                moves.add( d.UP );
                moves.add( d.UPRIGHT );
                moves.add( d.DOWN );
                moves.add( d.DOWNRIGHT);
            }
            else if(col == 7){
                moves.add( d.LEFT);
                moves.add( d.UP );
                moves.add( d.UPLEFT );
                moves.add( d.DOWN );
                moves.add( d.DOWNLEFT);
            }
            else if(col > 0 && col < 7){
                moves.add(d.UP);
                moves.add(d.DOWN);
                moves.add(d.LEFT);
                moves.add(d.RIGHT);
                moves.add(d.UPRIGHT);
                moves.add(d.UPLEFT);
                moves.add(d.DOWNRIGHT);
                moves.add(d.DOWNLEFT);
            }
        }
        if(row == 0) {
            moves.add( d.UP );
            if(col == 0){
                moves.add( d.UPRIGHT );
                moves.add( d.RIGHT );
            }
            else if(col == 7){
                moves.add( d.UPLEFT );
                moves.add( d.LEFT );
            }
            else if (col > 0 && col < 7){
                moves.add( d.UPRIGHT);
                moves.add( d.UPLEFT);
                moves.add( d.RIGHT);
                moves.add( d.LEFT);
            }
        }
        if(row == 7){
            moves.add( d.DOWN );
            if(col == 0){
                moves.add( d.DOWNRIGHT );
                moves.add( d.RIGHT );
            }
            else if(col == 7){
                moves.add( d.DOWNLEFT );
                moves.add( d.LEFT );
            }
            else if(col > 0 && col < 7){
                moves.add( d.DOWNRIGHT);
                moves.add( d.DOWNLEFT);
                moves.add( d.RIGHT);
                moves.add( d.LEFT);
            }
        }

        return moves;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.King;
    }

    @Override
    public String toString(){
        return "Ki";
    }

    King(Color color){
        this.color = color;
    }
}
