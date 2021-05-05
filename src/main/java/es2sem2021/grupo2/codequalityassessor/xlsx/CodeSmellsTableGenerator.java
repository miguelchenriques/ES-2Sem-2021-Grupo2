package es2sem2021.grupo2.codequalityassessor.xlsx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import es2sem2021.grupo2.codequalityassessor.gui.CodeSmellsPanel;
import es2sem2021.grupo2.codequalityassessor.metrics.MetricsExtractor;
import es2sem2021.grupo2.codequalityassessor.rules.Rule;
import es2sem2021.grupo2.codequalityassessor.rules.RulesSet;

public class CodeSmellsTableGenerator {

	private File folder;

	public CodeSmellsTableGenerator(File folder) {
		this.folder = folder;
	}

	public void main() throws IOException, InvalidFormatException {

		List<Method> methods = new ArrayList<>();
		parseFolders(folder, methods);
		CodeSmellsPanel.updateColunms( methods);
	}

	
	static void parseFolders(File folder, List<Method> methods) throws FileNotFoundException {
		for (File f: folder.listFiles()) {
			//System.out.println(f.getName());
			if(f.isDirectory())
				parseFolders(f,methods);
			else
				methods.addAll(MetricsExtractor.extract(f));  
		}
	}

}
