import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrix {
	private Row[] system;
	private java.io.FileWriter writer;
	
	public Matrix(Row... rows) {
		system = Arrays.copyOf(rows, rows.length);
		try { 
			writer = new java.io.FileWriter("countingProcess.txt");
		}catch(java.io.IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Matrix createFromRow(Row row) {
		Row[] rows = new Row[row.size() - 1];
		for (int i = 0; i < rows.length; i++) {
			try {
				rows[i] = (Row) row.clone();				
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		return new Matrix(rows);		
	}
	public int size() {
		return system.length;
	}
	public Row row(int index) {
		return system[index];
	}
	public double determinant() {
		if(size() < 1) {
			return 0;
		}
		else if(size() == 1) {
			return row(0).elem(0);
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
				result += Math.pow(-1, i) * row(i).elem(0) * minor.determinant(); 				
			}
			return result;
		}
	}
	public double[] solveByGauss() {
		log();
		for(int i = 0; i < size(); i++) {
			row(i).divide(row(i).elem(i));
			log();
			for (int j = 0; j < size(); j++) {
				if(i != j) {					
					Row next = null;
					try {
						next = (Row) row(i).clone();
					} catch (CloneNotSupportedException e) {}
					row(j).subtract(next.multiply(row(j).elem(i)));
					log();
				}
			}
		}		
		double[] result = new double[size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = row(i).elem(row(i).size() - 1);
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	public boolean isConverging() {
		for (int i = 0; i < size(); i++) {
			double sum = 0.0;
			for (int j = 0; j < size(); j++)
				if(j != i)
					sum += Math.abs(row(i).elem(j));
			if(sum > Math.abs(row(i).elem(i)))
				return false;
		}		
		return true;
	}
	public double[] solveBySimpleIterations(Row approach) {
		Matrix newSystem = Matrix.createFromRow(approach);
		for (int i = 0; i < newSystem.size(); i++) {
			newSystem.row(i).set(i, 0);
		}
		
		
		
		return null;
	}
	@Override
	public String toString() {
		String result = "";
		for (Row row : system) {
			result += row;
		}		
		return result + "\n";
	}
	private void log() {
		try {			
			writer.write(toString());
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
}
