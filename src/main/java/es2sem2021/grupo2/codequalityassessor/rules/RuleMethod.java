package es2sem2021.grupo2.codequalityassessor.rules;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

/**
 *
 * 	Object that stores a method and the result when asserted to a given Rule
 *
 */
public class RuleMethod {
	
	public Method method;
	public Boolean result;
	
	/**
	 * 
	 * Constructs a RuleMethod with the given method and the boolean result
	 * 
	 * @param method	
	 * @param result
	 */
	public RuleMethod(Method method, Boolean result){
		this.method=method;
		this.result=result;
	}

	/**
	 * 
	 * @return	method object in the object
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * 
	 * 	Modifies the method in the RuleMethod object
	 * 
	 * @param method
	 */
	public void setMethod(Method method) {
		this.method = method;
	}
	
	
	/**
	 * 
	 * @return result from the assertion against a rule
	 */
	public Boolean getResult() {
		return result;
	}

	/**
	 *  Modifies the result
	 * @param result
	 */
	public void setResult(Boolean result) {
		this.result = result;
	}
	
}
