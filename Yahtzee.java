
/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.Arrays;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	public void run() {

		readPlayers();
		display = new YahtzeeDisplay(getGCanvas(), playerNames);

		playGame();

	}

	private void readPlayers() {
		IODialog dialog = getDialog();

		nPlayers = dialog.readInt("Enter number of players");
		while (nPlayers > MAX_PLAYERS) {
			nPlayers = dialog.readInt("Enter number of players(less/equal to " + MAX_PLAYERS + ")");
		}

		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}

	}

	private void playGame() {
		/* Play Yahtzee game */
		dice = generateDice(N_DICE); // generate initials points

		for (int i = 0; i < dice.length; i++) {
			System.out.println(dice[i]);
		}
		
			display.waitForPlayerToClickRoll(1);
			display.displayDice(dice);
			
			display.waitForPlayerToSelectDice();
			rollSelected(dice);
			display.displayDice(dice);
			
			display.waitForPlayerToSelectDice();
			rollSelected(dice);
			display.displayDice(dice);
		

	}

	private void rollSelected(int[] array) {
		for (int i = 0; i < array.length; i++)
			if (display.isDieSelected(i)) {
				array[i] = rgen.nextInt(1, array.length);
			}
	}

	private int[] generateDice(int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = rgen.nextInt(1, size);
		}
		return arr;
	}

	/* Private instance variables */
	private int nPlayers; // number of players
	private static int[] dice; // array of dice elements
	private String[] playerNames; // array of strings containing player names
	private YahtzeeDisplay display; // instance of the YahtzeeDisplay class
	private RandomGenerator rgen = new RandomGenerator(); // instance of a random generator
	private int nturns = 3;

}
