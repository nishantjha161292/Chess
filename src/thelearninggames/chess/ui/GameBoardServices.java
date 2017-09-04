package thelearninggames.chess.ui;

import javax.swing.JPanel;
import thelearninggames.chess.core.Game;
import thelearninggames.chess.core.Pair;
import thelearninggames.chess.player.InputManager;
import thelearninggames.chess.player.PlayerFactory;

public class GameBoardServices implements InputManager {

	private static volatile int moveNumber = 0;
	private  static volatile int firstSelection = -1;
	private  static volatile int secondSelection = -1;
	private static volatile int prevselection = -1;
	private Thread t;
	private Game game;
	private ChessBoard board;

	private static GameBoardServices object;
	

	public static GameBoardServices getObject() {
		if(object != null){
			return object;
		}
		object = new GameBoardServices();
		return object;
	}

	

	public Pair<Integer,Integer> getLastMove(){
	    int first = firstSelection;
	    int second = secondSelection;
	    if(first != -1 && second != -1) {
	        firstSelection = -1;
	        secondSelection = -1;
	    }
	    return new Pair<>(first, second);
	}

	@Override
	public int getFrom() {
	    while(firstSelection == -1){
	        try {
	            Thread.sleep(500);
	        }catch(InterruptedException e){
	
	        }
	    }
	    return firstSelection;
	}

	@Override
	public int getTo() {
	    while(secondSelection == -1){
	        try {
	            Thread.sleep(500);
	        }catch(InterruptedException e){
	
	        }
	    }
	    int temp = secondSelection;
	    firstSelection = -1;
	    secondSelection = -1;
	    return temp;
	}

	public void playMove(JPanel p,int selection){
		
		if(moveNumber%2 == 0){
	    	if(firstSelection == selection || game.getState().at(selection) == null || game.getState().at(selection).getColor() != game.getCurrentPlayer().getColor()){
	            firstSelection = - 1;
	            secondSelection = -1;
	            board.unselectSquare(selection);    }
	    	else{
	    		firstSelection = selection;
	            board.selectSquare(selection);
	            moveNumber++;
	    	}        
	    }
	   	else{
	   		
	   		secondSelection = selection;
	   		board.selectSquare(selection);
	        moveNumber++;
	   	}
	    if(prevselection != -1)
	        board.unselectSquare(prevselection);
	    prevselection = selection;
	}

	public void startGame(){
		
        if(t == null) {
        	board = ChessBoard.newGame();
        	game = new Game(board, PlayerFactory.getPlayers(this,this));
            System.out.print("Game Started");
            t = new Thread(game);
            t.start();
           
        }
        else{
        	System.out.print("Game Running");
        }
        

	}
}
