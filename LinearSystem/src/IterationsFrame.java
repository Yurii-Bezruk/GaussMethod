import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IterationsFrame extends GaussFrame {
	private static final long serialVersionUID = -5053533516087717539L;
	private int m = 1;
	
	private JPanel mAndPrecisionPanel;
	private JTextField mTextField;
	private JTextField precisionTextField;
	private VectorPanel vectorPanel;
	private JPanel resultPanel;
	
	public IterationsFrame() {
		super();
		setTitle("Linear System Simple Iterations Method");
		dimension = 4;
		dimensionTextField.setText("4");
		dimensionTextField.setColumns(4);
		dimensionTextField.removeActionListener(dimensionTextField.getActionListeners()[0]);
		dimensionTextField.addActionListener(new DimensionListener());
		
		headPanel.remove(headPanel.getComponent(2));	
		headPanel.remove(resultTextField);
		
		mAndPrecisionPanel = new JPanel(new FlowLayout());
		mAndPrecisionPanel.add(new JLabel("precision: "));
		precisionTextField = new JTextField("0.005");
		precisionTextField.setColumns(5);
		mAndPrecisionPanel.add(precisionTextField);
		
		mAndPrecisionPanel.add(new JLabel("m: "));		
		mTextField = new JTextField(String.valueOf(m));
		mTextField.setColumns(3);
		mTextField.addActionListener(new MListener());
		mAndPrecisionPanel.add(mTextField);		
		
		headPanel.add(mAndPrecisionPanel);
		
		vectorPanel = new VectorPanel(dimension);
		vectorPanel.setDefaultState(m);
		contentPane.remove(matrixPanel);
		matrixPanel = new MatrixPanel(dimension);
		matrixPanel.setLab2DefaultState();
		matrixPanel.add(vectorPanel, BorderLayout.NORTH);
		
		resultPanel = new JPanel(new FlowLayout());
		resultPanel.add(new JLabel("Result: "));		
		resultTextField = new JTextField("");
		resultTextField.setColumns(20);
		resultPanel.add(resultTextField);
		matrixPanel.add(resultPanel, BorderLayout.SOUTH);
		contentPane.add(matrixPanel);
		
		solve.removeActionListener(solve.getActionListeners()[0]);
		solve.addActionListener(new SolveButtonListener());
		setFieldsWithM();
	}
	private void setFieldsWithM() {
		try {
			matrixPanel.setValueAt(0, dimension, 3 * m);
			matrixPanel.setValueAt(1, dimension, m - 6);
			matrixPanel.setValueAt(2, dimension, 15 - m);
			matrixPanel.setValueAt(3, dimension, m + 2);
			vectorPanel.setValueAt(0, m * 0.7);
		}catch(java.lang.ArrayIndexOutOfBoundsException e) {}
	}
	private class SolveButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			Row[] rows = new Row[dimension];
			double[] row = new double[dimension + 1];
			for(int i = 0; i < dimension; i++) {
				for (int j = 0; j < dimension + 1; j++) {
					try {
						row[j] = matrixPanel.getValueAt(i, j);
					}catch(NumberFormatException exception) {
						JOptionPane.showMessageDialog(IterationsFrame.this, "Uncorrect input. Please input numbers.");
						return;
					}
				}
				rows[i] = new Row(row);
			}	
			
			Matrix system = new Matrix(rows);
			double determinant = system.determinant().doubleValue();
			if(determinant == 0) {
				JOptionPane.showMessageDialog(IterationsFrame.this, "Zero determinant! system cannot be solved.");
			}
			else if(! system.isConverging()) {
				JOptionPane.showMessageDialog(IterationsFrame.this, "The method does not converge for this matrix.");
			}
			else {
				Row approach;
				try {
					approach = new Row(vectorPanel.getValueAt(0));
					for(int i = 1; i < dimension; i++) {
						approach.appendElem(vectorPanel.getValueAt(i));
					}
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(IterationsFrame.this, "Uncorrect approach vector value. Input numbers.");
					return;
				}
				double precision;
				try {
					precision = Double.parseDouble(precisionTextField.getText());
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(IterationsFrame.this, "Uncorrect precision value. Input numbers.");
					return;
				}
				
				Row roots = system.solveBySimpleIterations(approach, precision);
				resultTextField.setText("(");
				for (int i = 0; i < roots.size(); i++) 
					resultTextField.setText(resultTextField.getText() + String.format("%.3f; ", roots.elem(i)));
				resultTextField.setText(resultTextField.getText() + ")");
			}
		}
	}
	private class DimensionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {			
			contentPane.remove(matrixPanel);
			try {
				dimension = Integer.parseInt(dimensionTextField.getText());
				if(dimension <= 0) {
					JOptionPane.showMessageDialog(IterationsFrame.this, "Uncorrect input. Please input integer value > 0.");
					return;
				}
			}catch(NumberFormatException exception) {
				JOptionPane.showMessageDialog(IterationsFrame.this, "Uncorrect input. Please input integer value > 0.");
				return;
			}
			matrixPanel = new MatrixPanel(dimension);
			vectorPanel = new VectorPanel(dimension);
			matrixPanel.add(vectorPanel, BorderLayout.NORTH);
			contentPane.add(matrixPanel);
			IterationsFrame.this.revalidate();
			IterationsFrame.this.repaint();
		}				
	}
	private class MListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {	
			try {
				m = Integer.parseInt(mTextField.getText());
			}catch(NumberFormatException exception) {
				JOptionPane.showMessageDialog(IterationsFrame.this, "Uncorrect input. Please input integer value.");
				return;
			}
			IterationsFrame.this.setFieldsWithM();
		}				
	}
}
