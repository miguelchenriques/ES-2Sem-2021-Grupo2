package es2sem2021.grupo2.codequalityassessor.rules;

import static es2sem2021.grupo2.codequalityassessor.rules.Constants.CYCLO_METHOD;
import static es2sem2021.grupo2.codequalityassessor.rules.Constants.LOC_CLASS;
import static es2sem2021.grupo2.codequalityassessor.rules.Constants.LOC_METHOD;
import static es2sem2021.grupo2.codequalityassessor.rules.Constants.NOM;
import static es2sem2021.grupo2.codequalityassessor.rules.Constants.WMC_CLASS;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class RuleParser {

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
