// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	
		double total = loan;
		for (int i=0 ; i<n ; i++){ // calculate total after amount of payments
			total = (total-payment)*(1 + (rate/100.0)); //iterate on new total 
		}
		return total;
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		double g=loan/n; // initial guess
		iterationCounter = 0; //reset counter
		while (endBalance(loan, rate, n, g)>=epsilon){ // continue until within accuracy range
			g += epsilon; //increment guess by epsilon
			iterationCounter++; //increment counter
		}
		return g;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        double L = 0; //lower bound
		double H = loan; // upper bound
		double g = (L+H)/2; //initial guess
		double fg = endBalance (loan, rate, n ,g); //endBalance with payment g
		double fh = endBalance (loan, rate, n ,H);
		double fl = endBalance (loan, rate, n ,L);
		iterationCounter = 0; // reset step counter
		while ((H - L)>=epsilon){ // continue until within accuracy range
			iterationCounter++; //increment counter
			if (fg*fl>0){ 
				L=g;
				fl=fg;
			} else {
				H=g;
				fh=fg;
			}
			g = (L+H)/2.0; //new guess
			fg = endBalance (loan, rate, n ,g); //recaclulate fg
		}
		return g;
    }
}