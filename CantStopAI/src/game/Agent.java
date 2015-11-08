package game;

import java.util.ArrayList;

public abstract class Agent {
	public final int playerID;
	private Gamestate state;
	
	public Agent(int playerID, Gamestate state) {
		this.playerID = playerID;
		this.state = state;
	}
	
	/**
	 * @param options the list of legal moves the agent could make this turn
	 * @return the move that this agent chooses to make.
	 */
	public abstract Move getNextMove(ArrayList<Move> options);
}
