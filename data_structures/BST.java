import java.util.*;

/*
 * Binary Search Tree
 * 
 * all left subtrees are smaller, all right subtrees are larger
 * than parent node
 * 
 * nodes must contain elements which are comparable
 * 
 */

public class BST<T extends Comparable<T>>{

	private Node<T> root;
	
	private class Node<T>{
		
		Node<T> left;
		Node<T> right;
		T data;
		
		public Node(T data){
			left = null;
			right = null;
			this.data = data;
		}
	}
	
	public BST(){
		root = null;
	}
	
	/*
	 * add a new node to the tree
	 */
	public void add(T data){
		
		Node<T> n = new Node<T>(data);
		
		//if tree is empty
		if(root == null){
			root = n;
			return;
		}
		addHelper(root, n);
	}
	
	//helper method for add
	private void addHelper(Node<T> start, Node<T> newNode){
		
		if(newNode.data.compareTo(start.data) < 0){
			//go left
			if(start.left == null){
				start.left = newNode;
				return;
			}else{
				addHelper(start.left, newNode);
			}
			
		}else if(newNode.data.compareTo(start.data) > 0){
			//go right
			if(start.right == null){
				start.right = newNode;
				return;
			}else{
				addHelper(start.right, newNode);
			}
		}
	}
	
	/*
	 * remove a node from the tree
	 * 
	 */
	public void remove(T data){
		removeHelper(root, data);
	}
	
	//helper method for remove
	public void removeHelper(Node<T> start, T data){
		
		if(start == null){
			return;
		}
		//what if only one node in tree (the root?)
		if(start == root && isLeaf(start) && start.data.equals(data)){
			root = null;
			return;
		}
		
		//base cases
		if(start.left != null){
			if(start.left.data.equals(data) && isLeaf(start.left)){
				start.left = null;
				return;
			}
		}
		
		if(start.right != null){
			if(start.right.data.equals(data) && isLeaf(start.right)){
				start.right = null;
				return;
			}
		}
		
		//if target is inner node
		if(start.data.equals(data)){
			
			if(start.left != null){
				//get the max from left subtree
				//and replace current node value with it
				//this value will be the new target data to remove
				start.data = getMax(start.left).data;
				
				//then check if the left node of the current node
				//has the new target value. if it does, start recursion
				//at this current node again. otherwise, move on.
				if(start.left.data.equals(start.data)){
					removeHelper(start, start.data);
				}else{
					removeHelper(start.left, start.data);
				}
			}else if(start.right != null){
				
				start.data = getMin(start.right).data;
				
				if(start.right.data.equals(start.data)){
					removeHelper(start, start.data);
				}else{
					removeHelper(start.right, start.data);
				}
			}
			
		}else{
			//keep looking either left or right
			if(data.compareTo(start.right.data) <= 0){
				removeHelper(start.left, data);
			}else{
				removeHelper(start.right, data);
			}	
		}
	}
	
	//helper method - checks if a node is a leaf
	private boolean isLeaf(Node<T> n){
		if(n.left == null && n.right == null){
			return true;
		}
		return false;
	}
	
	//another helper method - find the maximum value node
	public Node<T> getMax(Node<T> n){
		if(n == null){
			return null;
		}
		if(n.right == null){
			return n;
		}
			return getMax(n.right);
	}
	
	//another helper method - find the minimum value node
	public Node<T> getMin(Node<T> n){
		if(n == null){
			return null;
		}
		if(n.left == null){
			return n;
		}
		return getMin(n.left);
	}
	
	/*
	 * print the tree in order
	 * 
	 */
	public void printTreeInOrder(){
		printTreeHelper(root);
	}
	
	//helper function
	private void printTreeHelper(Node<T> n){
		if(n == null){
			return;
		}
		printTreeHelper(n.left);
		System.out.println(n.data);
		printTreeHelper(n.right);
	}
	
	/*
	 * search for a particular value in the tree
	 * returns true if it's there, false if not
	 */
	public boolean search(T data){
		return searchHelper(root, data);
	}
	
	//helper function
	private boolean searchHelper(Node<T> node, T data){
		if(node == null){
			return false;
		}
		if(node.data.equals(data)){
			return true;
		}
		if(data.compareTo(node.data) <= 0){
			return searchHelper(node.left, data);
		}else{
			return searchHelper(node.right, data);
		}
	}
	
	
}
