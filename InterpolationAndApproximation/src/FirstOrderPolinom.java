
public class FirstOrderPolinom extends ApproximationPolinom {
	protected double a;
	protected double b;
	
	public FirstOrderPolinom(double[] X, double[] Y) {
		super(X, Y);		
	}

	@Override
	public double P(double x) {
		return a*x + b;
	}
	@Override
	protected void evaluateCoefficients() {		
		Row result;
		Row[] matrix = new Row[] {
				new Row(sum(X), n, sum(Y)),			
			    new Row(sum(X, 2), sum(X), sum(1, 1))
		};
		if(sum(X) == 0) {
			result = new Matrix(matrix[1], matrix[0]).solveByGauss();
		}
		else {
			result = new Matrix(matrix[0], matrix[1]).solveByGauss();			
		}
		a = result.elem(0).doubleValue();
		b = result.elem(1).doubleValue();
	}
	@Override
	public String toString() {
		return "P(x) = "+a+"x + "+b;
	}
}
