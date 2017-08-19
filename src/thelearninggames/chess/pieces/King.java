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
    public ArrayList<Move> getValidMoves(GameState state) {
        return null;
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
