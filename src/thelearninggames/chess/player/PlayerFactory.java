package thelearninggames.chess.player;

import com.sun.tools.javac.util.Pair;
import thelearninggames.chess.core.Color;

public class PlayerFactory {

    public static Pair<Player, Player> getPlayers(PlayerType t1, PlayerType t2){
        Player p1 = null, p2 = null;
        switch(t1){
            case UI:{
                p1 = new UIPlayer(Color.WHITE);
                break;
            }
            case CommandLine:{
                p1 = new CommandLinePlayer(Color.WHITE);
                break;
            }
            case CPU:{
                p1 = new CPUPlayer(Color.WHITE);
                break;
            }
        }
        switch(t2){
            case UI:{
                p2 = new UIPlayer(Color.BLACK);
                break;
            }
            case CommandLine:{
                p2 = new CommandLinePlayer(Color.BLACK);
                break;
            }
            case CPU:{
                p2 = new CPUPlayer(Color.BLACK);
                break;
            }
        }

        return new Pair<>(p1,p2);
    }
}
