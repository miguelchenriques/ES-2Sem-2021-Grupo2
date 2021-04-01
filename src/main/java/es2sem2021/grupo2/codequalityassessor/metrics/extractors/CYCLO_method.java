package es2sem2021.grupo2.codequalityassessor.metrics.extractors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import com.github.javaparser.utils.Pair;

public class CYCLO_method {

	/**
	 * 
	 * @param f
	 * @return
	 */
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

	/**
	 * 
	 * @param methods
	 * @return
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
			
			List<Node> l = new ArrayList<Node>();
			Visitor v = new Visitor();
			v.visit(n, l);
			
			String s = n.getDeclarationAsString(false,false,false);
			s = s.substring(s.indexOf(" ")+1);
			String className = ((ClassOrInterfaceDeclaration)n.getParentNode().get()).getNameAsString();
			s = className + "." + s;
			
			collector.add(new Pair<String, Integer>(s, l.size()+1));
		}
	}

	public static class ConstructorNameCollector extends VoidVisitorAdapter<List<Pair<String, Integer>>>{
		@Override
		public void visit(ConstructorDeclaration n, List<Pair<String, Integer>> collector) {
			super.visit(n, collector);
			
			List<Node> l = new ArrayList<Node>();
			Visitor v = new Visitor();
			v.visit(n, l);
						
			String s = n.getDeclarationAsString(false,false,false);
			String className = ((ClassOrInterfaceDeclaration)n.getParentNode().get()).getNameAsString();
			s = className + "." + s;

			collector.add(new Pair<String, Integer>(s, l.size()+1));
		}
	}

	public static class Visitor extends VoidVisitorAdapter<List<Node>>{
		@Override
		public void visit(ForStmt fs, List<Node> collector) {
			super.visit(fs, collector);
			collector.add(fs);
		}

		@Override
		public void visit(ForEachStmt fes, List<Node> collector) {
			super.visit(fes, collector);
			collector.add(fes);
		}

		@Override
		public void visit(IfStmt is, List<Node> collector) {
			super.visit(is, collector);
			collector.add(is);
		}

		@Override
		public void visit(WhileStmt ws, List<Node> collector) {
			super.visit(ws, collector);
			collector.add(ws);
		}
		
		@Override
		public void visit(SwitchStmt ss, List<Node> collector) {
			super.visit(ss, collector);
			collector.addAll(ss.getEntries());
		}
		
		@Override
		public void visit(DoStmt ds, List<Node> collector) {
			super.visit(ds, collector);
			collector.add(ds);
		}	
	}	
}
