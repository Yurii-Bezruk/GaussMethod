
public class Main {
	public static void main(String[] args) {		
//		java.awt.EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					new MainFrame();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});	
		int m = 30;
		Matrix system = new Matrix(new Row(5, 1, -1, 1, 3*m),
								   new Row(1, -4, 1, -1, m-6),
								   new Row(-1, 1, 4, 1, 15-m),
								   new Row(1, 2, 1, -5, m+2));
		double epsilon = 0.005;
		Row firstStep = new Row(0.7*m, 1, 2, 0.5);
		if(system.determinant() == 0) {
			System.err.println("Det is 0!");
			return;
		}
		System.out.println(new Row(1, 2, 3).add(new Row(1, 4, 5)));
		//system.solveBySimpleIterations(firstStep);
//		Matrix matr = Matrix.createFromRow(new Row(1, 2));
//		System.out.println(matr.appendRow(new Row(3, 4)).appendColumn(new Row(-1, -6, 4)).rightPart());
	}
}
