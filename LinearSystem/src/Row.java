import java.util.Arrays;

public class Row implements Cloneable{
	private double[] row;
	
	public Row(double[] arr) {
		row = Arrays.copyOf(arr, arr.length);			
	}
	public int size() {
		return row.length;
	}
	public Row add(Row otherRow) {
		for (int i = 0; i < row.length; i++) 
			row[i] += otherRow.row[i];	
		return this;
	}
	public Row subtract(Row otherRow) {
		for (int i = 0; i < row.length; i++) 
			row[i] -= otherRow.row[i];	
		return this;
	}
	public Row multiply(double coeff) {
		for (int i = 0; i < row.length; i++) 
			row[i] *= coeff;		
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
	public Row subRow(int from, int to) {
		return new Row(Arrays.copyOfRange(row, from, to));
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Row(row);
	}
	@Override
	public String toString() {
		String result = "";
		for (double elem : row) {
			result += String.format("%.2f ", elem);
		}		
		return result + "\n";
	}
}
