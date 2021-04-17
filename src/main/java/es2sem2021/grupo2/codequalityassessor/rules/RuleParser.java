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
			
			if (keywords.contains(element)) continue;
			
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

}
