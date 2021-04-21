
public class Main {
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ApproximationFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		/*double[] X = {-2, -1, 0, 1, 2};
		double[] Y = {-4.8, 0, 3.2, 4, 2.8};
		ApproximationPolinom polinom = new FirstOrderPolinom(X, Y);
		for (int i = -5; i < 3; i++) {
			System.out.println(i + " "  + polinom.P(i));
		}
		System.out.println(polinom.getRootMeanSquareError());
		System.out.println(polinom+"\n");
		
		polinom = new SecondOrderPolinom(X, Y);
		for (int i = -5; i < 3; i++) {
			System.out.println(i + " "  + polinom.P(i));
		}
		System.out.println(polinom.getRootMeanSquareError());
		System.out.println(polinom+"\n");*/
	}
}
