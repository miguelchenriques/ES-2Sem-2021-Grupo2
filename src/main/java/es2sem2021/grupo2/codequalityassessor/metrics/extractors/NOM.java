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
	/*private static class MethodNameCollector extends VoidVisitorAdapter<List<String>> {			
		@Override
		public void visit(MethodDeclaration md, List<String> collector) {
			super.visit(md, collector);
			collector.add(md.getDeclarationAsString());
		}
	}
	
	private static class ConstructorNameCollector extends VoidVisitorAdapter<List<String>> {			
		@Override
		public void visit(ConstructorDeclaration md, List<String> collector) {
			super.visit(md, collector);
			collector.add(md.getDeclarationAsString());
		}
	}*/
	
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
	/*public static int nom(String filepath){
		int res=0;
		try {
			CompilationUnit compilationUnit = StaticJavaParser.parse(new File(filepath));
			List<String> methodNames = new ArrayList<>();
			VoidVisitor<List<String>> methodNameCollector = new MethodNameCollector();
			VoidVisitor<List<String>> constructorNameCollector = new ConstructorNameCollector();
			methodNameCollector.visit(compilationUnit, methodNames);
			constructorNameCollector.visit(compilationUnit, methodNames);
			int innerMethods=0;
			//VoidVisitor<List<String>> innerMethodNameCollector = new MethodNameCollector();
			///VoidVisitor<List<String>> innerConstructorNameCollector = new ConstructorNameCollector();
			for(TypeDeclaration type : compilationUnit.getTypes()) {
		        List<BodyDeclaration> members = type.getMembers();
		        for(BodyDeclaration member : members) {
		        	if(member.isClassOrInterfaceDeclaration()) {
		        		for(MethodDeclaration method : member.asClassOrInterfaceDeclaration().getMethods()) {
		        			innerMethods++;
		        		}
		        		for(ConstructorDeclaration constructor : member.asClassOrInterfaceDeclaration().getConstructors()) {
		        			innerMethods++;
		        		}
		            }
	                
		        }
		    }
			res=methodNames.size()-innerMethods;;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;

	}*/
}
