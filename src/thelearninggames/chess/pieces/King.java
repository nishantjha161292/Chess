package thelearninggames.chess.pieces;

import thelearninggames.chess.Color;
import thelearninggames.chess.GameState;
import thelearninggames.chess.Move;

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
    public String toString(){
        return "Ki";
    }

    King(Color color){
        this.color = color;
    }
}