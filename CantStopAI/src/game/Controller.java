package game;

import java.util.ArrayList;
import java.util.Random;

public class Controller {
	public static Gamestate gamestate = new Gamestate();
	public static Random random = new Random();
	public static BasicAgent player1 = new BasicAgent(1, gamestate);
	public static BasicAgent player2 = new BasicAgent(2, gamestate);
	public static int active_playerID = 0;
	
	public static int[] roll_Dice(){;
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
			
			if(gamestate.is_valid_column(move0) && gamestate.is_valid_column(move1)){
				moves.add(new Move(movepair1,false));
			}
			else{
				int[] movear1 = new int[2];
				movear1[0] = move0;
				movear1[1] = 0;
				int[] movear2 = new int[2];
				movear2[0] = move1;
				movear2[1] = 0;
				if(gamestate.is_valid_column(move0))
					moves.add(new Move(movear1,false));
				if(gamestate.is_valid_column(move1))
					moves.add(new Move(movear2,false));
			}
			
			if(gamestate.is_valid_column(move2)&& gamestate.is_valid_column(move3)){
				moves.add(new Move(movepair2,false));
			}
			else{
				int[] movear1 = new int[2];
				movear1[0] = move2;
				movear1[1] = 0;
				int[] movear2 = new int[2];
				movear2[0] = move3;
				movear2[1] = 0;
				if(gamestate.is_valid_column(move2))
					moves.add(new Move(movear1,false));
				if(gamestate.is_valid_column(move3))
					moves.add(new Move(movear2,false));
			}
			
			if(gamestate.is_valid_column(move4) && gamestate.is_valid_column(move5)){
				moves.add(new Move(movepair3,false));
			}
			else{
				int[] movear1 = new int[2];
				movear1[0] = move4;
				movear1[1] = 0;
				int[] movear2 = new int[2];
				movear2[0] = move5;
				movear2[1] = 0;
				if(gamestate.is_valid_column(move4))
					moves.add(new Move(movear1,false));
				if(gamestate.is_valid_column(move5))
					moves.add(new Move(movear2,false));
			}
			
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
			if((curr_cols.contains(move0) && gamestate.is_valid_column(move1)) || 
					(curr_cols.contains(move1) && gamestate.is_valid_column(move0))){
				moves.add(new Move(movepair1,false));
			}
			else{
				int[] movear1 = new int[2];
				movear1[0] = move0;
				movear1[1] = 0;
				int[] movear2 = new int[2];
				movear2[0] = move1;
				movear2[1] = 0;
				if(gamestate.is_valid_column(move0))
					moves.add(new Move(movear1,false));
				if(gamestate.is_valid_column(move1))
					moves.add(new Move(movear2,false));
			}
			
			if((curr_cols.contains(move2) && gamestate.is_valid_column(move3)) || 
					(curr_cols.contains(move3) && gamestate.is_valid_column(move2))){
				moves.add(new Move(movepair2,false));
			}
			else{
				int[] movear1 = new int[2];
				movear1[0] = move2;
				movear1[1] = 0;
				int[] movear2 = new int[2];
				movear2[0] = move3;
				movear2[1] = 0;
				if(gamestate.is_valid_column(move2))
					moves.add(new Move(movear1,false));
				if(gamestate.is_valid_column(move3))
					moves.add(new Move(movear2,false));
			}
			
			if((curr_cols.contains(move4) && gamestate.is_valid_column(move5)) || 
					(curr_cols.contains(move5) && gamestate.is_valid_column(move4))){
				moves.add(new Move(movepair3,false));
			}
			else{
				int[] movear1 = new int[2];
				movear1[0] = move4;
				movear1[1] = 0;
				int[] movear2 = new int[2];
				movear2[0] = move5;
				movear2[1] = 0;
				if(gamestate.is_valid_column(move4))
					moves.add(new Move(movear1,false));
				if(gamestate.is_valid_column(move5))
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
				int[] movear = new int[2];
				movear[0] = move0;
				movear[1] = 0;
				moves.add(new Move(movear,false));
			}
			else if(curr_cols.contains(move1)){
				int[] movear = new int[2];
				movear[0] = move1;
				movear[1] = 0;
				moves.add(new Move(movear,false));
			}
			
			if(curr_cols.contains(move2) && curr_cols.contains(move3)){
				moves.add(new Move(movepair2,false));
			}
			else if(curr_cols.contains(move2)){
				int[] movear = new int[2];
				movear[0] = move2;
				movear[1] = 0;
				moves.add(new Move(movear,false));
			}
			else if(curr_cols.contains(move3)){
				int[] movear = new int[2];
				movear[0] = move3;
				movear[1] = 0;
				moves.add(new Move(movear,false));
			}
			
			if(curr_cols.contains(move4) && curr_cols.contains(move5)){
				moves.add(new Move(movepair3,false));
			}
			else if(curr_cols.contains(move4)){
				int[] movear = new int[2];
				movear[0] = move4;
				movear[1] = 0;
				moves.add(new Move(movear,false));
			}
			else if(curr_cols.contains(move5)){
				int[] movear = new int[2];
				movear[0] = move5;
				movear[1] = 0;
				moves.add(new Move(movear,false));
			}
		}
		
