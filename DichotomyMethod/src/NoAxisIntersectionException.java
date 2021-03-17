
public class NoAxisIntersectionException extends RuntimeException {
	private static final long serialVersionUID = -2665101544427083876L;
	private String diapazone;
	public NoAxisIntersectionException(String diapazone) {
		super();
		this.diapazone = diapazone;
	}
	@Override
	public void printStackTrace() {
		System.err.println("No insersections of X axis in this diapazone: "+diapazone);
		super.printStackTrace();
	}
}
