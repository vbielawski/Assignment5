
public class ScoresCounter implements YahtzeeConstants {

	public int FullHouseScore = 25;
	public int SmallStraightScore = 30;
	public int BigStraightScore = 40;
	public int YahtzeeScore = 50;
	

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
