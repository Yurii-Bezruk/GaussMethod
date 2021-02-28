import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Row implements Cloneable{
	private BigDecimal[] row;
	
	public Row(double... elems) {
		//double[] row = Arrays.copyOf(elems, elems.length);
		row = new BigDecimal[elems.length];
		for (int i = 0; i < row.length; i++) {
			row[i] = new BigDecimal(elems[i]);
			row[i].setScale(3, RoundingMode.HALF_DOWN);
		}
	}
	public int size() {
		return row.length;
	}
	public Row add(Row otherRow) {
		for (int i = 0; i < row.length; i++) 
			row[i] = row[i].add(otherRow.row[i]);	
		return this;
	}
	public Row subtract(Row otherRow) {
		for (int i = 0; i < row.length; i++) 
			row[i] = row[i].subtract(otherRow.row[i]);	
		return this;
	}
	public Row multiply(double coeff) {
		BigDecimal coeff = new
		for (int i = 0; i < row.length; i++) 
			row[i] = row[i].multiply();		
		return this;
	}
	public Row divide(double coeff) {
		for (int i = 0; i < row.length; i++) 
			row[i] /= coeff;		
		return this;
	}
	public double elem(int index) {
		return row[index];
	}
	public void set(int index, double value) {
		row[index] = value;
	}
	public Row subRow(int from, int to) {
		return new Row(Arrays.copyOfRange(row, from, to));
	}
	public Row appendElem(double elem) {
		double[] temp = Arrays.copyOf(row, row.length + 1);		
		temp[temp.length-1] = elem;		
		row = temp;
		return this;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Row(Arrays.copyOf(row, row.length));
	}
	@Override
	public String toString() {
		String result = "";
		for (double elem : row) {
			result += String.format("%.3f ", elem);
		}		
		return result + "\n";
	}
}
