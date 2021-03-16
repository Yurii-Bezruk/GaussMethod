
public class Equation {
	private Row coeffs;
	
	public Equation() {
		this(new Row(2.1, 1.4, -4.3, 6.1));
	}
	public Equation(Row row) {
		try {
			coeffs = (Row) row.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	public double evalf(double x) {
		return coeffs.elem(0).doubleValue()*x*x*x + 
			   coeffs.elem(1).doubleValue()*x*x +
			   coeffs.elem(2).doubleValue()*x +
			   coeffs.elem(3).doubleValue();
	}
}
