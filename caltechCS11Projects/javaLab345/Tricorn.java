import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator {
	
	//set up final for maximum iterations
		public static final int MAX_ITERATIONS = 2000;

		//get initial range
		public void getInitialRange(Rectangle2D.Double range){
			//modify rectangle's fields to reflect initial range for fractal
			range.x = -2;
			range.y = -2;
			range.width = 4.00;
			range.height = 4.00;
		}
		
		//for the tricorn fractal, the complex conjugate of zn-1 needs to be taken each iteration.
		public int numIterations(double x, double y){
			
			int counter = 0;
			double real = x;
			double imag = y;
			double zeroReal = x;
			double zeroImag = y;
			
			while (counter < MAX_ITERATIONS){
				
				double currentZreal = (real*real - imag*imag) + zeroReal;
				
				//get the complex conj. if the complex part is neg, make it pos and vice versa.
				double complexConj = 0 - (2*real*imag);
				
				double currentZimag = complexConj + zeroImag;
				
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
		
		public String toString(){
			return "Tricorn";
		}
}
