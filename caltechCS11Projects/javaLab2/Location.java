/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }
    
    /** equals method **/
    @Override
    public boolean equals(Object otherLoc){
    	if(!(otherLoc instanceof Location)){
    		return false;
    	}else{
    		Location oLoc = (Location)otherLoc;
    		if(oLoc.xCoord == xCoord &&
    				oLoc.yCoord == yCoord){
    			return true;
    		}
    	}
    	return false;
    }
    
    /** hash code method **/
    @Override
    public int hashCode(){
    	//assign arbitrary number to hash
    	int hash = 7;
    	//we have two things we checked in the equals method
    	
    	//xCoord 
    	hash = 31*hash + xCoord;
    	
    	//yCoord
    	hash = 37*hash + yCoord;
    	
    	//return it
    	return hash;
    }
    
}