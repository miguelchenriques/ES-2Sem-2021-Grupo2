package es2sem2021.grupo2.codequalityassessor.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CodeSmellTest {
	
	@Test
	void testGodClasseLongMethod() {
		CodeSmells.loadFromFile();
		assertEquals(true, CodeSmells.getCodeSmells().keySet().contains("is_Long_Method"));
		assertEquals(true, CodeSmells.getCodeSmells().keySet().contains("is_God_Class"));
	}	
	
	@Test
	void testAddRuleToCodeSmell() {
		RulesSet.getRules().clear();
		CodeSmells.loadFromFile();
		boolean add1 = RulesSet.addRule("Grande", "LOC_Method >= 45 And CYCLO_Method < 10");
		assertEquals(true, add1);
		boolean add2 = CodeSmells.addRuleToCodeSmell("is_Long_Method", RulesSet.getRules().get("Grande"));
		assertEquals(true, add2);
	}
	
	@Test
	void testFailAddRuleToCodeSmell() {
		RulesSet.getRules().clear();
		CodeSmells.loadFromFile();
		boolean add1 = RulesSet.addRule("Grande", "LOC_Method >= 45 And ( CYCLO_Method < 10 OR WMC_Class = 3 )");
		assertEquals(true, add1);
		boolean add2 = CodeSmells.addRuleToCodeSmell("is_Long_Method", RulesSet.getRules().get("Grande"));
		assertEquals(false, add2);
	}	
	
	@Test
	void testAddCodeSmell() {
		CodeSmells.getCodeSmells().clear();
		boolean add = CodeSmells.addCodeSmell("CodeSmellName");
		assertEquals(true, add);
	}
	
	@Test
	void testFaillAddCodeSmell() {
		CodeSmells.getCodeSmells().clear();
		boolean add = CodeSmells.addCodeSmell("CodeSmellName");
		assertEquals(true, add);
		boolean add2 = CodeSmells.addCodeSmell("CodeSmellName");
		assertEquals(false, add2);
	}
	
	@Test
	void testDeleteCodeSmell() {
		CodeSmells.getCodeSmells().clear();	
		boolean add = CodeSmells.addCodeSmell("CodeSmellName");
		assertEquals(true, add);
		boolean delete = CodeSmells.deleteCodeSmell("CodeSmellName");
		assertEquals(true, delete);
	}
	
	@Test
	void testFailDeleteCodeSmell() {
		CodeSmells.getCodeSmells().clear();	
		boolean delete = CodeSmells.deleteCodeSmell("is_Long_Method");
		assertEquals(false, delete);
	}
}
