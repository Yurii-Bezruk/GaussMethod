import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

public aspect Logging {
	private FileWriter writer;
	
	pointcut resourse() : call(BigDecimal[] solve(BigDecimal));
	pointcut log() : call(BigDecimal Equation.clarificateRoot(BigDecimal, BigDecimal...));
	
	before() : resourse() {
		try {
			writer = new FileWriter("countingProcess.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	before() : log() {
		BigDecimal[] diapazone = (BigDecimal[]) thisJoinPoint.getArgs()[1];
		try {
			writer.write(Arrays.toString(diapazone)+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	after() : resourse() {
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
