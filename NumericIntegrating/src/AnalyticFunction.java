import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Variable;

public class AnalyticFunction implements Function {
	private Expression fun;
	private Parser parser;
	private Variable var;
	private Variable par;	
	
	public AnalyticFunction() {
		parser = new Parser(Parser.STANDARD_FUNCTIONS);
		var = new Variable("x");
		parser.add(var);
		fun = parser.parse("x");
	}
	/**
	 * Creates new function of variable var
	 * @param expr - new function expression of variable var 
	 * @param var - variable that the function depends on
	 */
	public AnalyticFunction(String expr, String var) throws edu.hws.jcm.data.ParseError {		
		parser = new Parser(Parser.STANDARD_FUNCTIONS);
		this.var = new Variable(var);
		parser.add(this.var);
		fun = parser.parse(expr);
	}
	/**
	 * Creates new function of variable var with parameter par
	 * @param expr - new function expression of variable var 
	 * @param var - variable that the function depends on
	 * @param par - parameter specified in function
	 */
	public AnalyticFunction(String expr, String var, String par) {
		parser = new Parser(Parser.STANDARD_FUNCTIONS);
		this.var = new Variable(var);
		this.par = new Variable(par);
		parser.add(this.var);
		parser.add(this.par);
		fun = parser.parse(expr);
	}
	
	/**
	 * Sets the new function expression of current variable
	 * @param fun - new function expression
	 */
	public void setFunction(String fun) {
		this.fun = parser.parse(fun);
	}
	
	/**
	 * Sets the new function expression of new variable
	 * @param fun - new function expression
	 * @param var - new variable
	 */
	public void setFunction(String fun, String var) {
		parser.remove(this.var.toString());
		this.var = new Variable(var);
		parser.add(this.var);
		this.fun = parser.parse(fun);
	}
	
	/**
	 * Sets the new function expression of new variable with new parameter
	 * @param fun - new function expression
	 * @param var - new variable
	 * @param par - new parameter
	 */
	public void setFunction(String fun, String var, String par) {
		parser.remove(this.var.toString());
		if(this.par != null)
			parser.remove(this.par.toString());
		this.var = new Variable(var);
		this.par = new Variable(par);
		parser.add(this.var);
		parser.add(this.par);
		this.fun = parser.parse(fun);
	}
	
	/**
	 * @return current variable symbol
	 */
	public String getVariable() {
		return var.toString();
	}
	
	/**
	 * @return the Variable object of current variable
	 */
	public Variable getVariableObject() {
		return var;
	}
	/**
	 * sets the new variable of function
	 * @param var - new variable symbol
	 */
	public void setVariable(String var) {
		this.var = new Variable(var);
	}
	
	/**
	 * @return current parameter symbol or null
	 */
	public String getParameter() {
		if(par == null)
			return null;
		return par.toString();
	}
	
	/**
	 * @return the Variable object of current parameter
	 */
	public Variable getParameterObject() {
		return par;
	}
	
	/**
	 * sets the new parameter of function
	 * @param par - new parameter symbol
	 */
	public void setParameter(String par) {
		this.par = new Variable(par);
	}
	
	
	/**
	 * gets current parameter value of function
	 * @return - paramater value 
	 */
	public double getParameterValue() {
		return par.getVal();
	}
	
	/**
	 * sets new parameter value to current parameter
	 * @param param - new value of current parameter
	 */
	public void setParameterValue(double param) {
		par.setVal(param);
	}
	
	/**
	 * turn the variable to the parameter and parameter to variable
	 */
	public void swapVariableAndParameter(){
		String p = getParameter();
		String v = getVariable();
		parser.remove(p);
		parser.remove(v);
		var = new Variable(p);
		par = new Variable(v);			
		parser.add(var);
		parser.add(par);
		fun = parser.parse(fun.toString());
	}
	
	@Override
	public String toString() {
		return fun.toString();
	}
	@Override
	public double f(double x) {
		var.setVal(x);
		return fun.getVal();
	}
	public AnalyticFunction deritative() {
		if(par == null) return new AnalyticFunction(fun.derivative(var).toString(), var.toString());
		else return new AnalyticFunction(fun.derivative(var).toString(), var.toString(), par.toString());
	}
}
