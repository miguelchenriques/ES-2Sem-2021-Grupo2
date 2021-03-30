package es2sem2021.grupo2.codequalityassessor.metrics.extractors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import com.github.javaparser.utils.Pair;

public class LOCClass {
	
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
	
	private static int countLines(String code) {
		String[] lines = code.split("\r\n");
		int firstLine = 0;
		Pattern patternStart = Pattern.compile("class\\s+", Pattern.CASE_INSENSITIVE);
		for (String l: lines) {
			Matcher matcher = patternStart.matcher(l);
			firstLine++;
			if (matcher.find()) {
				break;
			}
		}
		int lastLine = firstLine;
		Pattern patternEnd = Pattern.compile("^\\s*\\}$", Pattern.CASE_INSENSITIVE);
		for (int i=lines.length-1; i>firstLine; i--) {
		    Matcher matcher = patternEnd.matcher(lines[i]);
			if (matcher.find()) {
				lastLine = i + 1;
				break;
			}
		}
		return lastLine - firstLine + 1;
	}
}
