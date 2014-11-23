import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class AcronymValidatorTests {

	@Test
	public void test() {

		testAcronym("VM","Virtual Machine",true);
		testAcronym("JDK","Java Development Kit",true);
		
		testAcronym("GIZTK3","GISi Zombie Tracker 3000",true);
		testAcronym("GZT3","GISi Zombie Tracker 3000",true);
		testAcronym("GISEE0","GISi Zombie Tracker 3000",true);
		
		testAcronym("GZT3k","GISi Zombie Tracker 3000",false);
		testAcronym("GZT","GISi Zombie Tracker 3000",false);
		testAcronym("GZTK","GISi Zombie Tracker 3000",false);
		testAcronym("BLAH","GISi Zombie Tracker 3000",false);
		testAcronym("GT3","GISi Zombie Tracker 3000",false);
		
		testAcronym("B","B",true);
		testAcronym("B","BC",true);
		testAcronym("","B",false);
		
		testAcronym("AB","AB B",true);
		testAcronym("ABC","AB BC",true);
		testAcronym("AB","ABB B",true);
	}

	private void testAcronym(String acronym, String productName, boolean assertion){
		
		System.out.println(String.format("Testing Acronym:%s, product:%s, assert:%s",acronym,productName,assertion));
		assertTrue(assertion == AcronymValidator.Validate(acronym, Arrays.asList(productName.split("\\s+"))));
	}
}
