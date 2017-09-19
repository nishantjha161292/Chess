package thelearninggames.chess.core;

public interface GameObservable {

    void register(GameObserver o);
    void unregister(GameObserver o);
    void notifyObservers();
}
