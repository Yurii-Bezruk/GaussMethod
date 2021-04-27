import java.awt.*;

public class Main {
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//new InterpolationFrame().setVisible(true);
					//new ApproximationFrame().setVisible(true);
					new MainFrame().setVisible(true);					
					//System.out.println(new LagrangePolinom(new double[]{-4, -3, -2, -1, 0}, new double[] {-2, 0, 1, -1, -3}).Ln(8));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
