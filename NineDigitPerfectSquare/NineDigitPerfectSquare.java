import java.util.ArrayList;
import java.util.Arrays;

/**
 * CS2010 (Hilary Term) - Assignment 1
 *
 * Nine Digit Perfect Square
 *
 * A natural number, p, is a perfect square if for some natural number k, k^2=p.
 * For example, 16 is a perfect square, as 4^2=16. The number 20 is not a
 * perfect square as there is no natural number k such that k^2=20.
 *
 * Problem: Develop an algorithm that will find all nine-digit perfect squares
 * that use all nine digits exactly once. For example, 139,854,276 is a solution
 * (the first) as 11,826^2=139,854,276.
 *
 * 1) Fill in the implementation of the methods described in this file.
 *
 * 2) Test your implementation by developing suitable test suite in the
 * NineDigitPerfectSquareTest JUnit test case.
 *
 * @author:
 *
 */

public class NineDigitPerfectSquare 
{

	/**
	 * A method to return an array containing all squeres between low and high
	 *
	 * @param low: lowest perfect square
	 * @param high: largest perfect square
	 *
	 * @return an array containing all the perfect squares between low and high
	 */
	public int[] perfectSquaresBetween(int low, int high)
	{
		boolean hasFoundStart = false;
		int odd = 0, s = 0, r = 0;

		while ( !hasFoundStart && low <= high )
		{
			double start = Math.sqrt(low);
			if ( start % 1 == 0)
			{
				hasFoundStart = true;
				r = (int) start - 1;
				odd = r + r + 1;
				s = low;
			}
			else 
			{
				low++;
			}
		}

		ArrayList<Integer> squares = new ArrayList<Integer>();

		while ( s <= high )
		{
			squares.add(s);
			r = r + 1;
			odd = odd + 2;
			s = s + odd;
		}


		
		int[] squaresInIntArray =  new int[squares.size()];

		for ( int i = 0 ; i < squaresInIntArray.length; i++ )
		{
			squaresInIntArray[i] = (int) squares.get(i);
		}


		return squaresInIntArray;
	}
	
	

	public int countNineDigitPerfectSquares()
	{
		return getNineDigitPerfectSquares().length;
	}

	/**
	 * A method to determine if the number specified in parameter "number"
	 * contains all 9 digits exactly once.
	 *
	 * @param number
	 *            : A number to be tested
	 * @return whether the number contains all 9 digits exactly once
	 */
	public boolean containsAllDigitsOnce(int number) 
	{
		boolean[] numbers = new boolean[10];
		for ( int i = 0; i < numbers.length; i++ )
		{
			if ( numbers[ number%10 ] ==  true)
			{
				return false;
			}
			numbers[ number%10 ] =  true ;
			number = number / 10;
		}

		
		return true;
	}


	/**
	 * A method to return an array containing all the squares discovered
	 *
	 * @return an array containing all of the perfect squares which
	 * contain all digits 1..9 exactly once.
	 */
	public int[] getNineDigitPerfectSquares() 
	{
		int[] squares = perfectSquaresBetween( 123456789 , 987654321 );
		ArrayList<Integer> squaresAsIntegerArrayList = new ArrayList<Integer>();

		for ( int i = 0 ; squares.length > i ; i++ )
		{
			if ( containsAllDigitsOnce(squares[i]) == true )
			{
				squaresAsIntegerArrayList.add(squares[i]);
			}
		}


		int[] squaresInIntArray =  new int[squaresAsIntegerArrayList.size()];

		for ( int i = 0 ; i < squaresInIntArray.length; i++ )
		{
			squaresInIntArray[i] = (int) squaresAsIntegerArrayList.get(i);
		}	
		
		

		return squaresInIntArray;

	}
}


















