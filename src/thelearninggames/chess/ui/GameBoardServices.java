package thelearninggames.chess.ui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import thelearninggames.chess.core.Game;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Pair;
import thelearninggames.chess.player.InputManager;
import thelearninggames.chess.player.Player;
import thelearninggames.chess.player.PlayerFactory;

public class GameBoardServices implements InputManager,ChessBoard {

	private static volatile int moveNumber = 0;
	private  static volatile int firstSelection = -1;
	private  static volatile int secondSelection = -1;
	private static volatile int prevselection = -1;
	private Thread t;
	private Game game;
	private Chess board;
	private static Clip clip;   

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

	public void playMove(int selection){
		
		if(moveNumber%2 == 0){
	    	if(firstSelection == selection || game.getState().at(selection) == null || game.getState().at(selection).getColor() != game.getCurrentPlayer().getColor()){
	            firstSelection = - 1;
	            secondSelection = -1;
	            board.unsetSquare(selection);   
	        }
	    	else{
	    		firstSelection = selection;
	            board.setSquare(selection);
	            moveNumber++;
	    	}        
	    }
	   	else{
	   		
	   		secondSelection = selection;
	        moveNumber++;
	   	}
	    if(prevselection != -1)
	        board.unsetSquare(prevselection);
	    prevselection = selection;
	}

	@Override
	public void startGame(){
        if(t == null) {
        	board = Chess.newGame();
        	game = new Game(object, PlayerFactory.getPlayers(this,this));
            System.out.print("Game Started");
            t = new Thread(game);
            t.start();
            try{
                clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource("resource/203.wav"));
                clip.open(inputStream);
                playLoadClip();

            }catch(Exception e){
                System.out.print("Sound Exception");
            }
        }
        else{
        	System.out.print("Game Running");
        }
	}
	
	public void playLoadClip(){
		clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stopLoadClip(){
		clip.stop();
	}
	
	@Override
	public void update(Player currentPlayer, GameState state) {
		board.setCurrentPlayer(currentPlayer.getColor().toString());
		board.repaint(state);
		
	}
}
