import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;
import java.io.*;

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
		
		//set up frame first
		JFrame frame = new JFrame("fractal generator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//set up a combobox at the top
		JPanel topPanel = new JPanel();
		JLabel pickFractal = new JLabel("Choose fractal:");
		JComboBox choices = new JComboBox();
		
		topPanel.add(pickFractal);
		topPanel.add(choices);
		//add fractals to combo box
		choices.addItem(new Mandelbrot());
		choices.addItem(new Tricorn());
		
		//position topPanel at top of frame
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		
		//this is where the mandelbrot picture goes
		frame.getContentPane().add(display, BorderLayout.CENTER);
		
		//connect mouse click on content pane with zoom in
		ZoomIn zoomIn = new ZoomIn();
		frame.getContentPane().addMouseListener(zoomIn);
		
		//make JPanel for bottom part of frame
		JPanel buttonPanel = new JPanel();
		frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		ActionHandler actionHandle = new ActionHandler();
		//make a button for saving
		JButton saveButton = new JButton("save image");
		saveButton.setActionCommand("save");
		saveButton.addActionListener(actionHandle);
		//make a reset button 
		JButton resetButton = new JButton("reset display");
		resetButton.setActionCommand("reset");
		resetButton.addActionListener(actionHandle);
		
		//connect buttons with JPanel
		buttonPanel.add(saveButton);
		buttonPanel.add(resetButton);
		
		//connect combobox with action listener
		choices.addActionListener(actionHandle);
		
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	//set up inner class for action handling
	class ActionHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//get the source
			Object source = e.getSource();
			
			//check what the source is
			if(source instanceof JComboBox){
			JComboBox theBox = (JComboBox)e.getSource();
			String fractalName = theBox.getSelectedItem().toString();
			if(fractalName.equals("Mandelbrot")){
				fractal = new Mandelbrot();
				fractal.getInitialRange(range);
				drawFractal();
			}else if(fractalName.equals("Tricorn")){
				fractal = new Tricorn();
				fractal.getInitialRange(range);
				drawFractal();
			}
			}else if(source instanceof JButton){
				JButton whichButton = (JButton)source;
				if(whichButton.getActionCommand().equals("reset")){
					//reset range
					fractal.getInitialRange(range);
					//then draw
					drawFractal();
				}else if(whichButton.getActionCommand().equals("save")){
					JFileChooser chooser = new JFileChooser();
					
					//restrict file save type to .png only
					FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
					chooser.setFileFilter(filter);
					chooser.setAcceptAllFileFilterUsed(false);
					
					//display the save dialog. returns either APPROVE_OPTION or no.
					//just return if user declines
					int result = chooser.showSaveDialog(display);
					
					if(result == JFileChooser.APPROVE_OPTION){
						String filename = chooser.getSelectedFile().getName();
						BufferedImage image = display.getImage();
						try{
							ImageIO.write(image, "png", new File("C:\\Users\\Nicholas\\Desktop\\" + filename + ".png"));
						}catch(IOException ex){
							JOptionPane.showMessageDialog(display, ex.getMessage(), "cannot save image", JOptionPane.ERROR_MESSAGE);
						}
						/*
						//save file to disk
						try{
							BufferedImage image = display.getImage();
							File outputFile = chooser.getSelectedFile();
							ImageIO.write(image, "png", outputFile);
						}catch(IOException ex){
							JOptionPane.showMessageDialog(display, ex.getMessage(), "cannot save image", JOptionPane.ERROR_MESSAGE);
						}
						*/	
					}else{
						return;
					}
				}
			}
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
