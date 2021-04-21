import java.util.Arrays;

public abstract class ApproximationPolinom {
	protected double[] X; 
	protected double[] Y;
	protected int n;
	
	
	public ApproximationPolinom(double[] X, double[] Y) {
		this.n = X.length;
		this.X = Arrays.copyOf(X, n);
		this.Y = Arrays.copyOf(Y, n);
		evaluateCoefficients();
	}
	
	public abstract double P(double x);
	
	public double getRootMeanSquareError() {		
		double result = 0.0;
		for (int i = 0; i < n; i++) {
			result += Math.pow(P(X[i]) - Y[i], 2);
		}
		return Math.sqrt(result / n);
	}
	
	protected abstract void evaluateCoefficients();
	
	protected double sum(double[] arr) {
		return sum(arr, 1);
	}
	protected double sum(double[] arr, int power) {
		double sum = 0.0;
		for (int i = 0; i < n; i++) {
			sum += Math.pow(arr[i], power);
		}
		return sum;
	}
	protected double sum(int xPower, int yPower) {
		double sum = 0.0;
		for (int i = 0; i < n; i++) {
			sum += Math.pow(X[i], xPower) * Math.pow(Y[i], yPower);
		}
		return sum;
	}
	
}
