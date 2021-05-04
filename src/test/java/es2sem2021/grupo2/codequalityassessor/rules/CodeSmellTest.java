package es2sem2021.grupo2.codequalityassessor.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CodeSmellTest {
	
	
	@Test
	void testGodClasseLongMethod() {
		CodeSmells.codesmells.clear();
		CodeSmells.importMandatoryCodeSmells();
		assertEquals(2, CodeSmells.codesmells.size());
	}
	
	@Test
	void testAddCodeSmellRule() {
		RulesSet.rules.clear();
		CodeSmells.codesmells.clear();
		CodeSmells.importMandatoryCodeSmells();
		boolean add1 = RulesSet.addRule("Grande", "LOC_Method >= 45 And ( CYCLO_Method < 10 OR WMC_Class = 3 )");
		assertEquals(true, add1);
		CodeSmells.codesmells.put("is_Long_Method",RulesSet.getRules().get("Grande"));
		System.out.println(CodeSmells.codesmells.get("is_Long_Method").getName());
		System.out.println(CodeSmells.codesmells.get("is_Long_Method").getConditions());
	}
}
