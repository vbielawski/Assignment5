
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

	// ScoresCounter scores = new ScoresCounter();

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
		while (true) {
			for (int player = 1; player <= nPlayers; player++) {
				dice = generateDice(N_DICE); // generate initials dice values

				display.waitForPlayerToClickRoll(player);
				display.displayDice(dice);

				for (int i = 0; i < 2; i++) {
					nextRollsCall();
				}

//			display.waitForPlayerToSelectDice();
//			rollSelected(dice);
//			display.displayDice(dice);

				int category = display.waitForPlayerToSelectCategory();
				setSelected(category, player);

				boolean isRelevant = checkCategory(category, dice);
				System.out.println(isRelevant);
				// boolean corresponds = checker.isOneToSix(dice, category);
				int score = countScores(category, isRelevant, dice);
				System.out.println(score);

				display.updateScorecard(category, player, score);

				String message = "Score = " + score;
				display.printMessage(message);
			}

			if (allCategoryIsSelected()) {
				sumScores();
			}
			printResult();
		}

	}

	private void printResult() {

	}

	private boolean allCategoryIsSelected() {
		return false;
	}

	private void sumScores() {
		sumUpper();
		writeBonus();
		sumBottom();
		sumTotal();
	}

	private void sumBottom() {
		// TODO Auto-generated method stub

	}

	private void sumTotal() {
		// TODO Auto-generated method stub

	}

	private void writeBonus() {
		// TODO Auto-generated method stub

	}

	private void sumUpper() {
		// TODO Auto-generated method stub

	}

	private void setSelected(int category, int player) {

	}

	private boolean checkCategory(int category, int[] array) {
		CategoryChecker checker = new CategoryChecker();
		boolean flag = false;
//		if (checker.isOneToSix(array, category)) {
//			flag = true;
		if (checker.isThreeOfAKind(array, category)) {
			flag = true;
		} else if (checker.isFourOfAKind(array, category)) {
			flag = true;
		} else if (checker.isSmallStraight(array, category)) {
			flag = true;
		} else if (checker.isBigStraight(array, category)) {
			flag = true;
		} else if (checker.isYahtzee(array, category)) {
			flag = true;
		} else {
			flag = false;
		}

		return flag;

	}

	private int countScores(int category, boolean isRight, int[] array) {

		int score = 0;
		if (isRight) {
			switch (category) {
			case ONES:
			case TWOS:
			case THREES:
			case FOURS:
			case FIVES:
			case SIXES:
				score = counter.getScoreOneSix(category, array);

			case THREE_OF_A_KIND:
				score = counter.getThreeOfAKindScore(array);
			case FOUR_OF_A_KIND:
				score = counter.getFourOfAKindScore(array);
			case SMALL_STRAIGHT:
				score = counter.getSmallStraightScore(array);
			case LARGE_STRAIGHT:
				score = counter.getLargeStraightScore(array);
			case FULL_HOUSE:
				score = counter.getFullHouseScore(array);
			case YAHTZEE:
				score = counter.getYahtzeeScore(array);
			case CHANCE:
				score = counter.getChanceScore(array);

			}

			return score;

		}
		return score;
	}

	private void nextRollsCall() {
		display.waitForPlayerToSelectDice();
		rollSelected(dice);
		display.displayDice(dice);
	}

	private void rollSelected(int[] array) {
		for (int i = 0; i < array.length; i++)
			if (display.isDieSelected(i)) {
				array[i] = rgen.nextInt(1, array.length + 1);
			}
	}

	private int[] generateDice(int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = rgen.nextInt(1, size + 1);
		}
		return arr;
	}

	/* Private instance variables */
	ScoresCounter counter = new ScoresCounter();
	private int nPlayers; // number of players
	private static int[] dice; // array of dice elements
	private String[] playerNames; // array of strings containing player names
	private YahtzeeDisplay display; // instance of the YahtzeeDisplay class
	private RandomGenerator rgen = new RandomGenerator(); // instance of a random generator

}
