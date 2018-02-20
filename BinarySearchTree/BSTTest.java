import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 3.1 09/11/15 11:32:15
 *
 *  @author  TODO
 */

@RunWith(JUnit4.class)
public class BSTTest
{

	//TODO write more tests here.


	/** <p>Test {@link BST#prettyPrintKeys()}.</p> */

	@Test
	public void testPrettyPrint() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals("Checking pretty printing of empty tree","-null\n", bst.prettyPrintKeys());

		//  -7
		//   |-3
		//   | |-1
		//   | | |-null
		bst.put(7, 7);       //   | |  -2
		bst.put(8, 8);       //   | |   |-null
		bst.put(3, 3);       //   | |    -null
		bst.put(1, 1);       //   |  -6
		bst.put(2, 2);       //   |   |-4
		bst.put(6, 6);       //   |   | |-null
		bst.put(4, 4);       //   |   |  -5
		bst.put(5, 5);       //   |   |   |-null
		//   |   |    -null
		//   |    -null
		//    -8
		//     |-null
		//      -null

		String result = 
						"-7\n" +
						" |-3\n" + 
						" | |-1\n" +
						" | | |-null\n" + 
						" | |  -2\n" +
						" | |   |-null\n" +
						" | |    -null\n" +
						" |  -6\n" +
						" |   |-4\n" +
						" |   | |-null\n" +
						" |   |  -5\n" +
						" |   |   |-null\n" +
						" |   |    -null\n" +
						" |    -null\n" +
						"  -8\n" +
						"   |-null\n" +
						"    -null\n";
		assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
	}
	



	@Test
	public void testPrintKeysInOrder() 
	{

		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		
		assertEquals("Checking testPrintKeysInOrder printing of empty tree", "()" , bst.printKeysInOrder());

		bst.put(7, 7);       //   | |  -2
		String result = "";
		assertEquals("Checking pretty printing of single entry tree", "(()7())", bst.printKeysInOrder());

		bst.put(1, 1);       //   | |   |-null
		bst.put(9, 9); 
		bst.put(10, 10);
		
		assertEquals("Checking pretty printing of non-empty tree", "((()1())7(()9(()10())))", bst.printKeysInOrder());
	}
	
	
	@Test
	public void testContains() 
	{

		BST<Integer, Integer> bst = new BST<Integer, Integer>();

		assertEquals("Checking contain of an empty tree", false , bst.contains(1));
		
		bst.put(null,null);
		
		bst.put(1, 1);       //   | |   |-null
		bst.put(9, 9); 
		
		boolean result = bst.contains(1);

		assertEquals("Checking get of an non-empty tree", true , result);
		
		bst.put(2, 2);   
		bst.put(2, 2);   
		bst.put(6, 6);   
		bst.put(4, 4);  
		bst.put(5, 5);   

		result = bst.contains(6);

		assertEquals("Checking get of an non-empty tree", true , result);
	}



	/** <p>Test {@link BST#delete(Comparable)}.</p> */
	
	
	
	
	@Test
	public void testGet() {
		
		BST<Integer, Integer> bst = new BST<Integer, Integer>();

		assertEquals("Checking get of an empty tree", null , bst.get(1));
		
		bst.put(null,null);
		
		bst.put(1, 1);       //   | |   |-null
		bst.put(9, 9); 
		
		int result = bst.get(1);

		assertEquals("Checking get of an non-empty tree", 1 , result);
		
		bst.put(2, 2);   
		bst.put(2, 2);   
		bst.put(6, 6);   
		bst.put(4, 4);  
		bst.put(5, 5);   

		result = bst.get(6);

		assertEquals("Checking get of an non-empty tree", 6 , result);

	}
	
	@Test
	public void testHeight() {
		
		BST<Integer, Integer> bst = new BST<Integer, Integer>();

		assertEquals("Checking heigth of an empty tree", -1 , bst.height());
		
		bst.put(7, 7);   //        _7_
		bst.height();
		assertEquals("Checking heigth of a tree with one element tree", 0 , bst.height());
		bst.put(8, 8);   //      /     \
		bst.put(3, 3);   //    _3       8
		bst.put(1, 1);   //  /     
		// 1       



		assertEquals("Checking heigth of a tree", 2 , bst.height());

		bst.put(2, 2);   
		bst.put(6, 6);   
		bst.put(4, 4);  
		bst.put(5, 5);   

		assertEquals("Checking heigth of a tree", 4 , bst.height());

	}
	
	@Test
	public void testMedian() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();

		assertEquals("Checking median of an empty tree", null , bst.median());

		bst.put(7, 7);   //        _7_
		int result = bst.median();
		assertEquals("Checking median of a tree with one element tree", 7 , result);
		bst.put(8, 8);   //      /     \
		bst.put(3, 3);   //    _3       8
		bst.put(1, 1);   //  /     
		// 1       


		result = bst.median();
		assertEquals("Checking median of a tree", 3 , result);

	
	}



	/** <p>Test {@link BST#delete(Comparable)}.</p> */
	@Test
	public void testDelete() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		bst.delete(1);
		assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());

		bst.put(7, 7);   //        _7_
		bst.put(8, 8);   //      /     \
		bst.put(3, 3);   //    _3_      8
		bst.put(1, 1);   //  /     \
		bst.put(2, 2);   // 1       6
		bst.put(6, 6);   //  \     /
		bst.put(4, 4);   //   2   4
		bst.put(5, 5);   //        \
		//         5

		assertEquals("Checking order of constructed tree",
				"(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

		bst.delete(9);
		assertEquals("Deleting non-existent key",
				"(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

		bst.delete(8);
		assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());

		bst.delete(6);
		assertEquals("Deleting node with single child",
				"(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());

		bst.delete(3);
		assertEquals("Deleting node with two children",
				"(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
		
		bst.delete(4);
		assertEquals("Deleting root node",
				"(((()1())2(()5()))7())", bst.printKeysInOrder());
		
		
		
		
		
	}

}
