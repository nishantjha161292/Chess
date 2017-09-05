package thelearninggames.chess.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import thelearninggames.chess.core.Pair;

public class GameBoardViewController implements MouseListener{
	
	public static class SwingInputStream{
		static Pair<Integer,Integer> move;
		public static int readFirstInput(){
			while(!GameBoardServices.getObject().moveDidHappen()){
	    		try {
	                Thread.sleep(500);
	            }catch(InterruptedException e){

	            }
	    	}
			move = GameBoardServices.getObject().getLastMove();
			
			return move.fst;
			
		}
		public static int readSecondInput(){
			System.out.println("jhsdk");
			return move.snd;
		}
	}
	
	private static GameBoardViewController object;
	
	public static GameBoardViewController getObject() {
		if(object != null){
			return object;
		}
		object = new GameBoardViewController();
		return object;
	}

	@Override
	public synchronized void  mouseClicked(MouseEvent e) {
	    JPanel p = (JPanel)e.getSource();
	    int selection = Integer.parseInt(p.getName());
	    GameBoardServices.getObject().playMove(selection);
	}
	
	public void enableMouseInteraction(JPanel p){
		p.addMouseListener(this);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
	}

	@Override
	public void mouseExited(MouseEvent e) {
	
	}

}
