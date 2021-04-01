package es2sem2021.grupo2.codequalityassessor.metrics;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
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
	void test() {
		File f = new File("testFiles\\src\\com\\jasml\\compiler\\ParsingException.java");
		try {
			List<Method> methods = MetricsExtractor.extract(f);
			assertNotNull(methods);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
