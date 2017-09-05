package thelearninggames.chess.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;

import thelearninggames.chess.core.Color;

import thelearninggames.chess.io.IODriver;
import thelearninggames.chess.io.IODriver.InputType;
import thelearninggames.chess.io.NetworkInputOutput;
import thelearninggames.chess.io.UIInput;
import thelearninggames.chess.core.Game;

import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Pair;
import thelearninggames.chess.player.Player;
import thelearninggames.chess.player.PlayerFactory;

public class GameBoardServices implements ChessBoard {

	private static volatile int moveNumber = 0;
	private  static volatile int firstSelection = -1;
	private  static volatile int secondSelection = -1;
	private static volatile int prevselection = -1;
	private static volatile InputType inputType;
	private static volatile boolean moved = false;
	private static volatile String serverIP = null;
	private Thread t;
	private Timer timer;
	private Game game;
	private Chess board;
	private static Clip clip;   
	
	private IODriver ioManager;
	 	
	private static GameBoardServices object;
	

	public static GameBoardServices getObject() {
		if(object != null){
			return object;
		}
		object = new GameBoardServices();
		return object;
	}

	public Pair<Integer,Integer> getLastMove(){
		System.out.println("firstSelection from: "+ firstSelection+ "  secondSelection: "+ secondSelection);
        
	    int first = firstSelection;
	    int second = secondSelection;
	    if(first != -1 && second != -1) {
	        firstSelection = -1;
	        secondSelection = -1;
	    }
	    ioManager.outMgr.setFrom(first);
	    ioManager.outMgr.setTo(second);
	    return new Pair<>(first, second);
	}
	
	public void playMove(int selection){
		
		if(moveNumber%2 == 0){
			if(firstSelection == -1){
				if(game.getState().at(selection) == null || game.getState().at(selection).getColor() != game.getCurrentPlayer().getColor()){
		            firstSelection = - 1;
		            secondSelection = -1;
		            board.removeSelection(selection); 
		        }
				else{
					firstSelection = selection;
		            board.selectSquare(selection);
		            moveNumber++;
				}
			}       
	    }
	   	else{
	   		if(secondSelection == -1){
	   			if(game.getState().at(selection) != null &&
		   			game.getState().at(selection).getColor() == game.getCurrentPlayer().getColor()){
		   				firstSelection = selection;
			            secondSelection = -1;
			            board.selectSquare(selection);
	   			}
	   			else{
	   				secondSelection = selection;
			        moveNumber++;
			        moved = true;
	   			}
	   			
	   		}
	   	}
	    if(prevselection != -1)
	        board.removeSelection(prevselection);
	    prevselection = selection;
	    
	}

	@Override
	public void startGame(final String ip){
		serverIP = ip;
		startGame();
	}
	@Override
	public void startGame(){
        if(t == null) {
        	board = Chess.newGame();
        	board.drawBoard();
        	setIOManager();
        	Player P1 = PlayerFactory.getPlayer(ioManager, Color.WHITE);
        	Player P2 = PlayerFactory.getPlayer(ioManager, Color.BLACK);
        	Pair<Player, Player> players  = new Pair<>(P1,P2);
        	game = new Game(object, players);
        	
            initMusic();
            timer = new Timer(100, repainter);
            timer.setRepeats(true);
            timer.start();
 
             if(t == null) {
                 t = new Thread(game);
                 t.start();
             }
             runMusic();
        }
        else{
        	System.out.print("Game Running");
        }
	}
	
    
    

private ActionListener repainter = new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
    	board.repaint(game.getState());
    }
};

	
	private void setIOManager(){
		ioManager = new IODriver();
		switch (inputType){
			case UI:
				ioManager.inpMgr = new UIInput();
				ioManager.outMgr = new UIInput();
				break;
			case NETWORK_CLIENT:
				ioManager.outMgr =new NetworkInputOutput(serverIP);
				ioManager.inpMgr = new UIInput();
				break;
			case NETWORK_SERVER:
				ioManager.outMgr = new UIInput();
				ioManager.inpMgr = new NetworkInputOutput();
				break;
			default:
				break;
		}
		
	}
	
	public void setInputType(InputType applicationType){
		inputType = applicationType;
		
	}
	
	public void playLoadClip(){
		clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stopLoadClip(){
		clip.stop();
	}
	
	@Override
	public void updateBoard(Player currentPlayer, GameState state) {
		board.setCurrentPlayer(currentPlayer.getColor().toString());
		board.repaint(state);
		
		
	}
	
	void initMusic(){
	    try{
	        clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource("resource/203.wav"));
	        clip.open(inputStream);
	
	    }catch(Exception e){
	        System.out.print("Sound Exception");
	    }
	}

	void runMusic(){
	    clip.start();
	    clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	@Override
	public boolean moveDidHappen() {
		System.out.println(moved);
		if(moved){
			moved = false;
			return !moved;
		}
		return moved;
	}
}
