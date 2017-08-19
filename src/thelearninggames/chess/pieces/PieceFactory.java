package thelearninggames.chess.pieces;

import thelearninggames.chess.core.Color;

import java.util.ArrayList;

public class PieceFactory {
    public static ArrayList<Piece> getInitialPieces(){

        ArrayList<Piece> pieces = new ArrayList<>();

        for(int i = 0; i < 8; i ++){
            pieces.add(new Pawn(Color.WHITE));
            pieces.add(new Pawn(Color.BLACK));
        }

        pieces.add(new King(Color.WHITE));
        pieces.add(new King(Color.BLACK));

        pieces.add(new Queen((Color.WHITE)));
        pieces.add(new Queen(Color.BLACK));

        pieces.add(new Bishop(Color.WHITE));
        pieces.add(new Bishop(Color.WHITE));
        pieces.add(new Bishop(Color.BLACK));
        pieces.add(new Bishop(Color.BLACK));

        pieces.add(new Rook(Color.WHITE));
        pieces.add(new Rook(Color.WHITE));
        pieces.add(new Rook(Color.BLACK));
        pieces.add(new Rook(Color.BLACK));

        pieces.add(new Knight(Color.WHITE));
        pieces.add(new Knight(Color.WHITE));
        pieces.add(new Knight(Color.BLACK));
        pieces.add(new Knight(Color.BLACK));

        return pieces;
    }
}
