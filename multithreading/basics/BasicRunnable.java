package threadBasics;

// can use a Runnable for a thread
public class BasicRunnable implements Runnable{
    
    // TODO: create a constructor so we can pass stuff to an instance
    // of this class that we can modify in the run() method
    
    public void run(){
        System.out.println("hello from thread");
    }
}