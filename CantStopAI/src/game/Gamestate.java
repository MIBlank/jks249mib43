package game;

import java.util.ArrayList;

public class Gamestate {
	public int moves_completed;
	public int freeCones = 0;
	public ArrayList<Column> columnList = new ArrayList<Column>();
	public int player1_columns_finished = 0;
	public int player2_columns_finished = 0;
	public ArrayList<Integer> active_columns = new ArrayList<Integer>();
	public ArrayList<Integer> finished_columns = new ArrayList<Integer>();
	
	Gamestate(){
		columnList.add(null);
		columnList.add(null);
		for(int i = 2; i < 8; i++){
			columnList.add(new Column(i));
		}
		for(int i = 6; i > 1; i--){
			columnList.add(new Column(i));
		}
		freeCones = 3;
	}
	
	public void reset() {
		columnList.clear();
		columnList.add(null);
		columnList.add(null);
		for(int i = 2; i < 8; i++){
			columnList.add(new Column(i));
		}
		for(int i = 6; i > 1; i--){
			columnList.add(new Column(i));
		}
		
		freeCones = 3;
		
		moves_completed = 0;
		player1_columns_finished = 0;
		player2_columns_finished = 0;
		active_columns.clear();
		finished_columns.clear();
	}
	
	/**
	 * @param column the column in question, from 2 to 12
	 * @param playerID the player whose progress we are checking, either 0 or 1.
	 * @return the number of spaces this player has advanced permanently, with
	 * the colored cone, in this column. 0 if the player has no cone on this column.
	 */
	public int getProgress(int column, int playerID) {
		return columnList.get(column).get_saved_progress(playerID);
	}
	
	
	/**
	 * @param column the column in question, from 2 to 12
	 * @return the number of spaces the white cone has advanced BEYOND THE COLORED
	 * CONE in this column for the current player this turn. 0 if there is no white
	 * cone in this column.
	 */
	public int getTempProgress(int column) {
		return columnList.get(column).get_temp_progress(Controller.active_playerID);
	}
	
	/**
	 * @param column the column in question, from 2 to 12
	 * @return the total number of spaces in this column, including the final space.
	 */
	public int getMaxProgress(int column) {
		return columnList.get(column).get_size();
	}
	
	
	/**
	 * @return the number of white cones that the current player has not yet
	 * placed this turn
	 */
	public int numFreeCones() {
		return freeCones;
	}
	
	/**
	 * @return the number of moves the current player has completed this turn
	 */
	public int turnLength() {
		return moves_completed;
	}
	
	/**
	 * Updates a player's number of finished columns.
	 */
	public void update_finished_columns(int col, int playerID){
		if(playerID == 0){
			player1_columns_finished++;
		}
		else{
			player2_columns_finished++;
		}
		finished_columns.add(col);
	}
	
	public boolean is_valid_column(int col){
		//I changed this. -James
		return (((active_columns.contains(col)) || numFreeCones() > 0) && !(finished_columns.contains(col)));
	}
	
}
