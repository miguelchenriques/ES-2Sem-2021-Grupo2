package es2sem2021.grupo2.codequalityassessor.rules;


public class CodeSmell {
	public String name;
	public Rule regra;
	
	CodeSmell(String name, Rule regra){
		this.name=name;
		this.regra=regra;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rule getRegra() {
		return regra;
	}

	public void setRegra(Rule regra) {
		this.regra = regra;
	}
	
	
}
