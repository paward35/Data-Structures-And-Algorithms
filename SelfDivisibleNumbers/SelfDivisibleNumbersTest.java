import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;


public class SelfDivisibleNumbersTest {

	static SelfDivisibleNumbers sdn ;

	@BeforeClass
	public static void oneTimeSetUp() throws Exception 
	{
		sdn = new SelfDivisibleNumbers();
	}



	@Test
	public void constructorTest() {
		assertNotNull("Checking the constructor", sdn);
	}

	@Test
	public void getFirstKDigitNumberTest()
	{
		int [] numberArray = {4 , 3, 2, 1 , 0};
		assertEquals("Checking one Number ", 4 , sdn.getFirstKDigitNumber(numberArray, 1)); 
		assertEquals("Checking last Number ", 43210, sdn.getFirstKDigitNumber(numberArray, 5)); 
	}

	@Test
	public void isDivisableTest()
	{
		assertEquals("Checking if true", true, sdn.isDivisible(12, 3)); 
		assertEquals("Checking if false", false, sdn.isDivisible(12, 7)); 
		assertEquals("Checking if to big", false, sdn.isDivisible(24, 48)); 
	}


	@Test
	public void getSelfDivisableTest()
	{
		ArrayList<Integer> test = new ArrayList<Integer>();
		test = (ArrayList<Integer>) sdn.getSelfDivisibleNumbers();
		int result = test.get(0);
		
		
		assertEquals("Checking size", 1,  sdn.getNumberOfSelfDivisibleNumbers() );
		assertEquals("Checking results", 381654729 ,  result = test.get(0) ); 
		 

	}

	


}


























