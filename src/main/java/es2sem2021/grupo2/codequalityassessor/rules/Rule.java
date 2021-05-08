package es2sem2021.grupo2.codequalityassessor.rules;

import static es2sem2021.grupo2.codequalityassessor.rules.Constants.CYCLO_METHOD;
import static es2sem2021.grupo2.codequalityassessor.rules.Constants.LOC_CLASS;
import static es2sem2021.grupo2.codequalityassessor.rules.Constants.LOC_METHOD;
import static es2sem2021.grupo2.codequalityassessor.rules.Constants.NOM_CLASS;
import static es2sem2021.grupo2.codequalityassessor.rules.Constants.WMC_CLASS;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mvel2.MVEL;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

public class Rule implements Serializable {


	@Override
	public String toString() {
		return "Rule [name=" + name + ", conditions=" + conditions + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5618173094709221790L;
	private String name, conditions;
	
	/**
	 * 
	 * Constructs a rule with the given name and condition, throws IllegalArgumentException if the condition has an
	 * invalid syntax
	 * 
	 * @param name
	 * @param conditions
	 * @throws IllegalArgumentException
	 */
	public Rule(String name, String conditions) throws IllegalArgumentException { //verificar o conditions antes de criar
		if(!validateConditionSyntax(conditions))
			throw new IllegalArgumentException("sintaxe nao valida");
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

		List<String> validMetrics = Arrays.asList(CYCLO_METHOD, LOC_CLASS, LOC_METHOD, NOM_CLASS, WMC_CLASS);
		List<String> keywords = Arrays.asList("and", "or", "(", ")", ">", "<", ">=", "<=", "=");

		// metricName pattern
		Pattern metricName = Pattern.compile("^[a-z][a-z_]*$", Pattern.CASE_INSENSITIVE);

		String[] conditions = conditionCode.split(" ");
		if(conditions.length < 3)
			return false;

		// Splits the conditions
		for (String element : Arrays.asList(conditions)) {

			if (keywords.contains(element.toLowerCase()))
				continue; 

			if (metricName.matcher(element).matches()) {
				if (!validMetrics.contains(element))
					return false;
				continue;
			}

			if (isInteger(element))
				continue;

			return false;
		}

		String[] condition = conditionCode.split(" ");
		int abreParenteses = 0;
		int fechaParenteses = 0;
		int logical = 0;
		int metrics = 0;
		int integers = 0;

		for (int i = 0; i < condition.length; i++) {

			if(i==0 && !(condition[i].equals("(") || validMetrics.contains(condition[i]))) {
				return false;
			}

			switch(condition[i].toLowerCase()) {
			case "(":
				abreParenteses++;
				if(i>0 && !(condition[i-1].equals("(") || condition[i-1].toLowerCase().equals("and") 
						|| condition[i-1].toLowerCase().equals("or"))) 
					return false;
				break;
			case ")": 
				fechaParenteses++; 
				if(i>0 && !(condition[i-1].equals(")") || isInteger(condition[i-1])))
					return false;
				break;
			case "and": 
				if(i>0 && !(condition[i-1].equals(")") || isInteger(condition[i-1]))) 
					return false;
				break;
			case "or":
				if(i>0 && !(condition[i-1].equals(")") || isInteger(condition[i-1]))) 
					return false;
				break;
			case ">": 
				logical++;
				if(i>0 && !(validMetrics.contains(condition[i-1]))) 
					return false;
				break;
			case "<": 
				logical++;
				if(i>0 && !(validMetrics.contains(condition[i-1]))) 
					return false;
				break;
			case ">=": 
				logical++;
				if(i>0 && !(validMetrics.contains(condition[i-1])))
					return false;
				break;
			case "<=": 
				logical++;
				if(i>0 && !(validMetrics.contains(condition[i-1])))
					return false;
				break;
			case "=": 
				logical++;
				if(i>0 && !(validMetrics.contains(condition[i-1])))
					return false;
				break;
			default:
				if(validMetrics.contains(condition[i])) {
					metrics++;
					if(i>0 && !(condition[i-1].equals("(") || condition[i-1].toLowerCase().equals("and") 
							|| condition[i-1].toLowerCase().equals("or")))
						return false;
					break;
				}
				if(isInteger(condition[i])) {
					integers++;
					if(i>0 && !(condition[i-1].equals("<") || condition[i-1].equals(">") || condition[i-1].equals("<=") 
							|| condition[i-1].equals(">=") || condition[i-1].equals("="))) 
						return false;
					break;
				}
				if(i==0) continue;
				return false;
			}
		}

		if((logical != integers) || (logical != metrics) || (abreParenteses != fechaParenteses))
			return false;

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
}
