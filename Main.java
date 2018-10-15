/*Kevin Ge's Program
Updated in Java 7/18/18
*/
import java.util.Scanner;

class Main {
static Scanner sc = new Scanner(System.in); 

	public static void main(String[] args) {
		System.out.print("\nWelcome to Kevin's zero finder.");
		int degree; //degree of polynomial
		System.out.print("\nHighest degree of the polynomial: "); 
		degree = sc.nextInt();
		int term = degree + 1; //amount of terms
		int[] coef = new int[term];
		int[] dcoef = new int[term]; //coefficients
		input(coef, term);
		System.out.println();
		
		derivative(coef, term, dcoef);
		
		System.out.print("f(x) =");
		output(coef, term);
		System.out.println();
		System.out.print("f'(x) =");
		doutput(dcoef, term);
		
		double guess = 0;
		System.out.print("\nGuess where the zero may be: ");
		guess = sc.nextInt();
		
		System.out.print("\nf'(" + guess + ") = " + dvalue(dcoef, term, guess)
		+ "\nf(" + guess + ") = " + value(coef, term, guess));
		
		System.out.println("\nZero is approximately: " + newton(coef, dcoef, term, guess));
	}

	static void input(int[] coef, int term) {
		int power = term - 1;
		for (int y = 0; y < term; y++) {
			System.out.print("\nEnter term " + (y + 1) + ": "); 
			coef[y] = sc.nextInt();
			System.out.print(coef[y] + "x^" + power);
			power = power - 1;
		}
	}

	static void output(int[] coef, int term) { //output for function
		int power = term - 1;
		for (int y = 0; y < term; y++) { //output loop
			if (coef[y] >= 0 && y != 0) 
				System.out.print(" + ");
			else if (coef[y] <= 0)
				System.out.print(" - ");
			else 
				System.out.print(" ");
			System.out.print(Math.abs(coef[y]) + "x^" + (power - y));
			//power = power - 1;
		}
	}

	static void doutput(int[] dcoef, int term) { //output for derivative
		int dpower = term - 2; //derivative power is power - 1
		for (int y = 0; y < term; y++) { //output loop
			if (dcoef[y] >= 0 && y != 0) 
				System.out.print(" + ");
			else if (dcoef[y] <= 0)
				System.out.print(" - ");
			else 
				System.out.print(" ");
			System.out.print(Math.abs(dcoef[y]) + "x^" + (dpower - y));
			//dpower = dpower - 1;
		}
	}

	static void derivative(int[] coef, int term, int[] dcoef) { //find derivative using power rule
		int power = term - 1;
		for (int y = 0; y < term; y++) {
			dcoef[y] = power * coef[y];
			power = power - 1;
		}
	}

	static double value(int coef[], int term, double guess) { //find value f(x)
		double sum = 0;
		double power = term - 1;
		for (int y = 0; y < term; y++) {
			sum = sum + coef[y] * Math.pow(guess, power - y);
		}
		
		return sum; //return value of f(x)
	}

	static double dvalue(int[] dcoef, int term, double guess) { //find derivative value f'(x)
		double dsum = 0;
		double dpower = term - 2;
		for (int y = 0; y < term; y++) {
			if (dpower - y < 0) {
				; //do nothing: derivatives of constants are 0
			}
			else {
				dsum = dsum + dcoef[y] * Math.pow(guess, dpower - y);
			}
		}
		
		return dsum; //return value of f'(x)
	}

	static double newton(int[] coef, int[] dcoef, int term, double guess) { //perform newton's method
		double result;
		for (int y = 0; ; y++) {
			result = guess - value(coef, term, guess) / dvalue(dcoef, term, guess);
			if (Math.abs(guess - result) < 0.001) {
				System.out.println("\nterm " + y + ": " + result);
				break;
			}
			guess = result;
			System.out.print("\nterm " + y + ": " + result);
		}
		return result;
	}

}