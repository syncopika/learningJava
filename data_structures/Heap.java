import java.util.*;

/*
 * This is a min heap.
 * A min-heap is a complete binary tree where each node's children 
 * is larger than that node.
 */
public class Heap<T extends Comparable<T>> {
	
	private ArrayList<T> theHeap;
	private int size;
	
	// constructor 
	public Heap(){
		theHeap = new ArrayList<T>();
		size = 0;
	}
	
	//public methods
	/*
	 * get smallest data from heap
	 */
	public T getSmallest(){
	
		//if heap size is 0
		if(theHeap.size() == 0){
			return null;
		}
		
		//get the root
		T smallest = theHeap.get(0);
		
		//check if heap is size 1
		if(theHeap.size() == 1){
			theHeap.remove(0);
			size--;
			return smallest;
		}
				
		//and replace root with last node
		theHeap.set(0, theHeap.get(theHeap.size() - 1));
		theHeap.remove(theHeap.size() - 1);
		
		//if only two nodes left in heap
		if(theHeap.size() == 2){
			if(smallest.compareTo(theHeap.get(1)) > 0){
				swap(1, 0, theHeap);
			}
			size--;
			return smallest;
		}
		
		T currNode = theHeap.get(0);
		int currIndex = 0;
		int smallestChildIndex = getSmallestChild(2*currIndex+1, 2*currIndex+2);
		
		//if heap size > 2
		while(currNode.compareTo(theHeap.get(smallestChildIndex)) > 0){
		
			//swap the current node with the smallest child
			swap(currIndex, smallestChildIndex, theHeap);
			
			//update current index to be the index of the smallest child
			currIndex = smallestChildIndex;
			
			//smallest child index is updated from the current node's perspective
			smallestChildIndex = getSmallestChild(2*currIndex+1, 2*currIndex+2);
			
			//update current node
			currNode = theHeap.get(currIndex);
		}	
		size--;
		return smallest;
	}
	
	/*
	 *  add data to heap
	 */
	public void add(T data){
		
		//add new data to end of array first
		theHeap.add(data);
		
		//then check if that data is smaller than the parent.
		//if it is, swap with the parent. continue until parent
		//index is larger than the data.
		//important formulas:
		//parent = (i-1) / 2; left child = 2i + 1; right child = 2i + 2
		int parentIndex = (theHeap.indexOf(data) - 1) / 2;
		int curIndex = theHeap.size() - 1;
		
		//bubble up data as needed
		while(theHeap.get(parentIndex).compareTo(theHeap.get(curIndex)) > 0){
	
			swap(parentIndex, curIndex, theHeap);
			
			//current index of data is now parent index
			curIndex = parentIndex;
			
			//look at next parent
			parentIndex = (theHeap.indexOf(data) - 1) / 2;
		}
		size++;
	}
	
	/*
	 *  print the heap
	 */
	public void printHeap(){
		for(int i = 0; i < theHeap.size(); i++){
			System.out.println("node " + i + " is: " + theHeap.get(i));
		}
	}
	
	/*
	 * get the size of the heap
	 */
	public int getSize(){
		return size;
	}
	
	/* equals method
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o){
		
		if(this == o){
			return true;
		}
		
		if(!(o instanceof Heap)){
			return false;
		}
		
		Heap<T> other = (Heap<T>)o;
		
		if(other.getSize() != size){
			return false;
		}
		
		for(int i = 0; i < size; i++){
			if(other.getHeap().get(i).compareTo(theHeap.get(i)) != 0){
				return false;
			}
		}
		return true;
	}
	
	
	/*
	 * auxiliary methods
	 * 
	 */

	//helper method for add - handles swapping
	private void swap(int index1, int index2, ArrayList<T> array){
		T temp = array.get(index1);
		array.set(index1, array.get(index2)); 
		array.set(index2, temp);
	}
	
	//helper method for finding smallest child of node
	private int getSmallestChild(int leftChild, int rightChild){
		
		int limit = theHeap.size();
		
		//if left and right child indices are out of bounds
		if(leftChild >= limit && rightChild >= limit){
			return (leftChild - 1) / 2; //return parent index
		}else if(leftChild >= limit){
			return rightChild;
		}else if(rightChild >= limit){
			return leftChild;
		}
		
		if(theHeap.get(leftChild).compareTo(theHeap.get(rightChild)) > 0){
			return rightChild;
		}else{
			return leftChild;
		}
	}
	
	private ArrayList<T> getHeap(){
		return theHeap;
	}
	
}
