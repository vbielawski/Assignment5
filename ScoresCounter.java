
public class ScoresCounter implements YahtzeeConstants {

	private int FullHouseScore = 25;
	private int SmallStraightScore = 30;
	private int LargeStraightScore = 40;
	private int YahtzeeScore = 50;

	

	public int getOnes(int[] array) {
		int sum = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == 1) {
				sum++;
			}
		}
		return sum;
	}

	public int getTwos(int[] array) {
		int sum = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == 2) {
				sum+=2;
			}
		}
		return sum;
	}

	public int getThrees(int[] array) {
		int sum = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == 3) {
				sum+=3;
			}
		}
		return sum;
	}

	public int getFours(int[] array) {
		int sum = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == 4) {
				sum+=4;
			}
		}
		return sum;
	}

	public int getFives(int[] array) {
		int sum = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == 5) {
				sum+=5;
			}
		}
		return sum;
	}
	
	public int getSixes(int[] array) {
		int sum = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == 6) {
				sum+=6;
			}
		}
		return sum;
	}

	public int getThreeOfAKindScore(int[] array) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum;
	}

	public int getFourOfAKindScore(int[] array) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum;
	}

	public int getFullHouseScore() {

		return FullHouseScore;
	}

	public int getSmallStraightScore() {
		int score = this.SmallStraightScore;
		return score;
	}

	public int getLargeStraightScore() {
		int score = this.LargeStraightScore;
		return score;
	}

	public int getYahtzeeScore() {

		return YahtzeeScore;
	}

	public int getChanceScore(int[] array) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum;
	}

	public int getUpperScore(int[] array) {
		int sum = 0;
		return sum;
	}

	public int getLowerScore(int[] array) {
		int sum = 0;
		return sum;
	}

	public int getUpperBonusScore(int[] array) {
		int sum = 0;
		return sum;
	}

	public int getTotalScore(int[] array) {
		int sum = 0;
		return sum;
	}

}
