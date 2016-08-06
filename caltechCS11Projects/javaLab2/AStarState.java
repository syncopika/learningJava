/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/

import java.util.HashMap;

public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;
    
    /**set up hash maps**/
    //for open
    HashMap<Location, Waypoint> openWaypoints = new HashMap<Location, Waypoint>();
    
    //for closed
    HashMap<Location, Waypoint> closedWaypoints = new HashMap<Location, Waypoint>();

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        //Waypoint smallestWaypt = null;
        
        if(openWaypoints.size() == 0){
        	//System.out.println("getMinOpenWaypt, null");
        	return null;
        }else{
        
        //reference to smallest waypoint
        Waypoint smallestWaypt = null;
        
        for(HashMap.Entry<Location, Waypoint> entry : openWaypoints.entrySet()){
        	if(smallestWaypt == null || (entry.getValue().getTotalCost() < smallestWaypt.getTotalCost())){
        		smallestWaypt = entry.getValue();
        	}
        }
        
        return smallestWaypt;
        }
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
    	//System.out.println(newWP.getLocation());
    	
    	if(!openWaypoints.containsKey(newWP.getLocation())){
    		System.out.println("addOpenWaypoint, new add");
    		openWaypoints.put(newWP.getLocation(), newWP);
    		return true;
    	}else{
    		for(HashMap.Entry<Location, Waypoint> entry : openWaypoints.entrySet()){
    			//find the waypoint already in the set with the same location
    			if(entry.getKey().hashCode() == newWP.getLocation().hashCode()){
    				//then check previous cost values
    				if(newWP.getPreviousCost() < entry.getValue().getPreviousCost()){
    					openWaypoints.put(entry.getKey(),newWP);
    					//entry.setValue(newWP);
    					System.out.println("addOpenWaypoint, replace");
    					return true;
    				}
    			}
    		}//end for loop
    	}
        return false;
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        return openWaypoints.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
    	 //put waypoint in closed list
        //loc stays the same!
    	closedWaypoints.put(loc, openWaypoints.get(loc));
        
    	//remove
        openWaypoints.remove(loc);

    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
        for(HashMap.Entry<Location, Waypoint> entry : closedWaypoints.entrySet()){
        	if(entry.getKey().equals(loc)){
        		//System.out.println("isLocationClosed, true");
        		return true;
        	}
        }
        return false;
    }
}