package thelearninggames.chess.pieces;

import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Move;

import java.util.ArrayList;

public class Queen implements Piece {

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
    public ArrayList<Integer> getValidMoves(int row , int col, boolean isToEmpty) {
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
        temp = d.RIGHT;
        i = 2;
        while(temp != d.CENTER){
            moves.add(temp);
            temp = d.getRIGHT(i);
            i++;
        }
        temp = d.LEFT;
        i = 2;
        while(temp != d.CENTER){
            moves.add(temp);
            temp = d.getLEFT(i);
            i++;
        }
        temp = d.DOWN;
        i = 2;
        while(temp != d.CENTER){
            moves.add(temp);
            temp = d.getDOWN(i);
            i++;
        }
        temp = d.UP;
        i = 2;
        while(temp != d.CENTER){
            moves.add(temp);
            temp = d.getUP(i);
            i++;
        }
        return moves;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.Queen;
    }

    @Override
    public String toString(){
        return "Q";
    }

    Queen(Color color){
        this.color = color;
    }
}
