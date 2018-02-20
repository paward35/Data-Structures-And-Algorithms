import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  Patrick Ward
 *  @version 13/10/16 18:15
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	private class DLLNode
	{
		public final T data; // this field should never be updated. It gets its
		// value once from the constructor DLLNode.
		public DLLNode next;
		public DLLNode prev;

		/**
		 * Constructor
		 * @param theData : data of type T, to be stored in the node
		 * @param prevNode : the previous Node in the Doubly Linked List
		 * @param nextNode : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
		{
			data = theData;
			prev = prevNode;
			next = nextNode;
		}
	}

	// Fields head and tail point to the first and last nodes of the list.
	private DLLNode head, tail;

	/**
	 * Constructor
	 * @return DoublyLinkedList
	 */
	public DoublyLinkedList() 
	{
		head = null;
		tail = null;
	}

	/**
	 * Tests if the doubly linked list is empty
	 * @return true if list is empty, and false otherwise
	 *
	 * Worst-case asymptotic runtime cost: 1
	 *
	 * Justification:
	 * TC is time taken to compare
	 * Function is only one TC
	 * not dependent on input size
	 */
	public boolean isEmpty()
	{
		return ( head == null );
	}

	/**
	 * Inserts an element in the doubly linked list
	 * @param pos : The integer location at which the new data should be
	 *      inserted in the list. We assume that the first position in the list
	 *      is 0 (zero). If pos is less than 0 then add to the head of the list.
	 *      If pos is greater or equal to the size of the list then add the
	 *      element at the end of the list.
	 * @param data : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 * Worst-case asymptotic runtime cost: n
	 *
	 * Justification:
	 * Taking a standard operation to be 1  assignment (o)
	 * Thus every worst case iteration of the  function will have a cost of 5(o)
	 * simply the constant expression to (o)
	 * calling function "findNodeAtPostionInList" at start costs n units time 
	 * calling function "enqueue" costs 1 unit time
	 * add together functions (o) + n
	 * keep only the highest power: n
	 * 
	 */
	public void insertBefore( int pos, T data ) 
	{
		if (pos < 0)
		{
			push(data); 
		}
		else  
		{
			DLLNode nodeAtPos = findNodeAtPostionInList(pos);
			if ( nodeAtPos == null)
			{
				enqueue(data);
			}
			else
			{
				DLLNode newNode = new DLLNode( data, nodeAtPos.prev, nodeAtPos );
				DLLNode previousNode = nodeAtPos.prev;

				nodeAtPos.prev = newNode;
				if ( previousNode != null)
				{
					previousNode.next = newNode;
				}
				else
				{
					head = newNode;
				}
				newNode.next = nodeAtPos;

			}
		}


	}

	/**
	 * Returns the data stored at a particular position
	 * @param pos : the position
	 * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
	 *
	 * Worst-case asymptotic runtime cost: n
	 *
	 * Justification:
	 * Taking a standard operation to be 1 comparison  (o)
	 * Thus every worst case iteration of the  function will have a cost of 1(o)
	 * simply the constant expression to (o)
	 * calling function at start costs n units time 
	 * add together functions (o) + n
	 * keep only the highest power 
	 * result = n
	 */
	public T get(int pos) 
	{
		DLLNode nodeAtPos = findNodeAtPostionInList(pos);
		if ( nodeAtPos == null)
		{
			return null;
		}
		else
		{
			return nodeAtPos.data;
		}
	}

	/**
	 * Deletes the element of the list at position pos.
	 * First element in the list has position 0. If pos points outside the
	 * elements of the list then no modification happens to the list.
	 * @param pos : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified. 
	 *
	 *  Worst-case asymptotic runtime cost: n
	 *
	 * Justification:
	 * Taking a standard operation to be 1  assignment (o)
	 * Thus every worst case iteration of the  function will have a cost of 5(o)
	 * simply the constant expression to (o)
	 * calling function at start costs n units time 
	 * add together functions (o) + n
	 * keep only the highest power 
	 * 
	 * 
	 */
	public boolean deleteAt(int pos) 
	{

		DLLNode nodeAtPos = findNodeAtPostionInList(pos);

		if (  nodeAtPos != null )
		{

			DLLNode nodePrevious = nodeAtPos.prev;
			DLLNode nodeNext = nodeAtPos.next;

			if ( nodeNext == null && nodePrevious == null)
			{
				head = null;
				tail = null;
				return true;
			}


			if (nodeNext != null )
			{
				nodeNext.prev = nodeAtPos.prev;
			}
			else 
			{
				nodePrevious.next = null;
				tail = nodePrevious;
			}

			if (nodePrevious != null)
			{
				nodePrevious.next = nodeAtPos.next;
			}
			else 
			{
				nodeNext.prev = null;
				head = nodeNext;
			}

			return true;
		}
		else 
		{
			return false;
		}

	}

	/**
	 * Reverses the list.
	 * If the list contains "A", "B", "C", "D" before the method is called
	 * Then it should contain "D", "C", "B", "A" after it returns.
	 *
	 * Worst-case asymptotic runtime cost: n
	 *
	 * Taking a standard operation to be an assignment (o)
	 * Thus every iteration of the while loop will have a cost of (o)
	 * Suppose the doubly-linked list has 'n' elements.
	 * The while-loop will always iterate over all n elements 
	 * Therefore the total cost of this method will be n(o) 
	 * plus the cost of the assignments at the start n + 1 
	 * take the highest power
	 * Therefore the  Worst-case asymptotic runtime cost is n 
	 */
	public void reverse()
	{
		DLLNode tempNode = tail;
		tail = head;
		head = tempNode;

		while ( tempNode != null )
		{
			DLLNode newNextNode = tempNode.prev;
			tempNode.prev = tempNode.next;
			tempNode.next = newNextNode;
			tempNode = newNextNode;
		}
	}


	/*----------------------- STACK */
	/**
	 * This method should behave like the usual push method of a Stack ADT.
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to push on the stack
	 *
	 *  Worst-case asymptotic runtime cost: 1
	 *
	 * Justification:
	 * Taking a standard operation to be 1  assignment (o)
	 * Thus every worst case iteration of the  function will have a cost of 3(o)
	 * simply the constant expression to (o)
	 */
	public void push(T item) 
	{
		if ( isEmpty() )
		{
			DLLNode newNode = new DLLNode(item, null, null); 
			tail = newNode;
			head = newNode;
		}
		else 
		{
			head.prev = new DLLNode(item, null, head);
			head = head.prev;
		}
	}

	/**
	 * This method should behave like the usual pop method of a Stack ADT.
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @return the last item inserted with a push; or null when the list is empty.
	 *
	 * Worst-case asymptotic runtime cost: 1
	 *
	 * Justification:
	 * Taking a standard operation to be 1  assignment (o)
	 * Thus every iteration of the  function will have a cost of 3(o)
	 * simply the constant expression to (o)
	 *   
	 */
	public T pop() 
	{
		if ( isEmpty() )
		{
			return null;
		}
		else 
		{
			T dataFromOldHead = head.data;
			head = head.next;

			if ( head == null )
			{
				tail = null;
			}
			else 
			{
				head.prev = null;
			}
			return dataFromOldHead;
		}

	}

	/*----------------------- QUEUE */

	/**
	 * This method should behave like the usual enqueue method of a Queue ADT.
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to be enqueued to the stack
	 *
	 * Worst-case asymptotic runtime cost: 1
	 *
	 * Justification: 
	 * Taking a standard operation to be 2  assignments (o)
	 * Thus every iteration of the  function will have a cost of (o)
	 * simply the constant expression to (o)
	 *   
	 */
	public void enqueue(T item) 
	{
		if ( isEmpty() )
		{
			DLLNode newNode = new DLLNode( item, null, null);
			head = newNode;
			tail = newNode;
		}
		else 
		{
			DLLNode newTail = new DLLNode( item, tail, null);
			tail.next = newTail;
			tail = tail.next;
		}
	}

	/**
	 * This method should behave like the usual dequeue method of a Queue ADT.
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @return the earliest item inserted with a push; or null when the list is empty.
	 *
	 * Worst-case asymptotic runtime cost: 1
	 *
	 * Justification:
	 *  cost of pop is 1
	 *  this is only action performed by function 
	 *  therefore cost is 1
	 */
	public T dequeue() 
	{
		return pop();
	}


	/**
	 * @return a string with the elements of the list as a comma-separated
	 * list, from beginning to end
	 *
	 * Worst-case asymptotic runtime cost:   Theta(n)
	 *
	 * Justification:
	 *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
	 *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
	 *  Thus, every one iteration of the for-loop will have cost Theta(1).
	 *  Suppose the doubly-linked list has 'n' elements.
	 *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
	 */
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		boolean isFirst = true; 

		// iterate over the list, starting from the head
		for (DLLNode iter = head; iter != null; iter = iter.next)
		{
			if (!isFirst)
			{
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}

		return s.toString();
	}

	/*public int determinePostionInList( DLLNode nodeToCheck) 
	{
		DLLNode tempNode = head;
		int count = 0;
		while ( tempNode != null  )
		{

			if ( tempNode == nodeToCheck)
			{
				return count;
			}d
			count++;
			tempNode = tempNode.next;
		}
		return -1;
	}
	 */
	/*
	 * Worst-case asymptotic runtime cost:   Theta(n)
	 *
	 * Justification: n(o)
	 * 
	 * Taking a standard operation to be an assignment (o)
	 * Thus every iteration of the while loop will have a cost of (o)
	 * Suppose the doubly-linked list has 'n' elements.
	 * The while-loop will always iterate over all n elements 
	 * Therefore the total cost of this method will be n(o) 
	 * 
	 */


	public DLLNode findNodeAtPostionInList( int pos) 
	{
		DLLNode tempNode = head;
		int count = 0;
		while ( tempNode != null  )
		{

			if (count == pos )
			{
				return tempNode;
			}
			count++;
			tempNode = tempNode.next;
		}
		return null;
	}


}


