package es2sem2021.grupo2.codequalityassessor.metrics.extractors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import com.github.javaparser.utils.Pair;

public class WMCClass {

	//TODO: Add javadocs
	/***
	 * 
	 * Returns a hashmap with all the classes names present in the file f as keys and an integer representing the sum of
	 * cyclomatic complexity of the class' methods
	 * 
	 * @param f	java file to be parsed
	 * @return	the classes and the sum of its method's cyclomatic complexity
	 */
	public static HashMap<String, Integer> getClassWMC(File f) {
		try {
			CompilationUnit compilationUnit = StaticJavaParser.parse(f);
			LexicalPreservingPrinter.setup(compilationUnit);
			HashMap<String, Integer> cycloMethod = CYCLO_method.getMethodCyclo(f);
			if (cycloMethod == null) return null;
			ArrayList<String> classes = new ArrayList<>();
			ClassNameCollector className = new ClassNameCollector();
			className.visit(compilationUnit, classes);
			


			return getResults(getPair(classes,cycloMethod));

		} catch (FileNotFoundException | ParseProblemException e) {
			return null;
		}
	}

	private static ArrayList<Pair<String, Integer>> getPair(ArrayList<String> classes, HashMap<String, Integer> cycloMethod ) {
		if (classes.size() < 1)
			return null;
		
		ArrayList<Pair<String, Integer>> results = new ArrayList<>();

		for(String classString : classes ){
			int count=0;
			for(String s : cycloMethod.keySet()) {	
				String[] c;
				if(s.indexOf(".")!=-1 && s.indexOf(".")<s.indexOf("("))
					c = s.split("\\.");
				else
					c = s.split("\\(");
				if(c[0].equals(classString)) {
					count += cycloMethod.get(s);			
				}
			}
			results.add(new Pair<String,Integer>(classString,count));
		}
		return results;
	}
	
	
	/***
	 * 
	 * Transforms an array of pairs (class, wmc) to an hashmap where inner classes names are defined as Outter.Inner
	 * 
	 * @param classes	array of pairs (classes, wmc)
	 * @return			hashmap with the class name as key and it's wmc
	 */
	private static HashMap<String,Integer> getResults(ArrayList<Pair<String,Integer>> classes){
		if (classes.size()<1) return null;
		
		Pair<String, Integer> main = classes.remove(classes.size()-1);
		HashMap<String, Integer> results = new HashMap<>();
		
		for (Pair<String, Integer> pair: classes) {
			results.put(main.a+ "." + pair.a, pair.b);
			main= new Pair<String,Integer>(main.a,main.b+ pair.b); 
		}
		results.put(main.a,main.b);
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
