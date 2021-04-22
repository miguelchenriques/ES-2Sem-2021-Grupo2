package es2sem2021.grupo2.codequalityassessor.rules;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;


public class RulesSet {

	static HashMap<String,Rule> rules = new HashMap<String,Rule>();

	public static boolean addRule(String name, String conditions) {
		try {
			Rule r = new Rule(name,conditions);
			if(rules.get(name) != null)
				return false;
			rules.put(name, r);
			saveToFile();
			return true;
		} catch(IllegalArgumentException e) {
			return false;
		}
	}

	public static HashMap<String, Rule> getRules() {
		return rules;
	}

	public static boolean changeRule(String name, String conditions) {
		try {
			Rule r = new Rule(name,conditions);
			if(rules.get(name) == null)
				return false;
			rules.put(name, r);
			saveToFile();
			return true;
		} catch(IllegalArgumentException e) {
			return false;
		}
	}

	public static boolean deleteRule(String name) {
		if(rules.get(name) == null)
			return false;
		rules.remove(name);
		saveToFile();
		return true;
	}
	
	public static void saveToFile() {
		try {
			FileOutputStream f = new FileOutputStream(new File("rules"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			
			o.writeObject(rules);
			
			o.close();
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void loadFromFile() {
		try {
			FileInputStream f = new FileInputStream(new File("rules"));
			ObjectInputStream o = new ObjectInputStream(f);
			
			Object in = o.readObject();
			
			if (in instanceof HashMap) {
				rules = (HashMap<String, Rule>) in;
			}

			o.close();
			f.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
