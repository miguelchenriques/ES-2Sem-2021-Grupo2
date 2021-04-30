package es2sem2021.grupo2.codequalityassessor.rules;

import java.util.ArrayList;

public class RuleResults {

	public String rule;
	public ArrayList<RuleMethod> methodsresults;
	
	RuleResults(String rule){
		this.rule=rule;
		methodsresults = new ArrayList<RuleMethod>();
	}

	public String getRule() {
		return rule;
	}

	public ArrayList<RuleMethod> getMethodsresults() {
		return methodsresults;
	}
	
}
