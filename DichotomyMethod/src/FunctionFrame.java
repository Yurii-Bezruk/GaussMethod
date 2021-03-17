import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

public class FunctionFrame extends JFrame {
		private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		
		private JPanel headPanel;
		private JLabel functionLabel;
		private JTextField functionTextField;
		
		private XYSeries functionValues;
		private Equation equation;
		
		private JPanel panelButtons;
		private JButton plot;
		private JButton exit;
		private JLabel startLabel; 
		private JLabel stopLabel; 
		private JLabel stepLabel; 
		private JTextField startTextField; 
		private JTextField stopTextField; 
		private JTextField stepTextField; 
		
		private JFreeChart chart;
		
		
		public FunctionFrame() {
			setResizable(false);
			setTitle("Function Frame");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setBounds(150, 150, 550, 550);
			
			contentPane = new JPanel(new BorderLayout());
			setContentPane(contentPane);
			
			JFreeChart chart = createChart();
			ChartPanel chartPanel = new ChartPanel(chart);
			contentPane.add(chartPanel, BorderLayout.CENTER);	
			
			panelButtons = new JPanel();
			contentPane.add(panelButtons, BorderLayout.SOUTH);
			
			plot = new JButton("Plot");
			plot.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					equation.setStart(startTextField.getText());
					equation.setStop(stopTextField.getText());
					equation.setStep(stepTextField.getText());
					java.math.BigDecimal[][] values = equation.getValuesDiapazone();
					functionValues.clear();
					for (java.math.BigDecimal[] value : values) {
						functionValues.add(value[0].doubleValue(), value[1].doubleValue());
					}
					
					((XYPlot)chart.getPlot()).getDomainAxis().setRangeAboutValue(0, 10);
					((XYPlot)chart.getPlot()).getRangeAxis().setRangeAboutValue(7, 8);
				}
			});
			panelButtons.add(plot);
			
			exit = new JButton("Exit");
			exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			panelButtons.add(exit);
			
			startLabel = new JLabel("start:");
			panelButtons.add(startLabel);
			startTextField = new JTextField(Double.toString(equation.getStart())); 
			startTextField.setColumns(5);
			//startTextField.addKeyListener(new EnterListener());
			panelButtons.add(startTextField);
			
			stopLabel = new JLabel("stop:");
			panelButtons.add(stopLabel);
			stopTextField = new JTextField(Double.toString(equation.getStop())); 
			stopTextField.setColumns(5);
			//stopTextField.addKeyListener(new EnterListener());
			panelButtons.add(stopTextField);
			
			stepLabel = new JLabel("step:");
			panelButtons.add(stepLabel);
			stepTextField = new JTextField(Double.toString(equation.getStep())); 
			stepTextField.setColumns(5);
			//stepTextField.addKeyListener(new EnterListener());
			panelButtons.add(stepTextField);
			
			headPanel = new JPanel();
						
			functionLabel = new JLabel("Function: ");
			headPanel.add(functionLabel);
			functionTextField = new JTextField("");
			functionTextField.setColumns(12);
			//functionTextField.addKeyListener(new EnterListener());
			headPanel.add(functionTextField);
			
			
			contentPane.add(headPanel, BorderLayout.NORTH);	
		}
				
		private JFreeChart createChart() {
			functionValues = new XYSeries("Function");				
			equation = new OrderEquation();		
			java.math.BigDecimal[][] values = equation.getValuesDiapazone();
			for (java.math.BigDecimal[] value : values) {
				functionValues.add(value[0].doubleValue(), value[1].doubleValue());
			}
			
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(functionValues);
			
			chart = ChartFactory.createXYLineChart("", //chart title
					"X", //x axis label
					"Y", //y axis label
					dataset, //data
					PlotOrientation.VERTICAL, true, //include legend
					true, //tooltips
					false //urls
			);
			//CUSTOMIZATION
			chart.setBackgroundPaint(Color.white);
			
			XYPlot plot = (XYPlot) chart.getPlot();
			plot.setBackgroundPaint(Color.lightGray);
			plot.setAxisOffset(new RectangleInsets(1.0, 1.0, 1.0, 1.0));
			plot.setDomainGridlinePaint(Color.white);
			plot.getDomainAxis().setRangeAboutValue(0, 10); //x
			plot.getRangeAxis().setRangeAboutValue(5, 12); //y
			return chart;		
		}
		/*
		private class EnterListener implements KeyListener {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(parameterTextField.getText().equals(""))
						f = new AnalyticFunction(functionTextField.getText(), variableTextField.getText());
					else {
						f = new AnalyticFunction(functionTextField.getText(), variableTextField.getText(), parameterTextField.getText());
						if(!parameterValueTextField.getText().equals("")) {
							f.setParameterValue(Double.parseDouble(parameterValueTextField.getText()));
						}
					}
					start = Double.parseDouble(startTextField.getText());
					stop = Double.parseDouble(stopTextField.getText());
					step = Double.parseDouble(stepTextField.getText());
					
					function.clear();
					deritative.clear();
					for (double x = start; x < stop; x += step) {
						function.add(x, f.evalf(x));
						deritative.add(x, f.deritative().evalf(x));
					}
				}
			}			
		}
*/
}
