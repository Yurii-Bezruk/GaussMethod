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
	
	private JPanel secondHeadPanel;
	private JPanel mAndPrecisionPanel;
	private JTextField mTextField;
	private JTextField precisionTextField;
	private InitialConditionsVectorPanel vectorPanel;
	
	public IterationsFrame() {
		super();
		setTitle("Linear System Simple Iterations Method");
		dimension = 4;
		dimensionTextField.setText("4");
		
		secondHeadPanel = new JPanel(new BorderLayout());
		mAndPrecisionPanel = new JPanel(new FlowLayout());
		mAndPrecisionPanel.add(new JLabel("m: "));
		
		mTextField = new JTextField(String.valueOf(m));
		mTextField.setColumns(2);
		mTextField.addActionListener(new MListener());
		mAndPrecisionPanel.add(mTextField);
		
		mAndPrecisionPanel.add(new JLabel("precision: "));
		precisionTextField = new JTextField("0.005");
		precisionTextField.setColumns(4);
		mAndPrecisionPanel.add(precisionTextField);
		secondHeadPanel.add(mAndPrecisionPanel, BorderLayout.NORTH);
		
		vectorPanel = new InitialConditionsVectorPanel();
		secondHeadPanel.add(vectorPanel, BorderLayout.SOUTH);
		
		contentPane.remove(matrixPanel);
		matrixPanel = new MatrixPanel(dimension);
		matrixPanel.setLab2DefaultState();
		matrixPanel.add(secondHeadPanel, BorderLayout.NORTH);
		contentPane.add(matrixPanel);
		
		solve.removeActionListener(solve.getActionListeners()[0]);
		solve.addActionListener(new SolveByIterationsButtonListener());
		setFieldsWithM();
	}
	private void setFieldsWithM() {
		try {
			matrixPanel.setValueAt(0, dimension, 3 * m);
			matrixPanel.setValueAt(1, dimension, m - 6);
			matrixPanel.setValueAt(2, dimension, 15 - m);
			matrixPanel.setValueAt(3, dimension, m + 2);
		}catch(java.lang.ArrayIndexOutOfBoundsException e) {	
		}
	}
	private class SolveByIterationsButtonListener implements ActionListener{
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
				JOptionPane.showMessageDialog(IterationsFrame.this,"The method does not converge for this matrix.");
			}
			else {
				Row roots = system.solveByGauss();
				resultTextField.setText("(");
				for (int i = 0; i < roots.size(); i++) 
					resultTextField.setText(resultTextField.getText() + String.format("%.2f; ", roots.elem(i)));
				resultTextField.setText(resultTextField.getText() + ")");
			}
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
