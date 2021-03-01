

public class Main {
	public static void main(String[] args) {		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new IterationsFrame().setVisible(true);
					//new GaussFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
}
