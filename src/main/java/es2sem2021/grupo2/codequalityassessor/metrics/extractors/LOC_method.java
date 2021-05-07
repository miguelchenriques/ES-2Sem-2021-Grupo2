package es2sem2021.grupo2.codequalityassessor.metrics.extractors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import com.github.javaparser.utils.Pair;


public class LOC_method {	  
	 
	/**
	 *Returns the names of every method and constructor in the file with it's corresponding lines of code. The name of the
	 *The name of the methods returns with the following format: "ClassName.MethodName(Parameters)"
	 *The name of the constructors returns with the following format: "ConstructorName(Parameters)"
	 *
	 * @param f		java file to count methods lines of code
	 * @return		hashmap with the method name and the lines of code
	*/
	public static HashMap<String, Integer> getLOCMethod(File f) {
		try {
			CompilationUnit compilationUnit = StaticJavaParser.parse(f);
			LexicalPreservingPrinter.setup(compilationUnit);
			
			
			ArrayList<Pair<String, Integer>> constructors = new ArrayList<>();
			ConstructorNameCollector constName = new ConstructorNameCollector();
			constName.visit(compilationUnit, constructors);
			
			ArrayList<Pair<String, Integer>> methods = new ArrayList<>();
			MethodNameCollector methodName = new MethodNameCollector();
			methodName.visit(compilationUnit, methods);				
			
			constructors.addAll(methods);
			
			return getResults(constructors);
		} catch (FileNotFoundException | ParseProblemException e) {	
			return null;
		}
	}
	
	
	/**
	 * Transforms a List of pairs (methodName, lines of code) to an hashmap where the methodName is stored as a key to
	 * it's lines of code
	 * 
	 * @param classes	list of pairs methodName,LOC
	 * @return			Hashmap with methodName as key to it's LOC
	 */
	
	private static HashMap<String, Integer> getResults(ArrayList<Pair<String, Integer>> methods) {
		if (methods.size()<1) return null;
		
		HashMap<String, Integer> results = new HashMap<>();
		
		for (Pair<String, Integer> pair: methods) {
			results.put(pair.a, pair.b);
		} 
		return results;
	}
	
	
	public static class MethodNameCollector extends VoidVisitorAdapter<List<Pair<String, Integer>>>{
	    @Override
	    public void visit(MethodDeclaration n, List<Pair<String, Integer>> collector) {
	        super.visit(n, collector);
	        String s = n.getDeclarationAsString(false,false,false);
	        s = s.substring(s.indexOf(" ")+1);
	        String className = ((ClassOrInterfaceDeclaration)n.getParentNode().get()).getNameAsString();
	        s = className + "." + s;
	        int lines = countLines(LexicalPreservingPrinter.print(n));
	        collector.add(new Pair<String, Integer>(s, lines));
	    }
	}
	
	public static class ConstructorNameCollector extends VoidVisitorAdapter<List<Pair<String, Integer>>>{
	    @Override
	    public void visit(ConstructorDeclaration n, List<Pair<String, Integer>> collector) {
	        super.visit(n, collector);
	        String s = n.getDeclarationAsString(false,false,false);
	        int lines = countLines(LexicalPreservingPrinter.print(n));
	        collector.add(new Pair<String, Integer>(s, lines));
	    }
	}
	
	/**
	 * Returns the number of lines of code, counting comments but not blank lines
	 * 
	 * @param classCode		the full method code
	 * @return				the lines of code number
	 */
	private static int countLines(String code) {
		List<String> lines = Arrays.asList(code.split("\r\n"));
		return (int) lines.stream()
				.map(l -> l.replaceAll("\t", ""))
				.filter(l -> !l.equals(""))
				.count();
	}
}
