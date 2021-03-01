import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VectorPanel extends JPanel {
	private static final long serialVersionUID = -6496041102969831227L;
	private int count = 4;
	private JTextField[] vectorCoeffs;
	private JLabel[] vectorLabels;
	
	public VectorPanel(int initialCount) {
		super(new FlowLayout());
		count = initialCount;
		vectorCoeffs = new JTextField[count];
		vectorLabels = new JLabel[count];
		this.add(new JLabel("initial conditions: "));
		for (int i = 0; i < count; i++) {
			vectorLabels[i] = new JLabel("x"+(i+1));
			vectorCoeffs[i] = new JTextField("");
			vectorCoeffs[i].setColumns(3);
			this.add(vectorCoeffs[i]);
			this.add(vectorLabels[i]);
		}
	}
	public void setDefaultState(int m) {
		try {
			vectorCoeffs[0].setText((0.7*m)+"");
			vectorCoeffs[1].setText("1");
			vectorCoeffs[2].setText("2");
			vectorCoeffs[3].setText("0.5");
		}catch(ArrayIndexOutOfBoundsException e) {}
	}
	public void setValueAt(int index, double value){
		vectorCoeffs[index].setText(String.valueOf(value));
	}
	public double getValueAt(int index) throws NumberFormatException {
		return Double.parseDouble(vectorCoeffs[index].getText());
	}
}
