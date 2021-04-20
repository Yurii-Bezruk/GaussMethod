
public class Main {

	public static void main(String[] args) {
		double[] x = {-4, -3, -2, -1, 0};
		double[] y = {-2, 0, 1, -1, -3};
		Polinom lagrange = new Polinom(x, y);
		for (int i = 0; i < x.length; i++) {
			System.out.println(x[i]+"  "+y[i]+"  "+lagrange.Ln(x[i]));
		}
	}

}
