public class FacebookCircles 
{

	private int[][] circles;
	private int lastCirclesNumber = 0;
	private int numberOfCircles = 0 ;
	private int numberOfPeopleInCircles = 0;
	/**
	 * Constructor
	 * @param numberOfFacebookUsers : the number of users in the sample data.
	 * Each user will be represented with an integer id from 0 to numberOfFacebookUsers-1.
	 */
	public FacebookCircles(int numberOfFacebookUsers) 
	{
		circles = new int[numberOfFacebookUsers][2];
		numberOfPeopleInCircles = numberOfFacebookUsers;
		numberOfCircles = numberOfFacebookUsers;
		for ( int i = 0 ; i < circles.length ; i++ )
		{
			circles[i][0] = 1 ;
			circles[i][1] = 0 ;
		}

	}

	/**
	 * creates a friendship connection between two users, represented by their corresponding integer ids.
	 * @param user1 : int id of first user
	 * @param user2 : int id of second  user
	 */
	public void friends( int user1, int user2 ) 
	{
		if ( user1 < numberOfPeopleInCircles &&  user2 < numberOfPeopleInCircles && user1 !=  user2)
		{
			int circleNumberForUserOne = circles[user1][1];
			int circleNumberForUserTwo = circles[user2][1];

			if (  (circleNumberForUserOne != 0 && circleNumberForUserTwo != 0) && (circleNumberForUserOne == circleNumberForUserTwo) )
			{
				return;
			}
			// if neither of them are in a circle 
			if ( circleNumberForUserOne == 0 && circleNumberForUserTwo == 0 )
			{
				lastCirclesNumber++;
				circles[user1][1] = lastCirclesNumber;
				circles[user2][1] = lastCirclesNumber;
				circles[user1][0] = 2;
				circles[user2][0] = 2;
			}
			else if ( circleNumberForUserOne != 0 && circleNumberForUserTwo != 0 )
			{
				int newSize = circles[user1][0] + circles[user2][0];

				for ( int i = 0 ; i < circles.length ; i++ )
				{
					if ( circles[i][1] == circleNumberForUserTwo )
					{
						circles[i][1] = circleNumberForUserOne;
						circles[i][0] = newSize;
					}
					if ( circles[i][1] == circleNumberForUserOne )
					{
						circles[i][0] = newSize;
					}
				}


			}
			else
			{
				int newSize = circles[user1][0] + circles[user2][0];
				if ( circleNumberForUserOne != 0) 
				{
					circles[user2][1] = circleNumberForUserOne ;
					circles[user2][0] = newSize ;
					for ( int i = 0 ; i < circles.length ; i++ )
					{
						if ( circles[i][1] == circleNumberForUserOne )
						{
							circles[i][0] = newSize;
						}
					}
				}
				else 
				{
					circles[user1][1] = circleNumberForUserTwo ;
					circles[user1][0] = newSize ;
					for ( int i = 0 ; i < circles.length ; i++ )
					{
						if ( circles[i][1] == circleNumberForUserTwo )
						{
							circles[i][0] = newSize;
						}
					}
				}
			}
			numberOfCircles--;
		}

	}

	/**
	 * @return the number of friend circles in the data already loaded.
	 */
	public int numberOfCircles() 
	{

		return numberOfCircles;
	}

	/**
	 * @return the size of the largest circle in the data already loaded.
	 */
	public int sizeOfLargestCircle() 
	{
		int largest = 0;
		for ( int i = 0 ; i < circles.length ; i++ )
		{
			if ( circles[i][0] > largest )
			{
				largest = circles[i][0] ;
			}
		}
		return largest;
	}

	/**
	 * @return the size of the median circle in the data already loaded.
	 */
	public int sizeOfAverageCircle() 
	{
		return numberOfPeopleInCircles/numberOfCircles;
	}

	/**
	 * @return the size of the smallest circle in the data already loaded.
	 */
	public int sizeOfSmallestCircle() 
	{
		int smallest = numberOfPeopleInCircles;
		for ( int i = 0 ; i < circles.length ; i++ )
		{
			if ( circles[i][0] < smallest )
			{
				smallest = circles[i][0] ;
			}
		}
		return smallest;
	}
}