import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EquationPanel extends JPanel {
	private static final long serialVersionUID = -6496041102969831227L;
	private int count = 4;
	private JTextField[] vectorCoeffs;
	private JLabel[] vectorLabels;
	
	public EquationPanel(int initialCount) {
		super(new FlowLayout());
		count = initialCount;
		vectorCoeffs = new JTextField[count];
		vectorLabels = new JLabel[count];
		for (int i = 0; i < count; i++) {
			int power = (count-1-i);
			String variable = (power >= 1 ? "x" : "") + (power > 1 ? "^" + power : "") + (power == 0 ? "= 0" : " +");
			vectorLabels[i] = new JLabel(variable);
			vectorCoeffs[i] = new JTextField("");
			vectorCoeffs[i].setColumns(3);
			this.add(vectorCoeffs[i]);
			this.add(vectorLabels[i]);
		}
	}
	public void setDefaultState() {
		try {
			vectorCoeffs[0].setText("1");
			vectorCoeffs[1].setText("-2.8");
			vectorCoeffs[2].setText("-6.2");
			vectorCoeffs[3].setText("3.7");
//			vectorCoeffs[0].setText("2.1");
//			vectorCoeffs[1].setText("1.4");
//			vectorCoeffs[2].setText("-4.3");
//			vectorCoeffs[3].setText("6.1");
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
