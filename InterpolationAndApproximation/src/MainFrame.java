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

public class MainFrame extends JFrame {
		private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		
		private JPanel headPanel;
		private JPanel northHeadPanel;
		private JPanel centerHeadPanel;
		private JPanel southHeadPanel;
		private JTextField orderTextField;
		private VectorPanel xPanel;
		private VectorPanel yPanel;
		private VectorPanel LnPanel;
		private JButton solveButton;
		
		private XYSeries functionValues;
		
		private JPanel panelButtons;
		private JButton plotButton;
		private JLabel startLabel; 
		private JLabel stopLabel; 
		private JLabel stepLabel; 
		private JTextField startTextField; 
		private JTextField stopTextField; 
		private JTextField stepTextField; 
		
		private JFreeChart chart;
		
		
		public MainFrame() {
			//setResizable(false);
			setTitle("Interpolation Frame");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setBounds(150, 150, 600, 600);
			
			contentPane = new JPanel(new BorderLayout());
			setContentPane(contentPane);
			
			headPanel = new JPanel(new BorderLayout());
			
			northHeadPanel = new JPanel(new FlowLayout());
			northHeadPanel.add(new JLabel("Order: "));
			orderTextField = new JTextField("3", 2);
			orderTextField.addActionListener(new OrderListener());			
			northHeadPanel.add(orderTextField);
			headPanel.add(northHeadPanel, BorderLayout.NORTH);
			
			centerHeadPanel = new JPanel(new BorderLayout());
			
			xPanel = new VectorPanel("x: ", 4);
			xPanel.setDefaultState();
			centerHeadPanel.add(xPanel, BorderLayout.NORTH);
			
			yPanel = new VectorPanel("y: ", 4);
			yPanel.setDefaultState();
			centerHeadPanel.add(yPanel, BorderLayout.CENTER);		
			
			LnPanel = new VectorPanel("L: ", 4);
			LnPanel.setDefaultState();
			centerHeadPanel.add(LnPanel, BorderLayout.SOUTH);
			
			headPanel.add(centerHeadPanel, BorderLayout.CENTER);
			
			southHeadPanel = new JPanel(new FlowLayout());
			solveButton = new JButton("Solve");
			solveButton.addActionListener(new SolveButtonListener());
			southHeadPanel.add(solveButton);
			headPanel.add(southHeadPanel, BorderLayout.SOUTH);
			
			contentPane.add(headPanel, BorderLayout.NORTH);		
			
			panelButtons = new JPanel();
			contentPane.add(panelButtons, BorderLayout.SOUTH);
			
			plotButton = new JButton("Revalidate plot");
			plotButton.addActionListener(new PlotChanger());
			panelButtons.add(plotButton);
			
			startLabel = new JLabel("start:");
			panelButtons.add(startLabel);
			startTextField = new JTextField(Double.toString(-5), 3); 
			startTextField.addActionListener(new PlotChanger());
			panelButtons.add(startTextField);
			
			stopLabel = new JLabel("stop:");
			panelButtons.add(stopLabel);
			stopTextField = new JTextField(Double.toString(5), 3); 
			stopTextField.addActionListener(new PlotChanger());
			panelButtons.add(stopTextField);
			
			stepLabel = new JLabel("step:");
			panelButtons.add(stepLabel);
			stepTextField = new JTextField(Double.toString(1), 3); 
			stepTextField.addActionListener(new PlotChanger());
			panelButtons.add(stepTextField);
			
			JFreeChart chart = createChart();
			ChartPanel chartPanel = new ChartPanel(chart);
			contentPane.add(chartPanel, BorderLayout.CENTER);
			
		}
				
		private JFreeChart createChart() {
			functionValues = new XYSeries("Function");				
			double[] x = {-4, -3, -2, -1, 0};
			double[] y = {-2, 0, 1, -1, -3};
			for (int i = 0; i < x.length; i++) {
				functionValues.add(x[i], y[i]);
			}
			
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(functionValues);
			
			chart = ChartFactory.createScatterPlot("", //chart title
					"X", //x axis label
					"Y", //y axis label
					dataset, //data
					PlotOrientation.VERTICAL, true, //include legend
					true, //tooltips
					true //urls
			);
			//CUSTOMIZATION
			chart.setBackgroundPaint(Color.white);
			
			XYPlot plot = (XYPlot) chart.getPlot();
			plot.setRangeZeroBaselineVisible(true);
			plot.setDomainZeroBaselineVisible(true);
			plot.setBackgroundPaint(Color.lightGray);
			plot.setAxisOffset(new RectangleInsets(1.0, 1.0, 1.0, 1.0));
			plot.setDomainGridlinePaint(Color.white);
			double middle = (Double.parseDouble(startTextField.getText())+Double.parseDouble(stopTextField.getText())) / 2;
			plot.getDomainAxis().setRangeAboutValue(middle, (Double.parseDouble(stopTextField.getText()) - middle)*2); //x
			plot.getRangeAxis().setRangeAboutValue(0, 10); //y			
			return chart;		
		}
		private class OrderListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*int order;
				try {
					order = Integer.parseInt(orderTextField.getText());					
				} catch(NumberFormatException exception) {
					JOptionPane.showMessageDialog(MainFrame.this, "Please, input integer number");
					return;
				}
				highHeadPanel.remove(equationPanel);
				equationPanel = new EquationPanel(order + 1);
				highHeadPanel.add(equationPanel);
				MainFrame.this.revalidate();
				MainFrame.this.repaint();*/
			}
			
		}
		private class PlotChanger implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*try {
					equation.setStart(startTextField.getText());
					equation.setStop(stopTextField.getText());
					equation.setStep(stepTextField.getText());
				} catch(NumberFormatException exception) {
					JOptionPane.showMessageDialog(MainFrame.this, "Please, input numbers");
					return;
				}
				java.math.BigDecimal[][] values = equation.getValuesDiapazone();
				functionValues.clear();
				for (java.math.BigDecimal[] value : values) {
					functionValues.add(value[0].doubleValue(), value[1].doubleValue());
				}
				double middle = (equation.getStop()+equation.getStart()) / 2;
				try {
					((XYPlot)chart.getPlot()).getDomainAxis().setRangeAboutValue(middle, (equation.getStop() - middle)*2);
					((XYPlot)chart.getPlot()).getRangeAxis().setRangeAboutValue(0, 40);
				} catch(IllegalArgumentException exception) {
					JOptionPane.showMessageDialog(MainFrame.this, "Uncorrect limits.");
					return;
				}*/
			}
			
		}
		
		private class SolveButtonListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*BigDecimal precision = null;
				try {
					equation = new OrderEquation(equationPanel.getValues());
					equation.setStart(startTextField.getText());
					equation.setStop(stopTextField.getText());
					equation.setStep(stepTextField.getText());
					precision = new BigDecimal(precisionTextField.getText());
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Please, input numbers");
					return;
				}
				rootsTextField.setText(Arrays.toString(equation.solve(precision)));*/
			}				
		}

}
