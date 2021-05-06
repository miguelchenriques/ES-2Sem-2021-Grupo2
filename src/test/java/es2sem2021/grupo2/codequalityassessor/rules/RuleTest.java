package es2sem2021.grupo2.codequalityassessor.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;



public class RuleTest {

	@Test
	void assertRuleTest(){
		Rule r = new Rule("ola","LOC_Method >= 45 And ( CYCLO_Method < 10 OR WMC_Class = 3 )");
		
		Method m = new Method("a","b","c",30, 2, 3, 45, 3);
		Method m2 = new Method("d","e","f",50,5, 3, 3, 3);
		
		boolean b = r.assertRule(m); 	
		boolean b2 = r.assertRule(m2);
		assertTrue(b);
		assertEquals(false,b2);
	}
	
	
	@Test
	public void whenExceptionThrown_thenAssertionSucceeds() {
	    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    	Rule r = new Rule("ola","wwwww >= 45 and ( Ceee < 10 or WMCeeee = 3 )");	    	    	
	    });

	    String expectedMessage = "sintaxe nao valida";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	
	
}
