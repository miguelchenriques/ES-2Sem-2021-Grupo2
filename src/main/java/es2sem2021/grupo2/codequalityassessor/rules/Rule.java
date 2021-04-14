package es2sem2021.grupo2.codequalityassessor.rules;

import es2sem2021.grupo2.codequalityassessor.rules.logical.conditions.LogicalCondition;
import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

public class Rule {
	
	private String name, conditions;
	//Representa a raiz da arvore da regra
	private LogicalCondition root;
	
	
	public Rule(String name, String conditions, LogicalCondition root) {
		this.name = name;
		this.conditions = conditions;
		this.root = root;
	}
	
	public boolean assertRule(Method method) {
		return root.assertCondition(method);
	}
	
	public String getName() {
		return name;
	}
	
	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) throws IllegalArgumentException {
		LogicalCondition newRoot = RuleParser.parseRuleCondition(conditions);
		this.root = newRoot;
		this.conditions = conditions;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRuleCode() {
		return "SE (" + conditions + ") ENTAO" + name;
	}
}
