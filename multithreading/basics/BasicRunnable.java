package threadBasics;

// can use a Runnable for a thread
public class BasicRunnable implements Runnable{
    
    private SomeObject obj;
    
    public BasicRunnable(SomeObject obj){
        this.obj = obj;
    }
    
    public void run(){
        //System.out.println("hello from thread");
        obj.increment();
        obj.decrement();
        System.out.println(obj.state());
    }
}