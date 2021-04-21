import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

public class InterpolationFrame extends JFrame {
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
		private JFreeChart chart;
		private ChartPanel chartPanel;
		
		private double start = -7;
		private double stop = 3;
		private double step = 1;
		
		
		private JPanel panelButtons;
		private JButton plotButton;
		private JLabel startLabel; 
		private JLabel stopLabel; 
		private JLabel stepLabel; 
		private JTextField startTextField; 
		private JTextField stopTextField; 
		private JTextField stepTextField; 
		
		
		public InterpolationFrame() {
			//setResizable(false);
			setTitle("Interpolation Frame");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setBounds(150, 150, 600, 600);
			
			contentPane = new JPanel(new BorderLayout());
			setContentPane(contentPane);
			
			headPanel = new JPanel(new BorderLayout());
			
			northHeadPanel = new JPanel(new FlowLayout());
			northHeadPanel.add(new JLabel("Order: "));
			orderTextField = new JTextField("5", 2);
			orderTextField.addActionListener(new OrderListener());			
			northHeadPanel.add(orderTextField);
			headPanel.add(northHeadPanel, BorderLayout.NORTH);
			
			centerHeadPanel = new JPanel(new BorderLayout());
			
			xPanel = new VectorPanel("x: ", 5);
			xPanel.setValues(new double[] {-4, -3, -2, -1, 0});
			centerHeadPanel.add(xPanel, BorderLayout.NORTH);
			
			yPanel = new VectorPanel("y: ", 5);
			yPanel.setValues(new double[] {-2, 0, 1, -1, -3});
			centerHeadPanel.add(yPanel, BorderLayout.CENTER);		
			
			LnPanel = new VectorPanel("L: ", 5);
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
			startTextField = new JTextField(Double.toString(start), 3); 
			startTextField.addActionListener(new PlotChanger());
			panelButtons.add(startTextField);
			
			stopLabel = new JLabel("stop:");
			panelButtons.add(stopLabel);
			stopTextField = new JTextField(Double.toString(stop), 3); 
			stopTextField.addActionListener(new PlotChanger());
			panelButtons.add(stopTextField);
			
			stepLabel = new JLabel("step:");
			panelButtons.add(stepLabel);
			stepTextField = new JTextField(Double.toString(step), 3); 
			stepTextField.addActionListener(new PlotChanger());
			panelButtons.add(stepTextField);
			
			//JFreeChart chart = createChart();
			createChart(xPanel.getValues(), yPanel.getValues(), Color.RED);
			chartPanel = new ChartPanel(chart);
			contentPane.add(chartPanel, BorderLayout.CENTER);			
		}
				
		private void createChart(double[] x, double[] y, Color color) {
			functionValues = new XYSeries("Points");
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
			ChartUtilities.applyCurrentTheme(chart);
			
			XYPlot plot = (XYPlot) chart.getPlot();
			plot.setRangeZeroBaselineVisible(true);
			plot.setDomainZeroBaselineVisible(true);
			plot.setBackgroundPaint(Color.lightGray);
			plot.getRenderer().setSeriesPaint(0, color);
			plot.setAxisOffset(new RectangleInsets(1.0, 1.0, 1.0, 1.0));
			plot.setDomainGridlinePaint(Color.white);
			double middle = (start + stop) / 2;
			plot.getDomainAxis().setRangeAboutValue(middle, (stop - middle) * 2); //x
			plot.getRangeAxis().setRangeAboutValue(0, 10); //y				
		}
		private class OrderListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int order;
				try {
					order = Integer.parseInt(orderTextField.getText());
					if(order <= 0) {
						JOptionPane.showMessageDialog(InterpolationFrame.this, "Please, input number > 0");
						return;
					}
				} catch(NumberFormatException exception) {
					JOptionPane.showMessageDialog(InterpolationFrame.this, "Please, input number");
					return;
				}
				centerHeadPanel.remove(xPanel);
				centerHeadPanel.remove(yPanel);
				centerHeadPanel.remove(LnPanel);
				
				double[] xValues = xPanel.getValues();
				xPanel = new VectorPanel("x: ", order);
				xPanel.setValues(xValues);
				centerHeadPanel.add(xPanel, BorderLayout.NORTH);
				
				double[] yValues = yPanel.getValues();
				yPanel = new VectorPanel("y: ", order);
				yPanel.setValues(yValues);
				centerHeadPanel.add(yPanel, BorderLayout.CENTER);
				
				LnPanel = new VectorPanel("L: ", order);
				centerHeadPanel.add(LnPanel, BorderLayout.SOUTH);
				
				createChart(xPanel.getValues(), yPanel.getValues(), Color.RED);
				contentPane.remove(chartPanel);
				chartPanel = new ChartPanel(chart);
				contentPane.add(chartPanel, BorderLayout.CENTER);
				InterpolationFrame.this.revalidate();
				InterpolationFrame.this.repaint();
			}
			
		}
		private class PlotChanger implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					start = Double.parseDouble(startTextField.getText());
					stop = Double.parseDouble(stopTextField.getText());
					step = Double.parseDouble(stepTextField.getText());
				} catch(NumberFormatException exception) {
					JOptionPane.showMessageDialog(InterpolationFrame.this, "Please, input numbers");
					return;
				}
				functionValues = new XYSeries("Function");				
				double[] x = xPanel.getValues();
				double[] y = yPanel.getValues();
				for (int i = 0; i < x.length; i++) {
					functionValues.add(x[i], y[i]);
				}
				double middle = (start + stop) / 2;
				try {
					XYPlot plot = (XYPlot) chart.getPlot();
					plot.getDomainAxis().setRangeAboutValue(middle, (stop - middle) * 2); //x
					plot.getRangeAxis().setRangeAboutValue(0, 10);
				} catch(IllegalArgumentException exception) {
					JOptionPane.showMessageDialog(InterpolationFrame.this, "Uncorrect limits.");
					return;
				}
			}
			
		}
		
		private class SolveButtonListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				double[] x = new double[Integer.parseInt(orderTextField.getText())];
				double[] y = new double[x.length];
				for (int i = 0; i < x.length; i++) {
					try {
						x[i] = xPanel.getValueAt(i); 
						y[i] = yPanel.getValueAt(i);
					}catch(NumberFormatException e) {
						JOptionPane.showMessageDialog(InterpolationFrame.this, "Please, input numbers.");
						return;
					}
				}
				LagrangePolinom lagrange = new LagrangePolinom(x, y);
				double[] Ln = new double[x.length];
				for (int i = 0; i < x.length; i++) {
					Ln[i] = lagrange.Ln(x[i]);
				}
				LnPanel.setValues(Ln);
				
				createChart(x, y, Color.BLUE);
				contentPane.remove(chartPanel);
				chartPanel = new ChartPanel(chart);
				contentPane.add(chartPanel, BorderLayout.CENTER);
				InterpolationFrame.this.revalidate();
				InterpolationFrame.this.repaint();
			}				
		}

}
