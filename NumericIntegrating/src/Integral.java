
public class Integral {
	private Function function;
	
	public Integral(Function function) {
		this.function = function;
	}
	
	public double solveBySquaresMethod(double a, double b) {
		int n = 10;
		double h = (b-a)/n;
		double result = 0.0;
		for (double x = a; x <= b; x += h) {			
			result += function.f(x + h/2);
		}
		return h * result;
	}
	public double solveByTrapezeMethod(double a, double b) {
		int n = 10;
		double h = (b-a)/n;
		double coeff = (function.f(a) + function.f(b)) / 2;
		double result = 0.0;
		for (double x = a + h; x < b; x += h) {			
			result += function.f(x);
		}
		return h * (coeff + result);
	}
	public double solveBySimpsonMethod(double a, double b) {
		int n = 10;
		double h = (b-a)/n;
		double x_0 = function.f(a);
		double x_n = function.f(b);
		double x_odd = 0.0;
		for (double x = a + h; x < b; x += 2 * h) {			
			x_odd += function.f(x);
		}
		double x_even = 0.0;
		for (double x = a + 2 * h; x < b; x += 2 * h) {			
			x_even += function.f(x);
		}
		return h/3 * (x_0 + x_n + 4 * x_odd + 2 * x_even);
	}
}
