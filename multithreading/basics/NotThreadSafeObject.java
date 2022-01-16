package threadBasics;

// this class is not thread-safe!
public class NotThreadSafeObject extends SomeObject{

    private String name;
    private int theValue;

    public NotThreadSafeObject(){
        theValue = 0;
        name = "hello";
    }
    
    public NotThreadSafeObject(String name){
        theValue = 0;
        this.name = name;
    }
    
    public synchronized void increment(){
        theValue++;
    }
    
    public void decrement(){
        theValue--;
    }
    
    public String state(){
        return "name: " + name + ", theValue: " + theValue;
    }

}