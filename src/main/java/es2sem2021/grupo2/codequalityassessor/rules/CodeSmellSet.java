package es2sem2021.grupo2.codequalityassessor.rules;

import java.util.ArrayList;

public class CodeSmellSet {
	static ArrayList<CodeSmell> codesmells = new ArrayList<>();
	
	public static void importMandatoryCodeSmells() {
		codesmells.add(new CodeSmell("is_Long_method", null));
		codesmells.add(new CodeSmell("is_God_Class", null));
	}
	
}
