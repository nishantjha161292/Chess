package thelearninggames.chess.core;

import com.sun.tools.javac.util.Pair;
import thelearninggames.chess.player.Player;
import thelearninggames.chess.player.PlayerFactory;
import thelearninggames.chess.player.PlayerType;
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

    public Game(GameUI ui){
        status = Status.Running;
        state = new GameState();
        Pair<Player,Player> pair = PlayerFactory.getPlayers(PlayerType.CommandLine,PlayerType.CommandLine);
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

            Move m = currentPlayer.getMove(state);
            state.add(m);
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
