package es2sem2021.grupo2.codequalityassessor.metrics.extractors;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class NOMTest {

	@Test
	void testGrammerException() {
		File f = new File("testFiles\\src\\com\\jasml\\compiler\\GrammerException.java");
		HashMap<String,Integer> results=NOM.getClassNOM(f);
		HashMap<String,Integer> expected= new HashMap<>();
		expected.put("GrammerException", 4);
		assertEquals(results,expected);
		
	}
	
	@Test
	void testParsingException() {
		File f = new File("testFiles\\src\\com\\jasml\\compiler\\ParsingException.java");
		HashMap<String,Integer> results=NOM.getClassNOM(f);
		HashMap<String,Integer> expected= new HashMap<>();
		expected.put("ParsingException", 6);
		assertEquals(results,expected);
	}
	
	@Test
	void testSourceCodeParser() {
		File f = new File("testFiles\\src\\com\\jasml\\compiler\\SourceCodeParser.java");
		HashMap<String,Integer> results=NOM.getClassNOM(f);
		HashMap<String,Integer> expected= new HashMap<>();
		expected.put("SourceCodeParser", 29);
		expected.put("SourceCodeParser.OpcodeWrapper", 2);
		expected.put("SourceCodeParser.LabeledInstructions", 1);
		assertEquals(results,expected);
	}
	
	@Test
	void testFNF() {
		File f = new File("dasdasdas");
		HashMap<String,Integer> results=NOM.getClassNOM(f);
		assertNull(results);
	}
	

}
