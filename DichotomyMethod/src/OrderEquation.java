import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderEquation extends Equation{
	public OrderEquation() {
		//this("1", "-2.8", "-6.2", "3.7");
		this("2.1", "1.4", "-4.3", "6.1");
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
		newX.setScale(scale, RoundingMode.HALF_DOWN);
		return f(newX);
	}
	public BigDecimal f(BigDecimal x) {	
		x = new BigDecimal(x.toString());
		return coeffs[0].multiply(x.pow(3)).add( 
			   coeffs[1].multiply(x.pow(2)).add(
			   coeffs[2].multiply(x).add(
			   coeffs[3]
					   )
			       )
			   );
	}
	
}
