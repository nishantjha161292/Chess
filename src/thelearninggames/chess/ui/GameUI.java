package thelearninggames.chess.ui;

import thelearninggames.chess.core.Pair;

public interface GameUI {

    void repaint();
    Pair<Integer,Integer> getLastMove();
}
