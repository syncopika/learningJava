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
		}else if(start == root && root.data.equals(data)){
			//if root is to be removed and tree has > 1 node
			if(getMax(start.left) != null){
				//replace the data!
				start.data = getMax(start.left).data;
				removeHelper(start.left, start.data);
			}else if(getMin(start.right) != null){
				start.data = getMin(start.right).data;
				removeHelper(start.right, start.data);
			}
		}else{
			//check if data is less than or more than start.data.
			//this will tell us which direction to go.
			boolean goLeft = data.compareTo(start.data) <= 0 ? true : false;
			boolean goRight = data.compareTo(start.data) >= 0 ? true : false;
		
			//look left if goLeft
			if(start.left != null && goLeft){	
				//if a left node is the target and it is a leaf, just make it null
				if(start.left.data.equals(data) && isLeaf(start.left)){
					start.left = null;
					return;
				}else if(start.left.data.equals(data)){
					//left node is to be removed and NOT a leaf.
					//replace data with either MAX node from LEFT subtree, if it exists,
					//or MIN node from RIGHT subtree.
					if(getMax(start.left) != null){
						//replace the data!
						start.left.data = getMax(start.left).data;
					}else if(getMin(start.right) != null){
						start.left.data = getMin(start.right).data;
					}
					removeHelper(start.left, start.left.data);
					return;
				}
			}
			
			//same for right side
			if(start.right != null && goRight){
				if(start.right.data.equals(data) && isLeaf(start.right)){
					start.right = null;
					return;
				}else if(start.right.data.equals(data)){
					if(getMax(start.right) != null){
						//replace the data!
						start.right.data = getMax(start.right).data;
					}else if(getMin(start.right) != null){
						start.right.data = getMin(start.right).data;
					}
					removeHelper(start.right, start.right.data);
					return;
				}
			}
			
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
