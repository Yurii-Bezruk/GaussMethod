

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
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
	
	
	private JLabel variableLabel;
	private JLabel parameterLabel;
	private JLabel parameterValueLabel;
	
	private JTextField dimentionTextField;
	
	
	private JTextField variableTextField;
	private JTextField parameterTextField;
	private JTextField parameterValueTextField;
	
	
	private JPanel panelButtons;
	private JButton solve;
	private JButton exit;
	
	
	
	
	
	public MainFrame() {
		setResizable(false);
		setTitle("Linear System Gauss Method");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(150, 150, 550, 550);
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
		
		dimentionTextField = new JTextField("3");
		dimentionTextField.setColumns(4);
		dimentionTextField.addKeyListener(new EnterListener());
		headPanel.add(dimentionTextField);		
		
		contentPane.add(headPanel, BorderLayout.NORTH);
		setVisible(true);
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
