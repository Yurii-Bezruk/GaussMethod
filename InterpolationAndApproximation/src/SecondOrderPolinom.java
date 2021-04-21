
public class SecondOrderPolinom extends FirstOrderPolinom {
	protected double c;
	
	public SecondOrderPolinom(double[] X, double[] Y) {
		super(X, Y);
	}
	
	@Override
	public double P(double x) {
		return a*x*x + b*x + c;
	}	
	@Override
	protected void evaluateCoefficients() {		
		Row result;
		Row[] matrix = new Row[] {
				new Row(sum(X, 2), sum(X), n, sum(Y)),			
			    new Row(sum(X, 3), sum(X, 2), sum(X), sum(1, 1)),
			    new Row(sum(X, 4), sum(X, 3), sum(X, 2), sum(2, 1))
		};
		if(sum(X, 2) == 0) {			
			if(sum(X, 3) == 0) {
				result = new Matrix(matrix[2], matrix[0], matrix[1]).solveByGauss();
			}
			else {
				result = new Matrix(matrix[1], matrix[0], matrix[2]).solveByGauss();		
			}
		}
		else {
			result = new Matrix(matrix[0], matrix[1], matrix[2]).solveByGauss();			
		}
		a = result.elem(0).doubleValue();
		b = result.elem(1).doubleValue();
		c = result.elem(2).doubleValue();
	}
	
	@Override
	public String toString() {
		return "P(x) = "+a+"x^2 + "+b+"x + "+c;
	}
}
