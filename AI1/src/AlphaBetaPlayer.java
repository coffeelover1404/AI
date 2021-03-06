

import java.util.*;

public class AlphaBetaPlayer extends CheckerPlayer implements CheckerBoardConstants {

	public AlphaBetaPlayer(String name){
		super(name);
	}

	public void calculateMove(int[][] bs, int whosTurn){

		// your code should go here// indicate that a move has not yet been chosen.
		
		/*chosenMove = null;
		Vector possibleMoves = Utils.getAllPossibleMoves(bs, whosTurn);
		if(possibleMoves.size()!=0) {
			int rand = (int)(Math.random()*possibleMoves.size());
			chosenMove = (Move)possibleMoves.elementAt(rand);
		}*/
		Move move = null;
		chosenMove = null;
		Stack executeMove = null;
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		int score = 0;
		Vector possibleMoves = Utils.getAllPossibleMoves(bs, whosTurn);
		int size = possibleMoves.size();
		
		if(size != 0) {
			for(int i=0; i<size; i++) {
				move = (Move)possibleMoves.elementAt(i);
				executeMove = Utils.executeMove(whosTurn, move, bs);
				score = AlphaBeta(10, max, min, bs, whosTurn, true);
				if(score > max) {
					max = score;
					chosenMove = move;
				}
				Utils.setBoardState(executeMove, bs);
			}
		}

	}
	
	private int AlphaBeta(int depth, int alpha, int beta, int[][] bs, int whosTurn, boolean maximizing){
		Vector<Move> child = Utils.getAllPossibleMoves(bs, whosTurn);
		int size = child.size();
		Move move = null;
		Stack<Move> executeMove = null;
		int compare = 0;
		
		if(depth == 0 || size == 0){
			if(whosTurn == 1) return Utils.scoreCheckerBoard(bs, whosTurn);
			else return -Utils.scoreCheckerBoard(bs, whosTurn);
		}
		if(maximizing){ //this player wants max
			for(int i=0; i<size; i++) { // for each child of node
				//possibleMoves.elementAt(i)
				//v := max(v, alphabeta(child, depth � 1, , ,maximizing)
				move = (Move)child.elementAt(i); //get move
				executeMove = Utils.executeMove(whosTurn, move, bs);
				
				if(whosTurn == 1)whosTurn = 2;
				else whosTurn = 1;
				
				compare = Math.max(compare, AlphaBeta(depth-1, alpha, beta, bs, whosTurn, false));
				Utils.setBoardState(executeMove, bs);
				// a:= max(a , v)
				alpha = Math.max(alpha,compare);
				if(beta <= alpha) break;
			}
			return compare;
		}
		else{ //this player wants min
			for(int i=0; i<size; i++) { // for each child of node
				//possibleMoves.elementAt(i)
				//v := max(v, alphabeta(child, depth � 1, , ,
				move = (Move)child.elementAt(i); //get move
				executeMove = Utils.executeMove(whosTurn, move, bs);
				
				if(whosTurn == 1)whosTurn = 2;
				else whosTurn = 1;
				
				compare = Math.min(compare, AlphaBeta(depth-1, alpha, beta, bs, whosTurn, true));
				Utils.setBoardState(executeMove, bs);
				// b := min(b , v)
				beta = Math.min(beta,compare);
				if(beta <= alpha) break;
			}
			return compare;
		}
	}

}