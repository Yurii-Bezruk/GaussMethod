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
	public void setDefaultState() {
		try {
			vectorCoeffs[0].setText("1");
			vectorCoeffs[1].setText("-2.8");
			vectorCoeffs[2].setText("-6.2");
			vectorCoeffs[3].setText("3.7");
		}catch(ArrayIndexOutOfBoundsException e) {}
	}
	public void setValueAt(int index, double value){
		vectorCoeffs[index].setText(String.valueOf(value));
	}
	public double getValueAt(int index) throws NumberFormatException {
		return Double.parseDouble(vectorCoeffs[index].getText());
	}
	public String[] getValues() {
		String[] values = new String[count];
		for (int i = 0; i < values.length; i++) {
			values[i] = vectorCoeffs[i].getText();
		}
		return values;
	}
}
