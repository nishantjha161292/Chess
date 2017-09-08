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
        // Invalid piece movement
        if(!(p.getValidMoves().stream().filter(a -> a == to).count() > 0))
            return false;
        //Also check if any piece is jumping over another piece
        if((p.getPieceType() == PieceType.Bishop || p.getPieceType() == PieceType.Rook || p.getPieceType() == PieceType.Queen) && isPathBlocked(from,to))
            return false;
        //Pawn only moves diagonal if there is  an enemy and forward only if location is empty
        if(p.getPieceType() == PieceType.Pawn){
            if(to % 8 != from % 8 && state.at(to) == null)
                return false;
            if(to % 8 == from % 8 && state.at(to) != null)
                return false;
        }

        return true;
    }

    private boolean isPathBlocked(int from, int to){
        if(from / 8 == to / 8){ // in same row
            if(from < to) {
                for (int i = from + 1; i < to ; i++) {
                    if (state.at(i) != null)
                        return true;
                }
            }
            else{
                for (int i = to + 1; i < from ; i++) {
                    if (state.at(i) != null)
                        return true;
                }
            }
        }
        else if(from % 8 == to % 8){ // in same column
            if(from < to){
                for(int i = from + 8; i < to ; i = i + 8){
                    if(state.at(i) != null)
                        return true;
                }
            }
            else{
                for(int i = to + 8; i < from ; i = i + 8){
                    if(state.at(i) != null)
                        return true;
                }
            }

        }
        else if (Math.abs(from /8 - to /8) == Math.abs(from % 8 - to % 8)){ // same diagonal
            if(from < to){
                if(from % 8 < to % 8){
                    for(int i = from + 8 + 1; i < to; i = i + 8 + 1){
                        if(state.at(i) != null)
                            return true;
                    }
                }
                else{
                    for(int i = from + 8 - 1; i < to; i = i + 8 - 1){
                        if(state.at(i) != null)
                            return true;
                    }
                }
            }
            else{
                if(from % 8 < to % 8){
                    for(int i = to + 8 - 1; i < from; i = i + 8 - 1){
                        if(state.at(i) != null)
                            return true;
                    }
                }
                else{
                    for(int i = to + 8 + 1; i < from; i = i + 8 + 1){
                        if(state.at(i) != null)
                            return true;
                    }
                }
            }
        }
        return false;
    }

}
