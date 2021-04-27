
public class Main {
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainFrame().setVisible(true);
					//new GaussFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
//		Integral I = new Integral(new FirstVariantFunction());
//		System.out.println(I.solveBySquaresMethod(1.6, 2.4));
//		System.out.println(I.solveByTrapezeMethod(1.6, 2.4));
//		System.out.println(I.solveBySimpsonMethod(1.6, 2.4));
	}
}
