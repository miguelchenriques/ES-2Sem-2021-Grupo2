package es2sem2021.grupo2.codequalityassessor.metrics.extractors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.utils.Pair; 

public class NOM {
	public static HashMap<String,Integer> getClassNOM(File f){
		try {
			CompilationUnit compilationUnit = StaticJavaParser.parse(f);
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
	        int methods = countMethods(n);
	        collector.add(new Pair<String, Integer>(n.getNameAsString(), methods));
	    }
	}
	
	public static int countMethods(ClassOrInterfaceDeclaration n) {
		int methods = 0;
		for(MethodDeclaration method : n.getMethods()) {
			methods++;
		}
		for(ConstructorDeclaration constructor : n.getConstructors()) {
			methods++;
		}
		return methods;
	}
}
