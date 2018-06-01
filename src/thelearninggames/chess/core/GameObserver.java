package thelearninggames.chess.core;

public interface GameObserver {

    void update();
    void whitePlayerUnderCheck();
    void blackPlayerUnderCheck();
    void gameOver();
}
