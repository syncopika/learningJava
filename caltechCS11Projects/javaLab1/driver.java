import java.io.*;

public class driver {
	
	public static void main(String args[]){
	
	/*	
	
	Point2d point1 = new Point2d(2, 3);
	Point2d point2 = new Point2d(2, 3);
	Point2d point3 = new Point2d(2, 4);
	
	Point3d point4 = new Point3d(1,2,3);
	Point3d point5 = new Point3d(2,3,4);
	Point3d point6 = new Point3d(1,2,3);
	
	System.out.println(point4.distanceTo(point5));
	
	System.out.println(point1.equals(point2));
	System.out.println(point4.equals(point6));
	
	*/
	
    int counter = 0;
	//Get input from user
	System.out.println("Please enter three triples, one number at a time. Each triple is a 3D point of a triangle.");
	
	//set up point3d vars
	Point3d point1 = new Point3d();
	Point3d point2 = new Point3d();
	Point3d point3 = new Point3d();
	
	//set up triples
	Double tripleOne;
	Double tripleTwo;
	Double tripleThree;
	
	//get triples and fill each point
	while (counter < 3){
	tripleOne = getDouble();
	System.out.println("next...");
	tripleTwo = getDouble();
	System.out.println("next...");
	tripleThree = getDouble();
	
	if(counter == 0){
		point1.setX(tripleOne);
		point1.setY(tripleTwo);
		point1.setZ(tripleThree);
	}else if(counter == 1){
		point2 = new Point3d(tripleOne, tripleTwo, tripleThree);
	}else{
		point3 = new Point3d(tripleOne, tripleTwo, tripleThree);
	}
	
	if(counter != 2){
	System.out.println("next triple...");
	}
	counter++;
	}//end while
	
	if(point1.equals(point2) ||
	   point2.equals(point3) ||
	   point1.equals(point3)){
		System.out.println("At least two points are equal. Cannot compute area.");
		return;
	}

	//have three points. now what?
	//use Heron's formula to get the area. 
	
	//get distance from points
	double dist1 = point1.distanceTo(point2);
	double dist2 = point1.distanceTo(point3);
	double dist3 = point2.distanceTo(point3);
	
	System.out.println("point1 = x:" + point1.getX() + " y:" + point1.getY() + " z:" + point1.getZ());
	System.out.println("point2 = x:" + point2.getX() + " y:" + point2.getY() + " z:" + point2.getZ());
	System.out.println("point3 = x:" + point3.getX() + " y:" + point3.getY() + " z:" + point3.getZ());
	
	System.out.println("the area is: " + computeArea(dist1, dist2, dist3));
	
	}//end public static void
	
	/**
	 * public static method for finding area of triangle using Heron's formula.
	 * the general formula is: 
	 * A = sqrt(s(s-a)(s-b)(s-c))
	 * s = (a + b + c)/2
	 * 
	 * parameters should be 3 lengths of a triangle
	 */
	
	public static double computeArea(double side1, double side2, double side3){
		double semiperimeter = (side1 + side2 + side3) / 2;
		double diff1 = semiperimeter - side1;
		double diff2 = semiperimeter - side2;
		double diff3 = semiperimeter - side3;
		
		double area = Math.sqrt(semiperimeter*diff1*diff2*diff3);
		return area;
	}
	
	 /**
     * Obtains one double-precision floating point number from
     * standard input.
     *
     * @return return the inputted double, or 0 on error.
     */
    public static double getDouble() {

        // There's potential for the input operation to "fail"; hard with a
        // keyboard, though!
        try {
            // Set up a reader tied to standard input.
            BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

            // Read in a whole line of text.
            String s = br.readLine();

            // Conversion is more likely to fail, of course, if there's a typo.
            try {
                double d = Double.parseDouble(s);

                //Return the inputted double.
                return d;
            }
            catch (NumberFormatException e) {
                // Bail with a 0.  (Simple solution for now.)
                return 0.0;
            }
        }
        catch (IOException e) {
            // This should never happen with the keyboard, but we'll
            // conform to the other exception case and return 0 here,
            // too.
            return 0.0;
        }
    }
	
	
	
	
	
	
	
	
	
	
	
}

