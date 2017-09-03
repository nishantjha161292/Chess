package thelearninggames.chess.ui;


import com.sun.tools.javac.util.Pair;

public interface GameUI {

    void repaint();
    Pair<Integer,Integer> getLastMove();
}
