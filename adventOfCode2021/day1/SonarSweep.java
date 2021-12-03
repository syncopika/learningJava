// javac *.java

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SonarSweep {
    
    static void part1(){
        int numIncrease = 0;
        int prev = -1;
        
        try{
            File f = new File("input.txt");
            Scanner scanner = new Scanner(f);
            while(scanner.hasNextLine()){
                int val = Integer.parseInt(scanner.nextLine());
                if(prev != -1){
                    if(val > prev){
                        numIncrease++;
                    }
                }
                prev = val;
            }
        }catch(FileNotFoundException exception){
            System.out.println("file not found!");
            return;
        }
        
        System.out.println("numIncrease: " + numIncrease);
    }
    
    static void part2(){
        int numIncrease = 0;
        int prev = -1;
        ArrayList<Integer> nums = new ArrayList<Integer>();
        
        try{
            File f = new File("input.txt");
            Scanner scanner = new Scanner(f);
            while(scanner.hasNextLine()){
                int val = Integer.parseInt(scanner.nextLine());
                nums.add(val);
            }
        }catch(FileNotFoundException exception){
            System.out.println("file not found!");
            return;
        }
        
        for(int i = 0; i <= nums.size() - 3; i++){
            if(i+3 > nums.size()){
                break;
            }
            int sum = 0;
            for(int j = i; j < i+3; j++){
                sum += nums.get(j);
            }
            
            if(prev != -1){
                if(sum > prev){
                    numIncrease++;
                }
            }
            prev = sum;
        };
        
        System.out.println("numIncrease: " + numIncrease);        
    }
    
    public static void main(String[] args){
        part1();
        part2();
    }
    
}
