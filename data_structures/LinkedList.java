import java.util.*;

public class LinkedList<T> {
	
	// head and tail references
	private Node<T> head;
	private Node<T> tail;
	private int length;
	
	// private inner class for the nodes
	private class Node<T>{
		
		private T data;
		private Node<T> next = null;
		
		// constructor
		public Node(T data){
			this.data = data;
		}
		
	}
	
	//constructor
	public LinkedList(){
		head = null;
		tail = null;
		length = 0;
	}
	
	// functions for linked list functionality
	
	/*
	 *  adds a new node to the tail
	 */
	public void addToTail(T value){
		
		Node<T> n = new Node<T>(value);
		
		if(head == null){
			head = n;
			tail = n;
		}else{
			tail.next = n;
			tail = n;
		}
		length++;
	}
	
	/*
	 *  adds a new node to the head
	 */
	public void addToHead(T value){
		
		Node<T> n = new Node<T>(value);
		
		if(head == null){
			head = n;
			tail = n;
		}else{
			n.next = head;
			head = n;
		}
		length++;
	}
	
	/*
	 *  inserts node after a certain node
	 */
	public void insertNodeAfter(T valueToFind, T valueToPut){
		
		Node<T> n = new Node<T>(valueToPut);
		
		Node<T> curr = head;
		
		while(curr != null){
			if(curr.data == valueToFind){
				n.next = curr.next;
				curr.next = n;
				length++;
			}
			curr = curr.next;
		}
	}
	
	/*
	 *  deletes a particular node - first instance only
	 */
	public void deleteNode(T valueToDelete){
		
		Node<T> curr = head;
		Node<T> prev = null;
		
		while(curr != null){
			//if head needs to be deleted and there is only one node
			if(head.data == valueToDelete && head.next == null){
				head = null;
				tail = null;
				length = 0;
			}else{
				if(curr.data == valueToDelete){
					if(curr == head){
						head = curr.next;
						length--;
						break;
					}else if(curr == tail){
						prev.next = curr.next;
						tail = prev;
						length--;
						break;
					}else{
						prev.next = curr.next;
						length--;
						break;
					}
				}
				prev = curr;
			}
			curr = curr.next;
		}
	}
	
	/*
	 *  delete duplicates
	 */
	public void deleteDuplicates(T value){
		
		//store all unique nodes here
		HashSet<T> s = new HashSet<T>();
		
		Node<T> curr = head;
		Node<T> prev = null;
		
		while(curr != null){
			if(s.contains(curr.data)){
				prev.next = curr.next;
				if(curr == tail){
					tail = prev;
				}
				length--;
			}else if(!s.contains(curr.data)){
				s.add(curr.data);
				prev = curr;
			}
			curr = curr.next;
		}
	}
	
	/* get head value */
	public T getHead(){
		return head.data;
	}
	
	/* get tail value */
	public T getTail(){
		return tail.data;
	}
	
	/* get length of list */
	public int getLength(){
		return length;
	}
	
	/*
	 *  prints linked list
	 */
	public void printList(){
		Node<T> n = head;
		int i = 0;
		while(n != null){
			System.out.println("node " + i + " is: " + n.data);
			i++;
			n = n.next;
		}
		System.out.println("The length of the list is: " + length);
	}
	
	/*
	 *  equals method! important!
	 */
	public boolean equals(Object o){
		
		if(this == o){
			return true;
		}
		
		if(!(o instanceof LinkedList)){
			return false;
		}
		
		LinkedList<T> l = (LinkedList<T>)o;
		
		if(l.getLength() != length){
			return false;
		}
		
		Node<T> otherList = l.head;
		Node<T> thisList = head;
		
		while(thisList != null && otherList != null){
			if(!thisList.data.equals(otherList.data)){
				return false;
			}
			thisList = thisList.next;
			otherList = otherList.next;
		}
		
		return true;
	}
	
	
	/*
	 *	hash code method
	 */
	public int hashCode(){
		
		int constant = 5;
		int total = 0;
		
		Node<T> curr = head;
		Node<T> prev = null;
		
		while(curr != null){
			if(prev == null){
				total += constant*curr.data.hashCode();
			}else{
				int diff = Math.abs(prev.data.hashCode() - curr.data.hashCode());
				total += diff;
			}
			prev = curr;
			curr = curr.next;
		}
		
		return total;
	}
	
}
