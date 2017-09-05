package thelearninggames.chess.pieces;

public class Direction {

    public final int UP;
    public final int DOWN;
    public final int RIGHT;
    public final int LEFT;
    public final int UPRIGHT;
    public final int UPLEFT;
    public final int DOWNRIGHT;
    public final int DOWNLEFT;
    public final int CENTER;

    private int row, col;
    Direction(int row, int col){

        this.row = row;
        this.col = col;

        int temp;

        CENTER = (row * 8 + col);
        temp = ((row + 1) * 8 + col); //Forward in memory
        DOWN = (temp < 0 || temp > 63) ? CENTER : temp;

        temp = ((row - 1) * 8 + col); //Backward in memory
        UP = (temp < 0 || temp > 63) ? CENTER : temp;

        temp = (row * 8 + col + 1); //Forward in memory
        RIGHT = (temp < 0 || temp > 63) ? CENTER : temp;

        temp = (row * 8 + col - 1); // Backward in memory
        LEFT = (temp < 0 || temp > 63) ? CENTER : temp;

        temp = ((row + 1) * 8 + col + 1);
        DOWNRIGHT = (temp < 0 || temp > 63) ? CENTER : temp;

        temp = ((row + 1) * 8 + col - 1);
        DOWNLEFT = (temp < 0 || temp > 63) ? CENTER : temp;

        temp = ((row - 1) * 8 + col + 1);
        UPRIGHT = (temp < 0 || temp > 63) ? CENTER : temp;

        temp = ((row - 1) * 8 + col - 1);
        UPLEFT = (temp < 0 || temp > 63) ? CENTER : temp;

    }
    Direction( Direction d){
        this (d.CENTER / 8, d.CENTER % 8);
    }

    int getUP(int n){
        if(n < 1 || n > 8)
            return CENTER;
        int temp = (row - n) * 8 + col;
        if(temp > 63 || temp % 8 != col)
            return CENTER;
        return temp;
    }

    int getUPRIGHT(int n){
        if(n < 1 || n > 8)
            return CENTER;
        int up = getUP(n);
        if(up == CENTER)
            return CENTER;
        int upright = new Direction(up/ 8 , up % 8).getRIGHT(n);
        if(upright == CENTER || Math.abs(upright / 8 - row) != Math.abs((upright %8)- col))
            return CENTER;
        return upright;
    }

    int getUPLEFT(int n){
        if(n < 1 || n > 8)
            return CENTER;
        int up = getUP(n);
        if(up == CENTER)
            return CENTER;
        int upleft = new Direction(up/ 8 , up % 8).getLEFT(n);
        if(upleft == CENTER || Math.abs(upleft / 8 - row) != Math.abs((upleft %8)- col))
            return CENTER;
        return upleft;
    }

    int getDOWN( int n){
        if(n < 1 || n > 8)
            return CENTER;
        int temp = (row + n) * 8 + col;
        if(temp < 0 || temp % 8 != col)
            return CENTER;
        return temp;
    }

    int getDOWNRIGHT(int n){
        if(n < 1 || n > 8)
            return CENTER;
        int down = getDOWN(n);
        if(down == CENTER)
            return CENTER;
        int downright = new Direction(down/ 8 , down % 8).getRIGHT(n);
        if(downright == CENTER || Math.abs(downright / 8 - row) != Math.abs((downright %8)- col))
            return CENTER;
        return downright;
    }

    int getDOWNLEFT(int n){
        if(n < 1 || n > 8)
            return CENTER;
        int down = getDOWN(n);
        if(down == CENTER)
            return CENTER;
        int downleft = new Direction(down/ 8 , down % 8).getLEFT(n);
        if(downleft == CENTER || Math.abs(downleft / 8 - row) != Math.abs((downleft %8)- col))
            return CENTER;
        return downleft;
    }

    int getRIGHT( int n){
        if(n < 1 || n > 8)
            return CENTER;
        int temp = (row ) * 8 + col + n;
        if(temp > 63 || temp / 8 != row)
            return CENTER;
        return temp;
    }

    int getLEFT( int n){
        if(n < 1 || n > 8)
            return CENTER;
        int temp = (row ) * 8 + col - n;
        if(temp > 63 || temp < 0 || temp / 8 != row)
            return CENTER;
        return temp;
    }
}
