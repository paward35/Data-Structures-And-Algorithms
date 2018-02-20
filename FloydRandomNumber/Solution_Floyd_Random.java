import java.util.*;

public class Solution_Floyd_Random
{
	RandomInterface r;

	public Solution_Floyd_Random(RandomInterface r) 
	{
		this.r = r;
	}
	/**
	 * @param m - sample size
	 * @param n - pool size
	 * @return m randomly generated numbers from 0..n-1
	 */
	int[] getRandomNum(int m, int n)
	{
		int[] arr = new int[m];
		for ( int j = 0; j < m; j = j+1 )
		{
			arr[j] =r.nextInt(n);
		}
		return arr;
	}

	/**
	 * @param m - sample size
	 * @param n - pool size
	 * @return m unique randomly generated numbers from 0...n-1
	 */
	int[] getCombinations(int m, int n)
	{
		int[] rs = new int[m];

		for(int k = 0; k < Math.min(m,n);k++){
			do{
				rs[k] = r.nextInt(n);
			}
			while(k > 0 && Arrays.binarySearch(rs, 0,k-1,rs[k]) >=0);
		}

		return rs;
	}

	// Algorithm F1. Floyd's Algorithm - Recursive
	/**
	 * @param m - sample size
	 * @param n - pool size
	 * @return m unique randomly generated numbers from 0...n-1 (recursive solution)
	 */
	int[] randomSample(int m, int n)
	{
		if ( m == 0)
		{
			int[] empty = new int[0];
			return empty;
		}
		else
		{
			int[] sample = randomSample( m-1 , n-1 );
			int[] temp = getRandomNum( 1, n);
			int t = temp[0];
			int[] result = new int[sample.length + 1];
			System.arraycopy(sample, 0, result, 0, sample.length);
			result[ sample.length ] = t;
			return result;
		}

	}

	// Iterative Solution to Random Sample, Algorithm F2.
	/**
	 * @param m - sample size
	 * @param n - pool size
	 * @return m unique randomly generated numbers from 0...n-1
	 */
	int[] recRandomSample(int m, int n)
	{
		int[] result = new int[0];
		for ( int j = n - m + 1; j <=n ; j++ )
		{
			int t = getRandomNum( 1, j)[0]; 
			int[] resultTemp = new int[result.length + 1];
			System.arraycopy(result, 0, resultTemp, 0, result.length);
			resultTemp[ result.length ] = t;
			result = resultTemp;
		}
		return result;
	}

	// Solution to Random Permutation, Algorithm P.
	int[] floydPermutations(int m, int n)
	{
		int[] s = new int[m];
		for ( int j= n - m  ; j != n; j++ )
		{
			int t = getRandomNum(1, j )[0];
			boolean contains = false;

			for ( int i = 0; i < s.length && contains == false ; i++ )
			{
				if ( t == s[i] )
				{
					contains = true;
					for ( int k = s.length -1  ; k > i + 1; k-- )
					{				
						s[k] = s[ k- 1];					
					}	
					s[i + 1] = j;
				}
			}
			
			if ( !contains )
			{
				for ( int i = s.length -1  ; i > 0; i-- )
				{				
					s[i] = s[ i- 1];					
				}				
				s[0] = t;
			}
			
		}
		return s;
	}


















}
