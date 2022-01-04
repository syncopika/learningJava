package basics;

// javac BasicClass.java -d ./
// java BasicClass.java

public class BasicClass {
    
    private int someNumber;
    private String someString;
    
    public BasicClass(int x, String str){
        someNumber = x;
        someString = str;
    }
    
    // if equals is overridden, then hashCode needs to be overridden as well
    // this is because it is expected that two objects that are considered equal
    // produce the same hashCode.
    @Override
    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        if(this == other){
            return true;
        }
        if(!(other instanceof BasicClass)){
            return false;
        }
        BasicClass b = (BasicClass)other;
        return b.someNumber == someNumber && b.someString.equals(someString);
    }
    
    @Override
    public int hashCode(){
        int hash = 0;
        // TODO: uh oh, this could potentially get into long territory 
        // if the string is long enough to produce a hash larger than an int?
        for(int i = 0; i < someString.length(); i++){
            Character c = someString.charAt(i);
            hash += (i+1) * Character.getNumericValue(c);
        }
        hash *= someNumber;
        return hash;
    }
    
    @Override
    public String toString(){
        return "someNumber: " + someNumber + ", someString: " + someString;
    }
    
    public static void main(String[] args){
        BasicClass bc1 = new BasicClass(5, "hello");
        BasicClass bc2 = new BasicClass(5, "hello");
        System.out.println(bc1.equals(bc1));
        System.out.println(bc1.equals(bc2));
        System.out.println(bc1.hashCode());
        System.out.println(bc1.hashCode() == bc2.hashCode());
    }
    
}