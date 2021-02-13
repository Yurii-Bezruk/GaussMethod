
public class Main {
	public static void main(String[] args) {
		/*java.util.Scanner scanner = new java.util.Scanner(System.in);
		for(int i = 0; i < equation.length; i++) {
			System.out.printf("Equation %d koefs:", i+1);
			for(int j = 0; j < equation[i].length; j++) {
				equation[i][j] = scanner.nextDouble();
			} 			
			
		}*/
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
//		Matrix system = new Matrix(new Row[]{
//			new Row(new double[]{1.21, 4.05, 2.11, 4.25}),
//			new Row(new double[]{0.75, 1.21, 3.21, 7.42}),
//			new Row(new double[]{2.27, 5.66, 3.06, 10.5})
//		});		
//		system.print();
//		if(system.determinant() == 0) {
//			System.err.println("Zero determinant! system cannot be solved.");
//			return;
//		}
//		double[] roots = system.solve();
//		system.print();	
//		System.out.print("The roots is: (");
//		for (double root : roots) 
//			System.out.printf("%.2f; ", root);
//		System.out.println(")");
	}
}
