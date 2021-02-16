import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private int dimention = 3;
	private JPanel headPanel;		
	private JTextField dimentionTextField;
	private JTextField resultTextField;
	
	private JPanel centralPanel;
	private JPanel[] matrixPanels;
	private JTextField[][] matrixCoeffs;
	private JLabel[][] matrixLabels;	
	
	private JPanel panelButtons;
	private JButton solve;
	private JButton exit;	
	
	public MainFrame() {
		setTitle("Linear System Gauss Method");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(150, 150, 400, 400);		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
				
		headPanel = new JPanel();		
		headPanel.add(new JLabel("Dimention: "));		
		dimentionTextField = new JTextField(String.valueOf(dimention));
		dimentionTextField.setColumns(2);
		dimentionTextField.addActionListener(new DimentionListener());
		headPanel.add(dimentionTextField);	
		
		headPanel.add(new JLabel("Result: "));		
		resultTextField = new JTextField("");
		resultTextField.setColumns(20);
		headPanel.add(resultTextField);
		contentPane.add(headPanel, BorderLayout.NORTH);
		
		centralPanel = new JPanel(new FlowLayout());
		dimention = Integer.parseInt(dimentionTextField.getText());
		initializeMatrixForm();
		defaultMatrixForm();		
		establishMatrixForm();		
		contentPane.add(centralPanel, BorderLayout.CENTER);
		
		panelButtons = new JPanel();
		
		solve = new JButton("Solve");
		solve.addActionListener(new SolveButtonListener());
		panelButtons.add(solve);
		
		exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panelButtons.add(exit);
		contentPane.add(panelButtons, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	private void initializeMatrixForm() {		
		matrixCoeffs = new JTextField[dimention][dimention+1];
		matrixLabels = new JLabel[dimention][dimention];
		matrixPanels = new JPanel[dimention];
		
		for (int i = 0; i < dimention; i++) {
			matrixPanels[i] = new JPanel(new FlowLayout());
			for(int j = 0; j < dimention; j++) {
				String str = "x" + (j+1) + (j == dimention-1 ? " = " : " + ");
				matrixLabels[i][j] = new JLabel(str);
			}
		}
	}
	private void defaultMatrixForm() {
		matrixCoeffs[0][0] = new JTextField("1.21");
		matrixCoeffs[0][1] = new JTextField("4.05");
		matrixCoeffs[0][2] = new JTextField("2.11");
		matrixCoeffs[0][3] = new JTextField("4.25");		
		matrixCoeffs[1][0] = new JTextField("0.75");
		matrixCoeffs[1][1] = new JTextField("1.21");
		matrixCoeffs[1][2] = new JTextField("3.21");
		matrixCoeffs[1][3] = new JTextField("7.42");		
		matrixCoeffs[2][0] = new JTextField("2.27");
		matrixCoeffs[2][1] = new JTextField("5.66");
		matrixCoeffs[2][2] = new JTextField("3.06");
		matrixCoeffs[2][3] = new JTextField("10.5");
	}
	private void establishMatrixForm() {
		for (int i = 0; i < dimention; i++) {
			for (int j = 0; j < dimention + 1; j++) {
				if(matrixCoeffs[i][j] == null)
					matrixCoeffs[i][j] = new JTextField("");
				matrixCoeffs[i][j].setColumns(3);
				matrixPanels[i].add(matrixCoeffs[i][j]);
				if(j < dimention)
					matrixPanels[i].add(matrixLabels[i][j]);
			}
		}
		for (JPanel panel : matrixPanels) 
			centralPanel.add(panel);
	}
	private class SolveButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			Row[] rows = new Row[dimention];
			double[] row = new double[dimention + 1];
			for(int i = 0; i < dimention; i++) {
				for (int j = 0; j < dimention + 1; j++) {
					try {
					row[j] = Double.parseDouble(matrixCoeffs[i][j].getText());
					}catch(NumberFormatException exception) {
						JOptionPane.showMessageDialog(MainFrame.this, "Uncorrect input. Please input numbers.");
						return;
					}
				}
				rows[i] = new Row(row);
			}	
			
			Matrix system = new Matrix(rows);		
			if(system.determinant() == 0) {
				resultTextField.setText("Zero determinant! system cannot be solved.");
			}
			else {
				double[] roots = system.solve();
				resultTextField.setText("(");
				for (double root : roots) 
					resultTextField.setText(resultTextField.getText() + String.format("%.2f; ", root));
				resultTextField.setText(resultTextField.getText() + ")");
			}
		}
	}
	private class DimentionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {			
			for (int i = 0; i < dimention; i++) {
				for (int j = 0; j < dimention + 1; j++) {
					matrixPanels[i].remove(matrixCoeffs[i][j]);
					if(j < dimention)
						matrixPanels[i].remove(matrixLabels[i][j]);
				}
			}
			for (JPanel panel : matrixPanels) 
				centralPanel.remove(panel);
			try {
				dimention = Integer.parseInt(dimentionTextField.getText());
			}catch(NumberFormatException exception) {
				JOptionPane.showMessageDialog(MainFrame.this, "Uncorrect input. Please input integer value.");
				return;
			}
			initializeMatrixForm();
			establishMatrixForm();			
			MainFrame.this.revalidate();
			MainFrame.this.repaint();
		}				
	}

}
