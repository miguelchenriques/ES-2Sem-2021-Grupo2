package es2sem2021.grupo2.codequalityassessor.metrics;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

class MetricsExtractorTest {
	@Test
	void testException() throws FileNotFoundException {
		File f = new File("testFiles" + File.separator + "src" + File.separator + "com" + File.separator + "jasml"
				+ File.separator + "compiler" + File.separator + "TestException.java");
		List<Method> methods = MetricsExtractor.extract(f);
		assertNotNull(methods);
		List<Method> methods1 = new ArrayList<>();
		methods1.add(new Method("com.jasml.compiler", "TestException.Test", "Test()", 1, 3, 1, 1, 1));
		assertEquals(methods1, methods);
	}

	@Test
	void testException2() throws FileNotFoundException {
		File f = new File("testFiles" + File.separator + "src" + File.separator + "com" + File.separator + "jasml"
				+ File.separator + "compiler" + File.separator + "TestException2.java");
		List<Method> methods = MetricsExtractor.extract(f);
		assertNotNull(methods);
		List<Method> methods1 =Arrays.asList(new Method("com.jasml.compiler", "TestException", "TestException()", 0, 2, 0, 0, 0));
		assertEquals(methods1, methods);
	}
}
