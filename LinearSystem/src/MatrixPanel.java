import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MatrixPanel extends JPanel {
	private static final long serialVersionUID = 1556441602606057108L;
	private int dimension;
	private JPanel[] matrixPanels;
	private JTextField[][] matrixCoeffs;
	private JLabel[][] matrixLabels;
	
	public MatrixPanel(int someDimension) {
		super(new FlowLayout());
		this.dimension = someDimension;
		
		matrixCoeffs = new JTextField[dimension][dimension+1];
		matrixLabels = new JLabel[dimension][dimension];
		matrixPanels = new JPanel[dimension];
		
		for (int i = 0; i < dimension; i++) {
			matrixPanels[i] = new JPanel(new FlowLayout());
			for(int j = 0; j < dimension; j++) {
				String str = "x" + (j+1) + (j == dimension-1 ? " = " : " + ");
				matrixLabels[i][j] = new JLabel(str);
			}
		}
		addContent();		
	}
	private void addContent() {
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension + 1; j++) {
				if(matrixCoeffs[i][j] == null)
					matrixCoeffs[i][j] = new JTextField("");
				matrixCoeffs[i][j].setColumns(3);
				matrixPanels[i].add(matrixCoeffs[i][j]);
				if(j < dimension)
					matrixPanels[i].add(matrixLabels[i][j]);
			}
		}
		for (JPanel panel : matrixPanels) 
			this.add(panel);
	}
	private void removeContent() {
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension + 1; j++) {
				matrixPanels[i].remove(matrixCoeffs[i][j]);
				if(j < dimension)
					matrixPanels[i].remove(matrixLabels[i][j]);
			}
		}
		for (JPanel panel : matrixPanels) 
			this.remove(panel);
	}
	
	public double getValueOn(int i, int j) {
		return Double.parseDouble(matrixCoeffs[i][j].getText());
	}
	
	public void setDefaultState() {
		removeContent();
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
		addContent();
	}
}
