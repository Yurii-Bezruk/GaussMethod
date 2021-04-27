import java.util.Arrays;

public class LagrangePolinom {
	private double[] X; 
	private double[] Y;
	private double[] coeffs;
	private int n;
	
	public LagrangePolinom(double[] X, double[] Y) {
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
			subResult *= Y[i];
			result += subResult;
		}
		return result;
	}
	
}
