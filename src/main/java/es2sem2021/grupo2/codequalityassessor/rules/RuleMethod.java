package es2sem2021.grupo2.codequalityassessor.rules;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

public class RuleMethod {
	
	public Method method;
	public Boolean result;
	
	public RuleMethod(Method method, Boolean result){
		this.method=method;
		this.result=result;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}
	
}
