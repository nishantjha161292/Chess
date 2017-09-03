package thelearninggames.chess.player;

import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Move;
import java.util.Scanner;

public class CommandLineInput implements InputManager {

    Scanner Cin ;

    CommandLineInput(){
       Cin = new Scanner(System.in);
    }

    @Override
    public int getFrom() {
        return Cin.nextInt();
    }

    @Override
    public int getTo() {
        return Cin.nextInt();
    }
}
