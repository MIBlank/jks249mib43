package game;

public class Move {
	public int[] columns;
	public boolean stop;
	
	/**
	 * Represents a single action resulting from a single roll of the dice.
	 * 
	 * Each entry in the array represents advancing a white cone by one space 
	 * in the column specified. If both entries are the same, then the cone is
	 * advanced two spaces in that column.
	 * 
	 * Example: if the die roll is 1, 2, 3, 4 and the player has white cones in
	 * the columns 5 and 7 (one free cone remaining) then the valid
	 * pairings of the dice are (3,7), (5,5), and (4,6); and the corresponding
	 * legal move arrays are [3, 7], [5, 5], [4], and [6]. 
	 * 
	 * Stop is set to true if the agent wishes to Stop after executing this move
	 * 
	 * @param columns an array of length at most 2 specifying the columns
	 * in which an advance by one space is made
	 * @param stop whether the player will stop after executing this move.
	 */
	public Move(int[] columns, boolean stop) {
		this.columns = columns;
		this.stop = stop;
	}
}
