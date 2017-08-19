package thelearninggames.chess.pieces;

import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Move;

import java.util.ArrayList;

public interface Piece {
    Color getColor();
    ArrayList<Move> getValidMoves(GameState state);
    int getPos();
    void setPos(int i);
}
