package thelearninggames.chess.pieces;

import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Move;

import java.util.ArrayList;

public interface Piece {
    Color getColor();
    PieceType getPieceType();
    ArrayList<Integer> getValidMoves(int row, int col, boolean isToEmpty);
    int getPos();
    void setPos(int i);
}
