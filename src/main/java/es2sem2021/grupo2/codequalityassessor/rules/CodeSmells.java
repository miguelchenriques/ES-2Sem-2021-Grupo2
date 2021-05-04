package es2sem2021.grupo2.codequalityassessor.rules;

import java.util.HashMap;

public class CodeSmells {
	static HashMap<String,Rule> codesmells = new HashMap<>();


	public static void importMandatoryCodeSmells() {
		codesmells.put("is_Long_Method", null);
		codesmells.put("is_God_Class", null);
	}


	public static boolean addRuleToCodeSmell(String codeSmell, Rule rule) {
		if(codeSmell.equals("is_Long_Method") && rule.getConditions().contains("Class"))
			return false;
		if(codeSmell.equals("is_God_Class") && rule.getConditions().contains("Method"))
			return false;
		
		codesmells.put(codeSmell, rule);
		return true;
	}
	
	public static boolean deleteRuleToCodeSmell(String codeSmell) {
		if(codeSmell.equals("is_Long_Method") || codeSmell.equals("is_God_Class") || codeSmell == null)
			return false;
		
		codesmells.remove(codeSmell);
		return true;
	}
	
	

	/**
	 * Returns a boolean
	 * If the adding process is successful returns true if not returns false
	 * 
	 * @param name-Rule name (string), conditions-Rule Condition
	 * @return true/false
	 * @throws IllegalArgumentException
	 */
	public static HashMap<String, Rule> getCodeSmells() {
		return codesmells;
	}

}
