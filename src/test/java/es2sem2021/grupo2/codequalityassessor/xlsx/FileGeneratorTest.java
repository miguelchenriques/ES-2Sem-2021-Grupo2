package es2sem2021.grupo2.codequalityassessor.xlsx;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class FileGeneratorTest {

	
	void init() {
		
	}
	
	@Test
	void parseFolder() {
		
		try {
			List<Method> methods = new ArrayList<>();
			File folder = new File("testFiles/src/parseFolderTest");
			FileGenerator.parseFolders(folder,methods);
			List<Method> methods1 = new ArrayList<>();
			methods1.add(new Method("","ParsingException", "getMessage()", 2, 1,1,0,1));
			methods1.add(new Method("","ParsingException", "ParsingException(int, int, int, String)", 2, 1,1,0,0));
			methods1.add(new Method("","GrammerException", "GrammerException(int, int, int, String)", 2, 1,0,0,0));
			methods1.add(new Method("","GrammerException", "GrammerException(int, String)", 2, 1,0,0,0));
			assertEquals(methods1, methods);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
