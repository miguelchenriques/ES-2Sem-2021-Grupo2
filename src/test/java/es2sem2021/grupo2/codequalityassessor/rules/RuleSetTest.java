package es2sem2021.grupo2.codequalityassessor.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RuleSetTest {
	
	@Test
	void testAddRule() {
		RulesSet.rules.clear();
		boolean add1 = RulesSet.addRule("Grande", "LOC_Method >= 45 And ( CYCLO_Method < 10 OR WMC_Class = 3 )");
		assertEquals(true, add1);
		boolean add2 = RulesSet.addRule("Grande", "LOC_Method >= 45 And ( CYCLO_Method < 10 OR WMC_Class = 3 )");
		assertEquals(false, add2);
		boolean add3 = RulesSet.addRule("pequeno", "LOC_MethodAAA >= 45 And ( CYCLO_MethodAAA < 10 OR WMC_ClassAAA = 3 )");
		assertEquals(false, add3);
	}
	
	@Test
	void testChangeRule() {
		RulesSet.rules.clear();
		boolean add1 = RulesSet.addRule("Grande", "LOC_Method >= 45 And ( CYCLO_Method < 10 OR WMC_Class = 3 )");
		assertEquals(true, add1);
		
		boolean change1 = RulesSet.changeRule("Grande", "LOC_Method >= 40 And ( CYCLO_Method < 12 OR WMC_Class = 5 )");
		assertEquals(true, change1);
		boolean change2 = RulesSet.changeRule("medio", "LOC_Method >= 45 And ( CYCLO_Method < 10 OR WMC_Class = 3 )");
		assertEquals(false, change2);
		boolean change3 = RulesSet.changeRule("Grande", "LOC_MethodAAA >= 45 And ( CYCLO_MethodAAA < 10 OR WMC_ClassAAA = 3 )");
		assertEquals(false, change3);
	}
	
	@Test
	void testDeleteRule() {
		RulesSet.rules.clear();
		boolean add1 = RulesSet.addRule("Grande", "LOC_Method >= 45 And ( CYCLO_Method < 10 OR WMC_Class = 3 )");
		assertEquals(true, add1);
		
		boolean delete1 = RulesSet.deleteRule("Grande");
		assertEquals(true, delete1);
		boolean delete2 = RulesSet.deleteRule("Pequeno");
		assertEquals(false, delete2);
	}
	
	

}
