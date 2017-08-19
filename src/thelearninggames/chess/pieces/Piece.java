package thelearninggames.chess.pieces;

import thelearninggames.chess.Color;
import thelearninggames.chess.GameState;
import thelearninggames.chess.Move;

import java.util.ArrayList;

public interface Piece {
    Color getColor();
    ArrayList<Move> getValidMoves(GameState state);
    int getPos();
    void setPos(int i);
}
