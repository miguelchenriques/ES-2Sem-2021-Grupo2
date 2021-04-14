package es2sem2021.grupo2.codequalityassessor.rules.logical.conditions;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

/**
 * 
 * Represents a logical condition from a rule. It can be a AND, an OR or a condition like metric > value
 *
 */
public interface LogicalCondition {
	
	/**
	 * Asserts if the method passes the condition
	 * 
	 * @param method	method to assert condition
	 * @return			assertion result
	 */
	public boolean assertCondition(Method method);

}
