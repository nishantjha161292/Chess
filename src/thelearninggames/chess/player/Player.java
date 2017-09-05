package thelearninggames.chess.player;

import thelearninggames.chess.io.IOManager;
import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Move;

public class Player{

    IOManager io;
    Color color;

    public Color getColor(){
        return color;
    }

    public Move getMove(final GameState state){
    	
        int from = io.inpMgr.getFrom();
        int to = io.inpMgr.getTo();
        while(from == -1 || to == -1){
        	from = io.inpMgr.getFrom();
        	to = io.inpMgr.getTo();
        }
        Move m = new Move(from, to);
        if(io.inpMgr != io.outMgr){
        	io.outMgr.setFrom(from);
            io.outMgr.setTo(to);
        }
        
        return m;
    }

    public Player(IOManager ioManager, Color c){
       
        io= ioManager;
        color = c;
    }
    
    public IOManager getIOManager(){
    	return io;
    }

}
