package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class NeuralNetwork {
	//player progress, opponent progress, temp progress in each of 11 columns,
	//number of free white cones, number of completed rolls this turn, number
	//of columns completed by white cones
	public static final int NUM_INPUTS = 36;
	
	//stop = 0, continue = 1, 2-12 = weight for respective columns
	public static final int NUM_OUTPUTS = 13;
	public static final int NUM_HIDDEN = 25;
	
	private int[][] leftWeights;
	private int[][] rightWeights;
	private int[] hidden;
	
	public NeuralNetwork() {
		this.leftWeights = new int[NUM_INPUTS][NUM_HIDDEN];
		this.rightWeights = new int[NUM_HIDDEN][NUM_OUTPUTS];
		this.hidden = new int[NUM_HIDDEN];
	}
	
	/**
	 * Requires inputs.length = NUM_INPUTS and outputs.length = NUM_OUTPUTS;
	 * @param inputs
	 * @param outputs the output array to be set, starting as all zeros
	 */
	public void setOutputs(int[] inputs, int[] outputs) {
		Arrays.fill(hidden, 0);
		for(int in = 0; in < inputs.length; in++) {
			for(int out = 0; out < hidden.length; out++) {
				hidden[out] += inputs[in] * leftWeights[in][out];
			}
		}
		for(int in = 0; in < hidden.length; in++) {
			for(int out = 0; out < outputs.length; out++) {
				outputs[out] += hidden[in] * rightWeights[in][out];
			}
		}
	}
	
	/**
	 * n times: chooses a random weight and modifies it by +1 or -1
	 * Returns a new neural network with these changes.
	 * Does not modify the current network.
	 */
	public NeuralNetwork mutate(int n) {
		NeuralNetwork child = new NeuralNetwork();
		
		//copy the current weights
		for(int in = 0; in < NUM_INPUTS; in++) {
			for(int out = 0; out < NUM_HIDDEN; out++) {
				child.leftWeights[in][out] = this.leftWeights[in][out];
			}
		}
		for(int in = 0; in < NUM_HIDDEN; in++) {
			for(int out = 0; out < NUM_OUTPUTS; out++) {
				child.rightWeights[in][out] = this.rightWeights[in][out];
			}
		}
		
		Random r = new Random();
		for(int i = 0; i < n; i++) {
			//uniformly choose a weight to mutate
			int half = r.nextInt((NUM_INPUTS+NUM_OUTPUTS)*NUM_HIDDEN);
			if(half < NUM_INPUTS * NUM_HIDDEN) {
				int in = r.nextInt(NUM_INPUTS);
				int out = r.nextInt(NUM_HIDDEN);
				int change = (r.nextInt(2) == 0) ? -1:1;
				child.leftWeights[in][out] += change;
			}
			else {
				int in = r.nextInt(NUM_HIDDEN);
				int out = r.nextInt(NUM_OUTPUTS);
				int change = (r.nextInt(2) == 0) ? -1:1;
				child.rightWeights[in][out] += change;
			}
		}
		
		return child;
	}
	
	//make sure the weights are written and read in the same order...
	
	public void save(String filename) {
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(filename));
			for(int in = 0; in < NUM_INPUTS; in++) {
				for(int out = 0; out < NUM_HIDDEN; out++) {
					w.write( this.leftWeights[in][out] + "\n");
				}
			}
			for(int in = 0; in < NUM_HIDDEN; in++) {
				for(int out = 0; out < NUM_OUTPUTS; out++) {
					w.write( this.rightWeights[in][out] + "\n");
				}
			}
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load(String filename) {
		try {
			BufferedReader r = new BufferedReader(new FileReader(filename));
			for(int in = 0; in < NUM_INPUTS; in++) {
				for(int out = 0; out < NUM_HIDDEN; out++) {
					leftWeights[in][out] = Integer.parseInt(r.readLine());
				}
			}
			for(int in = 0; in < NUM_HIDDEN; in++) {
				for(int out = 0; out < NUM_OUTPUTS; out++) {
					rightWeights[in][out] = Integer.parseInt(r.readLine());
				}
			}
			r.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
