package thelearninggames.chess.player;

import jdk.internal.util.xml.impl.Input;
import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.Pair;

public class PlayerFactory {

    public static Pair<Player, Player> getPlayers(InputManager white, InputManager black){
        Player p1 = new Player(white,Color.WHITE);
        Player p2 = new Player(black, Color.BLACK);
        return new Pair<>(p1,p2);
    }
}
