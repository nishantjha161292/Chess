package thelearninggames.chess;

import com.sun.tools.javac.util.Pair;
import thelearninggames.chess.player.Player;
import thelearninggames.chess.player.PlayerFactory;
import thelearninggames.chess.player.PlayerType;


public class Game {

    public enum Status { Running, Over};
    Status status;
    GameState state;
    Player white;
    Player black;
    Player currentPlayer;
    Player winner;

    Game(){
        status = Status.Running;
        state = new GameState();
        Pair<Player,Player> pair = PlayerFactory.getPlayers(PlayerType.CommandLine,PlayerType.CommandLine);
        white = pair.fst;
        black = pair.snd;
        currentPlayer = white;
    }

    void draw(){
        state.draw();
    }

    void run(){

        while(status == Status.Running){

            Move m = currentPlayer.getMove(state);
            state.add(m);
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
}
