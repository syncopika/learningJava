package threadBasics;

abstract class SomeObject{
    private String name;
    private int theValue;
    
    abstract void increment();
    
    abstract void decrement();
    
    abstract String state();
}