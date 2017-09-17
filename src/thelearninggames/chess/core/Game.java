package thelearninggames.chess.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Collectors;

import thelearninggames.chess.pieces.Pawn;
import thelearninggames.chess.pieces.Piece;
import thelearninggames.chess.pieces.PieceType;
import thelearninggames.chess.player.Player;


public class Game implements Runnable{

    public enum Status { Running, Over};
    Status status;
    GameState state;
    Player white;
    Player black;
    Player currentPlayer;
    Player winner;
    boolean ischeck = false;

    public Game(Pair<Player,Player> pair){
        status = Status.Running;
        state = new GameState();
        white = pair.fst;
        black = pair.snd;
        currentPlayer = white;
    }

    void draw(){
        state.draw();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void run(){
        while(status == Status.Running){

            Move m = currentPlayer.getMove(state);
            
            if(validateMove(m,currentPlayer)){
            	state.add(m);
            	if(state.isCheckState && kingUnderAttack(currentPlayer.getColor())){
            		System.out.println("you are on attack");
            		Move m1 = new Move(m.getTo(), m.getFrom());
            		state.add(m1);
            		continue;
            		
            	}
            }
            	   
            else
                continue;
            if(state.isCheckMate()){
                status = Status.Over;
                winner = currentPlayer;
            }
            if(isCheckMove(m)){
            	state.isCheckState = true;
        	}
            currentPlayer = (currentPlayer == white)? black : white;
            draw();
        }
    }

    Player getWinner(){
        return winner;
    }

    public final GameState getState() {
        return state;
    }

    private boolean validateMove(Move m, Player player){
        int from = m.getFrom();
        int to = m.getTo();

        Piece p = state.at(from);
        // Invalid move (move starting from empty state, moving other players piece, move to same destination)
        if(p == null || p.getColor() != player.getColor() || from == to)
            return false;
        //Invalid move (friendly fire)
        if(state.at(to) != null && state.at(to).getColor() == state.at(from).getColor())
            return false;
        //Invalid move killing king
//        if(state.at(to) != null && state.at(to).getPieceType() == PieceType.King){
//        	ischeck = true;
//            return false;
//        }
        // Invalid piece movement
        if(!(p.getValidMoves().stream().filter(a -> a == to).count() > 0))
            return false;
        //Also check if any piece is jumping over another piece
       // if((p.getPieceType() == PieceType.Pawn || p.getPieceType() == PieceType.Bishop || p.getPieceType() == PieceType.Rook || p.getPieceType() == PieceType.Queen) && isPathBlocked(from,to))
         if(p.getPieceType() != PieceType.Knight && isPathBlocked(from,to))
        	return false;
        //Pawn only moves diagonal if there is  an enemy and forward only if location is empty
        if(p.getPieceType() == PieceType.Pawn){
            if(to % 8 != from % 8 && state.at(to) == null)
                return false;
            if(to % 8 == from % 8 && state.at(to) != null)
                return false;
        }
       
        return true;
    }
    
    private boolean isCheckMove(Move m){
        if(isAttacking(currentPlayer, state.at(m.getTo()), state.getPieces(currentPlayer.getColor() == Color.BLACK? Color.WHITE: Color.BLACK)
        		.stream().filter(a-> a.getPieceType() == PieceType.King).findFirst().get())){
        	return true;
        }
    	return false;
    }
    
    private boolean isCheckState(){
    	if(kingUnderAttack(currentPlayer.getColor())){
    		return true;
    	}
    	return false;
    }
    
    private boolean kingUnderAttack(Color color){
    	Boolean check = false;
		for(PieceType pt : PieceType.values()){
			if(isAttacking((currentPlayer == white)? black : white, state.getPieces(color)
							.stream().filter(a -> a.getPieceType() == pt).findFirst().get(),
							state.getPieces(color).stream().filter(a-> a.getPieceType() == PieceType.King)
							.findFirst().get())){
				if(check == false){
					check = true;
					break;
				}
			}
			
		}
		return check;
    }

    private boolean isAttacking(Player p, Piece attacker, Piece victim){
    	int temp = victim.getPos();
    	victim.setPos(attacker.getPos());
    	attacker.setPos(temp);
    	
		ArrayList<Integer> validMove = attacker.getValidMoves();
		
		temp = victim.getPos();
    	victim.setPos(attacker.getPos());
    	attacker.setPos(temp);
    	
		int i=0;
		while(i < validMove.size()){
			if(state.at(validMove.get(i)) != null && state.at(validMove.get(i)).getPieceType() ==attacker.getPieceType() &&
				validateMove(new Move(validMove.get(i), victim.getPos()), p)){
				return true;
			}
			i++;
		}
		return false;
    }
    
    
    
    private boolean isPathBlocked(int from, int to){
        if(from / 8 == to / 8){ // in same row
            if(from < to) {
                for (int i = from + 1; i < to ; i++) {
                    if (state.at(i) != null)
                        return true;
                }
            }
            else{
                for (int i = to + 1; i < from ; i++) {
                    if (state.at(i) != null)
                        return true;
                }
            }
        }
        else if(from % 8 == to % 8){ // in same column
            if(from < to){
                for(int i = from + 8; i < to ; i = i + 8){
                    if(state.at(i) != null)
                        return true;
                }
            }
            else{
                for(int i = to + 8; i < from ; i = i + 8){
                    if(state.at(i) != null)
                        return true;
                }
            }

        }
        else if (Math.abs(from /8 - to /8) == Math.abs(from % 8 - to % 8)){ // same diagonal
            if(from < to){
                if(from % 8 < to % 8){
                    for(int i = from + 8 + 1; i < to; i = i + 8 + 1){
                        if(state.at(i) != null)
                            return true;
                    }
                }
                else{
                    for(int i = from + 8 - 1; i < to; i = i + 8 - 1){
                        if(state.at(i) != null)
                            return true;
                    }
                }
            }
            else{
                if(from % 8 < to % 8){
                    for(int i = to + 8 - 1; i < from; i = i + 8 - 1){
                        if(state.at(i) != null)
                            return true;
                    }
                }
                else{
                    for(int i = to + 8 + 1; i < from; i = i + 8 + 1){
                        if(state.at(i) != null)
                            return true;
                    }
                }
            }
        }
        return false;
    }

}
