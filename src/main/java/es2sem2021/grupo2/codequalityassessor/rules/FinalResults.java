package es2sem2021.grupo2.codequalityassessor.rules;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;


public class FinalResults {
	
	static ArrayList<RuleResults> finalresults = new ArrayList<>();
	
	public static ArrayList<RuleResults> getRulesResults(List<Method> methods){
		finalresults.clear();
		
		HashMap<String, Rule> rulesSet = CodeSmells.getCodeSmells();
		HashMap<String, Rule> codeSmellsSet = CodeSmells.getCodeSmells();
		Set<String> codesmells = codeSmellsSet.keySet();
		
		for(String s: codesmells) {
			Rule r = codeSmellsSet.get(s);
			RuleResults ruleresults = new RuleResults(s,r.getName());
			for(Method m: methods) {
				Rule rule = rulesSet.get(r.getName());
				Boolean result = rule.assertRule(m);
				ruleresults.methodsresults.add(new RuleMethod(m,result));			
			}
			finalresults.add(ruleresults);
		}
		
		return finalresults;
	}
}
