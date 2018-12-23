
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
		/*
		 * According to game terms, players more than 4 are not allowed. while loop
		 * controls this term
		 */
		while (nPlayers > MAX_PLAYERS) {
			nPlayers = dialog.readInt("Enter number of players(less/equal to " + MAX_PLAYERS + ")");
		}

		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		/*
		 * populates boolean tables with default values, table is used for saving the
		 * category selected by current player.
		 */
		populateTable(N_CATEGORIES, nPlayers);

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
				
				if(isAlreadyUpdated(category, player)) {
					display.printMessage("This category is already selected, try another!");
				}

				setCategorySelected(category, player);
				print2DArray();

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

	private void print2DArray() {
		int counter = 0;
		for (int i = 0; i < selected.length; i++) {
			for (int j = 0; j < selected[0].length; j++) {
				System.out.println(selected[i][j]);
				counter++;
			}
		}
		System.out.println(counter);
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

	private void setCategorySelected(int category, int player) {
		selected[category - 1][player - 1] = true;
	}
	
	private boolean isAlreadyUpdated(int category, int player) {
		return selected[category - 1][player - 1] == true;
	}

	private void populateTable(int rows, int colomns) {
		selected = new boolean[rows][colomns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < colomns; j++) {
				selected[i][j] = false;
			}
		}
	}

	private boolean checkCategory(int category, int[] array) {
		CategoryChecker checker = new CategoryChecker();
		boolean flag = false;
//		if (checker.isOneToSix(array, category)) {
//			flag = true;
		if (checker.isThreeOfAKind(array) && category == THREE_OF_A_KIND) {
			flag = true;
		} else if (checker.isFourOfAKind(array) && category == FOUR_OF_A_KIND) {
			flag = true;
		} else if (checker.isFullHouse(array) && category == FULL_HOUSE) {
			System.out.println(Arrays.toString(array));
			flag = true;
		} else if (checker.isSmallStraight(array) && category == SMALL_STRAIGHT) {
			System.out.println(Arrays.toString(array));
			flag = true;
		} else if (checker.isLargeStraight(array) && category == LARGE_STRAIGHT) {
			System.out.println(Arrays.toString(array));
			flag = true;
		} else if (checker.isYahtzee(array) && category == YAHTZEE) {
			flag = true;
		} else if (checker.isChance(array) && category == CHANCE) {
			flag = true;
		} else {
			switch (category) {
			case ONES:
			case TWOS:
			case THREES:
			case FOURS:
			case FIVES:
			case SIXES:
				flag = true;
				break;
			}
		}

		return flag;

	}

	private int countScores(int category, boolean isRight, int[] array) {

		int score = 0;
		if (isRight) {
			switch (category) {
			// case CHANCE:

			case ONES:
				score = scorescounter.getOnes(array);
				break;
			case TWOS:
				score = scorescounter.getTwos(array);
				break;
			case THREES:
				score = scorescounter.getThrees(array);
				break;
			case FOURS:
				score = scorescounter.getFours(array);
				break;
			case FIVES:
				score = scorescounter.getFives(array);
				break;
			case SIXES:
				score = scorescounter.getSixes(array);
				break;

			case THREE_OF_A_KIND:
				score = scorescounter.getThreeOfAKindScore(array);
				break;
			case FOUR_OF_A_KIND:
				score = scorescounter.getFourOfAKindScore(array);
				break;
			case SMALL_STRAIGHT:
				score = scorescounter.getSmallStraightScore();
				break;
			case LARGE_STRAIGHT:
				score = scorescounter.getLargeStraightScore();
				break;
			case FULL_HOUSE:
				score = scorescounter.getFullHouseScore();
				break;
			case YAHTZEE:
				score = scorescounter.getYahtzeeScore();
				break;
			default:
				score = scorescounter.getChanceScore(array);
				break;

			}
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
	ScoresCounter scorescounter = new ScoresCounter();
	private int nPlayers; // number of players
	private static int[] dice; // array of dice elements
	private String[] playerNames; // array of strings containing player names
	private YahtzeeDisplay display; // instance of the YahtzeeDisplay class
	private RandomGenerator rgen = new RandomGenerator(); // instance of a random generator
	private boolean[][] selected;

}