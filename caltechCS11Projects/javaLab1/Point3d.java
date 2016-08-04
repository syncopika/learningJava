
public class Point3d {
	/**
	 * A three-dimensional point class.
	 * http://courses.cms.caltech.edu/cs11/material/java/donnie/lab1/index.html
	 */
	    
	    /** X coordinate of the point */
	    private double xCoord;
	    
	    /** Y coordinate of the point */
	    private double yCoord;
	    
	    /** Z coordinate of the point */
	    private double zCoord;

	    /** Constructor to initialize point to (x, y, z) value. */
	    public Point3d(double x, double y, double z) {
	        xCoord = x;
	        yCoord = y;
	        zCoord = z;
	    }

	    /** No-argument constructor:  defaults to a point at the origin. */
	    public Point3d() {
	        // Call two-argument constructor and specify the origin.
	        this(0, 0, 0);
	    }

	    /** Return the X coordinate of the point. */
	    public double getX() {
	        return xCoord;
	    }

	    /** Return the Y coordinate of the point. */
	    public double getY() {
	        return yCoord;
	    }
	    
	    /** Return the Z coordinate of the point */
	    public double getZ() {
	    	return zCoord;
	    }

	    /** Set the X coordinate of the point. */
	    public void setX(double val) {
	        xCoord = val;
	    }

	    /** Set the Y coordinate of the point. */
	    public void setY(double val) {
	        yCoord = val;
	    }
	    
	    /** Set the Z coordinate of the point */
	    public void setZ(double val){
	    	zCoord = val;
	    }
	    
	    
	    /**get distance between this and another 3d point */
	    public double distanceTo(Point3d otherPoint){
	    	double pointX = Math.pow(xCoord - otherPoint.getX(), 2);
	    	double pointY = Math.pow(yCoord - otherPoint.getY(), 2);
	    	double pointZ = Math.pow(zCoord - otherPoint.getZ(), 2);
	    	double dist = Math.pow(pointX + pointY +  pointZ, .5);
	    	return dist;
	    }
	    
	    
	    /** equals method */
	    @Override
	    public boolean equals(Object otherPoint){
	    	if(!(otherPoint instanceof Point3d)){
	    		return false;
	    	}else{
	    		Point3d a = (Point3d)otherPoint;
	    		if(a.xCoord == this.xCoord && 
	    				a.yCoord == this.yCoord &&
	    				a.zCoord == this.zCoord){
	    			return true;
	    		}
	    		return false;
	    	}
	    }
	    
}
