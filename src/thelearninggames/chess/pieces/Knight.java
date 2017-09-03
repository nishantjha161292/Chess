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
        Direction d = new Direction(row, col);

        if (row > 1 && row < 6){
            if(col > 1 && col < 6) {
                moves.add(new Direction(d.getUP(2) / 8, d.getUP(2) % 8).RIGHT);
                moves.add(new Direction(d.getUP(2) / 8, d.getUP(2) % 8).LEFT);
                moves.add(new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8).RIGHT);
                moves.add(new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8).LEFT);
                moves.add(new Direction(d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).UP);
                moves.add(new Direction(d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).DOWN);
                moves.add(new Direction(d.getLEFT(2) / 8, d.getLEFT(2) % 8).UP);
                moves.add(new Direction(d.getLEFT(2) / 8, d.getLEFT(2) % 8).DOWN);
            }
            if(col == 0){
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).RIGHT);
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8).RIGHT);
                moves.add( new Direction(d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).UP);
                moves.add( new Direction(d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).DOWN);
            }
            if(col == 7){
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).LEFT);
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8).LEFT);
                moves.add( new Direction(d.getLEFT(2) / 8, d.getLEFT(2) % 8).UP);
                moves.add( new Direction(d.getLEFT(2) / 8, d.getLEFT(2) % 8).DOWN);
            }
            if(col == 1){
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).RIGHT);
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8).RIGHT);
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).LEFT);
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8).LEFT);
                moves.add( new Direction(d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).UP);
                moves.add( new Direction(d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).DOWN);
            }
            if(col == 6){
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).LEFT);
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8).LEFT);
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).RIGHT);
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8).RIGHT);
                moves.add( new Direction(d.getLEFT(2) / 8, d.getLEFT(2) % 8).UP);
                moves.add( new Direction(d.getLEFT(2) / 8, d.getLEFT(2) % 8).DOWN);
            }
        }
        if(row == 7){
            if(col > 1 && col < 6){
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).RIGHT);
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).LEFT);
                moves.add( new Direction(d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).UP);
                moves.add( new Direction(d.getLEFT(2) / 8, d.getLEFT(2) % 8).UP);
            }
            if(col == 0){
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).RIGHT);
                moves.add( new Direction(d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).UP);
            }
            if(col == 1){
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).RIGHT);
                moves.add( new Direction(d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).UP);
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).LEFT);

            }
            if(col == 6){
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).RIGHT);
                moves.add( new Direction(d.getLEFT(2) / 8, d.getLEFT(2) % 8).UP);
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).LEFT);
            }
            if(col == 7){
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).LEFT);
                moves.add( new Direction(d.getLEFT(2) / 8, d.getLEFT(2) % 8).UP);
            }
        }
        if(row == 6){
            if(col > 1 && col < 6){
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).RIGHT);
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).LEFT);
                moves.add( new Direction(d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).UP);
                moves.add( new Direction(d.getLEFT(2) / 8, d.getLEFT(2) % 8).UP);
                moves.add( new Direction( d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).DOWN);
                moves.add( new Direction( d.getLEFT(2) / 8, d.getLEFT(2) % 8).DOWN);
            }
            if(col == 0){
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).RIGHT);
                moves.add( new Direction(d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).UP);
                moves.add( new Direction( d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).DOWN);

            }
            if(col == 1){
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).RIGHT);
                moves.add( new Direction(d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).UP);
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).LEFT);
                moves.add( new Direction( d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).DOWN);
            }
            if(col == 6){
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).RIGHT);
                moves.add( new Direction(d.getLEFT(2) / 8, d.getLEFT(2) % 8).UP);
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).LEFT);
                moves.add( new Direction( d.getLEFT(2) / 8, d.getLEFT(2) % 8).DOWN);

            }
            if(col == 7){
                moves.add( new Direction(d.getUP(2) / 8, d.getUP(2) % 8).LEFT);
                moves.add( new Direction(d.getLEFT(2) / 8, d.getLEFT(2) % 8).UP);
                moves.add( new Direction( d.getLEFT(2) / 8, d.getLEFT(2) % 8).DOWN);
            }
        }
        if(row == 0){
            if(col > 1 && col < 6){
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8).RIGHT);
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8).LEFT);
                moves.add( new Direction( d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).DOWN);
                moves.add( new Direction( d.getLEFT(2) / 8, d.getLEFT(2) % 8).DOWN);
            }
            if(col == 0){
                moves.add( new Direction(d.getRIGHT(2) / 8, d.getRIGHT(2) % 8 ).DOWN);
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8 ).RIGHT);
            }
            if(col == 1){
                moves.add( new Direction(d.getRIGHT(2) / 8, d.getRIGHT(2) % 8 ).DOWN);
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8 ).RIGHT);
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8 ).LEFT);
            }
            if(col == 6){
                moves.add( new Direction(d.getLEFT(2) / 8, d.getLEFT(2) % 8 ).DOWN);
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8 ).RIGHT);
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8 ).LEFT);
            }
            if(col == 7){
                moves.add( new Direction(d.getLEFT(2) / 8, d.getLEFT(2) % 8 ).DOWN);
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8 ).LEFT);
            }
        }
        if(row == 1){
            if(col > 1 && col < 6){
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8).RIGHT);
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8).LEFT);
                moves.add( new Direction(d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).UP);
                moves.add( new Direction(d.getLEFT(2) / 8, d.getLEFT(2) % 8).UP);
                moves.add( new Direction( d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).DOWN);
                moves.add( new Direction( d.getLEFT(2) / 8, d.getLEFT(2) % 8).DOWN);
            }
            if(col == 0){
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8).RIGHT);
                moves.add( new Direction(d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).UP);
                moves.add( new Direction( d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).DOWN);

            }
            if(col == 1){
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8).RIGHT);
                moves.add( new Direction(d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).UP);
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8).LEFT);
                moves.add( new Direction( d.getRIGHT(2) / 8, d.getRIGHT(2) % 8).DOWN);
            }
            if(col == 6){
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8).RIGHT);
                moves.add( new Direction(d.getLEFT(2) / 8, d.getLEFT(2) % 8).UP);
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8).LEFT);
                moves.add( new Direction( d.getLEFT(2) / 8, d.getLEFT(2) % 8).DOWN);

            }
            if(col == 7){
                moves.add( new Direction(d.getDOWN(2) / 8, d.getDOWN(2) % 8).LEFT);
                moves.add( new Direction(d.getLEFT(2) / 8, d.getLEFT(2) % 8).UP);
                moves.add( new Direction( d.getLEFT(2) / 8, d.getLEFT(2) % 8).DOWN);
            }
        }
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
