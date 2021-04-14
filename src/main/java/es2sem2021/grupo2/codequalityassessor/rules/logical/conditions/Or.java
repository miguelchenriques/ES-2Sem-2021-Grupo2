package es2sem2021.grupo2.codequalityassessor.rules.logical.conditions;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

public class Or implements LogicalCondition {

	private LogicalCondition left, right;
	
	public Or(LogicalCondition left, LogicalCondition rigth) {
		super();
		this.left = left;
		this.right = rigth;
	}

	@Override
	public boolean assertCondition(Method method) {
		return left.assertCondition(method) || right.assertCondition(method);
	}
	
}
