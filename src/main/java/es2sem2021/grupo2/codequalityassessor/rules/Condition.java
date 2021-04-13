package es2sem2021.grupo2.codequalityassessor.rules;

public class Condition {
	private String metricName;
	private Operand operand;
	private int value;
	
	public Condition(String metricName, Operand operand, int value) {
		this.metricName = metricName;
		this.operand = operand;
		this.value = value;
	}
	
	/**
	 * Asserts if the passed value is valid for the condition.
	 * Ex: If the condition is GREATER 10 and the passed value is 5 the it will be false,
	 * but if the passed value is 11 then it will be true.
	 * 
	 * @param value	the value to assert if it passes the condition
	 * @return		assertion result
	 */
	public boolean assertCondition(int value) {
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
