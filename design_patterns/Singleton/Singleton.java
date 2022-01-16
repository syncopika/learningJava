package designPatterns;

// final == this class cannot be subclassed
//
// useful for a logging class in which there is only
// a need for one instance that can be used across multiple classes
// when a class that depends on it needs to instantiate the logger,
// it'll either create it if it doesn't yet exist or use the existing one.
// no need to pass the logger around.
public final class Singleton {
    
    private static Singleton instance;
    
    // private constructor
    private Singleton(){
    }
    
    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
    
    public void sayHello(){
        System.out.println("hello there");
    }
    
    // other methods
    
    
    public static void main(String[] args){
        Singleton ston = Singleton.getInstance();
        ston.sayHello();
        
        // can't do: Singleton s = new Singleton();
    }
}