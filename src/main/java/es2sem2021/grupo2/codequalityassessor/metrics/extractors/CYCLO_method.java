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
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import com.github.javaparser.utils.Pair;

public class CYCLO_method {
	
	public static HashMap<String, Integer> getMethodCyclo(File f) {
		try {
			CompilationUnit compilationUnit = StaticJavaParser.parse(f);
			LexicalPreservingPrinter.setup(compilationUnit);
			
			ArrayList<Pair<String, Integer>> consts = new ArrayList<>();
			ConstructorNameCollector constsName = new ConstructorNameCollector();
			constsName.visit(compilationUnit, consts);
			
			ArrayList<Pair<String, Integer>> methods = new ArrayList<>();
			MethodNameCollector methodName = new MethodNameCollector();
			methodName.visit(compilationUnit, methods);
			
			consts.addAll(methods);
			
			return getResults(consts);
		} catch (FileNotFoundException | ParseProblemException e) {
			return null;
		}
	}
	
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
	        int lines = countCyclo(LexicalPreservingPrinter.print(n));
	        String s = n.getDeclarationAsString(false,false,false);
            s = s.substring(s.indexOf(" ")+1);
            String className = ((ClassOrInterfaceDeclaration)n.getParentNode().get()).getNameAsString();
            s = className + "." + s;
	        collector.add(new Pair<String, Integer>(s, lines));
	    }
	}
	
	public static class ConstructorNameCollector extends VoidVisitorAdapter<List<Pair<String, Integer>>>{
	    @Override
	    public void visit(ConstructorDeclaration n, List<Pair<String, Integer>> collector) {
	        super.visit(n, collector);
	        int lines = countCyclo(LexicalPreservingPrinter.print(n));
	        String s = n.getDeclarationAsString(false,false,false);
            s = s.substring(s.indexOf(" ")+1);
            String className = ((ClassOrInterfaceDeclaration)n.getParentNode().get()).getNameAsString();
            s = className + "." + s;
	        collector.add(new Pair<String, Integer>(n.getDeclarationAsString(false,false,false), lines));
	    }
	}
	
	private static int countCyclo(String code) {
		String[] lines = code.split("\r\n");
		int count = 0;
		Pattern pattern = Pattern.compile("((if|while|for)\\s*)|(case\\s*)", Pattern.CASE_INSENSITIVE);
		for (String l: lines) {
			Matcher matcher = pattern.matcher(l);
			if (matcher.find()) {
				count++;
			}
		}
		
		return count;
	}
}
