package percolation;

public class PercolationApp {

	//Test the statistics collected from PercolationStats.java
	
	public static void main(String[] args) {
		PercolationStats test1 = new PercolationStats(200,100);
		PercolationStats test2 = new PercolationStats(200,100);
		PercolationStats test3 = new PercolationStats(2,100000);
		
		System.out.println("Test 1:");
		printTest(test1);
		System.out.println("\nTest 2:");
		printTest(test2);
		System.out.println("\nTest 3:");
		printTest(test3);
		
	}

	private static void printTest(PercolationStats test1) {
		System.out.println(test1.mean());
		System.out.println(test1.stddev());
		System.out.println(test1.confidenceLow());
		System.out.println(test1.confidenceHigh());
	}
}
