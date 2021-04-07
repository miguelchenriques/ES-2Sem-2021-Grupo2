package es2sem2021.grupo2.codequalityassessor.metrics.extractors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import com.github.javaparser.utils.Pair;

public class WMCClass {

	public static HashMap<String, Integer> getClassWMC(File f) {
		try {
			CompilationUnit compilationUnit = StaticJavaParser.parse(f);
			LexicalPreservingPrinter.setup(compilationUnit);

			HashMap<String, Integer> cycloMethod = CYCLO_method.getMethodCyclo(f);

			ArrayList<String> classes = new ArrayList<>();
			ClassNameCollector className = new ClassNameCollector();
			className.visit(compilationUnit, classes);

			String main = cycloMethod.keySet().iterator().next();
			main=main.substring(0,main.indexOf('.'));

			HashMap<String, Integer> classWMC= new HashMap<String, Integer>();
			for(String s : classes) {
				classWMC.put(s, 0);	
			}


			return getResults(classes,main,classWMC,cycloMethod);

		} catch (FileNotFoundException | ParseProblemException e) {
			return null;
		}
	}

	private static HashMap<String, Integer> getResults(ArrayList<String> classes, String main, HashMap<String, Integer> classWMC, HashMap<String, Integer> cycloMethod ) {
		if (classes.size() < 1)
			return null;

		HashMap<String, Integer> results = new HashMap<>();

		for(String classString : classes ){
			for(String s : cycloMethod.keySet()) {
				String[] c = s.split("\\.");
				if(c[0].equals(classString)) {
					int count = classWMC.get(classString);
					if(count==0) {
						classWMC.put(classString, cycloMethod.get(s));
					} 
					if(count!=0){
						count=count+ cycloMethod.get(s);
						classWMC.put(classString, count);
					}			
				}
			}
		}

		for (String s: classWMC.keySet()) {
			if(s.equals(main))
				results.put(s,classWMC.get(s));
			if(!s.equals(main))
				results.put(main + "." + s,classWMC.get(s));
		}
		return results;
	}


	public static class ClassNameCollector extends VoidVisitorAdapter<List<String>> {
		@Override
		public void visit(ClassOrInterfaceDeclaration n, List<String> collector) {
			super.visit(n, collector);

			collector.add(n.getNameAsString());
		}
	}

}
