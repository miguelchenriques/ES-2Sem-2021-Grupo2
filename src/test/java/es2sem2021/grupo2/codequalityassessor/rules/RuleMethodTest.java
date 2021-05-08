package es2sem2021.grupo2.codequalityassessor.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

class RuleMethodTest {
	
	RuleMethod ruleMethod;

	@BeforeEach
	void setUp() throws Exception {
		Method method = new Method("", "", "", 0, 0, 0, 0, 0);
		
		ruleMethod = new RuleMethod(method, false);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		ruleMethod = null;
	}

	@Test
	void test() {
		Method method = new Method("package", "class", "method", 0, 0, 0, 0, 0);
		
		assertNotEquals(method, ruleMethod.getMethod());
		assertFalse(ruleMethod.getResult());

		ruleMethod.setMethod(method);
		ruleMethod.setResult(true);
		
		assertEquals(method, ruleMethod.getMethod());
		assertTrue(ruleMethod.getResult());
	}

}
