package thelearninggames.chess.ui;

import thelearninggames.chess.core.GameState;

public interface Chess {

	void repaint(GameState state);
	void selectSquare(int squareNumber);
	void unselectSquare(int squareNumber);
	void setCurrentPlayer(String player);
}
