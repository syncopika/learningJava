// implementing a stack with a linked list
// pop and push at the first node.

public class Stack_List<T> {

	private class Node<T> {
		
		private T data;
		private Node<T> next;
		
		public Node(T val){
			data = val;
			next = null;
		}
	}
	
	private Node<T> first;
	
	public Stack_List(){
		first = null;
	}
	
	// methods
	public void push(T val){
		
		Node<T> newNode = new Node<T>(val);
		
		if(first == null){
			first = newNode;
		}else{
			newNode.next = first;
			first = newNode;
		}
	}
	
	public T pop(){
		
		// make sure there's an element to pop off!
		if(first == null){
			return null;
		}else{
			
			Node<T> temp = first;
			first = first.next;
			
			return temp.data;
		}
	}
	
}
