import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
	//~ Constructor ........................................................
	@Test
	public void testConstructor()
	{
		new DoublyLinkedList<Integer>();
	}

	//~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check if the insertBefore works
	 */
	@Test
	public void testInsertBefore()
	{
		// test non-empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);
		testDLL.insertBefore(0,4);
		
		assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
		testDLL.insertBefore(1,5);
		assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
		testDLL.insertBefore(2,6);       
		assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
		testDLL.insertBefore(-1,7);        
		assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
		testDLL.insertBefore(7,8);        
		assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
		testDLL.insertBefore(700,9);        
		assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

		// test empty list
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0,1);        
		assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(10,1);        
		assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(-10,1);        
		assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
	}


	@Test
	public void testPush()
	{
		// test non-empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.push(0);
		testDLL.push(1);
		testDLL.push(2);

		assertEquals( "Checking push to list using 2,1,0", "2,1,0", testDLL.toString() );

	}

	@Test
	public void testPop()
	{
		// test non-empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.push(0);
		testDLL.push(1);
		testDLL.push(2);
		testDLL.push(3);

		testDLL.pop();
		assertEquals( "Checking popping elements from a non-empty list", "2,1,0", testDLL.toString() );

		testDLL.pop();
		assertEquals( "Checking popping elements from a non-empty list", "1,0", testDLL.toString() );

		testDLL.pop();
		assertEquals( "Checking popping elements from a non-empty list", "0", testDLL.toString() );

		testDLL.pop();
		assertEquals( "Checking popping elements from an empty list", "", testDLL.toString() );

		testDLL.pop();
		assertEquals( "Checking popping elements from an empty list", "", testDLL.toString() );

	}

	@Test
	public void testEnqueue()
	{
		// test non-empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.enqueue(0);
		testDLL.enqueue(1);
		testDLL.enqueue(2);

		assertEquals( "Checking enqueue function", "0,1,2", testDLL.toString() );

	}

	@Test
	public void testDequeue()
	{
		// test non-empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.enqueue(0);
		testDLL.enqueue(1);
		testDLL.enqueue(2);

		testDLL.dequeue();
		assertEquals( "Checking dequeue function", "1,2", testDLL.toString() );
		testDLL.dequeue();
		assertEquals( "Checking dequeue function", "2", testDLL.toString() );
		testDLL.dequeue();
		assertEquals( "Checking dequeue function to empty list", "", testDLL.toString() );
		testDLL.dequeue();
		assertEquals( "Checking dequeue function on empty list", "", testDLL.toString() );

	}
	
	@Test
	public void testReverse()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.enqueue(0);
		testDLL.enqueue(1);
		testDLL.enqueue(2);
		testDLL.enqueue(3);
		testDLL.enqueue(4);
		
		testDLL.reverse();
		assertEquals( "Checking reverse", "4,3,2,1,0", testDLL.toString() );
		
		testDLL.reverse();
		assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "0,1,2,3,4", testDLL.toString() );
		
		testDLL.pop();
		testDLL.pop();
		testDLL.pop();
		testDLL.pop();
		testDLL.pop();
		testDLL.pop();
		
		assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "", testDLL.toString() );
		
	}
	
	@Test
	public void testDeleteAt()
	{
		// test non-empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();

		
		testDLL.enqueue(0);
		testDLL.deleteAt(0);
		assertEquals( "Checking delete at when only one item", "", testDLL.toString() );
		testDLL.enqueue(0);
		testDLL.enqueue(1);
		
		testDLL.enqueue(2);
		testDLL.enqueue(3);
		testDLL.enqueue(4);
		testDLL.enqueue(5);
		testDLL.enqueue(6);
		testDLL.enqueue(7);
		testDLL.enqueue(8);
		testDLL.enqueue(9);
		
		testDLL.deleteAt(2);


		assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "0,1,3,4,5,6,7,8,9", testDLL.toString() );
		
		testDLL.deleteAt(12);
		assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "0,1,3,4,5,6,7,8,9", testDLL.toString() );

		testDLL.deleteAt(2);
		assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "0,1,4,5,6,7,8,9", testDLL.toString() );
		
		testDLL.deleteAt(7);
		assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "0,1,4,5,6,7,8", testDLL.toString() );
		testDLL.deleteAt(0);
		assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "1,4,5,6,7,8", testDLL.toString() );
	}
	
	@Test
	public void testGet()
	{
		// test non-empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.enqueue(0);
		testDLL.enqueue(1);
		testDLL.enqueue(2);
		testDLL.enqueue(3);
		testDLL.enqueue(4);
		testDLL.enqueue(5);
		testDLL.enqueue(6);
		testDLL.enqueue(7);
		testDLL.enqueue(8);
		testDLL.enqueue(9);
		
		int result = testDLL.get(2);

		assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", 2, result );
		
		
		
		assertNull(  testDLL.get(11) );

	}
	
	/*@Test
	public void testFindNodeAtPos()
	{
		// test non-empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.push(0);
		testDLL.push(1);
		testDLL.push(2);
		testDLL.push(3);
		testDLL.push(4);
		
		DLLNode testy = testDLL.findNodeAtPostionInList(2);

		assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "2",(testDLL.findNodeAtPostionInList(2)).data );

	}*/









	// TODO: add more tests here. Each line of code in DoublyLinkedList.java should
	// be executed at least once from at least one test.

}

