package thelearninggames.chess.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class GameBoardViewController implements MouseListener{
	
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
