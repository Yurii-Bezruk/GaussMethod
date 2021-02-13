

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JPanel headPanel;	
	private JLabel dimentionLabel;		
	private JTextField dimentionTextField;
	private int dimention = 3;
	
	private JPanel centralPanel;
	private JPanel[] matrixPanels;
	private JTextField[][] matrixCoeffs;
	private JLabel[][] matrixLabels;
	
	private JPanel panelButtons;
	private JButton solve;
	private JButton exit;	
	
	public MainFrame() {
		setResizable(false);
		setTitle("Linear System Gauss Method");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(150, 150, 400, 400);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.SOUTH);
		
		solve = new JButton("Solve");
//		plot.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(parameterTextField.getText().equals(""))
//					f = new AnalyticFunction(functionTextField.getText(), variableTextField.getText());
//				else { 
//					f = new AnalyticFunction(functionTextField.getText(), variableTextField.getText(), parameterTextField.getText());
//					if(!parameterValueTextField.getText().equals("")) {
//						f.setParameterValue(Double.parseDouble(parameterValueTextField.getText()));
//					}
//				}
//				start = Double.parseDouble(startTextField.getText());
//				stop = Double.parseDouble(stopTextField.getText());
//				step = Double.parseDouble(stepTextField.getText());
//				
//				function.clear();
//				deritative.clear();
//				for (double x = start; x < stop; x += step) {
//					function.add(x, f.evalf(x));
//					deritative.add(x, f.deritative().evalf(x));
//				}
//				
//				((XYPlot)chart.getPlot()).getDomainAxis().setRangeAboutValue(0, 10);
//				((XYPlot)chart.getPlot()).getRangeAxis().setRangeAboutValue(0, 10);
//			}
//		});
		panelButtons.add(solve);
		
		exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panelButtons.add(exit);
				
		headPanel = new JPanel();					
		dimentionLabel = new JLabel("Dimention: ");
		headPanel.add(dimentionLabel);
		
		dimentionTextField = new JTextField(String.valueOf(dimention));
		dimentionTextField.setColumns(12);
		dimentionTextField.addKeyListener(new EnterListener());
		headPanel.add(dimentionTextField);	
		
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
		
		for (int i = 0; i < dimention; i++) {
			for (int j = 0; j < dimention + 1; j++) {
				matrixPanels[i].add(matrixCoeffs[i][j]);
				if(j < dimention)
					matrixPanels[i].add(matrixLabels[i][j]);
			}
		}
		for (JPanel panel : matrixPanels) 
			centralPanel.add(panel);
		
		contentPane.add(centralPanel, BorderLayout.CENTER);
		
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
	
	private class EnterListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyPressed(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {
//			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
//				if(parameterTextField.getText().equals(""))
//					f = new AnalyticFunction(functionTextField.getText(), variableTextField.getText());
//				else {
//					f = new AnalyticFunction(functionTextField.getText(), variableTextField.getText(), parameterTextField.getText());
//					if(!parameterValueTextField.getText().equals("")) {
//						f.setParameterValue(Double.parseDouble(parameterValueTextField.getText()));
//					}
//				}
//				start = Double.parseDouble(startTextField.getText());
//				stop = Double.parseDouble(stopTextField.getText());
//				step = Double.parseDouble(stepTextField.getText());
//				
//				function.clear();
//				deritative.clear();
//				for (double x = start; x < stop; x += step) {
//					function.add(x, f.evalf(x));
//					deritative.add(x, f.deritative().evalf(x));
//				}
//			}
		}			
	}

}
