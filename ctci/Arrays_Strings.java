import java.util.*;

public class Arrays_Strings {
	
	// check if a string is made up of unique chars only
	public static boolean isUnique(String input){
		
		HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
		for(int i = 0; i < input.length(); i++){
			
			if(hash.get(input.charAt(i)) != null){
				return false;
			}else{
				hash.put(input.charAt(i), 0);
			}
		}
		return true;
	}
	
	// check for unique chars only without data structures
	public static boolean isUnique2(String input){
		
		for(int i = 0; i < input.length(); i++){
			for(int j = i + 1; j < input.length() - 1; j++){
				if(input.charAt(i) == input.charAt(j)){
					return false;
				}
			}
		}
		return true;
	}

	// check permutation - is str2 a permutation of str1 ?
	public static boolean checkPerm(String s1, String s2){
		
		if(s1.length() != s2.length()){
			return false;
		}
		
		HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
		for(int i = 0; i < s1.length(); i++){
			if(hash.get(s1.charAt(i)) != null){
				int currVal = hash.get(s1.charAt(i));
				hash.put(s1.charAt(i), currVal + 1);
			}else{
				hash.put(s1.charAt(i), 1);
			}
		}
		
		for(int j = 0; j < s2.length(); j++){
			if(hash.get(s2.charAt(j)) == null){
				return false;
			}else{
				// decrement the value of the char in the hash.
				// if both strs are the same, all keys should have a value of 0 at the end.
				int currVal = hash.get(s2.charAt(j)) - 1;
				if(currVal < 0){
					currVal = 0;
				}
				hash.put(s2.charAt(j), currVal);
			}
		}

		int total = 0;
		for(int value: hash.values()){
			total += value;
		}
		
		return (total == 0);
	}
	
	
	// URLify
	// replace spaces with '%20'
	// you're given the length of the string with spaces.
	// the string provided has extra buffer space to accomodate the '%20'
	public static String URLify(String s, int len){
		
		char[] str = s.toCharArray();
		
		// figure out how long the new string is going to be
		// with all the replacements 
		// get the num of spaces first 
		int spaceCount = 0;
		for(int i = 0; i < len - 1; i++){
			if(str[i] == ' '){
				spaceCount++;
			}
		}

		int newLength = (len - spaceCount) + (spaceCount * 3); // multiply by 3 because '%','2','0'
		
		// need null terminator!
		str[newLength--] = '\0';
		
		// place the new string backwards 
		for(int i = len - 1; i >= 0; i--){
			if(str[i] != ' '){
				str[newLength--] = str[i];
			}else{
				str[newLength--] = '0';
				str[newLength--] = '2';
				str[newLength--] = '%';
			}
		}
		
		return new String(str);
	}
	
	// 1.4 Palindrome permutation
	// need to ask: does case matter? do spaces matter?
	public static boolean palPerm(String str){
		
		str = str.toLowerCase();
		str = String.join("", str.split(" "));
	
		// we just need to know if the str could be 
		// a palindrome based on the character counts 
		HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
		
		for(int i = 0; i < str.length(); i++){
			if(hash.get(str.charAt(i)) == null){
				hash.put(str.charAt(i), 1);
			}else{
				int currVal = hash.get(str.charAt(i));
				hash.put(str.charAt(i), currVal + 1);
			}
		}
		
		// now check the hash and verify that it could be a palindrome.
		// if the input string had an odd length, then there should be 
		// only one char with an odd number of occurrences, and the rest should
		// be even. if the string is even, then all chars should have even occurrences.
		int oddCount = 0;
		if(str.length() % 2 == 0){
			// for even length strings
			for(int val : hash.values()){
				if(val % 2 != 0){
					return false;
				}
			}
		}else{
			// odd length strings 
			for(int val : hash.values()){
				if(val % 2 != 0){
					oddCount++;
					if(oddCount > 1){
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	
	// 1.5 one away
	// check if str2 is one edit away from str 1
	// edit = insert char, remove char, replace char
	// 0 edits count as true
	public static boolean oneAway(String s1, String s2){
		
		// if the difference in length is more than 1, already false
		if(Math.abs(s1.length() - s2.length()) > 1){
			return false;
		}
		
		if(s1.length() == s2.length()){
			
			int count = 0;
			
			for(int i = 0; i < s1.length(); i++){
				if(s1.charAt(i) != s2.charAt(i)){
					count++;
				}
			}
			return count <= 1;
			
		}else{
			
			// if s1 and s2 are different lengths 
			String larger = s1.length() > s2.length() ? s1 : s2;
			String smaller = larger == s1 ? s2 : s1;
						
			int index2 = 0;  
			int count = 0;

			for(int i = 0; i < larger.length(); i++){	
				if(larger.charAt(i) != smaller.charAt(index2)){
					count++;
				}else if(larger.charAt(i) == smaller.charAt(index2)){
					index2++;
				}
			}
			return count <= 1;
		}
	}
	
	// 1.6 string compression
	// "aabcccccaaa" -> "a2b1c5a3"
	// be careful though! it might be easy to just add strings to concatenate
	// but the time complexity of concatenation is O(n^2). 
	// instead, use a string buffer! 
	// but also I think the memory cost is a bit higher than it could be though. 
  
	public static String compress(String str){
		
		StringBuffer strbuf = new StringBuffer();
		HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
		ArrayList<Character> arr = new ArrayList<Character>();
		
		for(int i = 0; i < str.length(); i++){
			
			if( hash.get(str.charAt(i)) == null ){
				hash.put(str.charAt(i), 1);
				arr.add(str.charAt(i));
			}else{
				int currVal = hash.get(str.charAt(i));
				hash.put(str.charAt(i), currVal + 1);
			}
		}
		
		if(hash.keySet().size() == str.length()){
			return str;
		}else{
			for(Character c : arr){
				strbuf.append(c);
				strbuf.append(hash.get(c));
			}
		}
		return strbuf.toString();
	}
	
	
	public static void main(String[] args){
		
		System.out.println(isUnique2("abcdfg"));
		System.out.println(checkPerm("abcd", "abcb"));
		
		System.out.println(URLify("Mr Blah Blah       ", 12));
		System.out.println("palindrome permutation - Tact Coa: " + palPerm("Tact Coa")); // true
		System.out.println("abc and abaa are one away: " + oneAway("abc", "abaa")); // false
		System.out.println("abc and aba are one away: " + oneAway("abc", "aba")); // true
		System.out.println("abc and abb are one away: " + oneAway("abc", "abb")); // true
		System.out.println("pale and ple are one away: " + oneAway("pale", "ple")); // true
		
		System.out.println(compress("aaaccccdbbb"));
		System.out.println(compress("abcd"));
			
	}
	
	
	
	
}
