import java.util.Arrays;

public class Polinom {
	private double[] X; 
	private double[] Y;
	private int n;
	
	public Polinom(double[] X, double[] Y) {
		this.n = X.length;
		this.X = Arrays.copyOf(X, n);
		this.Y = Arrays.copyOf(Y, n);
	}
	
	public double Ln(double x) {
		double result = 0.0;
		for(int i = 0; i < n; i++) {
			double subResult = 1;
			for (int j = 0; j < n; j++) {
				if(j != i) {
					subResult *= (x - X[j])/(X[i] - X[j]);
				}
			}
			result += Y[i] * subResult;
		}
		return result;
	}
	
}
