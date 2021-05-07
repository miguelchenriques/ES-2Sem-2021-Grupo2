package es2sem2021.grupo2.codequalityassessor.rules;

import java.util.HashMap;

public class CodeSmells {
	private static HashMap<String,Rule> codesmells = new HashMap<>();

	/**
	 * Imports the mandatory codesmells into the hashmap
	 */
	public static void importMandatoryCodeSmells() {
		codesmells.put("is_Long_Method", null);
		codesmells.put("is_God_Class", null);
	}

	/**
	 *Returns a boolean to indicate the result of the method.
	 *
	 * @param codesmell - name of the codesmell, rule - Rule to associate to codesmell
	 * @return boolean (true if succeded / false if failed)
	 */
	public static boolean addRuleToCodeSmell(String codeSmell, Rule rule) {
		if(codeSmell.equals("is_Long_Method") && rule.getConditions().contains("Class"))
			return false;
		if(codeSmell.equals("is_God_Class") && rule.getConditions().contains("Method"))
			return false;

		codesmells.put(codeSmell, rule);
		return true;
	}

	/**
	 *Returns a boolean to indicate the result of the method.
	 *
	 * @param codesmell  name of the codesmell		
	 * @return boolean (true if succeded / false if failed)
	 */
	public static boolean deleteRuleToCodeSmell(String codeSmell) {
		if(codeSmell.equals("is_Long_Method") || codeSmell.equals("is_God_Class") || codeSmell == null)
			return false;

		codesmells.remove(codeSmell);
		return true;
	}



	/**
	 * If the adding process is successful returns true if not returns false
	 * 
	 * @return hashmap with the codesmell name and rule associated
	 */
	public static HashMap<String, Rule> getCodeSmells() {
		return codesmells;
	}

}
