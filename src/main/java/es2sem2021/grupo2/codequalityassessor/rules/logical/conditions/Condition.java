package es2sem2021.grupo2.codequalityassessor.rules.logical.conditions;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

public class Condition implements LogicalCondition {
	private String metricName;
	private Operand operand;
	private int value;
	
	public Condition(String metricName, Operand operand, int value) {
		this.metricName = metricName;
		this.operand = operand;
		this.value = value;
	}
	
	/**
	 * Asserts if the passed method is valid for the condition.
	 * Ex: If the condition is nom GREATER 10 and the passed method nom is 5 then it will be false,
	 * but if the value is 11 then it will be true.
	 * 
	 * @param method	the method to assert if it passes the condition
	 * @return			assertion result
	 */
	@Override
	public boolean assertCondition(Method method) {
		//TODO: Verificar se passa a condição
		return true;
	}
	
	/**
	 * Represents the comparator operands.  <br>
	 * GREATER -> >  <br>
	 * GREATES_OR_EQUALS -> >=  <br>
	 * SMALLER -> <  <br>
	 * SMALLER_OR_EQUALS -> <=  <br>
	 * EQUAL -> =  <br>
	 * 
	 * 
	 * @author Miguel
	 */
	public enum Operand {
		GREATER,
		GREATER_OR_EQUALS,
		SMALLER,
		SMALLER_OR_EQUALS,
		EQUAL
	}
	
}
