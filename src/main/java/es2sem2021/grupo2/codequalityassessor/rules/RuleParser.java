package es2sem2021.grupo2.codequalityassessor.rules;

import static es2sem2021.grupo2.codequalityassessor.rules.Constants.CYCLO_METHOD;
import static es2sem2021.grupo2.codequalityassessor.rules.Constants.LOC_CLASS;
import static es2sem2021.grupo2.codequalityassessor.rules.Constants.LOC_METHOD;
import static es2sem2021.grupo2.codequalityassessor.rules.Constants.NOM;
import static es2sem2021.grupo2.codequalityassessor.rules.Constants.WMC_CLASS;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import es2sem2021.grupo2.codequalityassessor.rules.logical.conditions.LogicalCondition;

public class RuleParser {

	/**
	 * 
	 * Parses the entire rule code into a Rule object
	 * 
	 * @param ruleCode	the code that defines the rule
	 * @return			rule specified by the code
	 * @throws IllegalArgumentException thrown if syntax is not valid
	 */
	public static Rule parseRule(String ruleCode) throws IllegalArgumentException {
		if (!validateSyntax(ruleCode)) throw new IllegalArgumentException("Syntax not valid");
		//TODO: Parse the code and return a Rule object
		return null;
	}

	/**
	 * 
	 * Parses the conditions of a rule to a LogicalCondition object
	 * 
	 * @param conditionCode	the rules conditions
	 * @return				the LogicalCondition object representing the conditionCode
	 * @throws IllegalArgumentException thrown if syntax is not valid
	 */
	public static LogicalCondition parseRuleCondition(String conditionCode) throws IllegalArgumentException {
		if (!validateConditionSyntax(conditionCode)) throw new IllegalArgumentException("Syntax not valid");
		//TODO: Parse the condition and return the LogicalCondition object
		return null;
	}

	/**
	 * 
	 * Validates the syntax from the passed rule code
	 * 
	 * @param ruleCode	the rule code
	 * @return			true if it has a valid syntax, false otherwise
	 */
	public static boolean validateSyntax(String ruleCode) {
		// Verifies if it complies with the basic structure "SE (condicoes) ENTAO nome_regra
		Pattern base = Pattern.compile("^SE\\s[\\\\(]{1}[A-Za-z><=\\s0-9()_]*[\\\\)]{1}\\sENTAO\\s[A-Za-z_]*$");
		if (!base.matcher(ruleCode).matches())
			return false;

		// Extrai as condiçoes da regra
		String code = ruleCode.substring(ruleCode.indexOf('(') + 1, ruleCode.lastIndexOf(')'));
		
		return validateConditionSyntax(code);
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
		List<String> keywords = Arrays.asList("AND", "OR", "(", ")", ">", "<", ">=", "<=", "=");

		// metricName pattern
		Pattern metricName = Pattern.compile("^[a-z][a-z_]*$", Pattern.CASE_INSENSITIVE);

		// Splits the conditions
		for (String element : Arrays.asList(conditionCode.split(" "))) {
			if (metricName.matcher(element).matches()) {
				if (!validMetrics.contains(element))
					return false;
				continue;
			}
			if (!keywords.contains(element) && !isInteger(element))
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

}
