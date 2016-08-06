import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {
	
	//set up final for maximum iterations
	public static final int MAX_ITERATIONS = 2000;
	
	//get initial range
	public void getInitialRange(Rectangle2D.Double range){
		//modify rectangle's fields to reflect initial range for fractal
		range.x = -2;
		range.y = -1.5;
		range.width = 3.00;
		range.height = 3.00;
	}
	
	//number of iterations NUMBER OF ITERATIONS 
	//just return the number of iterations...
	public int numIterations(double x, double y){
		
		//if z0 = a + bi, a = x, b = y
		//z1 = z0^2 + z0
		//z2 = z1^2 + z0
		
		int counter = 0;
		//boundary for divergence is 2.0
		//so check if norm(z) > 2 every iteration
		//or counter > MAX_ITERATIONS,
		//return counter
		//else return -1
		
		double real = x;
		double imag = y;
		double zeroReal = x;
		double zeroImag = y;
		
		while (counter < MAX_ITERATIONS){
			
			double currentZreal = (real*real - imag*imag) + zeroReal;
			double currentZimag = (2*real*imag) + zeroImag;
			
			//find norm of current zn
			double normSq = currentZreal + currentZimag;
			
			//is norm squared > 4 yet?
			if(normSq > 4){
				return counter;
			}
			
			real = currentZreal;
			imag = currentZimag;
			
			counter++;
		}
		
		return -1;
	}
}
