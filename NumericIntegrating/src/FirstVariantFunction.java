
public class FirstVariantFunction implements Function {

	@Override
	public double f(double x) {		
		return (1.5*x*x + x)/(Math.pow(x, 5) + 1);
	}
	
	@Override
	public String toString() {
		return "(1.5x^2 + x) / (x^5 + 1)";
	}
}
