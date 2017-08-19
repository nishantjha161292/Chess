package thelearninggames.chess.player;

import com.sun.tools.javac.util.Pair;
import thelearninggames.chess.Color;

public class PlayerFactory {

    public static Pair<Player, Player> getPlayers(PlayerType t1, PlayerType t2){

        Player p1 = (t1 == PlayerType.CommandLine)? new CommandLinePlayer(Color.WHITE) : new CPUPlayer(Color.WHITE);

        Player p2 = (t2 == PlayerType.CPU)? new CPUPlayer(Color.BLACK) : new CommandLinePlayer(Color.BLACK);

        return new Pair<>(p1,p2);
    }
}
