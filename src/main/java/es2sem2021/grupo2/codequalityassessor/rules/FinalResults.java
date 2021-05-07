package es2sem2021.grupo2.codequalityassessor.rules;

import java.util.List;
import java.util.HashMap;
import java.util.Set;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;


public class FinalResults {
	
	private static HashMap<String,HashMap<String,Boolean>> finalresults = new HashMap<String,HashMap<String,Boolean>>();
	
	public static HashMap<String,HashMap<String,Boolean>> getRulesResults(List<Method> methods){
		finalresults.clear();

		HashMap<String, Rule> codeSmellsSet = CodeSmells.getCodeSmells();
		Set<String> codesmells = codeSmellsSet.keySet();
		
		for(String s: codesmells) {
			Rule r = codeSmellsSet.get(s);
			HashMap<String,Boolean> results = new HashMap<String,Boolean>();
			if(r != null && methods!= null) {
				for(Method m: methods) {
					Boolean result = r.assertRule(m);
					results.put(m.m_class+"."+m.m_method, result);
				}
			}
			finalresults.put(s,results);
		}
		
		return finalresults;
	}
}
