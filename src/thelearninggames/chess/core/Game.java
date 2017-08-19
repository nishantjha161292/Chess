package thelearninggames.chess.core;

import com.sun.tools.javac.util.Pair;
import thelearninggames.chess.player.Player;
import thelearninggames.chess.ui.GameUI;


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

    public void run(){
        ui.repaint();
        while(status == Status.Running){

            state.add(currentPlayer.getMove(state));
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

}
