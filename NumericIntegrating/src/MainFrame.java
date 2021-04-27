import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 2269971701250845501L;
	private JPanel contentPane;
	private AnalyticFunction function;
	private double start = 1.6;
	private double finish = 2.4;
	private JTextField startTextField;
	private JTextField finishTextField;
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
		
		startTextField = new JTextField(Double.toString(start), 4);
		contentPane.add(startTextField);
		finishTextField = new JTextField(Double.toString(finish), 4);
		contentPane.add(finishTextField);
		function = new AnalyticFunction(new ExampleFunction().toString(), "x");
		functionTextField = new JTextField(function.toString(), 22);
		functionTextField.setHorizontalAlignment(JTextField.CENTER);
		functionTextField.addKeyListener(new KeyListener() {			
			@Override
			public void keyTyped(KeyEvent e) {}			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					MainFrame.this.solve();
				}
			}			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
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
			public void actionPerformed(ActionEvent event) {
				MainFrame.this.solve();
			}
		});
		contentPane.add(solveButton);
	}
	
	private void solve() {
		try {
			function = new AnalyticFunction(functionTextField.getText(), "x");
		} catch (edu.hws.jcm.data.ParseError e) {
			JOptionPane.showMessageDialog(MainFrame.this, "Uncorrect function expression");
			return;
		}
		Integral I = new Integral(function);
		try {
			start = Double.parseDouble(startTextField.getText());
			finish = Double.parseDouble(finishTextField.getText());
			if(finish < start) {
				JOptionPane.showMessageDialog(MainFrame.this, "Uncorrect limits, finish < start");
				return;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(MainFrame.this, "Uncorrect limits");
			return;
		}
		squareTextField.setText(Double.toString(I.solveBySquaresMethod(start, finish)));
		trapezeTextField.setText(Double.toString(I.solveByTrapezeMethod(start, finish)));
		simpsonTextField.setText(Double.toString(I.solveBySimpsonMethod(start, finish)));
	}
}
