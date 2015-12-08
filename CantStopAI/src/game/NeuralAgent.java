package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class NeuralAgent extends Agent {
	
	public NeuralNetwork network;
	private int[] inputs;
	private int[] outputs;

	public NeuralAgent(int playerID, Gamestate state) {
		super(playerID, state);
		this.network = new NeuralNetwork();
		this.inputs = new int[NeuralNetwork.NUM_INPUTS];
		this.outputs = new int[NeuralNetwork.NUM_OUTPUTS];
	}

	@Override
	public Move getNextMove(ArrayList<Move> options) {
		int base = 0;
		
		int numPastCompleted = 0;
		int numJustCompleted = 0;
		for(int i = 2; i <= 12; i++) {
			int playerProgress = state.getProgress(i, playerID);
			inputs[base] = playerProgress;
			inputs[base + 11] = state.getProgress(i, 1 - playerID);
			int tempProgress = state.getTempProgress(i);
			inputs[base + 22] = tempProgress;
			base++;
			
			if(playerProgress == state.getMaxProgress(i)) {
				numPastCompleted++;
			}
			else {
				if(playerProgress + tempProgress == state.getMaxProgress(i)) {
					numJustCompleted++;
				}
			}
		}
		inputs[33] = state.numFreeCones();
		inputs[34] = state.turnLength();
		inputs[35] = numJustCompleted;
		
		
		Arrays.fill(outputs, 0);
		
		network.setOutputs(inputs, outputs);
		
		int bestScore = Integer.MIN_VALUE;
		Move bestMove = null;
		
		for(Move move: options) {
			int score = 0;
			for(int col: move.columns) {
				if(col != 0) {
					score += outputs[col];
				}
			}
			
			if(score > bestScore) {
				bestScore = score;
				bestMove = move;
			}
		}
		
		//outputs[0] = score for stop, outputs[1] = score for continue
		//chose option with higher score, break ties randomly.
		if(outputs[0] > outputs[1] || numJustCompleted + numPastCompleted == 3) {
			bestMove.stop = true;
		}
		else {
			if(outputs[0] < outputs[1]) {
				bestMove.stop = false;
			}
			else {
				bestMove.stop = new Random().nextInt(2) == 0;
			}
		}
		
		return bestMove;
	}
	
	/**
	 * sets this agent's network to be a copy of parent's network with n mutations
	 */
	public void mutate(NeuralAgent parent, int n) {
		this.network = parent.network.mutate(n);
	}
}
