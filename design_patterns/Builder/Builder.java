package designPatterns;

// useful for incrementally building a particular object
// if its arguments can't be passed at once to the constructor
public class Builder {
    
    private int id;
    private String name;
    
    private class Something{
        private int id;
        private String name;
        
        Something(int id, String name){
            this.id = id;
            this.name = name;
        }
        
        public String toString(){
            return "id: " + id + ", name: " + name; 
        }
    }
    
    public Builder setId(int id){
        this.id = id;
        return this;
    }
    
    public Builder setName(String name){
        this.name = name;
        return this;
    }
    
    public Something build(){
        return new Something(id, name);
    }
    
    public static void main(String[] args){
        Builder b = new Builder();
        b.setId(123);
        b.setName("hello");
        Something s = b.build();
        System.out.println(s);
    }
    
}