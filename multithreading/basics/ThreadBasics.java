package threadBasics;

public class ThreadBasics{
    public static void main(String[] args){
        (new Thread(new BasicRunnable())).start();
        (new BasicThread()).start();
    }
}