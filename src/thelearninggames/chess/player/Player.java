package thelearninggames.chess.player;

import thelearninggames.chess.io.IODriver;
import thelearninggames.chess.core.Color;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Move;

public class Player{

    IODriver io;
    Color color;

    public Color getColor(){
        return color;
    }

    public Move getMove(final GameState state){
        int from = io.inpMgr.getFrom();
        int to = io.inpMgr.getTo();
        Move m = new Move(from, to);
        io.outMgr.setFrom(from);
        io.outMgr.setTo(to);
        return m;
    }

    public Player(IODriver ioManager, Color c){
       
        io= ioManager;
        color = c;
    }
    
    public IODriver getIOManager(){
    	return io;
    }

}
