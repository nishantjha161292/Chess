package thelearninggames.chess.player;

import thelearninggames.chess.Color;
import thelearninggames.chess.GameState;
import thelearninggames.chess.Move;
import java.util.Scanner;

public class CommandLinePlayer implements Player {

    Color color;

    Scanner Cin = new Scanner(System.in);


    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Move getMove(final GameState state) {

        int from = Cin.nextInt();
        int to = Cin.nextInt();
        return new Move(from,to);
    }

    CommandLinePlayer(Color c){
        this.color = c;
    }
}
