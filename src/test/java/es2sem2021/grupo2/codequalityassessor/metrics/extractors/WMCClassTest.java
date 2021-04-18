package es2sem2021.grupo2.codequalityassessor.metrics.extractors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class WMCClassTest {
	
	@Test
	void ParsingExceptionLines() {
		File f = new File("testFiles\\src\\com\\jasml\\compiler\\ParsingException.java");
		HashMap<String,Integer> numberCyclo = WMCClass.getClassWMC(f);
		System.out.println(numberCyclo.keySet());
		assertEquals(11, numberCyclo.get("ParsingException").intValue());
	}
	
	@Test
	void SourceCodeParserLines() {
		File f = new File("testFiles\\src\\com\\jasml\\compiler\\SourceCodeParser.java");
		HashMap<String,Integer> numberCyclo = WMCClass.getClassWMC(f);
		assertEquals(300, numberCyclo.get("SourceCodeParser").intValue());
		assertEquals(2, numberCyclo.get("SourceCodeParser.OpcodeWrapper").intValue());
		assertEquals(1, numberCyclo.get("SourceCodeParser.LabeledInstructions").intValue());
	}
	
	@Test
	void InvalidFile() {
		File f = new File("not\\valid\\path.java");
		HashMap<String, Integer> results = WMCClass.getClassWMC(f);
		assertNull(results);
	}
	
	@Test
	void InvalidClass() {
		File f = new File("testFiles\\src\\InvalidClass.java");
		HashMap<String, Integer> results = WMCClass.getClassWMC(f);
		assertNull(results);
	}
}