		return moves;
	}
	
	
	/**
	 * Saves the players progress by adding the temp progress to the active progress
	 */
	public static void handle_stop(int playerID){
		for(int col : gamestate.active_columns){
			/*if(playerID == 1){
				System.out.println("before: " + gamestate.columnList.get(col).player1_saved_progress);
				System.out.println(gamestate.columnList.get(col).player1_temp_progress);
			}
			else{
				System.out.println("before: " + gamestate.columnList.get(col).player2_saved_progress);
				System.out.println(gamestate.columnList.get(col).player2_temp_progress);
			}*/
			
			
			
			gamestate.columnList.get(col).save_temp_progress(playerID);
			if(gamestate.columnList.get(col).is_finished){
				gamestate.update_finished_columns(col, playerID);
				/*System.out.println(gamestate.columnList.get(col).player1_saved_progress);
				System.out.println(gamestate.columnList.get(col).player2_saved_progress);
				System.out.println(gamestate.columnList.get(col).player1_temp_progress);
				System.out.println(gamestate.columnList.get(col).player2_temp_progress);
				System.out.println("new turn");*/
			}
			/*
			if(playerID == 1){
				System.out.println("after: " +gamestate.columnList.get(col).player1_saved_progress);
			}
			else{
				System.out.println("after: " +gamestate.columnList.get(col).player2_saved_progress);
			}*/
		}
		gamestate.active_columns.clear();
		gamestate.freeCones = 3;
		active_playerID = (active_playerID + 1)%2;
	}
	
	
	public static void take_turn(int playerID){
		while(active_playerID == playerID){
			int[] roll = roll_Dice();
			ArrayList<Move> moves = get_moves(playerID,roll,gamestate.active_columns);
			
			//Handle the loss of progress in the case of no moves.
			if(moves.size() == 0){
				for(int col : gamestate.active_columns)
					gamestate.columnList.get(col).reset_temp_progress(playerID);
				gamestate.active_columns.clear();
				gamestate.freeCones = 3;
				active_playerID = (active_playerID + 1)%2;
			}
			else{
				Move chosen_move = null;
				
				if(playerID == 0){
					chosen_move = player1.getNextMove(moves);
				}
				else{
					chosen_move = player2.getNextMove(moves);
				}
				
				for(int i = 0; i < chosen_move.columns.length; i++){
					int column = chosen_move.columns[i];
					
					if(column == 0){
						
					}
					else{
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
				gamestate.moves_completed++;
				if(chosen_move.stop){
					handle_stop(playerID);
				}
			}
		}
	}
	
	
	public static void main(String args[]){
		while((gamestate.player1_columns_finished < 3) && gamestate.player2_columns_finished < 3){
			take_turn(active_playerID);
			ArrayList<Integer> progress = new ArrayList<Integer>();
			for(int i = 2; i < 13; i++){
				progress.add(gamestate.columnList.get(i).get_saved_progress(1));
			}
			System.out.println("Player 1" + progress);
			//System.out.println(gamestate.player1_columns_finished);
			//System.out.println(gamestate.player2_columns_finished);
			//Note: Use turnLength() in the gamestate here, before it is reset for the next turn
			gamestate.moves_completed = 0;
		}
	}
	
}
