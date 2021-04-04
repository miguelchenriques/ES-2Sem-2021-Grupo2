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
		assertEquals(6, numberLines.get("ParsingException(int, int, int, String)").intValue());
	}
	
	@Test
	void SourceCodeParserLines() {
		File f = new File("testFiles\\src\\com\\jasml\\compiler\\SourceCodeParser.java");
		HashMap<String,Integer> numberLines = LOC_method.getLOCMethod(f);
		assertEquals(20, numberLines.get("SourceCodeParser.preprocessConstantValues()").intValue());
		assertEquals(513, numberLines.get("SourceCodeParser.parseMethodInstructions(Method)").intValue());
		assertEquals(73, numberLines.get("SourceCodeParser.parseInnerClasses()").intValue());
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
