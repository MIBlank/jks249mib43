package game;

public class Gamestate {
	
	/**
	 * @param column the column in question, from 2 to 12
	 * @param playerID the player whose progress we are checking, either 0 or 1.
	 * @return the number of spaces this player has advanced permanently, with
	 * the colored cone, in this column. 0 if the player has no cone on this column.
	 */
	public int getProgress(int column, int playerID) {
		return 0;
	}
	
	
	/**
	 * @param column the column in question, from 2 to 12
	 * @return the number of spaces the white cone has advanced BEYOND THE COLORED
	 * CONE in this column for the current player this turn. 0 if there is no white
	 * cone in this column.
	 */
	public int getTempProgress(int column) {
		return 0;
	}
	
	/**
	 * @param column the column in question, from 2 to 12
	 * @return the total number of spaces in this column, including the final space.
	 */
	public int getMaxProgress(int column) {
		return 0;
	}
	
	
	/**
	 * @return the number of white cones that the current player has not yet
	 * placed this turn
	 */
	public int numFreeCones() {
		return 0;
	}
	
	/**
	 * @return the number of moves the current player has completed this turn
	 */
	public int turnLength() {
		return 0;
	}
}
