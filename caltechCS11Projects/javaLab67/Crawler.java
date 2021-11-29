package webCrawler;
import java.net.*;
import java.io.*;
import java.util.*;
import java.net.UnknownHostException;

public class Crawler{
	//max depth is set by user upon initializing a new Crawler()
	int maxDepth;
	
	//currentDepth keeps track of the 'level' of URLs you're in 
	int currentDepth = 0;
	
	//linked lists to hold URLDepth pairs
	LinkedList<URLDepthPair> visitedSites = new LinkedList<URLDepthPair>();
	LinkedList<URLDepthPair> sitesToVisit = new LinkedList<URLDepthPair>();

	/**
	 * constructor for Crawler
	**/
	public Crawler(int depth){
		maxDepth = depth;
	}
	
	/**
	 * return list of sites visited by crawler
	**/
	public LinkedList<URLDepthPair> getSites(){
		return sitesToVisit;
	}
	
	/**
	 * a function for processing ONE URL (make sure to throw exception!)
	 * when calling this function, wrap it in a try/catch block
	 * that way as the function runs any exceptions will just spill
	 * out of the function and be caught by the try/catch outside it.
	 * also, before processing each URL, check if currentDepth < maxDepth
	**/
	public void processURL(String urlStr) throws Exception{
		//host should be in the format: "http://.....com" with possible path.
		//URL object should fix up the string 
        //System.out.println("checking: " + urlStr);
        
		URL url = new URL(urlStr); //URLDepthPair.getHost(a);
		String host = url.toString();
		
		//create initial URLDepthPair
		URLDepthPair initialPair = new URLDepthPair(host, currentDepth); 
		
		//add to linked list
		visitedSites.add(initialPair);
		
        // TODO: use SSL Socket? https://stackoverflow.com/questions/18787419/ssl-socket-connection
        // see https://stackoverflow.com/questions/45415145/https-get-http-1-1-request-through-java-socket
		//then explore the initial URL
        //attempt to connect (port by default is 80)
        Socket sock = new Socket(url.getHost(), 80);
        sock.setSoTimeout(3000);
        
        //if successful...
        //set up outputstream to send info to host server
        OutputStream os = sock.getOutputStream();
        
        //then set up a writer and hook up the outputstream to it
        //this sends a request to the host server
        PrintWriter writer = new PrintWriter(os, true);
        writer.println("GET " + URLDepthPair.getDocPath(urlStr) + " HTTP/1.1");
        writer.println("Host: " + url.getHost());
        writer.println("Connection: close");
        writer.println();
        
        //request should be sent. now we can read the web page.
        InputStream is = sock.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        
        //read the web page and find URLs
        while(true){
            String line = br.readLine();
            System.out.println(line);
            if(line == null){
                //checked all the URLs for the page
                currentDepth++;
                break;
            }else if(!URLDepthPair.checkLine(line)){
                //if we come across a non-valid URL
                continue;
            }else{
                //found a match for a valid URL
                if(currentDepth < maxDepth){
                    URLDepthPair.parseURL(line, currentDepth, sitesToVisit);
                }else{
                    break;
                }
            }
        }
        //close socket when done
        sock.close();
    }
	

	public static void main(String[] args) throws Exception{
		/*
        //let's assume user will input correctly for now
        Scanner scan = new Scanner(System.in);
        
        System.out.println("please enter a url.");
        String webHost = scan.nextLine();
        
        System.out.println("please enter a non-negative integer that will represent the search depth.");
        int searchDepth = scan.nextInt();
        
        scan.close();
        //int webPort = 80;
		*/
        
        //set up new crawler
        Crawler crawler1 = new Crawler(2);
        
        //process a url for crawler1
        try{
            //http://users.cms.caltech.edu/~donnie/testcrawl/
            //www.cms.caltech.edu/people
            
            //pass in a URL you want to crawl
            crawler1.processURL("http://syncopika.tumblr.com/"); // getting a redirect :/
            
            //checked linked list and loop
            LinkedList<URLDepthPair> sitesToCheck = crawler1.getSites();
            LinkedList<URLDepthPair> sitesChecked = new LinkedList<URLDepthPair>();;
            
            while(sitesToCheck.size() > 0){
                String site = sitesToCheck.getFirst().getURL();
                crawler1.processURL(site);
                sitesChecked.add(sitesToCheck.getFirst());
                sitesToCheck.removeFirst();
                //System.out.println(sitesChecked);
            }
                
            for(int i = 0; i < sitesChecked.size(); i++){
                System.out.println(sitesChecked.get(i));
            }
            
        }catch(IOException e){
            System.out.println("IO error hi");
        }

        //this is fine if you are using command-line, but I don't really want
        //to so using scanner to read in user input through the console may be
        // a good substitute
        /*
        if(args.length != 2){
            System.err.println("Usage: java <host name> <port number>");
            return;
        }
            
        String webHost = args[0];
        int webPort = 80;//Integer.parseInt(args[1]);
        int searchDepth = Integer.parseInt(args[1]);;
        
        */

	}
}

