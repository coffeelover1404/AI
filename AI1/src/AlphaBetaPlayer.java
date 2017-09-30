

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
		int max = 0, min = 100;
		int score = 0;
		Move finalMove = null;
		Vector possibleMoves = Utils.getAllPossibleMoves(bs, whosTurn);
		int size = possibleMoves.size();
		
		if(whosTurn==1) { 		// RED_PLAYER / MAX
			if(size != 0) {
				for(int i=0; i<size; i++) {
					move = (Move)possibleMoves.elementAt(i);
					executeMove = Utils.executeMove(whosTurn, move, bs);
					score = Utils.scoreCheckerBoard(bs, whosTurn);
					System.out.println(score);
					if(score > max) {
						max = score;
						finalMove = move;
					}
				}
			}
		} else { 				// BLACK_PLAYER / MIN
			if(size != 0) {
				for(int i=0; i<size; i++) {
					move = (Move)possibleMoves.elementAt(i);
					executeMove = Utils.executeMove(whosTurn, move, bs);
					score = Utils.scoreCheckerBoard(bs, whosTurn);
					System.out.print(score+"  ");
					if(score < min) {
						min = score;
						finalMove = move;
					}
				}
				System.out.println();
			}
		}
		if(whosTurn==1) System.out.println("max -------------"+max);
		else System.out.println("min -------------"+min);
		chosenMove = finalMove;

	}

}