
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

		readPlayers(); // read and initialise players

		display = new YahtzeeDisplay(getGCanvas(), playerNames);

		playGame(); // gives the player opportunity to play yahtzee game

	}

	/*
	 * method reads number of players and names from console and writes them in
	 * array
	 */
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
		selected = populateTable(N_CATEGORIES, nPlayers); // 2D array of booleans
		scorecard = new int[N_CATEGORIES][nPlayers]; // 2D array of integers

	}

	private void playGame() {
		/* Play Yahtzee game */
		// int steps = N_SCORING_CATEGORIES * nPlayers;
		int category = 0;
		/*
		 * game goes on until all scoring categories are selected, then program sums all
		 * scores, updates scorecard and prints the results.
		 */
		for (int n = 0; n < N_SCORING_CATEGORIES; n++) {
			for (int player = 1; player <= nPlayers; player++) {
				dice = generateDice(N_DICE); // generate initials dice values
				display.printMessage(playerNames[player - 1] + "'s turn! Click 'Roll Dice' Button to roll the dice! ");

				display.waitForPlayerToClickRoll(player);
				display.displayDice(dice);

				// re-roll of dices
				for (int i = 0; i < 2; i++) {
					display.printMessage("Select the dice you wish to re-roll and click 'Roll again'!");
					nextRollsCall();

				}

				display.printMessage("Select a category for this roll.");

				category = display.waitForPlayerToSelectCategory();
				if (!(isAlreadyUpdated(category, player, selected))) {
					setCategorySelected(category, player, selected);
				} else {
					display.printMessage("Category is already selected, try another.");

				}

				/*
				 * category return integer from YahtzeeConstants interface, and player returns
				 * index of a player, from 1 to number of players(including)
				 */
				boolean isRelevant = checkCategory(category, dice);
				int score = countScores(category, isRelevant, dice);

				scorecard[category - 1][player - 1] = score;
				display.updateScorecard(category, player, score);

//				steps--;
//				System.out.println(steps);
			}

//			if (steps == 0) {
//				sumScores();
//				// printResult();
//				
//				// break;
//			}

			// break;

		}
		sumScores();
		findWinner();

	}

//	public void printGrid() {
//		for (int i = 0; i < N_CATEGORIES; i++) {
//			for (int j = 0; j < nPlayers; j++) {
//				System.out.printf("%5d ", scorecard[i][j]);
//			}
//			System.out.println();
//		}
//	}

	private void findWinner() {
		/*
		 * Method finds the index of a winner player, corresponding score and updates
		 * display
		 */
//		if(findFirstMax(totals) != findSecondMax(totals))
		int index = 0;
		int maximum = 0;
		for (int i = 1; i <= nPlayers; i++) {
			int x = totals[i - 1];
			if (x > maximum) {
				maximum = totals[i - 1];
				index = i - 1;
			}
		}
		display.printMessage(
				"Congrats, " + playerNames[index] + ", you are the winner with the total score of " + maximum + "!");
		;
	}

	private void sumScores() {
		/*
		 * method is called, when all possible categories are selected, it sums
		 * upperscore, writes a bonus if possible, calculates lowerscore and total. it
		 * saves the scores in integer array
		 */
		totals = new int[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			/*
			 * sum and update upper score
			 */
			int upperscore = 0;
			for (int j = ONES; j <= SIXES; j++) {
				upperscore += scorecard[j - 1][i - 1];
			}
			scorecard[UPPER_SCORE - 1][i - 1] = upperscore;
			display.updateScorecard(UPPER_SCORE, i, upperscore);
			/*
			 * write bonus if upperscore is equal or more than 63, bonus point is 35.update
			 * scorecard and corresponding indexes in scores array
			 */
			int bonus = 35;
			int upperbonus = 0;
			if (upperscore >= 63) {
				display.updateScorecard(UPPER_BONUS, i, bonus);
				upperbonus = bonus;
			} else {
				display.updateScorecard(UPPER_BONUS, i, 0);
			}
			scorecard[UPPER_BONUS - 1][i - 1] = upperbonus; // write in array

			int lowerscore = 0;
			for (int k = THREE_OF_A_KIND; k <= CHANCE; k++) {
				lowerscore += scorecard[k - 1][i - 1];
			}
			display.updateScorecard(LOWER_SCORE, i, lowerscore);
			scorecard[LOWER_SCORE - 1][i - 1] = lowerscore;

			int total = upperscore + upperbonus + lowerscore;
			totals[i - 1] = total;
			display.updateScorecard(TOTAL, i, total);
			scorecard[TOTAL - 1][i - 1] = total;

		}

	}

	/*
	 * whenever category is selected, information is updated in boolean array
	 */
	private void setCategorySelected(int row, int col, boolean[][] arr) {
		arr[row - 1][col - 1] = true;
	}

	/*
	 * checks whether the category has been selected previously
	 */
	private boolean isAlreadyUpdated(int row, int col, boolean[][] arr) {
		if (arr[row][col - 1] == true) {
			return true;
		}
		return false;
	}

	/*
	 * initialises boolean array with default values.
	 */
	private boolean[][] populateTable(int rows, int colomns) {
		boolean arr[][] = new boolean[rows][colomns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < colomns; j++) {
				arr[i][j] = false;
			}
		}
		return arr;
	}

	private boolean checkCategory(int category, int[] array) {
		/*
		 * checkCategory() method checks whether received argument corresponds the terms
		 * of categories with the given array values. This method uses helper
		 * class: @CategoryCecker, creates instance of this class and calls it's
		 * methods.
		 */
		CategoryChecker checker = new CategoryChecker();
		boolean flag = false;

		if (checker.isThreeOfAKind(array) && category == THREE_OF_A_KIND) {
			flag = true;
		} else if (checker.isFourOfAKind(array) && category == FOUR_OF_A_KIND) {
			flag = true;
		} else if (checker.isFullHouse(array) && category == FULL_HOUSE) {
			flag = true;
		} else if (checker.isSmallStraight(array) && category == SMALL_STRAIGHT) {
			flag = true;
		} else if (checker.isLargeStraight(array) && category == LARGE_STRAIGHT) {
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
		/*
		 * Method receives category and boolean value about its state, if it is true,
		 * method returns the score for given category, 0 otherwise.
		 */
		int score = 0;
		if (isRight) {
			switch (category) {
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
	private int[][] scorecard;
	private int[] totals;

}