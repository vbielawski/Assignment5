import java.awt.event.MouseEvent;

import javax.swing.JButton;

import acm.graphics.GCanvas;
import acm.program.GraphicsProgram;

public class TestYahtzeeDisplay extends GraphicsProgram implements YahtzeeConstants{
	// Constructor of class
	public TestYahtzeeDisplay() {
		
	}
	
	public TestYahtzeeDisplay(GCanvas canvas, String[] starray) {
		this.canvas = canvas;
		this.names = starray;
	}
	
	public void init() {
		addMouseListeners();
		addActionListeners();
	}
	
	public void waitPlayerToClickRoll(MouseEvent e) {
		if(e.getSource().toString().equals("Roll Dice")) {
			
		} 
	}
	
	public void displayDice(int[] elements) {
		// draws elements
		for(int i = 0; i < elements.length; i++) {
			canvas.add(new JButton());
		}
	}
	
	public int waitForPlayerToSelectDice(MouseEvent e, int[] dice) {
		for(int i = 0; i < dice.length; i++) {
			if(e.equals(e)) {
				return i;
			}
		}
		return 0;
	}
	
	public boolean isDieSelected(int diceindex) {
		boolean selected = false;
		return selected;
			
	}
	
	public int waitForPlayerToSelectCategory(int player) {
		return N_CATEGORIES;
	}
	
	public void updateScorecard(int player, int category, int score) {
		canvas.getGraphics();
	}
	
	public void printMessage(String message) {
		println(message);
	}
	
	
	
	private GCanvas canvas;
	private String[] names;
}
