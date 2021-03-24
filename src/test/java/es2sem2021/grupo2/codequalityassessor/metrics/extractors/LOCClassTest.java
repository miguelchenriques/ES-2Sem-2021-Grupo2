package es2sem2021.grupo2.codequalityassessor.metrics.extractors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class LOCClassTest {
	
	@Test
	void ParsingExceptionLines() {
		File f = new File("testFiles\\src\\com\\jasml\\compiler\\ParsingException.java");
		HashMap<String,Integer> numberLines = LOCClass.getClassLOC(f);
		assertEquals(50, numberLines.get("ParsingException").intValue());
	}
	
	@Test
	void SourceCodeParserLines() {
		File f = new File("testFiles\\src\\com\\jasml\\compiler\\SourceCodeParser.java");
		HashMap<String,Integer> numberLines = LOCClass.getClassLOC(f);
		assertEquals(1371, numberLines.get("SourceCodeParser").intValue());
		assertEquals(14, numberLines.get("SourceCodeParser.OpcodeWrapper").intValue());
		assertEquals(13, numberLines.get("SourceCodeParser.LabeledInstructions").intValue());
	}
	
	@Test
	void InvalidFile() {
		File f = new File("not\\valid\\path.java");
		HashMap<String, Integer> results = LOCClass.getClassLOC(f);
		assertNull(results);
	}
	
	@Test
	void InvalidClass() {
		File f = new File("testFiles\\src\\InvalidClass.java");
		HashMap<String, Integer> results = LOCClass.getClassLOC(f);
		assertNull(results);
	}
	
	@Test
	void EmptyFile() {
		File f = new File("testFiles\\src\\Empty.java");
		HashMap<String, Integer> results = LOCClass.getClassLOC(f);
		assertNull(results);
	}
}
