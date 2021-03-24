package es2sem2021.grupo2.codequalityassessor.metrics.extractors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NOMTest {

	@Test
	void testGrammerException() {
		assertEquals(4,NOM.nom("testFiles\\src\\com\\jasml\\compiler\\GrammerException.java"));
	}
	
	@Test
	void testParsingException() {
		assertEquals(6,NOM.nom("testFiles\\src\\com\\jasml\\compiler\\ParsingException.java"));
	}
	
	@Test
	void testSourceCodeParser() {
		assertEquals(29,NOM.nom("testFiles\\src\\com\\jasml\\compiler\\SourceCodeParser.java"));
	}
	

}
