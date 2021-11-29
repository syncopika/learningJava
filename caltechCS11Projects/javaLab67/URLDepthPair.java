package webCrawler;
import java.net.MalformedURLException;
import java.util.*;

/*
 * special inner class for creating URLDepth pairs
*/
public class URLDepthPair{
	public static final String URL_PREFIX = "http://";
	private static final String AHREF = "a href=\"";
	private static final String QUO_MARK = "\"";
	
	private String url;
	private int depth;
	
	URLDepthPair(String aURL, int aDepth){
		url = aURL;
		depth = aDepth;
	}
	
	/**
	 * getString method
	**/
	public String toString(){
		return "URL: " + url + ", depth: " + depth; 
	}
	
	/**
	 * get the url from a URLDepthPair object
	**/
	public String getURL(){
		return url;
	}
	
	/**
	 * get the depth from a URLDepthPair object
	**/
	public int getDepth(){
		return depth;
	}
	
	/**
	 * get the host url
	 * @param s
	 * @return
	**/
	public static String getHost(String s){
		//What if the string doesn't have www? it still could have the host name. 
		//maybe create a URL object if just 'http://' is present, then getHostName from that
		return s.substring(s.indexOf("www."), s.lastIndexOf(".") + 4);
	}
	
	/**
	 * get proper path from host url
	 * @param s
	 * @return the string that should have just the resource path, or "/"
	**/
	public static String getDocPath(String s){
		//i.e. www.cms.caltech.edu/people
		//complicated? way to check - see if there's a slash included if you use lastIndex of "."
		String url = s.substring(s.lastIndexOf("."), s.length());
		
		//if there is a slash, there is a resource path
		if(url.lastIndexOf("/") >= 0){
			//add 4 because a domain (i.e. .com) is always 3 characters
			return url.substring(url.lastIndexOf(".") + 4, url.length());
		}else{
			//if nothing, i.e. just plain blah.edu
			return "/";
		}
	}
	
	/**
	 * check if a line has the proper tags for links (doesn't check if link is valid url though)
	 * @param s
	 * @return true if line has what we want (link tags)
	**/
	public static boolean checkLine(String s){
		if(s.indexOf(AHREF) >= 0){
			return true;
		}
		return false;
	}
	
	/**
	 * checks if a line is a valid url (the stuff between quotes in the link tags)
	 * @param url
	 * @return
	 * @throws Exception
	**/
	public static boolean isURLValid(String url) throws Exception{
		//if passed string does not start with http://...
		if(!url.contains(URL_PREFIX)){
			throw new MalformedURLException();
		}
		//add the url to linked list
		return true;
	}
	
	/**
	 * if a line does have the link tags, look at the stuff between the quotes
	 * parse the URL(s) from string and put them in the linked list
	 * This function is not yet complete.
	 * TODO: MAKE A BETTER PARSER
	**/
	public static void parseURL(String line, int currentDepth, LinkedList<URLDepthPair> listToAddTo){
		//check if ahref is in the line
		if(line.indexOf(AHREF) == line.lastIndexOf(AHREF)){
			line = line.substring(line.indexOf(AHREF)+8, line.lastIndexOf(QUO_MARK));
			//int startPoint = line.indexOf(URL_PREFIX); //line.indexOf(QUO_MARK) + 1;
			//int endPoint = line.indexOf(QUO_MARK);//line.lastIndexOf(QUO_MARK);
			
			String url = line;//line.substring(startPoint, endPoint);
		
			try{
				if(isURLValid(url)){
					URLDepthPair newPair = new URLDepthPair(url, currentDepth);
					listToAddTo.add(newPair);
				}
			}catch(Exception e){
				System.out.println("error with parseURL");
			}
		//there might be multiple links in one line?
		}else if(line.indexOf(AHREF) != line.lastIndexOf(AHREF)){
			while(line.indexOf(AHREF) != line.lastIndexOf(AHREF)){
					int ahrefLoc = line.indexOf(AHREF) + AHREF.length(); //get the location of the first instance of ahref
			}
		}		
	}

}//end URLDepthPair
