package game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Basic reference agent that more or less just takes random moves and stops
 * when it has made a reasonable amount of progress
 * @author jamessenter
 *
 */
public class BasicAgent extends Agent {

	public BasicAgent(int playerID, Gamestate state) {
		super(playerID, state);
	}

	@Override
	public Move getNextMove(ArrayList<Move> options) {
		Move chosenMove = options.get(new Random().nextInt(options.size()));
		if(state.numFreeCones() == 0) {
			float progress = 0;
			for(int col = 2; col <= 12; col++) {
				int tempProg = state.getTempProgress(col);
				if(chosenMove.columns[0] == col) {
					tempProg += 1;
				}
				if(chosenMove.columns[1] == col) {
					tempProg += 1;
				}
				if(tempProg > 0 && state.getProgress(col, playerID) + tempProg >= state.getMaxProgress(col)) {
					chosenMove.stop = true;
					break;
				}
				progress += ((float)tempProg)/state.getMaxProgress(col);
			}
			if(progress >= 0.5) {
				chosenMove.stop = true;
			}
		}
		return chosenMove;
	}

}
