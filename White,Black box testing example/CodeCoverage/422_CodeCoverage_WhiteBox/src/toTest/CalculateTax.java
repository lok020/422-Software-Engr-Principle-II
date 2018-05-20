package toTest;

public class CalculateTax {
	
	//default constructor
	public CalculateTax() {}
	
	public int calculateTax(int salary, int children) {
		int percTax = 0, discount = 0;
		
		if(salary < 0 || children < 0)
			return -1;
		
		if(salary >= 0 && salary <= 15000)
			percTax = 0;
		else if(salary > 15000 && salary <= 50000)
			percTax = 25;
		else if(salary > 50000)
			percTax = 45;
		
		if (children == 0)
			discount = 0;
		else if(children == 1)
			discount = 2;
		else if(children == 2)
			discount = 5;
		else if(children == 3)
			discount = 7; 
		else if (children >= 4)
			discount = 10;
				
		if(salary > 80000)
			discount = 0;
		
		if(percTax - discount < 0)
			return 0;
		return (percTax - discount);
	}
}
