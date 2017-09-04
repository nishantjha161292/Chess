package thelearninggames.chess.player;

import thelearninggames.chess.InputOutput.InputManager;
import thelearninggames.chess.InputOutput.OutputManager;
import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.Pair;

public class PlayerFactory {

    public static Pair<Player, Player> getPlayers(InputManager iwhite, OutputManager owhite, InputManager iblack, OutputManager oblack){
        Player p1 = new Player(iwhite, Color.WHITE, owhite);
        Player p2 = new Player(iblack, Color.BLACK, oblack);
        return new Pair<>(p1,p2);
    }
}
