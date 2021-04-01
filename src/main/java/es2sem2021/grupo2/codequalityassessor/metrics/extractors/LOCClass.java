package es2sem2021.grupo2.codequalityassessor.metrics.extractors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import com.github.javaparser.utils.Pair;

public class LOCClass {
	
	/**
	 * Returns the names of every class in the file with it's corresponding lines of code. The name of the
	 * inner classes returns with the following format: "MainClass.InnerClass"
	 * 
	 * @param f		java file to count classes lines of code
	 * @return		hashmap with the class name and the lines of code
	 */
	public static HashMap<String, Integer> getClassLOC(File f) {
		try {
			CompilationUnit compilationUnit = StaticJavaParser.parse(f);
			LexicalPreservingPrinter.setup(compilationUnit);
			
			ArrayList<Pair<String, Integer>> classes = new ArrayList<>();
			ClassNameCollector className = new ClassNameCollector();
			className.visit(compilationUnit, classes);
			
			return getResults(classes);
		} catch (FileNotFoundException | ParseProblemException e) {
			return null;
		}
	}
	
	/**
	 * Transforms an List of pairs (className, lines of code) to an hashmap where the className is stored as a key to
	 * it's lines of code
	 * 
	 * @param classes	list of pairs className,LOC
	 * @return			Hashmap with className as key to it's LOC
	 */
	private static HashMap<String, Integer> getResults(ArrayList<Pair<String, Integer>> classes) {
		if (classes.size()<1) return null;
		
		Pair<String, Integer> main = classes.remove(classes.size()-1);
		HashMap<String, Integer> results = new HashMap<>();
		
		results.put(main.a, main.b);
		for (Pair<String, Integer> pair: classes) {
			results.put(main.a+ "." + pair.a, pair.b);
		}
		return results;
	}
	
	public static class ClassNameCollector extends VoidVisitorAdapter<List<Pair<String, Integer>>>{
	    @Override
	    public void visit(ClassOrInterfaceDeclaration n, List<Pair<String, Integer>> collector) {
	        super.visit(n, collector);
	        int lines = countLines(LexicalPreservingPrinter.print(n));
	        collector.add(new Pair<String, Integer>(n.getNameAsString(), lines));
	    }
	}
	
	/**
	 * Returns the number of lines of code, counting comments but not blank lines
	 * 
	 * @param classCode		the full class code
	 * @return				the lines of code number
	 */
	private static int countLines(String classCode) {
		List<String> lines = Arrays.asList(classCode.split("\r\n"));
		lines = lines.stream().filter(l -> !l.equals("")).collect(Collectors.toList());
		return lines.size();
	}
}
