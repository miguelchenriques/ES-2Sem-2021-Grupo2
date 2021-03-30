package es2sem2021.grupo2.codequalityassessor.metrics.extractors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class LOC_methodTest {
	@Test
	void ParsingExceptionLines() {
		File f = new File("testFiles\\src\\com\\jasml\\compiler\\ParsingException.java");
		HashMap<String,Integer> numberLines = LOC_method.getLOCMethod(f);
		assertEquals(21, numberLines.get("getMessage").intValue());
	}
	
	@Test
	void SourceCodeParserLines() {
		File f = new File("testFiles\\src\\com\\jasml\\compiler\\SourceCodeParser.java");
		HashMap<String,Integer> numberLines = LOC_method.getLOCMethod(f);
		assertEquals(20, numberLines.get("main.preprocessConstantValues").intValue());
		assertEquals(523, numberLines.get("main.parseMethodInstructions").intValue());
		assertEquals(3, numberLines.get("main.parseLineNumbers").intValue());
	}
	
	@Test
	void InvalidFile() {
		File f = new File("not\\valid\\path.java");
		HashMap<String, Integer> results = LOC_method.getLOCMethod(f);
		assertNull(results);
	}
	
	@Test
	void InvalidClass() {
		File f = new File("testFiles\\src\\InvalidClass.java");
		HashMap<String, Integer> results = LOC_method.getLOCMethod(f);
		assertNull(results);
	}
}
