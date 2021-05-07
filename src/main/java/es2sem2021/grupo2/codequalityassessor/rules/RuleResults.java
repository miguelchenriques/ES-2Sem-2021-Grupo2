package es2sem2021.grupo2.codequalityassessor.rules;

import java.util.ArrayList;

/**
 *	
 *	Object that stores the rule and an array of objects of type RuleMethod that stores the method and
 *	the result when asserted to the rule
 *
 */
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
