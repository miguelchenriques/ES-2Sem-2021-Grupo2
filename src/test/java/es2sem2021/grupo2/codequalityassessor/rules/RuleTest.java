package es2sem2021.grupo2.codequalityassessor.rules;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;



public class RuleTest {

	Rule ola;
	
	@BeforeEach
	void setUp() throws Exception {
		ola = new Rule("ola","LOC_Method >= 45 And ( CYCLO_Method < 10 OR WMC_Class = 3 )");
	}

	@AfterEach
	void tearDown() throws Exception {
		ola = null;
	}
	
	@Test
	void assertRuleTest(){
		Method m = new Method("a","b","c",30, 2, 3, 45, 3);
		Method m2 = new Method("d","e","f",50,5, 3, 3, 3);
		
		assertTrue(ola.assertRule(m));
		assertFalse(ola.assertRule(m2));
	}
	
	@Test
	void testGettersSetters() {
		assertEquals("ola", ola.getName());
		assertEquals("LOC_Method >= 45 And ( CYCLO_Method < 10 OR WMC_Class = 3 )", ola.getConditions());
		
		ola.setName("adeus");
		ola.setConditions("LOC_Method >= 45");
		
		assertEquals("adeus", ola.getName());
		assertEquals("LOC_Method >= 45", ola.getConditions());
	}
	
	@Test
	public void whenExceptionThrown_thenAssertionSucceeds() {
	    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    	new Rule("ola","wwwww >= 45 and ( Ceee < 10 or WMCeeee = 3 )");	    	    	
	    });

	    String expectedMessage = "sintaxe nao valida :(";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testInvalidNumber() {
		boolean result = Rule.validateConditionSyntax("LOC_Method > 1a2");
		assertFalse(result);
	}
	
	@Test
	void complexRule() {
		Rule complex = new Rule("complex", "LOC_Method >= 45 And ( ( CYCLO_Method < 10 OR WMC_Class = 3 ) or ( LOC_Class > 80 and NOM <= 20 ) )");
		Method m1 = new Method("a","b","c",10, 68, 3, 45, 3);
		Method m2 = new Method("a","b","c",10, 88, 3, 40, 3);
		
		assertTrue(complex.assertRule(m1));
		assertFalse(complex.assertRule(m2));
	}
	
}
