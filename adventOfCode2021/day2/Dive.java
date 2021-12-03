import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dive {
    
    static void part1(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("horizontal", 0);
        map.put("depth", 0);
        
        try{
            File f = new File("input.txt");
            Scanner scanner = new Scanner(f);
            while(scanner.hasNextLine()){
                String[] tokens = scanner.nextLine().split(" ");
                String direction = tokens[0];
                int val = Integer.parseInt(tokens[1]);
                
                int currDepth = map.get("depth");
                int currHorz = map.get("horizontal");
                
                if(direction.equals("forward")){
                    map.put("horizontal", currHorz + val);
                }else if(direction.equals("up")){
                    map.put("depth", currDepth - val);
                }else{
                    map.put("depth", currDepth + val);
                }
            }
        }catch(FileNotFoundException exception){
            System.out.println("file not found!");
            return;
        }
        
        for(String s : map.keySet()){
            System.out.println(s + ": " + map.get(s));
        }
        System.out.println("horizontal x depth: " + (map.get("horizontal") * map.get("depth")));
        System.out.println("----------------");
    }
    
    static void part2(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("horizontal", 0);
        map.put("depth", 0);
        map.put("aim", 0);
        
        try{
            File f = new File("input.txt");
            Scanner scanner = new Scanner(f);
            while(scanner.hasNextLine()){
                String[] tokens = scanner.nextLine().split(" ");
                String direction = tokens[0];
                int val = Integer.parseInt(tokens[1]);            
                
                int currDepth = map.get("depth");
                int currHorz = map.get("horizontal");
                int currAim = map.get("aim");
                
                if(direction.equals("forward")){
                    map.put("horizontal", currHorz + val);
                    map.put("depth", currDepth + (currAim * val));
                }else if(direction.equals("up")){
                    map.put("aim", currAim - val);
                }else{
                    map.put("aim", currAim + val);
                }
            }
        }catch(FileNotFoundException exception){
            System.out.println("file not found!");
            return;
        }
        
        map.keySet().forEach(val -> {
            System.out.println(val + ": " + map.get(val));
        });
        System.out.println("horizontal x depth: " + (map.get("horizontal") * map.get("depth")));
    }
    
    public static void main(String[] args){
        part1();
        part2();
    }
}