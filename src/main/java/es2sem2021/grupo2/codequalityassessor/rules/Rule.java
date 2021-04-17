package es2sem2021.grupo2.codequalityassessor.rules;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

public class Rule {
	
	private String name, conditions;
	
	public Rule(String name, String conditions) {
		this.name = name;
		this.conditions = conditions;
	}
	
	public boolean assertRule(Method method) {
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) throws IllegalArgumentException {
		if (!RuleParser.validateConditionSyntax(conditions)) throw new IllegalArgumentException();
		this.conditions = conditions;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRuleCode() {
		return "SE (" + conditions + ") ENTAO" + name;
	}
}
