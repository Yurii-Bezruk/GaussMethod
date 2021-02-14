import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrix {
	private Row[] system;
	
	public Matrix(Row[] arr) {
		system = Arrays.copyOf(arr, arr.length);
	}
	public int size() {
		return system.length;
	}
	public Row row(int index) {
		return system[index];
	}
	public double determinant() {
		if(size() < 2) {
			return 0;
		}
		else if(size() == 2) {
			return (row(0).elem(0) * row(1).elem(1)) - (row(0).elem(1) * row(1).elem(0));
		}
		else {
			double result = 0;
			for(int i = 0; i < size(); i++) {				
				List<Row> rows = new ArrayList<>();				
				for (int j = 0; j < size(); j++) {
					if(i != j) {
						rows.add(row(j).subRow(1, row(j).size() - (row(j).size() > this.size() ? 1 : 0) ));
					}
				}
				Matrix minor = new Matrix(rows.toArray(new Row[rows.size()]));
				minor.print();
				result += Math.pow(-1, i) * row(i).elem(0) * minor.determinant(); 				
			}
			return result;
		}
	}
	public double[] solve() {
		for(int i = 0; i < size(); i++) {
			row(i).divide(row(i).elem(i));
			for (int j = 0; j < size(); j++) {
				if(i != j) {					
					Row next = null;
					try {
						next = (Row) row(i).clone();
					} catch (CloneNotSupportedException e) {}
					row(j).subtract(next.multiply(row(j).elem(i)));
				}
			}
		}		
		double[] result = new double[size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = row(i).elem(row(i).size() - 1);
		}
		return result;
	}
	public void print() {
		for (Row row : system) 
			System.out.println(row);		
		System.out.println();
	}
}
