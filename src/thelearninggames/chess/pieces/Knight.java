package thelearninggames.chess.pieces;

import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Move;

import java.util.ArrayList;

public class Knight implements Piece {

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
        return moves;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.Knight;
    }

    @Override
    public String toString(){
        return "K";
    }

    Knight(Color color){
        this.color = color;
    }
}
