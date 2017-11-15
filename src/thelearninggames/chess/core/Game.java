package thelearninggames.chess.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import thelearninggames.chess.pieces.Piece;
import thelearninggames.chess.pieces.PieceType;
import thelearninggames.chess.player.Player;


public class Game implements Runnable, GameObservable{

    @Override
    public void register(GameObserver o) {
        observerList.add(o);
    }

    @Override
    public void unregister(GameObserver o) {
        observerList.remove(o);
    }

    @Override
    public void notifyUpdate() {
        for(GameObserver o : observerList)
            o.update();
    }

    @Override
    public void notifyWhitePlayerUnderCheck(){
        for(GameObserver o : observerList)
            o.whitePlayerUnderCheck();
    }

    @Override
    public void notifyBlackPlayerUnderCheck(){
        for(GameObserver o : observerList)
            o.blackPlayerUnderCheck();
    }

    @Override
    public void notifyGameOver(){
        for(GameObserver o : observerList)
            o.gameOver();
    }

    public enum Status { Running, Over};
    Status status;
    GameState state;
    Player white;
    Player black;
    Player currentPlayer;
    Player winner;
    List<GameObserver> observerList;

    public Game(Pair<Player,Player> pair){
        observerList = new ArrayList<>();
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

    public void run() {
        while (status == Status.Running) {
            if (isMate()) {
                if (state.isCheckState) {
                    status = Status.Over;
                    winner = (currentPlayer.getColor() == Color.BLACK)? white : black;
                } else {
                    status = Status.Over;
                    winner = null;
                }
                notifyGameOver();
                break;
            }
            notifyUpdate();
            Move m = currentPlayer.getMove(state);

            if (validateMove(m, currentPlayer)) {

                if (causesCheck(m)) {
                    state.isCheckState = true;
                    if (currentPlayer.getColor() == Color.BLACK)
                        notifyWhitePlayerUnderCheck();
                    else
                        notifyBlackPlayerUnderCheck();
                } else {
                    state.isCheckState = false;
                }
                state.add(m);
            } else
                continue;
            currentPlayer = (currentPlayer == white) ? black : white;
//            draw();
        }
    }

    public Player getWinner(){
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
        if(p.getPieceType() != PieceType.Knight && isPathBlocked(from,to))
        	return false;
        //Pawn only moves diagonal if there is  an enemy and forward only if location is empty
        if(p.getPieceType() == PieceType.Pawn){
            if(to % 8 != from % 8 && state.at(to) == null)
                return false;
            if(to % 8 == from % 8 && state.at(to) != null)
                return false;
        }

        //when player is in check, one can only move such that check is avoided
        if(state.isCheckState){
            state.add(m);
            int kPos = state.getPieces(player.getColor()).stream().filter(piece -> piece.getPieceType() == PieceType.King).findFirst().get().getPos();
            if(isPositionUnderAttack(kPos, player.getColor() == Color.BLACK ? Color.WHITE : Color.BLACK)){
                state.undo();
                return false;
            }
            state.undo();
        }
        //does not cause self check by moving from current position
        if(causesSelfCheck(m))
            return false;

        return true;
    }
    
    private boolean isCheckMove(Move m){
        if(isAttacking(currentPlayer, state.at(m.getTo()), state.getPieces(currentPlayer.getColor() == Color.BLACK? Color.WHITE: Color.BLACK)
        		.stream().filter(a-> a.getPieceType() == PieceType.King).findFirst().get())){
        	return true;
        }
    	return false;
    }
    
    private boolean kingUnderAttack(Color color){
    	
		for(PieceType pt : PieceType.values()){
			if(isAttacking((currentPlayer == white)? black : white, state.getPieces(color)
							.stream().filter(a -> a.getPieceType() == pt).findFirst().get(),
							state.getPieces(color).stream().filter(a-> a.getPieceType() == PieceType.King)
							.findFirst().get())){
					return true;
				}
			}
		return false;
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
    
    private boolean isPathBlocked(final int from, final int to){

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

    boolean causesSelfCheck(final Move m) {// Check if moving current player piece causes self check.
        Piece p = state.at(m.getFrom());
        int to = m.getTo();
        int from = m.getFrom();
        if (p.getPieceType() != PieceType.King) { // If piece is not king then check only the direction from where current player's  piece has moved.

        	int kPos = state.getPieces(currentPlayer.getColor()).stream()
        				.filter(a -> a.getPieceType() == PieceType.King).findFirst().get().getPos();
        	return isIndirectUnderAttack(kPos, m);
        }
        else{ // If piece is King
            return isPositionUnderAttack(m.getTo(), currentPlayer.getColor() == Color.BLACK ? Color.WHITE : Color.BLACK);
        }
    }

    boolean causesCheck(final Move m) { // Check if moving current player piece causes check.
        Player currentPlayer = state.at(m.getFrom()).getColor() == Color.BLACK ? black : white;
        Player enemy = currentPlayer == black ? white : black;
        int kPos = state.getPieces(enemy.getColor()).stream()
                .filter(a -> a.getPieceType() == PieceType.King).findFirst().get().getPos();
        state.add(m);
        if(isPositionUnderAttack(kPos, currentPlayer.getColor())){
            state.undo();
            return true;
        }
        state.undo();
        return false;
    }

    boolean isPositionUnderAttack(int kPos, Color enemy){ // checks is kPos position is under attack by Player p (Color)
        return state.getPieces(enemy).stream().anyMatch(piece -> { //get all enemy pieces that can attack at location 'to'
            if(piece.getValidMoves().stream().anyMatch(i -> i == kPos)) {
                if (piece.getPieceType() == PieceType.Pawn) { // if it is pawn then check it's not in same column
                    if (piece.getPos() % 8 != kPos % 8)
                        return true;
                }else if (piece.getPieceType() != PieceType.Knight){
                    return !isPathBlocked(piece.getPos(), kPos);
                }
                else
                    return true;
            }
            return false;
        });
    }

    boolean isDirectUnderAttack(int kPos, Move m){
        if(m.getTo() == kPos) // move directly attacking
            return true;
        return false;

    }
    boolean isIndirectUnderAttack(int kPos, Move m){ // Check if kPos is under indirect attack if Move m is done, Assumption : M is a valid move for piece. Not for pawn only for long range enemies
        int from = m.getFrom();
        if(kPos < 0 || kPos > 63 || state.at(m.getFrom()) == null)
            throw new IllegalArgumentException("invalid move");
        Player currentPlayer = (state.at(m.getFrom()).getColor() == Color.BLACK)? black : white;

        if (kPos / 8 == from / 8) { // Same Row
            state.add(m);
            if (from < kPos) {
                int i = kPos - 1;
                while (i >=0 && i <64 && state.at(i) == null && i / 8 == kPos / 8)
                    i++;
                if (( i >=0 && i <64 && state.at(i) != null && state.at(i).getColor() != currentPlayer.getColor() ) && (state.at(i).getPieceType() == PieceType.Rook || state.at(i).getPieceType() == PieceType.Queen) ) {
                    state.undo();
                    return true;
                }
            } else {
                int i = kPos + 1;
                while (i >=0 && i <64 && state.at(i) == null && i / 8 == kPos / 8)
                    i++;
                if (( i >=0 && i <64 && state.at(i) != null && state.at(i).getColor() != currentPlayer.getColor() ) && (state.at(i).getPieceType() == PieceType.Rook || state.at(i).getPieceType() == PieceType.Queen) )  {
                    state.undo();
                    return true;
                }
            }
            state.undo();
        } else if (kPos % 8 == from % 8) { // Same Column
            state.add(m);
            if (from < kPos) {
                int i = kPos - 8;
                while (i >=0 && i <64 && state.at(i) == null && i % 8 == kPos % 8)
                    i -= 8;
                if (( i >=0 && i <64 && state.at(i) != null && state.at(i).getColor() != currentPlayer.getColor() ) && (state.at(i).getPieceType() == PieceType.Rook || state.at(i).getPieceType() == PieceType.Queen) )  {
                    state.undo();
                    return true;
                }
            } else {
                int i = kPos + 8;
                while (i >=0 && i <64 && state.at(i) == null && i % 8 == kPos % 8)
                    i += 8;
                if (( i >=0 && i <64 && state.at(i) != null && state.at(i).getColor() != currentPlayer.getColor() ) && (state.at(i).getPieceType() == PieceType.Rook || state.at(i).getPieceType() == PieceType.Queen) )  {
                    state.undo();
                    return true;
                }
            }
            state.undo();
        } else if (Math.abs(from / 8 - kPos / 8) == Math.abs(from % 8 - kPos % 8)) { // same diagonal
            state.add(m);
            if (from < kPos) {
                if (from % 8 < kPos % 8) {
                    int i = kPos - 8 - 1;
                    while (i >=0 && i <64 && state.at(i) == null && Math.abs(i / 8 - kPos / 8) == Math.abs(i % 8 - kPos % 8)) {
                        i = i - 8 - 1;
                    }
                    if ((i >=0 && i <64 && state.at(i) != null && state.at(i).getColor() != currentPlayer.getColor()) && (state.at(i).getPieceType() == PieceType.Bishop || state.at(i).getPieceType() == PieceType.Queen)){
                        state.undo();
                        return true;
                    }
                } else {
                    int i = kPos - 8 + 1;
                    while (i >=0 && i <64 && state.at(i) == null && Math.abs(i / 8 - kPos / 8) == Math.abs(i % 8 - kPos % 8)) {
                        i = i - 8 + 1;
                    }
                    if ((i >=0 && i <64 && state.at(i) != null && state.at(i).getColor() != currentPlayer.getColor()) && (state.at(i).getPieceType() == PieceType.Bishop || state.at(i).getPieceType() == PieceType.Queen)) {
                        state.undo();
                        return true;
                    }
                }
            } else {
                if (from % 8 < kPos % 8) {
                    int i = kPos + 8 - 1;
                    while (i >=0 && i <64 && state.at(i) == null && Math.abs(i / 8 - kPos / 8) == Math.abs(i % 8 - kPos % 8)) {
                        i = i + 8 - 1;
                    }
                    if ((i >=0 && i <64 && state.at(i) != null && state.at(i).getColor() != currentPlayer.getColor()) && (state.at(i).getPieceType() == PieceType.Bishop || state.at(i).getPieceType() == PieceType.Queen)) {
                        state.undo();
                        return true;
                    }
                } else {
                    int i = kPos + 8 + 1;
                    while (i >=0 && i <64 && state.at(i) == null && Math.abs(i / 8 - kPos / 8) == Math.abs(i % 8 - kPos % 8)) {
                        i = i + 8 + 1;
                    }
                    if ((i >=0 && i <64 && state.at(i) != null && state.at(i).getColor() != currentPlayer.getColor()) && (state.at(i).getPieceType() == PieceType.Bishop || state.at(i).getPieceType() == PieceType.Queen)) {
                        state.undo();
                        return true;
                    }
                }
            }
            state.undo();
        }
        return false;
    }

    private boolean isMate(){ // check if current player is left with any valid moves.
        return !(state.getPieces(currentPlayer.getColor()).stream().anyMatch(piece -> { //all pieces of current player
            ArrayList<Integer> moves = piece.getValidMoves();
            return (moves.stream().anyMatch(i -> { // validating all moves for a piece
                if(validateMove(new Move(piece.getPos(), i), currentPlayer)) {
                    return true;
                }
                return false;
            }));
        }));
    }
}
