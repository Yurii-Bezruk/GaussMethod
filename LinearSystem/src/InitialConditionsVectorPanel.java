import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InitialConditionsVectorPanel extends JPanel {
	private static final long serialVersionUID = -6496041102969831227L;
	private int count = 4;
	private JTextField[] vectorCoeffs;
	private JLabel[] vectorLabels;
	
	public InitialConditionsVectorPanel() {
		super(new FlowLayout());
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
	
	public void setValueAt(int index, double value){
		vectorCoeffs[index].setText(String.valueOf(value));
	}
	public double getValueAt(int index){
		return Double.parseDouble(vectorCoeffs[index].getText());
	}
}
