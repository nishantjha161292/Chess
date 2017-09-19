package thelearninggames.chess.ui;

import thelearninggames.chess.core.GameObserver;
import thelearninggames.chess.core.Pair;

public interface GameUI extends GameObserver{

    void repaint();
    Pair<Integer,Integer> getLastMove();
}
