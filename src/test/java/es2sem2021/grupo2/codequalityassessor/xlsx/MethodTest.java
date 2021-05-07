package es2sem2021.grupo2.codequalityassessor.xlsx;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MethodTest {

	Method method;

	@BeforeEach
	void setUp() throws Exception {
		method = new Method("package", "Some", "method", 5, 3, 10, 8, 5);
	}

	@AfterEach
	void tearDown() throws Exception {
		method = null;
		assertNull(method);
	}

	@Test
	void testGetInvalidMetric() {
		assertThrows(IllegalArgumentException.class, () -> {
			method.getMetric("invalid");
		});
	}
	
	@Test
	void testGetMetric() {
		assertEquals(5, method.getMetric("NOM"));
		assertEquals(3, method.getMetric("LOC_Class"));
		assertEquals(10, method.getMetric("WMC_Class"));
		assertEquals(8, method.getMetric("LOC_Method"));
		assertEquals(5, method.getMetric("CYCLO_Method"));
	}
	
	@Test
	void testEquals() {
		Method equal = new Method("package", "Some", "method", 5, 3, 10, 8, 5);
		Method notEqual = new Method("package", "Some", "method", 10, 3, 10, 8, 5);
		assertEquals(method, equal);
		assertEquals(method, method);
		assertNotEquals(method, notEqual);
	}
	
	@Test
	void testInvalidEquals() {
		assertFalse(method.equals(null));
		assertFalse(method.equals("String"));
	}
	
	@Test
	void testToString() {
		assertEquals("Method [m_package=package, m_class=Some, m_method=method, nom_class=5, loc_class=3, wmc_class=10," + 
				" loc_method=8, cyclo_method=5]", method.toString());
	}

}
