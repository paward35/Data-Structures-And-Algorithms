/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author TODO
 *
 *************************************************************************/

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> 
{
	private Node root;             // root of BST

	/**
	 * Private node class.
	 */
	private class Node 
	{
		private Key key;           // sorted by key
		private Value val;         // associated data
		private Node left, right;  // left and right subtrees
		private int N;             // number of nodes in subtree

		public Node(Key key, Value val, int N) 
		{
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	// is the symbol table empty?
	public boolean isEmpty() 
	{ 
		return size() == 0; 
	}

	// return number of key-value pairs in BST
	public int size() 
	{ 
		return size(root); 
	}

	// return number of key-value pairs in BST rooted at x
	private int size(Node x) 
	{
		if (x == null) return 0;
		else return x.N;
	}

	/**
	 *  Search BST for given key.
	 *  Does there exist a key-value pair with given key?
	 *
	 *  @param key the search key
	 *  @return true if key is found and false otherwise
	 */
	public boolean contains(Key key)
	{
		return get(key) != null;
	}

	/**
	 *  Search BST for given key.
	 *  What is the value associated with given key?
	 *
	 *  @param key the search key
	 *  @return value associated with the given key if found, or null if no such key exists.
	 */
	public Value get(Key key)
	{ 
		return get(root, key); 
	}

	private Value get(Node x, Key key) 
	{
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) return get(x.left, key);
		else if (cmp > 0) return get(x.right, key);
		else              return x.val;
	}

	

	/**
	 *  Insert key-value pair into BST.
	 *  If key already exists, update with new value.
	 *
	 *  @param key the key to insert
	 *  @param val the value associated with key
	 */
	public void put(Key key, Value val) 
	{
		if (val == null) 
		{ 
			delete(key); 
			return; 
		}
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) 
	{
		if (x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = put(x.left,  key, val);
		else if (cmp > 0) x.right = put(x.right, key, val);
		else              x.val   = val;
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}

	/**
	 * Tree height.
	 *
	 * Asymptotic worst-case running time using Theta notation: Theta N
	 * 
	 *   worst cast all nodes are on one side of the tree
	 *   the program must then iterate through every node to find the height
	 *   ie the number of nodes ( N ) 
	 *   Theta is the cost of a compare
	 *   4 of these are done each time
	 *   so the final answer is 4 Theta N
	 *   ignore the constants and multiples 
	 *   answer is Theta N 
	 * 
	 * search space is reduced by half every time function branches left or right 
	 * n
	 *
	 * @return the number of links from the root to the deepest leaf.
	 *
	 *
	 *
	 *
	 *
	 * Example 1: for an empty tree this should return -1.
	 * Example 2: for a tree with only one node it should return 0.
	 * Example 3: for the following tree it should return 2.
	 *   B
	 *  / \
	 * A   C
	 *      \
	 *       D
	 */
	public int height() 
	{
		if ( root == null )
		{
			return -1;
		}
		else if ( root.right == null && root.left == null )
		{
			return 0;
		}
		else
		{	
			return height(root);
		}

	}

	/*
	 * worst case runtime: 
	 * 
	 */

	private int height( Node node)
	{

		if ( node.left == null && node.right == null)
		{
			return 0;
		}
		else if ( node.left == null)
		{
			return( height(node.right) + 1 );
		}
		else if ( node.right == null)
		{
			return( height(node.left) + 1);
		}

		return ( height(node.left) > height( node.right) ? height(node.left) + 1  : height(node.right) + 1 );
	}

	/**
	 * Median key.
	 * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
	 * is the element at position (N+1)/2 (where "/" here is integer division)
	 *
	 * @return the median key, or null if the tree is empty.
	 */
	public Key median() 
	{
		if ( isEmpty() ) return null ;
		int median = ( size(root) - 1 ) / 2;
		return ( select(median) );
		//TODO fill in the correct implementation. The running time should be Theta(h), where h is the height of the tree.

	}

	public Key select(int n) {
		if (n < 0 || n >= size()) return null;
		Node x = select(root, n);
		return x.key;
	}
	private Node select(Node x, int n) 
	{
		if (x == null) return null;
		int t = size(x.left);
		if (t > n) return select(x.left, n);
		else if (t < n) return select(x.right, n-t-1);
		else return x;
	}



	/**
	 * Print all keys of the tree in a sequence, in-order.
	 * That is, for each node, the keys in the left subtree should appear before the key in the node.
	 * Also, for each node, the keys in the right subtree should appear before the key in the node.
	 * For each subtree, its keys should appear within a parenthesis.
	 *
	 * Example 1: Empty tree -- output: "()"
	 * Example 2: Tree containing only "A" -- output: "(()A())"
	 * Example 3: Tree:
	 *   B
	 *  / \
	 * A   C
	 *      \
	 *       D
	 *
	 * output: "((()A())B(()C(()D())))"
	 *
	 * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
	 *
	 * @return a String with all keys in the tree, in order, parenthesized.
	 */
	public String printKeysInOrder() 
	{
		if (isEmpty()) return "()";

		return printKeysInOrder(root);
		// TODO fill in the correct implementation
	}

	private String printKeysInOrder( Node node )
	{
		if ( node == null)
		{
			return "()";
		}
		else
			return "(" + printKeysInOrder(node.left) + node.key + printKeysInOrder(node.right) + ")";
	}

	/**
	 * Pretty Printing the tree. Each node is on one line -- see assignment for details.
	 *
	 * @return a multi-line string with the pretty ascii picture of the tree.
	 */
	public String prettyPrintKeys() {

		if (isEmpty()) return "-null\n";

		return prettyPrintKeys(root, "");
	}

	private String prettyPrintKeys(Node node, String prefix)
	{
		if ( node == null)
		{
			return ( prefix + "-null\n" );
		}

		return ( prefix + "-" +  node.val + "\n" + prettyPrintKeys( node.left, prefix + " |" ) + prettyPrintKeys( node. right, prefix + "  "  ) );
	}

	/**
	 * Deteles a key from a tree (if the key is in the tree).
	 * Note that this method works symmetrically from the Hibbard deletion:
	 * If the node to be deleted has two child nodes, then it needs to be
	 * replaced with its predecessor (not its successor) node.
	 *
	 * @param key the key to delete
	 */




	public void delete(Key key) 
	{
		root = delete(root, key);
	}

	private Node delete(Node thisNode, Key key) 
	{
		if (thisNode == null) return null;

		int cmp = key.compareTo(thisNode.key);
		if (cmp < 0)
		{
			thisNode.left  = delete(thisNode.left,  key);
		}
		else if (cmp > 0) 
		{
			thisNode.right = delete(thisNode.right, key);
		}
		else 
		{ 
			if (thisNode.right == null)
			{
				return thisNode.left;
			}
			if (thisNode.left  == null) 
			{
				return thisNode.right;
			}
			Node t = thisNode;
			thisNode = max(t.left);
			thisNode.left = deleteMax(t.left);
			thisNode.right = t.right;
		} 
		thisNode.N = size(thisNode.left) + size(thisNode.right) + 1;
		return thisNode;
	} 

	private Node max(Node thisNode) 
	{
		if (thisNode.right == null) 
		{
			return thisNode; 
		}
		else                 
		{
			return max(thisNode.right); 
		}
	}

	private Node deleteMax(Node thisNode) 
	{
		if (thisNode.right == null) 
		{
			return thisNode.left;
		}
		thisNode.right = deleteMax(thisNode.right);
		thisNode.N = size(thisNode.left) + size(thisNode.right) + 1;
		return thisNode;
	}



}
























