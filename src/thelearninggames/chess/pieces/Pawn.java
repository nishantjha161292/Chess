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
        if(row < 7)
            moves.add((row + 1) * 8 + col);
        if(row > 0)
            moves.add((row - 1) * 8 + col);
        if(col < 7)
            moves.add(row * 8 + col + 1);
        if(col > 0)
            moves.add(row * 8 + col - 1);
        //Forward Diagonal for killing enemy
        if(this.getColor() == Color.WHITE && row < 7 && col < 7 && !isToEmpty)
            moves.add((row + 1)* 8 + col + 1);
        if(this.getColor() == Color.WHITE && row < 7 && col > 0 && !isToEmpty)
            moves.add((row + 1)* 8 + col - 1);
        //Backward Diagonal for killing enemy
        if(this.getColor() == Color.BLACK && row > 0 && col < 7 && !isToEmpty)
            moves.add((row - 1)* 8 + col + 1);
        if(this.getColor() == Color.BLACK && row > 0  && col > 0 && !isToEmpty)
            moves.add((row - 1)* 8 + col - 1);
        // move 2 steps from init location
        if(this.getColor() == Color.WHITE && row == 1)
            moves.add((row + 2) * 8 + col);
        if(this.getColor() == Color.BLACK && row == 6)
            moves.add((row - 2) * 8 + col);

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
