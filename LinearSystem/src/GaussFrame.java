import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GaussFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	
	protected int dimension = 3;
	protected JPanel headPanel;		
	protected JTextField dimensionTextField;
	protected JTextField resultTextField;
	
	protected MatrixPanel matrixPanel;	
	
	protected JPanel panelButtons;
	protected JButton solve;
	protected JButton exit;	
	
	public GaussFrame() {
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
		matrixPanel.setLab1DefaultState();	
		contentPane.add(matrixPanel, BorderLayout.CENTER);
		
		panelButtons = new JPanel();
		
		solve = new JButton("Solve");
		solve.addActionListener(new SolveByGaussButtonListener());
		panelButtons.add(solve);
		
		exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panelButtons.add(exit);
		contentPane.add(panelButtons, BorderLayout.SOUTH);
	}
	private class SolveByGaussButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			Row[] rows = new Row[dimension];
			double[] row = new double[dimension + 1];
			for(int i = 0; i < dimension; i++) {
				for (int j = 0; j < dimension + 1; j++) {
					try {
						row[j] = matrixPanel.getValueAt(i, j);
					}catch(NumberFormatException exception) {
						JOptionPane.showMessageDialog(GaussFrame.this, "Uncorrect input. Please input numbers.");
						return;
					}
				}
				rows[i] = new Row(row);
			}	
			
			Matrix system = new Matrix(rows);		
			if(system.determinant().doubleValue() == 0) {
				JOptionPane.showMessageDialog(GaussFrame.this, "Zero determinant! system cannot be solved.");
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
	private class DimensionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {			
			contentPane.remove(matrixPanel);		
			try {
				dimension = Integer.parseInt(dimensionTextField.getText());
				if(dimension <= 0) {
					JOptionPane.showMessageDialog(GaussFrame.this, "Uncorrect input. Please input integer value > 0.");
					return;
				}
			}catch(NumberFormatException exception) {
				JOptionPane.showMessageDialog(GaussFrame.this, "Uncorrect input. Please input integer value > 0.");
				return;
			}
			matrixPanel = new MatrixPanel(dimension);
			contentPane.add(matrixPanel);
			GaussFrame.this.revalidate();
			GaussFrame.this.repaint();
		}				
	}

}
