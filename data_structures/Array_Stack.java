// this stack utilizes an internal array 

public class Stack<T> {
	
	private T[] arr; // this is the stack
	private int index; // keep track of array index to store next item
	private int size; // keep track of size of items in stack
	
	// constructor
	public Stack() {
		arr = (T[]) new Object[10]; // initial size is 10
		index = 0;
		size = 0;
	}
	
	// methods
	public int getSize(){
		return size;
	}
	
	public void push(T val){
		if(index == arr.length){
			// resize array
			int newSize = arr.length * 2;
			T[] newArr = (T[]) new Object[newSize];
			
			for(int i = 0; i < arr.length; i++){
				newArr[i] = arr[i];
			}
			
			// add the new item now
			newArr[index] = val;
			
			// increment index for new item
			index++;
			
			// assign new array to old array
			arr = newArr;
			
		}else{
			arr[index] = val;
			index++;	
		}
		size++;
	}
	
	public T peek(){
		// make sure there's something in the stack first!
		if(size == 0){
			return null;
		}
		return arr[index - 1];
	}
	
	public T pop(){	
		// make sure size isn't 0 before popping
		if(size == 0){
			return null;
		}
		
		T item = arr[index - 1];
		arr[index - 1] = null;
		index--;
		size--;
		return item;
	}

}
