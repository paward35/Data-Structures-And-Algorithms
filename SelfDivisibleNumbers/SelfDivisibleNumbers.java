import java.util.ArrayList;
import java.util.List;

/**
 * CS2010 (Hilary Term) - Assignment 3
 * 
 * Self Divisible Numbers
 * 
 * Self divisible numbers are those, that satisfy the following property:
 * 		a) All the 9 digits in the number are different, i.e. each of the 9 digits 1..9 is used once.
 * 		b) The number denoted by the first k-digits is divisible by k (i.e. the first k-digits are a multiple of k)
 *  
 *  	Consider the number 723654981; 
 *  	We have:   1|7,  2|72, 3|723, 4|7236, 5|72365, 6|723654  [read  a|b  as “a divides b” or “b is a multiple of a” ] 
 *  	but 7 does not divide  7236549. So this number does not satisfy property b).
 *  
 * Create a Java program that generates all 9-digit numbers.
 * 
 * 1) Implement all methods described bellow - like in HT assignment 1, calculate the values in the constructor
 * 2) Implement tests, which sufficiently cover your code
 *  
 * @author:
 * 
 */

public class SelfDivisibleNumbers {
	
	ArrayList<Integer> listOfSelfDivisableNineDigitNumbers = new ArrayList<Integer>();
	public SelfDivisibleNumbers() 
	{
		int [] numberArray = {1,0,0,0,0,0,0,0,0};
		getSetOfSelfDivisibleNumbers(numberArray, 0);
	}

	public void getSetOfSelfDivisibleNumbers( int[] currentArray, int currentIndex)
	{
		int currentNumber = getFirstKDigitNumber(currentArray, currentIndex + 1);
		if  (currentArray[currentIndex] == 10)
		{
			currentIndex--;
			currentArray[currentIndex ]++;
			getSetOfSelfDivisibleNumbers(currentArray,currentIndex);
		}
		else if ( isDivisible( currentNumber,  currentIndex + 1) )
		{
			if ( currentIndex != 8 )
			{
				currentIndex++;
				currentArray[currentIndex] =  findUnique(currentArray, currentIndex, 1);
				getSetOfSelfDivisibleNumbers(currentArray,currentIndex);
			}
			else
			{
				currentArray[currentIndex]++;
				listOfSelfDivisableNineDigitNumbers.add(currentNumber);
			}
		}
		else 
		{
			currentArray[currentIndex] = findUnique(currentArray, currentIndex, currentArray[currentIndex]) ;
			getSetOfSelfDivisibleNumbers(currentArray,currentIndex);
		}
	}

	private int findUnique( int[] numberArray, int currentIndex, int startingDigit)
	{
		int nextDigit = startingDigit;
		boolean hasFoundUnique = false;
		while ( !hasFoundUnique)
		{
			hasFoundUnique = true;
			for ( int i =0; i <= currentIndex; i++ )
			{
				if (numberArray[i] == nextDigit)
				{
					nextDigit++;
					hasFoundUnique= false;
				}
			}
		}
		return nextDigit;

	}

	/**
	 * Method to produce a number corresponding to first k digits of the digits array
	 * @param digits
	 * @param k number of digits to construct the result from
	 * @return number
	 */
	public int getFirstKDigitNumber(int[] digits, int k) 
	{
		boolean hasMadeNumber = false;
		int result = 0;
		int i =0;
		int mod = 10;
		while ( hasMadeNumber == false )
		{
			result = (result * mod) + (digits[i]);
			i++;
			if ( i == k )
			{
				hasMadeNumber = true;
			}
		}
		return result;
	}

	/**
	 * Method to check if the specified number is divisible by the divisor
	 * @param number
	 * @param divisor
	 * @return true if number is divisible by the divisor
	 */
	public boolean isDivisible(int number, int divisor) 
	{
		return ( (number % divisor) == 0);
	}

	/**
	 * Method to return a list containing all self divisible numbers found
	 * @return 9-digit self divisible numbers
	 */
	public List<Integer> getSelfDivisibleNumbers() 
	{
		
		return listOfSelfDivisableNineDigitNumbers;
	}

	/**
	 * Method to return the number of self divisible numbers found
	 * @return number of 9-digit self divisible numbers
	 */
	public int getNumberOfSelfDivisibleNumbers() 
	{
		return listOfSelfDivisableNineDigitNumbers.size();
	}


}









































