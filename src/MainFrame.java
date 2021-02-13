import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private int dimention = 3;
	private JPanel headPanel;	
	private JLabel dimentionLabel;		
	private JTextField dimentionTextField;	
	private JLabel resultLabel;
	private JTextField resultTextField;
	
	private JPanel centralPanel;
	private JPanel[] matrixPanels;
	private JTextField[][] matrixCoeffs;
	private JLabel[][] matrixLabels;	
	
	private JPanel panelButtons;
	private JButton solve;
	private JButton exit;	
	
	public MainFrame() {
		//setResizable(false);
		setTitle("Linear System Gauss Method");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(150, 150, 400, 400);		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
				
		headPanel = new JPanel();					
		dimentionLabel = new JLabel("Dimention: ");
		headPanel.add(dimentionLabel);
		
		dimentionTextField = new JTextField(String.valueOf(dimention));
		dimentionTextField.setColumns(2);
		dimentionTextField.addActionListener(new DimentionListener());
		headPanel.add(dimentionTextField);	
		
		resultLabel = new JLabel("Result: ");
		headPanel.add(resultLabel);		
		resultTextField = new JTextField("");
		resultTextField.setColumns(20);
		headPanel.add(resultTextField);
		contentPane.add(headPanel, BorderLayout.NORTH);
		
		centralPanel = new JPanel(new FlowLayout());
		initializeMatrixForm();
		
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
		dimention = Integer.parseInt(dimentionTextField.getText());
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
		public void actionPerformed(ActionEvent e) {
			Row[] rows = new Row[dimention];
			double[] row = new double[dimention + 1];
			for(int i = 0; i < dimention; i++) {
				for (int j = 0; j < dimention + 1; j++) {
					row[j] = Double.parseDouble(matrixCoeffs[i][j].getText());
				}
				rows[i] = new Row(Arrays.copyOf(row, row.length));
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
			initializeMatrixForm();
			establishMatrixForm();
			
			revalidate();
			repaint();
		}				
	}

}
