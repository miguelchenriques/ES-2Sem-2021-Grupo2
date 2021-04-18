package es2sem2021.grupo2.codequalityassessor.rules;

import static es2sem2021.grupo2.codequalityassessor.rules.Constants.CYCLO_METHOD;
import static es2sem2021.grupo2.codequalityassessor.rules.Constants.LOC_CLASS;
import static es2sem2021.grupo2.codequalityassessor.rules.Constants.LOC_METHOD;
import static es2sem2021.grupo2.codequalityassessor.rules.Constants.NOM;
import static es2sem2021.grupo2.codequalityassessor.rules.Constants.WMC_CLASS;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mvel2.MVEL;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

public class Rule {
	
	private String name, conditions;
	
	public Rule(String name, String conditions) throws IllegalArgumentException { //verificar o conditions antes de criar
		if(!validateConditionSyntax(conditions))
				throw new IllegalArgumentException("sintaxe nao valida :(");
		this.name = name;
		this.conditions = conditions;

		
	}
	
	
	/**
	 * 
	 * Evaluates if the method meets the rule's conditions
	 * 
	 * @param method		method that is going to be evaluated 
	 * @return				true if method meets the conditions, false otherwise
	 */
	
	public boolean assertRule(Method method) {
		
		String rule = this.conditions;
		
		Pattern p = Pattern.compile("[A-Za-z_]*\s(>|<|=|>=|<=)\\s[0-9]*");
		Matcher matcher = p.matcher(this.conditions);
		
		while (matcher.find()) {
			int start = matcher.start();
			int end = matcher.end();
			String match = this.conditions.substring(start, end);
			String[] condition = match.split(" ");
			boolean value = assertCondition(method, condition);
			
			String valueString = value ? "true": "false";
			
			rule = rule.replaceAll(match, valueString);
			
		}
		rule = rule.toLowerCase().replaceAll("and", "&&");
		rule = rule.replaceAll("or", "||");
		
		return (boolean) MVEL.eval(rule);
	}
	
	/**
	 * 
	 * Evaluates if the condition is true in the given method
	 * 
	 * @param m				method that is going to be compared with the condition
	 * 		  condition		condition that is going to be compared with the method
	 * @return				true if the comparison is true, false otherwise
	 */
	
	private boolean assertCondition(Method m, String[] condition) {
		String metric = condition[0];
		String comp = condition[1];
		int value = Integer.parseInt(condition[2]);
		
		return compare(m.getMetric(metric), comp, value);
	}
	
	private boolean compare(int metric, String comp, int value) {
		switch (comp) {
		case ">":
			return metric > value;
		case ">=":
			return metric >= value;
		case "<":
			return metric < value;
		case "<=":
			return metric <= value;
		case "=":
			return metric == value;

		default:
			break;
		}
		return false;
	}
	
	/**
	 * 
	 * Validates the syntax from the conditions code
	 * 
	 * @param conditionCode	the code that specifies the condition
	 * @return				true if it has a valid syntax, false otherwise
	 */
	
	public static boolean validateConditionSyntax(String conditionCode) {
		//TODO: Validate completly the condition
		List<String> validMetrics = Arrays.asList(CYCLO_METHOD, LOC_CLASS, LOC_METHOD, NOM, WMC_CLASS);
		List<String> keywords = Arrays.asList("and", "or", "(", ")", ">", "<", ">=", "<=", "=");

		// metricName pattern
		Pattern metricName = Pattern.compile("^[a-z][a-z_]*$", Pattern.CASE_INSENSITIVE);

		// Splits the conditions
		for (String element : Arrays.asList(conditionCode.split(" "))) {
			
			if (keywords.contains(element.toLowerCase())) continue;
			
			if (metricName.matcher(element).matches()) {
				if (!validMetrics.contains(element))
					return false;
				continue;
			}
			
			if (isInteger(element))
				continue;
			
			return false;
		}
		return true;
	}

	private static boolean isInteger(String i) {
		try {
			Integer.parseInt(i);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) throws IllegalArgumentException {
		if (!validateConditionSyntax(conditions)) throw new IllegalArgumentException();
		this.conditions = conditions;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRuleCode() {
		return "SE (" + conditions + ") ENTAO" + name;
	}
}
