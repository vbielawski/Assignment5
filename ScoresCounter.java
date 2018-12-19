
public class ScoresCounter implements YahtzeeConstants {

	private int FullHouseScore = 25;
	private int SmallStraightScore = 30;
	private int BigStraightScore = 40;
	private int YahtzeeScore = 50;
	

	public int getScoreOneSix(boolean isCategory, int category, int[] array) {
		boolean onetosixfound = categoryfounder(array, category);
		int sum = 0;
		if(onetosixfound) {
			
		}
		return sum;
	}


	private boolean categoryfounder(int[] array, int category) {
		// TODO Auto-generated method stub
		switch(category) {
			
		}
		return false;
	}


	


	
}
