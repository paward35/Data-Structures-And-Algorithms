import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author  
 *  @version 03/10/16 17:10:35
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new Collinear();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
        int expectedResult = 0;

        assertEquals("countCollinear failed with 3 empty arrays",       expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleFalse()
    {
        int[] a3 = { 15 };
        int[] a2 = { 5 };
        int[] a1 = { 10 };

        int expectedResult = 0;

        assertEquals("countCollinear({10}, {5}, {15})",       expectedResult, Collinear.countCollinear(a1, a2, a3) );
        assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3) );
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleTrue()
    {
        int[] a3 = { 15, 5, 10, 20, 50, 45, 35 };       int[] a2 = { 5 , 10 , 35};       int[] a1 = { 15, 5 ,35};

        int expectedResult = 4;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }

    @Test
    public void testBinarySearch()
    {
    	int[] a1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    	
    	boolean expectedResult = true;
    	int testInt = 8;
    	
    	assertEquals("binarySearch failed", expectedResult, Collinear.binarySearch(a1, testInt));
    	
    }
    @Test
    public void testSort()
    {
    	int[] a1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 7, 3, 2, 5 };
    	
    	boolean expectedResult = true;
    	int testInt = 15;
    	
    	Collinear.sort(a1);
    	
    }


    // TODO: add more tests here. Each line of code and ech decision in Collinear.java should
    // be executed at least once from at least one test.

    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     *  You should read the lecture notes and/or book to understand how to correctly implement the main methods.
     *  You can use any of the provided classes to read from files, and time your code.
     *
     */
     public static void main(String[] args)
     {
    	 In fileReader = new In("r04000-3.txt");
    	 int [] a3 =  fileReader.readAllInts();
    	 fileReader = new In("r04000-2.txt");
    	 int [] a2 =  fileReader.readAllInts();
    	 fileReader = new In("r04000-1.txt");
    	 int [] a1 =  fileReader.readAllInts();
    	 
    	 Stopwatch thisWatch = new Stopwatch();
    	 Collinear.countCollinear(a1, a2, a3);
    	 System.out.print( thisWatch.elapsedTime() );
    	
     }

}

