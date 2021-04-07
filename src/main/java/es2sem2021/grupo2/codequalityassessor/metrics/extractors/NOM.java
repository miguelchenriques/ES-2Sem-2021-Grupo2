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
import com.github.javaparser.utils.Pair; 

public class NOM {

	/**
	 * Returns an HashMap with all the classes belonging to the file as keys and their corresponding number of methods.
	 * If the file doesn't exist or there is some problem with the parsing it returns null.
	 * 
	 * @param f	java file to be parsed
	 * @return the classes of the file and their number of methods
	 * @throws FileNotFoundException or ParseProblemException
	 */
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
	
	/**
	 * Returns an HashMap with all the classes belonging to the file as keys and their corresponding number of methods. 
	 * The HashMap is a conversion of the ArrayList created on ClassNameCollector with the classes named correctly(The inner classes have the main class as well).
	 * Returns null if the ArrayList doesn't have any pair.
	 * 
	 * @param classes ArrayList with all the class names and their corresponding number of methods	
	 * @return an HashMap with all the results
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
	        int methods = n.getMethods().size()  + n.getConstructors().size();
	        collector.add(new Pair<String, Integer>(n.getNameAsString(), methods));
	    }
	}
}
