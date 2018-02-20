// -------------------------------------------------------------------------
/**
 *  This class contains only two static methods that search for points on the
 *  same line in three arrays of integers. 
 *
 *  @author  
 *  @version 03/10/16 17:10:35
 */
class Collinear
{

	// ----------------------------------------------------------
	/**
	 * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
	 * This method is static, thus it can be called as Collinear.countCollinear(a1,a2,a3)
	 * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
	 * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
	 * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
	 * @return the number of points which are collinear and do not lie on a horizontal line.
	 *
	 * Array a1, a2 and a3 contain points on the horizontal line y=1, y=2 and y=3, respectively.
	 * A non-horizontal line will have to cross all three of these lines. Thus
	 * we are looking for 3 points, each in a1, a2, a3 which lie on the same
	 * line.
	 *
	 * Three points (x1, y1), (x2, y2), (x3, y3) are collinear (i.e., they are on the same line) if
	 * 
	 * x1(y2−y3)+x2(y3−y1)+x3(y1−y2)=0 
	 *
	 * In our case y1=1, y2=2, y3=3.
	 *
	 * You should implement this using a BRUTE FORCE approach (check all possible combinations of numbers from a1, a2, a3)
	 *
	 * ----------------------------------------------------------
	 *
	 * Experimental Performance:
	 * -------------------------
	 *  Write the running time of the algorithm when run with the following input sizes
	 *  
	 *  Input Size N      Running Time (sec)
	 *  ------------------------------------
	 *  1000              14.395
	 *  2000              101.243
	 *  4000              708.439
	 *
	 *  Assuming that the running time of your algorithm is of the form aN^b,
	 *  estimate 'b' and 'a' by fitting a line to the experimental points:
	 *
	 *  b = .0000000531143253
	 *  a = 2.811
	 *
	 *  What running time do you predict using your results for input size 5000?
	 *  What is the actual running time you get with such an input?
	 *  What is the error in percentage?
	 *
	 *  Error = ( (Actual time) - (Predicted time) ) * 100 / (Predicted time)
	 *
	 *  Input Size N      Predicted Running Time (sec)        Actual Running Time (sec)       Error (%)
	 *  ------------------------------------------------------------------------------------------------
	 *  5000              1327.444                                1298.543                            2.22%
	 * 
	 *  Approximate Mathematical Performance:
	 *  -------------------------
	 *
	 *  Using an appropriate cost model, give the performance of your algorithm.
	 *  Explain your answer.
	 *
	 *  Performance: n^3
	 *
	 *  Explanation: for loop = n
	 *  			three nested for loops = (n)(n)(n)
	 *  			answer = n^3
	 */
	static int countCollinear(int[] a1, int[] a2, int[] a3)
	{
		int count = 0;
		for ( int i = 0; i < a1.length; i++)
		{
			for ( int j = 0; j < a2.length ; j++ )
			{
				for ( int k = 0; k < a3.length; k++ )
				{

					if ( ( a1[i] * (-1) ) + ( a2[j] * ( 2 ) ) + ( a3[k] * ( -1 ) ) == 0 )
					{
						count= count + 1;
					}
				}
			}
		}


		return count;
	}

	// ----------------------------------------------------------
	/**
	 * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
	 * This method is static, thus it can be called as Collinear.countCollinearFast(a1,a2,a3)
	 * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
	 * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
	 * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
	 * @return the number of points which are collinear and do not lie on a horizontal line.
	 *
	 * In this implementation you should make non-trivial use of InsertionSort and Binary Search.
	 * The performance of this method should be much better than that of the above method.
	 *
	 * Experimental Performance:
	 * -------------------------
	 *  Measure the running time of the algorithm when run with the following input sizes
	 *  
	 *  Input Size N      Running Time (sec)
	 *  ------------------------------------
	 *  1000              .451
	 *  2000              1.976
	 *  4000              8.814
	 *  5000              14.066
	 *
	 *
	 *  Compare Implementations:
	 *  ------------------------
	 *  Show the sppedup achieved by this method, using the times you got from your experiments.
	 *
	 *  Input Size N      Speedup = (time of countCollinear)/(time of countCollinearFast)
	 *  ---------------------------------------------------------------------------------
	 *  1000              31.918
	 *  2000              58.112
	 *  4000              80.289
	 *  5000              92.318
	 *
	 *
	 *  Approximate Mathematical Performance:
	 *  -------------------------------------
	 *
	 *  Using an appropriate cost model, give the performance of your algorithm.
	 *  Explain your answer.
	 *
	 *  Performance: (n^2)*(log(n))
	 *
	 *  Explanation: Collinear.sort is n^2
	 *  			 nested for loops = n * n
	 *  			 calls function = binary search = log(n)
	 *				 binary search in for loop = (n^2)(log(n)) 
	 *				 total = n^2 + (n^2)(log(n)))
	 *				 drop n^2 
	 */
	static int countCollinearFast(int[] a1, int[] a2, int[] a3)
	{
		Collinear.sort(a3); 
	
		int count = 0;
	
		for ( int i = 0; i < a1.length ; i++)
		{
			for ( int j = 0; j < a2.length ; j++ )
			{				
				if ( Collinear.binarySearch(a3, ( a1[i] * (-1) ) + ( a2[j] * ( 2 ) )) )
				{
					count = count + 1;
				}
			}
		}
		return count;
	}

	// ----------------------------------------------------------
	/**
	 * Sorts an array of integers according to InsertionSort.
	 * This method is static, thus it can be called as Collinear.sort(a)
	 * @param a: An UNSORTED array of integers. 
	 * @return after the method returns, the array must be in ascending sorted order.
	 *
	 * ----------------------------------------------------------
	 *
	 * Approximate Mathematical Performance:
	 * -------------------------------------
	 *  Using an appropriate cost model, give the performance of your algorithm.
	 *  Explain your answer.
	 *
	 *  Performance: n^2
	 *
	 *  Explanation: Two array access, one with a for loop and one with a while loop in a for loop. 
	 *				 for loop = (n - 1)
	 *				 while loop = (n)
	 *				 total = (n)(n - 1) = n^2 - n			
	 *				 only take highest power = n ^ 2
	 */				
	static void sort(int[] a)
	{
		for ( int j = 1; j< a.length; j++ )    	 // n -1 
		{
			int temp; 								
			int i = j - 1;						
			while ( i >= 0 && a[i]> a[i+1] )    // n 
			{
				temp = a[i];
				a[i] = a[i+1];
				a[i+1] = temp;
				i = i - 1;
			}

		}


	}

	// ----------------------------------------------------------
	/**
	 * Searches for an integer inside an array of integers.
	 * This method is static, thus it can be called as Collinear.binarySearch(a,x)
	 * @param a: A array of integers SORTED in ascending order.
	 * @param x: An integer.
	 * @return true if 'x' is contained in 'a'; false otherwise.
	 *
	 * ----------------------------------------------------------
	 *
	 * Approximate Mathematical Performance:
	 * -------------------------------------
	 *  Using an appropriate cost model, give the performance of your algorithm.
	 *  Explain your answer.
	 *
	 *  Performance: log(n) 
	 *
	 *  Explanation: while loop (n) 
	 *  			 limit is halved each time
	 *  			 problem size is reduced by half each time 
	 *  			 range divided by 2 in each while loop = log(n)
	 *				 
	 */
	static boolean binarySearch(int[] a, int x)
	{
		int lo = 0, hi = a.length-1;
		while (lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;
			if (x < a[mid]) hi = mid - 1;
			else if ( x > a[mid]) lo = mid + 1;
			else return true;
		}
		return false;
	}

}
