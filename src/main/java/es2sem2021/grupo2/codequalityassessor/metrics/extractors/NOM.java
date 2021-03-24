package es2sem2021.grupo2.codequalityassessor.metrics.extractors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter; 

public class NOM {
	public static void main( String[] args )
	{
		System.out.println(nom("C:\\Users\\MestreGui\\Downloads\\jasml_0.10\\src\\com\\jasml\\compiler\\GrammerException.java"));
	}

	private static class MethodNameCollector extends VoidVisitorAdapter<List<String>> {			
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
	}
	public static int nom(String filepath){
		int res=0;
		try {
			CompilationUnit compilationUnit = StaticJavaParser.parse(new File(filepath));
			List<String> methodNames = new ArrayList<>();
			VoidVisitor<List<String>> methodNameCollector = new MethodNameCollector();
			VoidVisitor<List<String>> constructorNameCollector = new ConstructorNameCollector();
			methodNameCollector.visit(compilationUnit, methodNames);
			constructorNameCollector.visit(compilationUnit, methodNames);
			//methodNames.forEach(n -> System.out.println("Method Name Collected: " + n));
			res=methodNames.size();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;

	}
}