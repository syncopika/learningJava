/**
 * A two-dimensional point class.
 * http://courses.cms.caltech.edu/cs11/material/java/donnie/lab1/index.html
 */
public class Point2d {
    
    /** X coordinate of the point */
    private double xCoord;
    
    /** Y coordinate of the point */
    private double yCoord;

    /** Constructor to initialize point to (x, y) value. */
    public Point2d(double x, double y) {
        xCoord = x;
        yCoord = y;
    }

    /** No-argument constructor:  defaults to a point at the origin. */
    public Point2d() {
        // Call two-argument constructor and specify the origin.
        this(0, 0);
    }

    /** Return the X coordinate of the point. */
    public double getX() {
        return xCoord;
    }

    /** Return the Y coordinate of the point. */
    public double getY() {
        return yCoord;
    }

    /** Set the X coordinate of the point. */
    public void setX(double val) {
        xCoord = val;
    }

    /** Set the Y coordinate of the point. */
    public void setY(double val) {
        yCoord = val;
    }
    
    /** equals method */
    @Override
    public boolean equals(Object otherPoint){
    	if(!(otherPoint instanceof Point2d)){
    		return false;
    	}else{
    		Point2d a = (Point2d)otherPoint;
    		if(a.xCoord == this.xCoord && 
    				a.yCoord == this.yCoord){
    			return true;
    		}
    		return false;
    	}
    }
    
}
