package thelearninggames.chess.core;

import thelearninggames.chess.pieces.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.lang.reflect.Array;
import java.util.*;

public class GameState {

    volatile Piece[] state;
    boolean isCheckState;
    ArrayList<Piece> whites;
    ArrayList<Piece> blacks;
    Deque<Move> progress;
    Deque<Piece> killed;

    public final Piece[] getState() {
        return state;
    }

    public GameState(){

        state = new Piece[64];
        whites = new ArrayList<>();
        blacks = new ArrayList<>();
        progress = new ArrayDeque<>();
        killed = new ArrayDeque<>();
        ArrayList<Piece> pieces = PieceFactory.getInitialPieces();
        int whitePawnItr = 8;
        int blackPawnItr = 48;
        int whiteRookItr = 0;
        int blackRookItr = 56;
        int whiteKnightItr = 1;
        int blackKnightItr = 57;
        int whiteBishopItr = 2;
        int blackBishopitr = 58;

        Iterator<Piece> it = pieces.iterator();
        while(it.hasNext()){
            Piece p = it.next();
            if( p instanceof Pawn && p.getColor() == Color.WHITE) {
                p.setPos(whitePawnItr);
                state[whitePawnItr] = p;
                whitePawnItr++;
                
            }
            else if( p instanceof Pawn && p.getColor() == Color.BLACK) {
                p.setPos(blackPawnItr);
                state[blackPawnItr] = p;
                blackPawnItr++;
                
            }
            else if( p instanceof Rook && p.getColor() == Color.WHITE) {
                p.setPos(whiteRookItr);
                state[whiteRookItr] = p;
                whiteRookItr += 7;
                
            }
            else if( p instanceof Rook && p.getColor() == Color.BLACK) {
                p.setPos(blackRookItr);
                state[blackRookItr] = p;
                blackRookItr += 7;
                
            }
            else if( p instanceof Knight && p.getColor() == Color.WHITE) {
                p.setPos(whiteKnightItr);
                state[whiteKnightItr] = p;
                whiteKnightItr += 5;
                
            }
            else if( p instanceof Knight && p.getColor() == Color.BLACK) {
                p.setPos(blackKnightItr);
                state[blackKnightItr] = p;
                blackKnightItr += 5;
                
            }
            else if( p instanceof Knight && p.getColor() == Color.WHITE) {
                p.setPos(whiteKnightItr);
                state[whiteKnightItr] = p;
                whiteKnightItr += 5;
                
            }
            else if( p instanceof Knight && p.getColor() == Color.BLACK) {
                p.setPos(blackKnightItr);
                state[blackKnightItr] = p;
                blackKnightItr += 5;
                
            }
            else if( p instanceof Bishop && p.getColor() == Color.WHITE) {
                p.setPos(whiteBishopItr);
                state[whiteBishopItr] = p;
                whiteBishopItr += 3;
                
            }
            else if( p instanceof Bishop && p.getColor() == Color.BLACK) {
                p.setPos(blackBishopitr);
                state[blackBishopitr] = p;
                blackBishopitr += 3;
                
            }
            else if( p instanceof King && p.getColor() == Color.WHITE) {
                p.setPos(3);
                state[3] = p;
                
            }
            else if( p instanceof King && p.getColor() == Color.BLACK) {
                p.setPos(59);
                state[59] = p;
                
            }
            else if( p instanceof Queen && p.getColor() == Color.WHITE) {
                p.setPos(4);
                state[4] = p;
                
            }
            else if( p instanceof Queen && p.getColor() == Color.BLACK) {
                p.setPos(60);
                state[60] = p;
                
            }
            if(p.getColor() == Color.WHITE)
                whites.add(p);
            else
                blacks.add(p);
        }

    }

    public Piece at(int i){
        return state[i];
    }

    void add(Move move){
        // JLT implementation work need to be done here
        Piece temp = state[move.getFrom()];
        state[move.getFrom()] = null;
        if(state[move.getTo()] != null)
            killed.add(state[move.getTo()]);
        state[move.getTo()] = temp;
        temp.setPos(move.getTo());
        progress.add(move);
    }

    void undo(){
        Move undo = progress.getLast();
        add(undo.getInverseMove());
        if(killed.size() > 0 && killed.getLast().getPos() == undo.getTo())
            state[undo.getTo()] = killed.getLast();
    }

    void validate(Move move){

    }
    
    public ArrayList<Piece> getPieces(Color color){
    	if(color == Color.BLACK){
    		 return blacks;
    	}
    	else{
    		return whites;
    	}
    }
    void draw(){
        for(int i = 0 ;i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(state[i*8+j] + "|");
            }
            System.out.println();
        }
    }
}
