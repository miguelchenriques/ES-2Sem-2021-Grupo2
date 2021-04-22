package es2sem2021.grupo2.codequalityassessor.rules;

import java.util.HashMap;


public class RulesSet {

	static HashMap<String,Rule> rules = new HashMap<String,Rule>();

	public static boolean addRule(String name, String conditions) {
		try {
			Rule r = new Rule(name,conditions);
			if(rules.get(name) != null)
				return false;
			rules.put(name, r);
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
			return true;
		} catch(IllegalArgumentException e) {
			return false;
		}
	}

	public static boolean deleteRule(String name) {
		if(rules.get(name) == null)
			return false;
		rules.remove(name);
		return true;
	}
}
