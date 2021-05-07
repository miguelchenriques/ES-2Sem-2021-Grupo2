package es2sem2021.grupo2.codequalityassessor.xlsx;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class FileGeneratorTest {

	@Test
	void parseFolder() throws FileNotFoundException {
		List<Method> methods = new ArrayList<>();
		File folder = new File("testFiles/src/parseFolderTest");
		FileGenerator.parseFolders(folder, methods);
		List<Method> methods1 = new ArrayList<>();
		methods1.add(new Method("parseFolderTest", "ParsingException", "getMessage()", 2, 29, 7, 20, 6));
		methods1.add(new Method("parseFolderTest", "ParsingException", "ParsingException(int, int, int, String)", 2, 29, 7, 6, 1));
		methods1.add(new Method("parseFolderTest.pasta", "GrammerException", "GrammerException(int, int, int, String)", 2, 8, 2, 3, 1));
		methods1.add(new Method("parseFolderTest.pasta", "GrammerException", "GrammerException(int, String)", 2, 8, 2, 3, 1));
		assertEquals(methods1, methods);
	}

}
