// a linked list will be great for a Queue.
// FIFO - first in, first out

public class Queue<T> {

	// inner node class for linked list
	private class Node<T> {
		private T data;
		private Node<T> next;
		
		// constructor
		public Node(T val){
			data = val;
			next = null;
		}
	}

	// keep a head reference - this is what's removed first
	private Node<T> head;
	
	// keep a tail reference too - this is the node to add to 
	private Node<T> tail;
	
	// constructor
	public Queue(){
		head = null;
		tail = null;
	}
	
	// methods
	public void enqueue(T val){
		// create a new node
		Node<T> newNode = new Node<T>(val);
		
		if(head == null){
			head = newNode;
			tail = newNode;
		}else{
			tail.next = newNode;
			tail = newNode;
		}	
	}
	
	public T dequeue(){
		// check to make sure queue has something in it first!
		if(head == null){
			return null;
		}
		
		// remove from head
		Node<T> out = head;
		head = head.next;
		
		// then check if head now becomes null. if so, make sure tail is null too. 
		if(head == null){
			tail = null;
		}
		
		return out.data;
	}
	
}
