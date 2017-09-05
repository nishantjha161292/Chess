package IO;

import thelearninggames.chess.ui.GameBoardViewController.SwingInputStream;

public class UIInput implements IOManager  {
	
    int from = 0;
    int to = 0;
    

    @Override
    public int getFrom() {
    	from = SwingInputStream.readFirstInput();
    	System.out.println(from);
    	return from;
    }

    @Override
    public int getTo() {
    	to = SwingInputStream.readSecondInput();
    	System.out.println(to);
         return to;
    }

	@Override
	public void setFrom(int a) {
		from = a;
		
	}

	@Override
	public void setTo(int a) {
		to = a;
		
	}

}
