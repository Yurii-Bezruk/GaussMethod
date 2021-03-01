import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrix {
	private Row[] system;
	private java.io.FileWriter writer;
	
	public Matrix(Row... rows) {
		system = new Row[rows.length];
		for (int i = 0; i < rows.length; i++) {
			try {
				system[i] = (Row) rows[i].clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		try { 
			writer = new java.io.FileWriter("countingProcess.txt");
		}catch(java.io.IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Matrix createFromRow(Row row) {
		Matrix matrix = new Matrix(row);
		for (int i = 0; i < row.size() - 1; i++) {
			matrix.appendRow(row);
		}
		return matrix;		
	}
	public int size() {
		return system.length;
	}
	public Row row(int index) {
		return system[index];
	}
	public Row rightPart() {
		Row rightPart = new Row(row(0).elem(row(0).size() - 1));
		for (int i = 1; i < system.length; i++) {
			rightPart.appendElem(row(i).elem(row(i).size() - 1));
		}
		return rightPart;
	}
	public Row diagonal() {
		Row diagonal = new Row(row(0).elem(0));
		for (int i = 1; i < system.length; i++) {
			diagonal.appendElem(row(i).elem(i));
		}
		return diagonal;
	}
	public Matrix appendRow(Row row) {
		Row[] temp = Arrays.copyOf(system, system.length + 1);
		try {
			temp[temp.length-1] = (Row) row.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		system = temp;
		return this;
	}
	public Matrix appendColumn(Row column) {
		for (int i = 0; i < system.length; i++) {
			row(i).appendElem(column.elem(i));
		}
		return this;
	}
	public Matrix minor(int rowNumber) {
		List<Row> rows = new ArrayList<>();				
		for (int j = 0; j < size(); j++) {
			if(j != rowNumber) {
				rows.add(row(j).subRow(1, row(j).size() - (row(j).size() > this.size() ? 1 : 0)));
			}
		}
		return new Matrix(rows.toArray(new Row[rows.size()]));
	}
	public BigDecimal determinant() {
		if(size() < 1) {
			return new BigDecimal(0);
		}
		else if(size() == 1) {
			return row(0).elem(0);
		}
		else if(size() == 2) {
			return row(0).elem(0).multiply(row(1).elem(1)).subtract(row(0).elem(1).multiply(row(1).elem(0)));
		}
		else {
			BigDecimal result = new BigDecimal(0);
			result.setScale(3, RoundingMode.HALF_DOWN);
			for(int i = 0; i < size(); i++) {
				BigDecimal sign = new BigDecimal(Math.pow(-1, i));
				sign.setScale(3, RoundingMode.HALF_DOWN);
				result = result.add(sign.multiply(row(i).elem(0).multiply(minor(i).determinant()))); 				
			}
			return result;
		}
	}
	public Row solveByGauss() {
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
		
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rightPart();
	}
	public boolean isConverging() {
		for (int i = 0; i < size(); i++) {
			BigDecimal sum = new BigDecimal(0);
			sum.setScale(3, RoundingMode.HALF_DOWN);
			for (int j = 0; j < size(); j++)
				if(j != i)
					sum = sum.add(row(i).elem(j).abs());
			if(sum.compareTo(row(i).elem(i).abs()) == 1)
				return false;
		}		
		return true;
	}
	public Row solveBySimpleIterations(Row approach, double precision) {
		BigDecimal bigPrecision = new BigDecimal(precision);
		bigPrecision.setScale(3, RoundingMode.HALF_DOWN);
		Row difference = new Row(bigPrecision.add(new BigDecimal(1)));
		
		while(difference.max().compareTo(bigPrecision) == 1) {
			Matrix newSystem = Matrix.createFromRow(approach).appendColumn(this.rightPart());
			for (int i = 0; i < newSystem.size(); i++) {
				BigDecimal xi = newSystem.row(i).elem(row(i).size() - 1);
				xi.setScale(3, RoundingMode.HALF_DOWN);
				for (int j = 0; j < newSystem.size(); j++) {
					if(i != j) {
						xi = xi.subtract(newSystem.row(i).elem(j));
					}
				}
				newSystem.row(i).set(i, xi.divide(this.row(i).elem(i), 3, RoundingMode.HALF_DOWN));
			}
			this.log(newSystem.toString());
			difference = newSystem.diagonal();
			for (int i = 0; i < system.length; i++) {
				difference.set(i, newSystem.diagonal().elem(i).subtract(approach.elem(i)).abs());
			}
			approach = newSystem.diagonal();
			this.log(approach.toString());
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return approach;
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
		log(toString());
	}
	private void log(String what) {
		try {			
			writer.write(what+"\n");
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
}
