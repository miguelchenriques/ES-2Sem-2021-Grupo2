package es2sem2021.grupo2.codequalityassessor.rules;

public class Rule {
	
	private String name;
	//TODO: Como vamos armazenar as associações logicas
	//private  rule;
	
	public Rule(String ruleCode) {
		parseCode(ruleCode);
	}
	
	private void parseCode(String code) {
		String[] ruleSplited = code.split("ENTAO");
		
		this.name = ruleSplited[1].trim();
	}
	
	
	private enum LogicalOperand {OR, AND}
}
