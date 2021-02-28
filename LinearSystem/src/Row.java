import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Row implements Cloneable{
	private BigDecimal[] row;
	
	public Row(double... elems) {
		row = new BigDecimal[elems.length];
		for (int i = 0; i < row.length; i++) {
			row[i] = new BigDecimal(elems[i]);
			row[i].setScale(3, RoundingMode.HALF_DOWN);
		}
	}
	public Row(BigDecimal... elems) {
		row = new BigDecimal[elems.length];
		for (int i = 0; i < row.length; i++) {
			row[i] = new BigDecimal(elems[i].doubleValue());
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
	public Row multiply(double value) {
		BigDecimal coeff = new BigDecimal(value);
		coeff.setScale(3, RoundingMode.HALF_DOWN);
		return multiply(coeff);
	}
	public Row multiply(BigDecimal value) {
		for (int i = 0; i < row.length; i++) 
			row[i] = row[i].multiply(value);		
		return this;
	}
	public Row divide(double value) {
		BigDecimal coeff = new BigDecimal(value);
		coeff.setScale(3, RoundingMode.HALF_DOWN);
		return divide(coeff);
	}
	public Row divide(BigDecimal value) {
		for (int i = 0; i < row.length; i++) 
			row[i] = row[i].divide(value, 3, RoundingMode.HALF_DOWN);			
		return this;
	}
	public BigDecimal elem(int index) {
		return row[index];
	}
	public void set(int index, double value) {
		BigDecimal elem = new BigDecimal(value);
		elem.setScale(3, RoundingMode.HALF_DOWN);
		set(index, elem);
	}
	public void set(int index, BigDecimal value) {
		row[index] = value;
	}
	public Row subRow(int from, int to) {
		Row subRow = new Row(elem(from));
		for (int i = from + 1; i < to; i++) {
			subRow.appendElem(elem(i));
		}
		return subRow;
	}
	public Row appendElem(double elem) {
		BigDecimal value = new BigDecimal(elem);
		value.setScale(3, RoundingMode.HALF_DOWN);
		return appendElem(value);
	}
	public Row appendElem(BigDecimal elem) {
		BigDecimal[] temp = Arrays.copyOf(row, row.length + 1);		
		temp[temp.length-1] = elem;		
		row = temp;
		return this;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {		
		return new Row(row);
	}
	@Override
	public String toString() {
		String result = "";
		for (BigDecimal elem : row) {
			result += String.format("%.3f ", elem);
		}		
		return result + "\n";
	}
}
