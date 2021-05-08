package es2sem2021.grupo2.codequalityassessor.metrics.extractors;

import static es2sem2021.grupo2.codequalityassessor.metrics.MetricsExtractor.getMethodSignature;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
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
	 * Returns an HashMap with all the methods belonging to the file as keys and their corresponding number of cycles.
	 * If the file doesn't exist or there is some problem with the parsing it returns null.
	 * 
	 * @param f	java file to be parsed
	 * @return the methods of the file and their number of cycles
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
	 * Returns an HashMap with all the Methods belonging to the file as keys and their corresponding number of cycles. 
	 * The HashMap is a conversion of the ArrayList created on MethodNameCollector/ConstructorNameCollector with the methods named correctly.
	 * Returns null if the ArrayList doesn't have any pair.
	 * 
	 * @param classes ArrayList with all the method names and their corresponding number of cycles
	 * @return an HashMap with all the results
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
			
			String s = getMethodSignature(n);
			
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


			collector.add(new Pair<String, Integer>(s, l.size()+1));
		}
	}

	/**
	 * 
	 *	Visitor class that will visit every possible cyclic or branching line in the class and add it to the list of nodes
	 */
	public static class Visitor extends VoidVisitorAdapter<List<Node>>{
		@Override
		public void visit(ForStmt stmt, List<Node> collector) {
			super.visit(stmt, collector);
			collector.add(stmt);
		}

		@Override
		public void visit(ForEachStmt stmt, List<Node> collector) {
			super.visit(stmt, collector);
			collector.add(stmt);
		}

		@Override
		public void visit(IfStmt stmt, List<Node> collector) {
			super.visit(stmt, collector);
			collector.add(stmt);
		}

		@Override
		public void visit(WhileStmt stmt, List<Node> collector) {
			super.visit(stmt, collector);
			collector.add(stmt);
		}
		
		@Override
		public void visit(SwitchStmt stmt, List<Node> collector) {
			super.visit(stmt, collector);
			collector.addAll(stmt.getEntries());
		}
		
		@Override
		public void visit(DoStmt stmt, List<Node> collector) {
			super.visit(stmt, collector);
			collector.add(stmt);
		}	
	}	
}
