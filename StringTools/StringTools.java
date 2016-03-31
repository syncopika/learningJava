//This StingTools class supplies some string manipulation methods.

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//comment what this class does

public class StringTools {
	
	//count characters
	public static int count(String a, char c){
		int counter = 0;
		for(int i=0; i<a.length(); i++){
			if(a.charAt(i) == c){
				counter++;
			}
		}
		return counter;
	}
	
	//count substrings
	public static int countSubstring(String a, String b){
		int counter = 0;
		for(int i = 0; i<=a.length()-b.length();i++){
			if(a.substring(i, i+b.length()).equals(b)){
				counter++;
			}
		}
		return counter;
	}
	
	//reverse method
	public static String reverse(String a){
		String reversedStr = "";
		for(int i = a.length()-1; i >= 0; i--){
			reversedStr += a.charAt(i);
		}
		return reversedStr;
	}
	
	//expand method
	public static String expand(String a){
		String expanded = "";
		for(int i = 0; i < a.length(); i++){
			for(int j = -1; j < i; j++){
				expanded += a.charAt(i);
			}
		}
		return expanded;
	}
	
	//alternating method
	public static String alternating(String a, String b){
		//find which word is smaller so we can set a limit
		int limit = Math.max(a.length(), b.length());
		String newString = "";
		for(int i = 0; i < limit; i++){
			if(i < a.length() && i < b.length()){
			newString += a.charAt(i);
			newString += b.charAt(i);
			}
			else if(i < b.length() || i < a.length()){
			newString += b.charAt(i);
			}
		}
		return newString;
	}
	
	public static String linkExtractor(String a){
		String link = "";
		//get rid of spaces
		for(int i = 0; i<a.length();i++){
			if(!a.substring(i,i+1).equals(" ")){
				link += a.substring(i, i+1);
			}
		}
		//System.out.println(link);
		//now look for just href="blahhhhh...."
		
		//make a pattern, then a matcher
		Pattern p = Pattern.compile("href=\".*?\"");
		Matcher m = p.matcher(link);
		boolean b = m.find();
		if(b){
			//link = m.group(1);
			return m.group();
		}else{
			return "NULL";
		}
	}
}
