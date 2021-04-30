package es2sem2021.grupo2.codequalityassessor.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;


public class FinalResults {
	
	static ArrayList<RuleResults> finalresults = new ArrayList<>();
	
	public static ArrayList<RuleResults> getRulesResults(ArrayList<Method> methods){
		finalresults.clear();
		
		HashMap<String, Rule> rulesSet = RulesSet.getRules();
		Set<String> rules = rulesSet.keySet();
		
		for(String s: rules) {
			RuleResults ruleresults = new RuleResults(s);
			for(Method m: methods) {
				Rule rule = rulesSet.get(s);
				Boolean result = rule.assertRule(m);
				ruleresults.methodsresults.add(new RuleMethod(m,result));
			}
			finalresults.add(ruleresults);
		}
		
		return finalresults;
	}
}
