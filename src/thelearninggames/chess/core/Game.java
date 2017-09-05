package thelearninggames.chess.core;

import thelearninggames.chess.pieces.Piece;
import thelearninggames.chess.pieces.PieceType;
import thelearninggames.chess.player.Player;
import thelearninggames.chess.ui.GameUI;
import thelearninggames.chess.ui.SwingUI;


public class Game implements Runnable{

    public enum Status { Running, Over};
    Status status;
    GameState state;
    Player white;
    Player black;
    Player currentPlayer;
    Player winner;

    public Game(Pair<Player,Player> pair){
        status = Status.Running;
        state = new GameState();
        white = pair.fst;
        black = pair.snd;
        currentPlayer = white;
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
            if(validateMove(m))
                state.add(m);
            else
                continue;
            if(state.isCheckMate()){
                status = Status.Over;
                winner = currentPlayer;
            }
            currentPlayer = (currentPlayer == white)? black : white;
            draw();
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
            if(from < to) {
                for (int i = from + 1; i < to && i < 63; i++) {
                    if (state.at(i) != null)
                        return true;
                }
            }
            else{
                for (int i = to + 1; i < from && i < 63; i++) {
                    if (state.at(i) != null)
                        return true;
                }
            }
        }
        else if(from % 8 == to % 8){ // in same column
            if(from < to){
                for(int i = from + 8; i < to && i <63 ; i++){
                    if(state.at(i) != null)
                        return true;
                }
            }
            else{
                for(int i = to + 8; i < from && i <63 ; i = i + 8){
                    if(state.at(i) != null)
                        return true;
                }
            }

        }
        else if (Math.abs(from /8 - to /8) == Math.abs(from % 8 - to % 8)){ // same diagonal
            System.out.print("Same Diagonal");
        }

        return false;
    }

}
