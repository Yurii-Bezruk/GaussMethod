import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 2269971701250845501L;
	private JPanel contentPane;
	private Function function;
	private JTextField functionTextField;
	private JTextField squareTextField;
	private JTextField trapezeTextField;
	private JTextField simpsonTextField;
	private JButton solveButton;
	
	public MainFrame() {
		setTitle("Integral");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(150, 150, 400, 206);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 12));
		setContentPane(contentPane);
		
		function = new ExampleFunction();
		functionTextField = new JTextField(function.toString(), 30);
		functionTextField.setHorizontalAlignment(JTextField.CENTER);
		contentPane.add(functionTextField);
		
		contentPane.add(new JLabel("squares method: "));
		squareTextField = new JTextField(20);
		contentPane.add(squareTextField); 
		
		contentPane.add(new JLabel("trapeze method: "));
		trapezeTextField = new JTextField(20);
		contentPane.add(trapezeTextField);
		
		contentPane.add(new JLabel("simpson method: "));
		simpsonTextField = new JTextField(20);
		contentPane.add(simpsonTextField);	
		
		solveButton = new JButton("Solve");
		solveButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integral I = new Integral(function);
				squareTextField.setText(Double.toString(I.solveBySquaresMethod(1.6, 2.4)));
				trapezeTextField.setText(Double.toString(I.solveByTrapezeMethod(1.6, 2.4)));
				simpsonTextField.setText(Double.toString(I.solveBySimpsonMethod(1.6, 2.4)));
			}
		});
		contentPane.add(solveButton);
	}

}
