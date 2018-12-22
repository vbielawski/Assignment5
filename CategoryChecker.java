import java.io.Console;

import acm.io.IOConsole;
import acm.io.IODialog;

public class CategoryChecker implements YahtzeeConstants {
	
//	public void main(String[] args) {
//		IODialog dial = new IODialog();
//		IOConsole cons = new IOConsole();
//		int[] array = new int[5];
//		for(int i = 0; i < array.length; i++) {
//			array[i] = dial.readInt("number: ");
//		}
//		
//		for(int el : array) {
//			cons.print(el);
//		}
//		
//	}
	
	
	private int[] initialize() {
		int[] arr = new int[frequencylength];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = 0;
		}
		return arr;
		
	}
	
	public boolean isOneToSix(int[] array, int category) {
		return true;
	}
	
	public boolean isThreeOfAKind(int[] array, int category) {
		int[] result = initialize();
		boolean status = false;
		for(int i = 0; i < array.length; i++) {
			int index = array[i];
			result[index]++;
		}
		for(int i = 0; i < result.length; i++) {
			if(result[i] >= 3) {
				status = true;
			}
		}
		return status;
		
	}
	
	public boolean isFourOfAKind(int[] array, int category) {
		return false;
		
	}
	
	public boolean isSmallStraight(int[] array, int category) {
		return false;
		
	}
	
	public boolean isBigStraight(int[] array, int category) {
		return false;
		
	}
	
	public boolean isYahtzee(int[] array, int category) {
		return false;
		
	}
	
	public boolean isChance(int[] array, int category) {
		return true;
		
	}
	
	private int frequencylength = 7;
	
}
