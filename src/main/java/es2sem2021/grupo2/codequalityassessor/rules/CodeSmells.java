package es2sem2021.grupo2.codequalityassessor.rules;

import java.util.HashMap;

public class CodeSmells {
	static HashMap<String,Rule> codesmells = new HashMap<>();
	
	public static HashMap<String, Rule> getCodesmells() {
		return codesmells;
	}

	public static void importMandatoryCodeSmells() {
		codesmells.put("is_Long_Method", null);
		codesmells.put("is_God_Class", null);
	}
	
}
