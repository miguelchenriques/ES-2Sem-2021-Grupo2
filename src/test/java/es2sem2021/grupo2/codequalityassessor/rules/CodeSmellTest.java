package es2sem2021.grupo2.codequalityassessor.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CodeSmellTest {
	
	@Test
	void testGodClasseLongMethod() {
		CodeSmells.getCodeSmells().clear();
		CodeSmells.importMandatoryCodeSmells();
		assertEquals(2, CodeSmells.getCodeSmells().size());
	}	
	
	@Test
	void testAddRuleToCodeSmell() {
		RulesSet.getRules().clear();
		CodeSmells.getCodeSmells().clear();
		CodeSmells.importMandatoryCodeSmells();
		boolean add1 = RulesSet.addRule("Grande", "LOC_Method >= 45 And CYCLO_Method < 10");
		assertEquals(true, add1);
		boolean add2 = CodeSmells.addRuleToCodeSmell("is_Long_Method", RulesSet.getRules().get("Grande"));
		assertEquals(true, add2);
	}
	
	@Test
	void testFailAddRuleToCodeSmell() {
		RulesSet.getRules().clear();
		CodeSmells.getCodeSmells().clear();
		CodeSmells.importMandatoryCodeSmells();
		boolean add1 = RulesSet.addRule("Grande", "LOC_Method >= 45 And ( CYCLO_Method < 10 OR WMC_Class = 3 )");
		assertEquals(true, add1);
		boolean add2 = CodeSmells.addRuleToCodeSmell("is_Long_Method", RulesSet.getRules().get("Grande"));
		assertEquals(false, add2);
	}	
	
	@Test
	void testDeleteRuleToCodeSmell() {
		RulesSet.getRules().clear();
		CodeSmells.getCodeSmells().clear();
		CodeSmells.importMandatoryCodeSmells();
		boolean add1 = RulesSet.addRule("Grande", "LOC_Method >= 45 And CYCLO_Method < 10");
		assertEquals(true, add1);
		boolean add2 = CodeSmells.addRuleToCodeSmell("nome", RulesSet.getRules().get("Grande"));
		assertEquals(true, add2);
		boolean remove = CodeSmells.deleteRuleToCodeSmell("nome");
		assertEquals(true, remove);
	}
	
	@Test
	void testFailDeleteRuleToCodeSmell() {
		RulesSet.getRules().clear();
		CodeSmells.getCodeSmells().clear();
		CodeSmells.importMandatoryCodeSmells();
		boolean add1 = RulesSet.addRule("Grande", "LOC_Method >= 45 And CYCLO_Method < 10");
		assertEquals(true, add1);
		boolean add2 = CodeSmells.addRuleToCodeSmell("is_Long_Method", RulesSet.getRules().get("Grande"));
		assertEquals(true, add2);
		boolean remove = CodeSmells.deleteRuleToCodeSmell("is_Long_Method");
		assertEquals(false, remove);
	}
}
