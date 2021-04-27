
public class ExampleFunction implements Function {

	@Override
	public double f(double x) {
		return (x+1)*Math.sin(x);
	}
	
	@Override
	public String toString() {
		return "(x + 1) * sin(x)";
	}
}
