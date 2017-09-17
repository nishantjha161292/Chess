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
        if((p.getPieceType() == PieceType.Pawn || p.getPieceType() == PieceType.Bishop || p.getPieceType() == PieceType.Rook || p.getPieceType() == PieceType.Queen) && isPathBlocked(from,to))
            return false;
        //Pawn only moves diagonal if there is  an enemy and forward only if location is empty
        if(p.getPieceType() == PieceType.Pawn){
            if(to % 8 != from % 8 && state.at(to) == null)
                return false;
            if(to % 8 == from % 8 && state.at(to) != null)
                return false;
        }
        //does not cause self check
        if(causesSelfCheck(from, to, p, m))
            return false;

        return true;
    }

    private boolean isPathBlocked(final int from, final int to){
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

    boolean causesSelfCheck(final int from, final int to, final Piece p, final Move m) {
        if (p.getPieceType() != PieceType.King) {

            int kPos = (currentPlayer.getColor() == Color.WHITE) ?
                    state.getWhites().stream().filter(a -> a.getPieceType() == PieceType.King).findFirst().get().getPos() :
                    state.getBlacks().stream().filter(a -> a.getPieceType() == PieceType.King).findFirst().get().getPos();

            if (kPos / 8 == from / 8) { // Same Row
                state.add(m);
                if (from < kPos) {
                    int i = kPos - 1;
                    while (state.at(i) == null && i / 8 == kPos / 8)
                        i++;
                    if (( state.at(i) != null && state.at(i).getColor() != currentPlayer.getColor() ) && (state.at(i).getPieceType() == PieceType.Rook || state.at(i).getPieceType() == PieceType.Queen) ) {
                        state.undo();
                        return true;
                    }
                } else {
                    int i = kPos + 1;
                    while (state.at(i) == null && i / 8 == kPos / 8)
                        i++;
                    if (( state.at(i) != null && state.at(i).getColor() != currentPlayer.getColor() ) && (state.at(i).getPieceType() == PieceType.Rook || state.at(i).getPieceType() == PieceType.Queen) )  {
                        state.undo();
                        return true;
                    }
                }
                state.undo();
            } else if (kPos % 8 == from % 8) { // Same Column
                state.add(m);
                if (from < kPos) {
                    int i = kPos - 8;
                    while (state.at(i) == null && i % 8 == kPos % 8)
                        i -= 8;
                    if (( state.at(i) != null && state.at(i).getColor() != currentPlayer.getColor() ) && (state.at(i).getPieceType() == PieceType.Rook || state.at(i).getPieceType() == PieceType.Queen) )  {
                        state.undo();
                        return true;
                    }
                } else {
                    int i = kPos + 8;
                    while (state.at(i) == null && i % 8 == kPos % 8)
                        i += 8;
                    if (( state.at(i) != null && state.at(i).getColor() != currentPlayer.getColor() ) && (state.at(i).getPieceType() == PieceType.Rook || state.at(i).getPieceType() == PieceType.Queen) )  {
                        state.undo();
                        return true;
                    }
                }
                state.undo();
            } else if (Math.abs(from / 8 - kPos / 8) == Math.abs(from % 8 - kPos % 8)) { // same diagonal
                state.add(m);
                if (from < kPos) {
                    if (from % 8 < kPos % 8) {
                        int i = kPos - 8 - 1;
                        while (state.at(i) == null && Math.abs(i / 8 - kPos / 8) == Math.abs(i % 8 - kPos % 8)) {
                            i = i - 8 - 1;
                        }
                        if ((state.at(i) != null && state.at(i).getColor() != currentPlayer.getColor()) && (state.at(i).getPieceType() == PieceType.Bishop || state.at(i).getPieceType() == PieceType.Queen)){
                            state.undo();
                            return true;
                        }
                    } else {
                        int i = kPos - 8 + 1;
                        while (state.at(i) == null && Math.abs(i / 8 - kPos / 8) == Math.abs(i % 8 - kPos % 8)) {
                            i = i - 8 + 1;
                        }
                        if ((state.at(i) != null && state.at(i).getColor() != currentPlayer.getColor()) && (state.at(i).getPieceType() == PieceType.Bishop || state.at(i).getPieceType() == PieceType.Queen)) {
                            state.undo();
                            return true;
                        }
                    }
                } else {
                    if (from % 8 < kPos % 8) {
                        int i = kPos + 8 - 1;
                        while (state.at(i) == null && Math.abs(i / 8 - kPos / 8) == Math.abs(i % 8 - kPos % 8)) {
                            i = i + 8 - 1;
                        }
                        if ((state.at(i) != null && state.at(i).getColor() != currentPlayer.getColor()) && (state.at(i).getPieceType() == PieceType.Bishop || state.at(i).getPieceType() == PieceType.Queen)) {
                            state.undo();
                            return true;
                        }
                    } else {
                        int i = kPos + 8 + 1;
                        while (state.at(i) == null && Math.abs(i / 8 - kPos / 8) == Math.abs(i % 8 - kPos % 8)) {
                            i = i + 8 + 1;
                        }
                        if ((state.at(i) != null && state.at(i).getColor() != currentPlayer.getColor()) && (state.at(i).getPieceType() == PieceType.Bishop || state.at(i).getPieceType() == PieceType.Queen)) {
                            state.undo();
                            return true;
                        }
                    }
                }
                state.undo();
            }
        }
        return false;
    }

}
