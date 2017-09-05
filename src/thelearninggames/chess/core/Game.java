package thelearninggames.chess.core;

import thelearninggames.chess.pieces.Piece;
import thelearninggames.chess.pieces.PieceType;
import thelearninggames.chess.player.Player;
import thelearninggames.chess.ui.ChessBoard;


public class Game implements Runnable{

    public enum Status { Running, Over};
    Status status;
    GameState state;
    Player white;
    Player black;
    Player currentPlayer;
    Player winner;
    ChessBoard ui;
    
    public Game(ChessBoard ui, Pair<Player,Player> pair){
        status = Status.Running;
        state = new GameState();
        white = pair.fst;
        black = pair.snd;
        currentPlayer = white;
        this.ui = ui;
    }

    void draw(){
        state.draw();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void run(){
    
    	while(status == Status.Running){
            Move m = currentPlayer.getMove(state);
            if(validateMove(m)){
            	state.add(m);
            	if(state.isCheckMate()){
                    status = Status.Over;
                    winner = currentPlayer;
                }
            	currentPlayer = (currentPlayer == white)? black : white;
            }	
        }
    }

    Player getWinner(){
        return winner;
    }

    public final GameState getState() {
        return state;
    }

    private boolean validateMove(Move m){
        int from = m.getFrom();
        int to = m.getTo();
        System.out.println("FROM: "+ from);
        System.out.println("TO: "+to);

        Piece p = state.at(from);
        // Invalid move (move starting from empty state, moving other players piece, move to same destination)
        if(p == null || p.getColor() != currentPlayer.getColor() || from == to)
            return false;
        //Invalid move (friendly fire)
        if(state.at(to) != null && state.at(to).getColor() == state.at(from).getColor())
            return false;
        //Invalid move killing king
        if(state.at(to) != null && state.at(to).getPieceType() == PieceType.King)
            return false;

        if((p.getValidMoves(from / 8, from % 8, state.at(to) == null).stream().filter(a -> a == to).count() > 0)){

            //Also check if any piece is jumping over another piece
            if(p.getPieceType() == PieceType.Bishop || p.getPieceType() == PieceType.Rook || p.getPieceType() == PieceType.Queen){
                return !isPathBlocked(from,to);
            }
            else{
                return true;
            }
        }
        return false;
    }

    private boolean isPathBlocked(int from, int to){
        if(from / 8 == to / 8){ // in same row
            System.out.print("Same Row");
        }
        else if(from % 8 == to % 8){ // in same column
            System.out.print("Same Column");
        }
        else if (Math.abs(from /8 - to /8) == Math.abs(from % 8 - to % 8)){ // same diagonal
            System.out.print("Same Diagonal");
        }
        return false;
    }
    
    
}
