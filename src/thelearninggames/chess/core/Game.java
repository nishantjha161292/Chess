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
    	ui.update(currentPlayer,state);
        while(status == Status.Running){

            Move m = currentPlayer.getMove(state);
            if(validateMove(m)){
            	state.add(m);
            	if(state.isCheckMate()){
                    status = Status.Over;
                    winner = currentPlayer;
                }
            	currentPlayer = (currentPlayer == white)? black : white;
            	ui.update(currentPlayer,state);
                draw();
            }
            
           // Chess.newGame().repaint(state);
           
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

        if((p.getValidMoves(from / 8, from % 8, state.at(to) == null).stream().filter(a -> a == to).count() > 0))
            return true;

        return false;
    }
    
    
}
