package thelearninggames.chess.pieces;

import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Move;

import java.util.ArrayList;

public class Bishop implements Piece {

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
    public PieceType getPieceType() {
        return PieceType.Bishop;
    }

    @Override
    public ArrayList<Integer> getValidMoves() {
        int row = pos / 8;
        int col = pos % 8;
        ArrayList<Integer> moves = new ArrayList<>();
        Direction d = new Direction(row, col);
        int temp = d.UPRIGHT;
        int i = 2;
        while(temp != d.CENTER){
            moves.add(temp);
            temp = d.getUPRIGHT(i);
            i++;
        }
        temp = d.UPLEFT;
        i = 2;
        while(temp != d.CENTER){
            moves.add(temp);
            temp = d.getUPLEFT(i);
            i++;
        }
        temp = d.DOWNRIGHT;
        i = 2;
        while(temp != d.CENTER){
            moves.add(temp);
            temp = d.getDOWNRIGHT(i);
            i++;
        }
        temp = d.DOWNLEFT;
        i = 2;
        while(temp != d.CENTER){
            moves.add(temp);
            temp = d.getDOWNLEFT(i);
            i++;
        }
        return moves;

    }

    @Override
    public String toString(){
        return "B";
    }

    Bishop(Color color){
        this.color = color;
    }
}
