package thelearninggames.chess.player;

import thelearninggames.chess.io.IODriver;
import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.Pair;

public class PlayerFactory {

    public static Pair<Player, Player> getPlayers(IODriver ioManager){
        Player p1 = new Player(ioManager, Color.WHITE);
        Player p2 = new Player(ioManager, Color.BLACK);
        return new Pair<>(p1,p2);
    }
    
    public static Player getPlayer(IODriver ioManager, Color c){
        
        return new Player(ioManager,c);
        
    }
}
