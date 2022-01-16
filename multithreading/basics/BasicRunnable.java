package threadBasics;

// can use a Runnable for a thread
public class BasicRunnable implements Runnable{
    
    private SomeObject obj;
    private int id;
    
    public BasicRunnable(SomeObject obj, int id){
        this.obj = obj;
        this.id = id;
    }
    
    public void run(){
        obj.increment();
        obj.decrement();
        System.out.println(obj.state());
        //System.out.println("hello from thread " + id);
    }
}