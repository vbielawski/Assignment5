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
		for (int i = 0; i < arr.length; i++) {
			arr[i] = 0;
		}
		return arr;

	}

	private void setStatus() {
		this.status = false;
	}

	public boolean isOneToSix(int[] array, int category) {
		return true;
	}

	public boolean isThreeOfAKind(int[] array, int category) {
		int[] result = initialize();
		boolean status = false;
		for (int i = 0; i < array.length; i++) {
			int index = array[i];
			result[index]++;
		}
		for (int i = 0; i < result.length; i++) {
			if (result[i] >= 3) {
				status = true;
			}
		}
		return status;

	}

	public boolean isFourOfAKind(int[] array, int category) {
		frequencyTable = initialize();
		// boolean status = false;
		for (int i = 0; i < array.length; i++) {
			int index = array[i];
			frequencyTable[index]++;
		}

		for (int i = 0; i < frequencyTable.length; i++) {
			if (frequencyTable[i] >= 4) {
				status = true;
			}
		}

		return status;

	}

	public boolean isSmallStraight(int[] array, int category) {
		sortArray(array);
		int counter = 0;
		for(int i = 0; i < array.length - 1; i++) {
			if(isStraight(i, i+1, array)) {
				counter++;
			}
		}
		if(counter >= 4) {
			return true;
		}
		
		return false;

	}

	public boolean isLargeStraight(int[] array, int category) {
		sortArray(array);
		int counter = 0;
		for(int i = 0; i < array.length - 1; i++) {
			if(isStraight(i, i+1, array)) {
				counter++;
			}
		}
		if(counter == 5) {
			return true;
		}
		return false;

	}

	public boolean isYahtzee(int[] array, int category) {
		return false;

	}

	public boolean isChance(int[] array, int category) {
		return true;

	}

	private boolean isStraight(int index1, int index2, int[] arr) {
		boolean isPlusOne = (arr[index2] == arr[index1] + 1);
		return isPlusOne;
	}
	
	private void sortArray(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			int min = findSmallest(arr, i, arr.length); 
			swapElements(arr, i, min);	
			}
		}
	
	
	private int findSmallest(int[] array, int p1, int p2) {
		int smallestIndex = p1;
		for(int i = p1 + 1; i < p2; i++) {
			if (array[i] < array[smallestIndex]) smallestIndex = i;
		}
		return smallestIndex;
	}
	
	private void swapElements(int[] array, int p1, int p2) {
		int temp = array[p1];
		array[p1] = array[p2];
		array[p2] = temp;
	}
	
	

	private int frequencylength = 7;
	private int[] frequencyTable;
	boolean status;

}
