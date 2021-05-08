package es2sem2021.grupo2.codequalityassessor.rules;

import java.util.List;
import java.util.HashMap;
import java.util.Set;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;


public class FinalResults {
	
	private static HashMap<String,HashMap<String,Boolean>> finalresults = new HashMap<String,HashMap<String,Boolean>>();
	
	
	/**
	 * Returns a hashmap with the results of the evaluation of the codesmells
	 *
	 * @param methods  list of methods to evaluate	
	 * @return hashmap with results
	*/
	public static HashMap<String,HashMap<String,Boolean>> getRulesResults(List<Method> methods){
		finalresults.clear();

		HashMap<String, Rule> codeSmellsSet = CodeSmells.getCodeSmells();
		Set<String> codesmells = codeSmellsSet.keySet();
		
		for(String s: codesmells) {
			HashMap<String, Boolean> results = getResultsFromCodeSmell(methods, codeSmellsSet, s);
			finalresults.put(s,results);
		}
		
		return finalresults;
	}

	/**
	 * 
	 * 	Asserts the methods that contain the given code smell
	 * 
	 * @param methods			list of methods to assert code smell
	 * @param codeSmellsSet		set of available code smells and their rules
	 * @param smell				code smell to assert
	 * @return					Hash map containing the methods as keys and their result from the code smell assertion
	 */
	private static HashMap<String, Boolean> getResultsFromCodeSmell(List<Method> methods,
			HashMap<String, Rule> codeSmellsSet, String smell) {
		Rule r = codeSmellsSet.get(smell);
		HashMap<String,Boolean> results = new HashMap<String,Boolean>();
		if(r != null) {
			for(Method m: methods) {
				Boolean result = r.assertRule(m);
				results.put(m.m_class+"."+m.m_method, result);
			}
		}
		return results;
	}
}
