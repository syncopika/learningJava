//http://courses.cms.caltech.edu/cs11/material/java/donnie/lab3/index.html

import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent {
	
	//private field
	//will hold image to be drawn
	private BufferedImage image;
	
	//constructor
	//accepts a specified width and height
	public JImageDisplay(int width, int height){
	
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	
		setPreferredSize(new Dimension(width, height));
	}
	
	//method to get image for saving
	public BufferedImage getImage(){
		return image;
	}
	
	//paintComponent will draw the image on the frame
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image,  0,  0,  image.getWidth(), image.getHeight(), null);
	}
	
	//clear image 
	//causes all pixels to be rgb(0,0,0)
	public void clearImage(){
		
		int color = 0;
		
		for(int i = 0; i < image.getWidth(); i++){
			for(int j = 0; j < image.getHeight(); j++){
				image.setRGB(i, j, color);
			}
		}
	}
	
	//set a pixel a particular color
	public void drawPixel(int x, int y, int rgbColor){
		image.setRGB(x,  y,  rgbColor);
	}
}
