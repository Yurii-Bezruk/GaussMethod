import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VectorPanel extends JPanel {
	private static final long serialVersionUID = -6496041102969831227L;
	private int count = 4;
	private JLabel label;
	private JTextField[] vectorCoeffs;
	
	public VectorPanel(String label, int initialCount) {
		super(new FlowLayout());
		this.label = new JLabel(label);
		count = initialCount;
		vectorCoeffs = new JTextField[count];
		this.add(this.label);
		for (int i = 0; i < count; i++) {
			vectorCoeffs[i] = new JTextField("");
			vectorCoeffs[i].setColumns(3);
			this.add(vectorCoeffs[i]);
		}
	}
	public void setValues(double[] values) {
		try {
			for(int i = 0; i < count; i++) {
				vectorCoeffs[i].setText(Double.toString(values[i]));
			}
		}catch(ArrayIndexOutOfBoundsException e) {}
	}
	public void setValueAt(int index, double value){
		vectorCoeffs[index].setText(String.valueOf(value));
	}
	public double getValueAt(int index) throws NumberFormatException {
		return Double.parseDouble(vectorCoeffs[index].getText());
	}
	public double[] getValues() {
		double[] values = new double[count];
		for (int i = 0; i < values.length; i++) {
			try {
				values[i] = Double.parseDouble(vectorCoeffs[i].getText());
			}catch(NumberFormatException e) {}
		}
		return values;
	}
}
