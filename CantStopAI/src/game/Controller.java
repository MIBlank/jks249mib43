package game;

import java.util.ArrayList;
import java.util.Random;

public class Controller {
	public static Gamestate gamestate = new Gamestate();
	public static Random random = new Random();
	public static BasicAgent player1 = new BasicAgent(1, gamestate);
	public static BasicAgent player2 = new BasicAgent(2, gamestate);
	
	public static int[] roll_Dice(){
		System.out.println("ok");
		int die1 = random.nextInt(6) + 1;
		int die2 = random.nextInt(6) + 1;
		int die3 = random.nextInt(6) + 1;
		int die4 = random.nextInt(6) + 1;
		int[] roll = new int[4];
		roll[0] = die1;
		roll[1] = die2;
		roll[2] = die3;
		roll[3] = die4;
		return roll;
		
	}
	
	public static ArrayList<Move> get_moves(int playerid, int[] roll, ArrayList<Integer> curr_cols){
		ArrayList<Move> moves = new ArrayList<Move>();
		
		int move0 = roll[0] + roll[1];
		int move1 = roll[2] + roll[3];
		int move2 = roll[0] + roll[2];
		int move3 = roll[1] + roll[3];
		int move4 = roll[0] + roll[3];
		int move5 = roll[1] + roll[2];
		
		if(gamestate.numFreeCones()>1){
			int[] movepair1 = new int[2];
			movepair1[0] = move0;
			movepair1[1] = move1;
			
			int[] movepair2 = new int[2];
			movepair2[0] = move2;
			movepair2[1] = move3;
			
			int[] movepair3 = new int[2];
			movepair3[0] = move4;
			movepair3[1] = move5;
			
			moves.add(new Move(movepair1,false));
			moves.add(new Move(movepair2,false));
			moves.add(new Move(movepair3,false));
		}
		else if(gamestate.numFreeCones() == 1){
			int[] movepair1 = new int[2];
			movepair1[0] = move0;
			movepair1[1] = move1;
			
			int[] movepair2 = new int[2];
			movepair2[0] = move2;
			movepair2[1] = move3;
			
			int[] movepair3 = new int[2];
			movepair3[0] = move4;
			movepair3[1] = move5;
			
			//Split the pair of moves into 2 moves if the pair is not a valid move
			if(curr_cols.contains(move0) || curr_cols.contains(move1)){
				moves.add(new Move(movepair1,false));
			}
			else{
				int[] movear1 = new int[1];
				movear1[0] = move0;
				int[] movear2 = new int[1];
				movear2[0] = move1;
				moves.add(new Move(movear1,false));
				moves.add(new Move(movear2,false));
			}
			
			if(curr_cols.contains(move2) || curr_cols.contains(move3)){
				moves.add(new Move(movepair2,false));
			}
			else{
				int[] movear1 = new int[1];
				movear1[0] = move2;
				int[] movear2 = new int[1];
				movear2[0] = move3;
				moves.add(new Move(movear1,false));
				moves.add(new Move(movear2,false));
			}
			
			if(curr_cols.contains(move4) || curr_cols.contains(move5)){
				moves.add(new Move(movepair3,false));
			}
			else{
				int[] movear1 = new int[1];
				movear1[0] = move4;
				int[] movear2 = new int[1];
				movear2[0] = move5;
				moves.add(new Move(movear1,false));
				moves.add(new Move(movear2,false));
			}
			
		}
		
		else if(gamestate.numFreeCones() == 0){
			int[] movepair1 = new int[2];
			movepair1[0] = move0;
			movepair1[1] = move1;
			
			int[] movepair2 = new int[2];
			movepair2[0] = move2;
			movepair2[1] = move3;
			
			int[] movepair3 = new int[2];
			movepair3[0] = move4;
			movepair3[1] = move5;
			
			//Split the pair of moves into 2 moves if the pair is not a valid move
			if(curr_cols.contains(move0) && curr_cols.contains(move1)){
				moves.add(new Move(movepair1,false));
			}
			else if(curr_cols.contains(move0)){
				int[] movear = new int[1];
				movear[0] = move0;
				moves.add(new Move(movear,false));
			}
			else if(curr_cols.contains(move1)){
				int[] movear = new int[1];
				movear[0] = move1;
				moves.add(new Move(movear,false));
			}
			
			if(curr_cols.contains(move2) && curr_cols.contains(move3)){
				moves.add(new Move(movepair2,false));
			}
			else if(curr_cols.contains(move2)){
				int[] movear = new int[1];
				movear[0] = move2;
				moves.add(new Move(movear,false));
			}
			else if(curr_cols.contains(move3)){
				int[] movear = new int[1];
				movear[0] = move3;
				moves.add(new Move(movear,false));
			}
			
			if(curr_cols.contains(move4) && curr_cols.contains(move5)){
				moves.add(new Move(movepair3,false));
			}
			else if(curr_cols.contains(move4)){
				int[] movear = new int[1];
				movear[0] = move4;
				moves.add(new Move(movear,false));
			}
			else if(curr_cols.contains(move5)){
				int[] movear = new int[1];
				movear[0] = move5;
				moves.add(new Move(movear,false));
			}
		}
		
		return moves;
	}
	
	public void handle_stop(int playerID){
		
	}
	
	
	public static void take_turn(int playerID){
		int[] roll = roll_Dice();
		ArrayList<Move> moves = get_moves(playerID,roll,gamestate.active_columns);
		Move chosen_move = null;
		if(playerID == 0){
			chosen_move = player1.getNextMove(moves);
		}
		else{
			chosen_move = player2.getNextMove(moves);
		}
		for(int i = 0; i < chosen_move.columns.length; i++){
			int column = chosen_move.columns[i];
			if(gamestate.numFreeCones() > 0)
				gamestate.freeCones--;
			if(!(gamestate.active_columns.contains(column)))
					gamestate.active_columns.add(column);
			if(playerID == 0){
				gamestate.columnList.get(column).player1_temp_progress++;
			}
			else{
				gamestate.columnList.get(column).player2_temp_progress++;
			}
		}
		
		
	}
	
	
	public static void main(String args[]){
		//while(gamestate.numFreeCones() <3){
		//}
		take_turn(0);
		
	}
	
}
