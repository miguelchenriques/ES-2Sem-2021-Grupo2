package es2sem2021.grupo2.codequalityassessor.rules;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class CodeSmells {
	private static HashMap<String,Rule> codesmells = new HashMap<>();

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
		saveToFile();
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
		saveToFile();
		return true;
	}
	
	public static void addCodeSmell(String codeSmell) {
		codesmells.put(codeSmell, null);
	}
	
	/**
	 * Returns a boolean
	 * If the adding process is successful returns true if not returns false
	 * 
	 * @param name-Code Smell name (string)
	 * @return true/false
	 * @throws IllegalArgumentException
	 */
	public static boolean addCodeSmell(String name) {
			if(codesmells.keySet().contains(name))
				return false;
			codesmells.put(name, null);
			saveToFile();
			return true;
	}
	
	/**
	 * Returns a boolean
	 * If the deleting process is successful returns true if not returns false
	 * 
	 * @param name-CodeSmell name (string)
	 * @return true/false
	 */
	public static boolean deleteCodeSmell(String name) {
		if(codesmells.keySet().contains(name))
			return false;
		codesmells.remove(name);
		saveToFile();
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
	
	/**
	 * Saves the codesmells into a file
	 */
	public static void saveToFile() {
		try {
			FileOutputStream f = new FileOutputStream(new File(Constants.CODE_SMELLS_DATA_FILE));
			ObjectOutputStream o = new ObjectOutputStream(f);
			
			o.writeObject(codesmells);
			
			o.close();
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads the codesmells from a file
	 */
	@SuppressWarnings("unchecked")
	public static void loadFromFile() {
		try {
			FileInputStream f = new FileInputStream(new File(Constants.CODE_SMELLS_DATA_FILE));
			ObjectInputStream o = new ObjectInputStream(f);
			
			Object in = o.readObject();

			if (in instanceof HashMap) {
				codesmells = (HashMap<String, Rule>) in;
			}
			
			if(codesmells.get("is_God_Class")==null)
				codesmells.put("is_God_Class", null);
			
			if(codesmells.get("is_Long_Method")==null)
				codesmells.put("is_Long_Method", null);
			
			o.close();
			f.close();
			
		} catch (EOFException e) {
			codesmells.put("is_God_Class", null);
			codesmells.put("is_Long_Method", null);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
