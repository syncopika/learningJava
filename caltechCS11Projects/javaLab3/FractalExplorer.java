import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FractalExplorer{
	
	//START UP PROGRAM HERE
	public static void main(String[] args){
		FractalExplorer test = new FractalExplorer(500);
		test.createAndShowGUI();
		test.drawFractal();
	}

	//state vars
	private int displaySize;
	private JImageDisplay display;
	private FractalGenerator fractal;
	private Rectangle2D.Double range;
	
	//constructor
	public FractalExplorer(int size){
		displaySize = size;
		display = new JImageDisplay(size, size);
		fractal = new Mandelbrot();
		range = new Rectangle2D.Double();
		fractal.getInitialRange(range);
	}
	
	//initialize Swing GUI
	public void createAndShowGUI(){
		JFrame frame = new JFrame("fractal generator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(display, BorderLayout.CENTER);
		
		//connect mouse click on content pane with zoom in
		ZoomIn zoomIn = new ZoomIn();
		frame.getContentPane().addMouseListener(zoomIn);
		
		//make a button 
		ActionHandler makeFractal = new ActionHandler();
		JButton resetButton = new JButton("reset display");
		resetButton.addActionListener(makeFractal);
		
		frame.add(resetButton, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	//set up inner class for action handling
	class ActionHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//reset range
			fractal.getInitialRange(range);
			//then draw
			drawFractal();
		}
	}
	
	//other inner class for zooming in
	class ZoomIn extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e){
			double xC = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, e.getX());
			double yC = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, e.getY());
			fractal.recenterAndZoomRange(range, xC, yC, 0.5);
			drawFractal();
		}
	}
	
	//private helper method for displaying fractals
	private void drawFractal(){
		for(int x = 0; x < displaySize; x++){
			for(int y = 0; y < displaySize; y++){
			
				double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x);
				double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, y);
				
				int numIters = fractal.numIterations(xCoord, yCoord);
				
				if(numIters == -1){
					display.drawPixel(x, y, 0);
				}else{
					float hue = 0.7f + (float) numIters / 200f;
					int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
					display.drawPixel(x,  y, rgbColor);
				}
			}
		}
		display.repaint();
	}
	
}
