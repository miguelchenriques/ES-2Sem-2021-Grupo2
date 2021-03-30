package es2sem2021.grupo2.codequalityassessor.metrics.extractors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class CYCLO_methodTest {
	
	@Test
	void ParsingExceptionLines() {
		File f = new File("testFiles\\src\\com\\jasml\\compiler\\ParsingException.java");
		HashMap<String,Integer> numberCyclo = CYCLO_method.getMethodCyclo(f);
		assertEquals(5, numberCyclo.get("getMessage").intValue());
	}
	
	@Test
	void SourceCodeParserLines() {
		File f = new File("testFiles\\src\\com\\jasml\\compiler\\SourceCodeParser.java");
		HashMap<String,Integer> numberCyclo = CYCLO_method.getMethodCyclo(f);
		assertEquals(1, numberCyclo.get("main.parse").intValue());
	}
	
	@Test
	void InvalidFile() {
		File f = new File("not\\valid\\path.java");
		HashMap<String, Integer> results = CYCLO_method.getMethodCyclo(f);
		assertNull(results);
	}
	
	@Test
	void InvalidClass() {
		File f = new File("testFiles\\src\\InvalidClass.java");
		HashMap<String, Integer> results = CYCLO_method.getMethodCyclo(f);
		assertNull(results);
	}
}
