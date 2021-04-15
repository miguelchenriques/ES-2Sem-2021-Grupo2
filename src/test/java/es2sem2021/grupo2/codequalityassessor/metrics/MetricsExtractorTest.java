package es2sem2021.grupo2.codequalityassessor.metrics;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

class MetricsExtractorTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testException() {
		File f = new File("testFiles" + File.separator + "src" + File.separator + "com" + File.separator + "jasml" + File.separator + "compiler" + File.separator + "TestException.java");
		try {
			List<Method> methods = MetricsExtractor.extract(f);
			assertNotNull(methods);
			List<Method> methods1 = new ArrayList<>();
			methods1.add(new Method("","TestException.Test","Test()",1,1,0,0,0));
			assertEquals(methods1,methods);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testException2() {
		File f = new File("testFiles" + File.separator + "src" + File.separator + "com" + File.separator + "jasml" + File.separator + "compiler" + File.separator + "TestException2.java");
		try {
			List<Method> methods = MetricsExtractor.extract(f);
			assertNotNull(methods);
			List<Method> methods1 = new ArrayList<>();
			methods1.add(new Method("","TestException","TestException()",0,1,0,0,0));
			assertEquals(methods1,methods);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
