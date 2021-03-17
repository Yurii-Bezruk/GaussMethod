import java.math.BigDecimal;

public class OrderEquation extends Equation{
	public OrderEquation() {
		this("1", "-2.8", "-6.2", "3.7");
		//this("2.1", "1.4", "-4.3", "6.1");
	}
	public OrderEquation(double... row) {
		super(row);
	}
	public OrderEquation(String... row) {
		super(row);
	}
	public OrderEquation(BigDecimal... row) {
		super(row);
	}
	
	public BigDecimal f(double x) {
		BigDecimal newX = new BigDecimal(x);
		newX.setScale(scale, mode);
		return f(newX);
	}
	public BigDecimal f(BigDecimal x) {	
		x = new BigDecimal(x.toString());
		BigDecimal result = new BigDecimal("0.0");
		result.setScale(scale, mode);
		for (int i = 0; i < coeffs.length; i++) {
			result = result.add(coeffs[i].multiply(x.pow(coeffs.length-i-1)));
		}
		return result;
	}
	
}
