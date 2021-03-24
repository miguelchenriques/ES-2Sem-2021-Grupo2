package es2sem2021.grupo2.codequalityassessor.metrics.extractors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class LOCClassTest {

	@Test
	void GrammerExceptionLines() throws IOException {
		File f = new File("C:\\jasml_0.10\\src\\com\\jasml\\compiler\\GrammerException.java");
		int numberLines = LOCClass.getClassLOC(f);
		assertEquals(18, numberLines);
	}
	
	@Test
	void ParsingExceptionLines() throws IOException {
		File f = new File("C:\\jasml_0.10\\src\\com\\jasml\\compiler\\ParsingException.java");
		int numberLines = LOCClass.getClassLOC(f);
		assertEquals(50, numberLines);
	}

}
