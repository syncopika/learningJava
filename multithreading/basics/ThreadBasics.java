package threadBasics;

public class ThreadBasics{
    public static void main(String[] args){
        NotThreadSafeObject obj = new NotThreadSafeObject();
        
        (new Thread(new BasicRunnable(obj))).start();
        (new Thread(new BasicRunnable(obj))).start();
        (new Thread(new BasicRunnable(obj))).start();
        (new Thread(new BasicRunnable(obj))).start();
        (new Thread(new BasicRunnable(obj))).start();
        (new Thread(new BasicRunnable(obj))).start();
        (new Thread(new BasicRunnable(obj))).start();
        
        /* example output due to not-thread-safeness (we'd expect all 0s)
           seems to work properly most of the time though lol.
           
            name: hello, theValue: 0
            name: hello, theValue: 0
            name: hello, theValue: 1
            name: hello, theValue: 0
            name: hello, theValue: 0
            name: hello, theValue: 1
            name: hello, theValue: 0
        */
        
        //(new BasicThread()).start();
    }
}