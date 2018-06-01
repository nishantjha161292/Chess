package thelearninggames.chess.core;

public interface GameObservable {

    void register(GameObserver o);
    void unregister(GameObserver o);
    void notifyUpdate();
    void notifyWhitePlayerUnderCheck();
    void notifyBlackPlayerUnderCheck();
    void notifyGameOver();
}
