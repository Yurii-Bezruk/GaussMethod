
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
		Row row = new Row(1, 2, 3);
		Row row2 = new Row(1, 2, 3, 5, 7);
		Matrix m = new Matrix(new Row(1,23, 0),
							  new Row(3, 4, 6));
	}
}
