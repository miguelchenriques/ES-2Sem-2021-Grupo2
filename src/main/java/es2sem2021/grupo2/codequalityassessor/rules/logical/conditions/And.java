package es2sem2021.grupo2.codequalityassessor.rules.logical.conditions;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

public class And implements LogicalCondition {
	private LogicalCondition left, right;
	
	public And(LogicalCondition left, LogicalCondition right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public boolean assertCondition(Method method) {
		return left.assertCondition(method) && right.assertCondition(method);
	}
}
