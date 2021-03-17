import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public abstract class Equation {
	protected BigDecimal[] coeffs;

	protected BigDecimal start;
	protected BigDecimal stop; 
	protected BigDecimal step; 
	protected int scale = 5;
	protected RoundingMode mode = RoundingMode.HALF_DOWN;
	
	{
		start = new BigDecimal("-3.0");
		start.setScale(scale, mode);
		stop = new BigDecimal("6.0");
		stop.setScale(scale, mode);
		step = new BigDecimal("0.1");
		step.setScale(scale, mode);
	}
	
	public Equation(double... row) {
		coeffs = new BigDecimal[row.length];
		for (int i = 0; i < coeffs.length; i++) {
			coeffs[i] = new BigDecimal(row[i]);
			coeffs[i].setScale(scale, mode);
		}
	}
	public Equation(String... row) {
		coeffs = new BigDecimal[row.length];
		for (int i = 0; i < coeffs.length; i++) {
			coeffs[i] = new BigDecimal(row[i]);
			coeffs[i].setScale(scale, mode);
		}
	}
	public Equation(BigDecimal... row) {
		coeffs = Arrays.copyOf(row, row.length);
	}
	
	
	public abstract BigDecimal f(BigDecimal x);
	
	
	public BigDecimal[][] getValuesDiapazone() {
		java.util.List<BigDecimal[]> diapazone = new java.util.ArrayList<>();		
		BigDecimal I = new BigDecimal(start.toString());
		I.setScale(scale, mode);
		for(; I.compareTo(stop) != 1; I = I.add(step)) {			
			diapazone.add(new BigDecimal[] {I, f(I)});
		}
		return diapazone.toArray(new BigDecimal[diapazone.size()][2]);
	}
	public BigDecimal[] solve(BigDecimal precision) {
		precision = precision.multiply(new BigDecimal("2.0"));
		java.util.List<BigDecimal> result = new java.util.ArrayList<>();
		BigDecimal[][] diapazone = this.getValuesDiapazone();
		for (int i = 1; i < diapazone.length; i++) {
			if(diapazone[i][1].signum() != diapazone[i-1][1].signum()) {
				//System.out.println("---------/"+diapazone[i-1][1]+" "+diapazone[i][1]);
				BigDecimal middle = diapazone[i-1][0].add(diapazone[i][0]).divide(new BigDecimal("2.0"));
				//System.out.println("<"+middle+">");
				BigDecimal subResult = this.clarificateRoot(precision, diapazone[i-1][0], middle, diapazone[i][0]); 
				result.add(subResult);
				
			}
		}		
		return result.toArray(new BigDecimal[result.size()]);
	}
	private BigDecimal clarificateRoot(BigDecimal precision, BigDecimal... diapazone) {
		//System.out.println(Arrays.toString(diapazone));
		for (int i = 1; i < diapazone.length; i++) {
			if(f(diapazone[i]).signum() != f(diapazone[i-1]).signum()) {
				if(diapazone[i].subtract(diapazone[i-1]).abs().compareTo(precision) == -1)
					return diapazone[i-1];
				else {
					BigDecimal middle = diapazone[i-1].add(diapazone[i]).divide(new BigDecimal("2.0"));
					return clarificateRoot(precision, diapazone[i-1], middle, diapazone[i]);
				}
			}
		}
		throw new NoAxisIntersectionException(Arrays.toString(diapazone));
	}
	
	
	public double getStart() {
		return start.doubleValue();
	}
	public void setStart(double start) {
		this.start = new BigDecimal(start);
		this.start.setScale(scale, mode);
	}
	public void setStart(String start) {
		this.start = new BigDecimal(start);
		this.start.setScale(scale, mode);
	}
	public double getStop() {
		return stop.doubleValue();
	}
	public void setStop(double stop) {
		this.stop = new BigDecimal(stop);
		this.stop.setScale(scale, mode);
	}
	public void setStop(String stop) {
		this.stop = new BigDecimal(stop);
		this.stop.setScale(scale, mode);
	}
	public double getStep() {
		return step.doubleValue();
	}
	public void setStep(double step) {
		this.step = new BigDecimal(step);
		this.step.setScale(scale, mode);
	}
	public void setStep(String step) {
		this.step = new BigDecimal(step);
		this.step.setScale(scale, mode);
	}
}
