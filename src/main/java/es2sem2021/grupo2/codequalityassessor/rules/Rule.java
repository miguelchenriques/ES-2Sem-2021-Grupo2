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
import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

public class Rule {
	
	private String name, ruleCode;
	//Representa a raiz da arvore da regra
	private LogicalCondition root;
	
	/**
	 * 
	 * Constructs a rule object based on the ruleCode.
	 * 
	 * @param ruleCode	code that defines the rule
	 * @throws IllegalArgumentException	thrown if rule syntax is not valid
	 */
	public Rule(String ruleCode) throws IllegalArgumentException {
		if (!validateSyntax(ruleCode)) throw new IllegalArgumentException("Invalid Syntax");
		this.ruleCode = ruleCode;
		parseCode(ruleCode);
	}
	
	public boolean assertRule(Method method) {
		return root.assertCondition(method);
	}
	
	public String getName() {
		return name;
	}

	public String getRuleCode() {
		return ruleCode;
	}
	
	//Validates if the rule syntax is correct
	private boolean validateSyntax(String ruleCode) {
		// Verifies if it complies with the basic structure "SE (condicoes) ENTAO nome_regra
		Pattern base = Pattern.compile("^SE\\s[\\\\(]{1}[A-Za-z><=\\s0-9()_]*[\\\\)]{1}\\sENTAO\\s[A-Za-z_]*$");
		if (!base.matcher(ruleCode).matches()) return false;
		
		// Extrai as condiçoes da regra
		String code = ruleCode.substring(ruleCode.indexOf('(')+1, ruleCode.lastIndexOf(')'));
		
		List<String> validMetrics = Arrays.asList(CYCLO_METHOD, LOC_CLASS, LOC_METHOD, NOM, WMC_CLASS);
		List<String> keywords = Arrays.asList("AND", "OR", "(", ")", ">", "<", ">=", "<=", "=");
		
		// metricName pattern
		Pattern metricName = Pattern.compile("^[a-z][a-z_]*$", Pattern.CASE_INSENSITIVE);
		
		// Splits the conditions 
		for (String element: Arrays.asList(code.split(" "))) {
			if (metricName.matcher(element).matches()) {
				if (!validMetrics.contains(element)) return false;
				continue;
			}
			if (!keywords.contains(element) && !isInteger(element)) return false;
		}
		
		return false;
	}

	private void parseCode(String code) {
		String[] ruleSplited = code.split("ENTAO");
		
		this.name = ruleSplited[1].trim();
	}
	
	private boolean isInteger(String i) {
		try {
			Integer.parseInt(i);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
}
