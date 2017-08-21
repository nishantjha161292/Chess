package thelearninggames.chess.core;

import com.sun.tools.javac.util.Pair;
import thelearninggames.chess.pieces.Piece;
import thelearninggames.chess.player.Player;
import thelearninggames.chess.ui.GameUI;

import java.util.Collections;


public class Game implements Runnable{

    public enum Status { Running, Over};
    Status status;
    GameState state;
    Player white;
    Player black;
    Player currentPlayer;
    Player winner;
    GameUI ui;

    public Game(GameUI ui, Pair<Player,Player> pair){
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
        ui.repaint();
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
            ui.repaint();
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
        if(p == null || p.getColor() != currentPlayer.getColor() || from == to)
            return false;
        if(state.at(to) != null && state.at(to).getColor() == state.at(from).getColor())
            return false;

        if((p.getValidMoves(from / 8, from % 8, state.at(to) == null).stream().filter(a -> a == to).count() > 0))
            return true;

        return false;
    }

}
