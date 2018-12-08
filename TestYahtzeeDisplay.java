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
	
	protected static void waitPlayerToClickRoll() {
		
	}
	
	
	private GCanvas canvas;
	private String[] names;
}
