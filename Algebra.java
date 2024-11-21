// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
		System.out.println(plus(-2,-3));   // -2 + -3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
		System.out.println(times(-3,-4));  // -3 * -4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3  
		System.out.println(div(-12,-3));   // -12 / -3   
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7	
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));

	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		if (x2>0){
			for (int i = 0 ; i<x2 ; i++){
			x1++; // increment x1 by +1, x2 times
			}
		} else if (x2<0){
			for (int i = 0 ; i<-x2 ; i++){
				x1--; // increment x1 by -1, x2 times	
			}
		}
		return x1;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		if (x2>0){
			for (int i = 0 ; i<x2 ; i++){
				x1--; // increment x1 by +1, x2 times
			}
		} else if (x2<0){
			for (int i = 0 ; i<-x2 ; i++){
				x1++; // increment x1 by -1, x2 times 
			}
		}
		return x1;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		int mult = 0;
		if (x2==0 || x1==0){ // exception for times 0
			return mult;
		} else if (x2<0 && x1>0){ //case for single negative
			mult =x2;
			for (int i = 1 ; i<x1 ; i++){
				mult = plus(mult,x2); // increment x2 by x2, x1 times
			}
		}else if (x1<0 && x2>0){ //case for single negative
			mult =x1;
			for (int i = 1 ; i<x2 ; i++){
				mult = plus(mult,x1); // increment x1 by x1, x2 times
			}
		} else if(x1<0 && x2<0) { //case for both negative
			mult =x1;
			for (int i = 1 ; i>x2 ; i--){
				mult = minus(mult,x1); // increment x1 by x1, x2 times
			}
		} else {
			mult =x2;
			for (int i = 1 ; i<x1 ; i++){
				mult = plus(mult,x2); // increment x2 by x2, x1 times
			}
		}
		return mult; 
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		if (n==0){
			return 1; //exception for n=0
		}
		int pow =x;
		for (int i = 1 ; i<n ; i++){
			pow = times(pow,x); //multiply x by x, n times
		}
		return pow;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
		int count = 0;
		if (0==x2){ 
			return -1; 
		} else if ((x2<0 && x1<0)&&x1>x2){
			return 0;
		} else if ((x2>0 && x1>0)&&x1<x2){
			return 0;
		} else if (x1<0 && x2>0){
			x1 = times (x1,-1);
			while (x2<=x1){
				x1 = minus(x1,x2);
				count++;
			} 
			return times (count,-1);
		} else if (x1>0 && x2<0){
			x2 = times (x2,-1);
			while (x2<=x1){
				x1 = minus(x1,x2);
				count++;
			} 
			return times (count,-1);
		}else  if (x2<0 && x1<0){
			x1 = times (x1,-1);
			x2 = times (x2,-1);
			while (x2<=x1){
				x1 = minus(x1,x2);
				count++;
			} 
			return count;
		}else {
			while (x2<=x1){
				x1 = minus(x1,x2);
				count++;
			} 
			return count;
		}
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		if (x1<x2){
			return x1;
		}
		while (x2<=x1){
			x1 = minus(x1,x2);
		}
		return x1;
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		int g = 1;
		if (x==0){     // exception for 0
			return 0;
		} else if (x<0){ // exception for negatives
			return -1;
		} else {
			while (times(g, g)<=x){
				g = plus(g, 1);
			}
		}
		return minus(g, 1);
	}
	
	//created my own absolute value function for sorting algorithm
	public static int abs(int x) {
		x= (x<0) ? times(x, -1) : x;
		return x;
	}
}