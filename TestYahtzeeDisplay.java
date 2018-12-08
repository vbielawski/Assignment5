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
	
	
	private GCanvas canvas;
	private String[] names;
}
