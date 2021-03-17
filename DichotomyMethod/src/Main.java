import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {		
//		java.awt.EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					new FunctionFrame().setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		Equation e = new OrderEquation();
		System.out.println(Arrays.toString(e.solve(new BigDecimal("0.0001"))));
	}
}
