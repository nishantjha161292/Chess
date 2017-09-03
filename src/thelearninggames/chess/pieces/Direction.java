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

        CENTER = row * 8 + col;
        DOWN = ((row + 1) * 8 + col); //Forward in memory
        UP = ((row - 1) * 8 + col); //Backward in memory
        RIGHT = (row * 8 + col + 1); //Forward in memory
        LEFT = (row * 8 + col - 1); // Backward in memory
        DOWNRIGHT = ((row + 1) * 8 + col + 1);
        DOWNLEFT = ((row + 1) * 8 + col - 1);
        UPRIGHT = ((row - 1) * 8 + col + 1);
        UPLEFT = ((row - 1) * 8 + col - 1);
    }
    Direction( Direction d){
        this (d.CENTER / 8, d.CENTER % 8);
    }

    int getUP(int n){
        if(n < 1 || n > 8)
            return CENTER;
        int temp = (row - n) * 8 + col;
        if(temp > 63 )
            return CENTER;
        return temp;
    }

    int getDOWN( int n){
        if(n < 1 || n > 8)
            return CENTER;
        int temp = (row + n) * 8 + col;
        if(temp < 0)
            return CENTER;
        return temp;
    }

    int getRIGHT( int n){
        if(n < 1 || n > 8)
            return CENTER;
        int temp = (row ) * 8 + col + n;
        if(temp > 63)
            return CENTER;
        return temp;
    }

    int getLEFT( int n){
        if(n < 1 || n > 8)
            return CENTER;
        int temp = (row ) * 8 + col - n;
        if(temp > 63 || temp < 0)
            return CENTER;
        return temp;
    }
}
