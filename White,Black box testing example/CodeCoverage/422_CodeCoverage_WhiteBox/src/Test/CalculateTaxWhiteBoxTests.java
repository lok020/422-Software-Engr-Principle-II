package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import toTest.CalculateTax;

public class CalculateTaxWhiteBoxTests {
	
	@Test
	public void testCalculateTax() {
		System.out.println("inside testCalculateTax");
		
		CalculateTax app = new CalculateTax();
		
		// no chidren case green
		assertEquals("salary 40000, children 0", app.calculateTax(40000, 0), 25);
		
		// makes the > 80000 branch green
		assertEquals("salary  > 80000", app.calculateTax(80001, 0), 45);
		
		// nodes 1-9 green
		assertEquals("salary < 0", app.calculateTax(-1, 0), -1);
		assertEquals("children < 0", app.calculateTax(40000, -1), -1);
		
		// nodes 2-12 green
		assertEquals("salary 15000, children 1", app.calculateTax(15000, 1), 0);
		assertEquals("salary 30000, children 2", app.calculateTax(30000, 2), 20);
		assertEquals("salary 40000, children 3", app.calculateTax(40000, 3), 18);
		assertEquals("salary 60000, children 4", app.calculateTax(60000, 4), 35);
		assertEquals("salary 60000, children 5", app.calculateTax(60000, 5), 35);
		
		/* BLACKBOX TESTING
		
		// boundry value analysis 
		Assert.assertEquals("s1 nom, c1 min", app.calculateTax(40000, 0), -1);
		Assert.assertEquals("s1 nom, c1 min+", app.calculateTax(40000, 1), 23);
		Assert.assertEquals("s1 nom, c1 nom", app.calculateTax(40000, 2), 20);
		Assert.assertEquals("s1 nom, c1 max-", app.calculateTax(40000, 3), 18);
		Assert.assertEquals("s1 nom, c1 max", app.calculateTax(40000, 4), 15);
		Assert.assertEquals("s1 min, c1 nom", app.calculateTax(-1, 2), -1);
		Assert.assertEquals("s1 min+, c1 nom", app.calculateTax(0, 2), -5);
		Assert.assertEquals("s1 max-, c1 nom", app.calculateTax(80000, 2), 40);
		Assert.assertEquals("s1 max, c1 nom", app.calculateTax(80001, 2), 45);
		
		// category partitioning
		assertEquals("salary = -1, should return -1 (error)", -1, app.calculateTax(-1, 0));
		assertEquals("salary = 7500, children 0 (error)", -1, app.calculateTax(7500, 0));
		assertEquals("salary = 7500, children 1 (OK)", -2, app.calculateTax(7500, 1));
		//assertEquals("salary = 35000, children 5 (error)", -1, app.calculateTax(35000, 5));
		assertEquals("salary = 35000, children 2 (OK)", 20, app.calculateTax(35000, 2));
		assertEquals("salary = 65000, children 0 (error)", -1, app.calculateTax(65000, 0));
		assertEquals("salary = 65000, children 1 (OK)", 43, app.calculateTax(65000, 1));
		//assertEquals("salary = 85000, children 5 (error)", -1, app.calculateTax(85000, 5));
		assertEquals("salary = 85000, children 2 (OK)", 45, app.calculateTax(85000, 2));
		
		*/
	}

}
