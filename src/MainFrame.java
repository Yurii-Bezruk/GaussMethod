import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private int dimension = 3;
	private JPanel headPanel;		
	private JTextField dimensionTextField;
	private JTextField resultTextField;
	
	private MatrixPanel matrixPanel;
		
	
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
		headPanel.add(new JLabel("dimension: "));		
		dimensionTextField = new JTextField(String.valueOf(dimension));
		dimensionTextField.setColumns(2);
		dimensionTextField.addActionListener(new DimensionListener());
		headPanel.add(dimensionTextField);	
		
		headPanel.add(new JLabel("Result: "));		
		resultTextField = new JTextField("");
		resultTextField.setColumns(20);
		headPanel.add(resultTextField);
		contentPane.add(headPanel, BorderLayout.NORTH);
		
		dimension = Integer.parseInt(dimensionTextField.getText());
		matrixPanel = new MatrixPanel(dimension);		
		matrixPanel.setDefaultState();	
		contentPane.add(matrixPanel, BorderLayout.CENTER);
		
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
	private class SolveButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			Row[] rows = new Row[dimension];
			double[] row = new double[dimension + 1];
			for(int i = 0; i < dimension; i++) {
				for (int j = 0; j < dimension + 1; j++) {
					try {
					row[j] = matrixPanel.getValueOn(i, j);
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
	private class DimensionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {			
			contentPane.remove(matrixPanel);
			try {
				dimension = Integer.parseInt(dimensionTextField.getText());
			}catch(NumberFormatException exception) {
				JOptionPane.showMessageDialog(MainFrame.this, "Uncorrect input. Please input integer value.");
				return;
			}
			matrixPanel = new MatrixPanel(dimension);
			contentPane.add(matrixPanel);
			MainFrame.this.revalidate();
			MainFrame.this.repaint();
		}				
	}

}
