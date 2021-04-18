package es2sem2021.grupo2.codequalityassessor.metrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.utils.Pair;

import es2sem2021.grupo2.codequalityassessor.metrics.extractors.CYCLO_method;
import es2sem2021.grupo2.codequalityassessor.metrics.extractors.LOCClass;
import es2sem2021.grupo2.codequalityassessor.metrics.extractors.LOC_method;
import es2sem2021.grupo2.codequalityassessor.metrics.extractors.NOM;
import es2sem2021.grupo2.codequalityassessor.metrics.extractors.WMCClass;
import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

public class MetricsExtractor {
	
	/**
	 * Returns the list of Method objects with all the metrics from the java file passed as argument
	 * 
	 * @param f		the java file to extract metrics and methods
	 * @return		list of Method objects 
	 * @throws FileNotFoundException
	 */
	public static List<Method> extract(File f) throws FileNotFoundException {
		List<Method> methods = new ArrayList<Method>();
		
		String packageName = getPackage(f);
		
		HashMap<String, Integer> locClass = LOCClass.getClassLOC(f);
		HashMap<String, Integer> locMethod = LOC_method.getLOCMethod(f);
		HashMap<String, Integer> cycloMethod = CYCLO_method.getMethodCyclo(f);
		HashMap<String, Integer> nom = NOM.getClassNOM(f);
		HashMap<String, Integer> wmcClass = WMCClass.getClassWMC(f);
		
		if(locClass!=null) {
			String mainClass = getMainClass(locClass.keySet());
			if(nom!=null && locMethod==null) {
				methods.add(new Method (packageName,mainClass,mainClass+"()",0,locClass.get(mainClass),0,0,0));
			}
			if(locMethod!=null && cycloMethod!= null && nom!=null && wmcClass!=null) {
				for (String method: locMethod.keySet()) {
				Pair<String, String> methodPair = getMethodPair(method);
				String className = methodPair.a;
				String methodDeclaration = methodPair.b;
				if(!mainClass.equals(className)) {
					className=mainClass + "." + className;
				}	
				methods.add(new Method( packageName, className, methodDeclaration, nom.get(className), locClass.get(className),wmcClass.get(className) , locMethod.get(method), cycloMethod.get(method)));
				}				
			}			
		}
		//methods.add(new Method( packageName, "Fill", "Main", 10, 11, 12, 14, 15));
		return methods;
	}
	
	/**
	 * Returns the name of the Parent class from the set of classes passed. The classes inside the Set must be 
	 * "ParentClass.InnerClass1" for a inner class and "ParentClass" for the parent class.
	 * 
	 * @param classes	the set of classes in the file
	 * @return			the parent class name
	 */
	private static String getMainClass(Set<String> classes) {
		for (String s: classes) {
			if (!s.contains(".")) {
				return s;
			}
		}
		return null;
	}
	
	/**
	 * Returns a pair with the first element being the class name and the second being the method name and it's
	 * parameter types. The method passed can be a constructor or a method, if it is a method it has do be passed
	 * with the following format: "Class.method(ParamType1, ParamType2, etc..)"
	 * If it is a constructor: "Class(ParamType1, ParamType2, etc...)"
	 * 
	 * @param method    the method or constructor declaration
	 * @return			A pair with class as the first element and the name and parameters as the second element
	 */
	private static Pair<String, String> getMethodPair(String method) {
		// Pattern para verificar se existe um ponto entre nomes por ex: Class.Methodo(Tipo, int)
		// Pattern falha se for depois dos parenteses por ex: Construtor(Tipo.OutroTipo, int, String)
		Pattern patternStart = Pattern.compile("^[a-z0-9\\_]*\\.[a-z0-9]*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = patternStart.matcher(method);
		
		if (matcher.find()) {
			int ponto = method.indexOf(".");
			String className = method.substring(0, ponto);
			String methodDeclaration = method.substring(ponto+1);
			return new Pair<String, String>(className, methodDeclaration);
		}
		
		String className = method.substring(0, method.indexOf("("));
		return new Pair<String, String>(className, method);
	}
	
	/**
	 * Returns the package name from the file java file passed as argument if it exists a package declaration,
	 * return an empty string if it doesn't exist.
	 * 
	 * @param f  java file to be parsed
	 * @return   the package name
	 * @throws FileNotFoundException
	 */
	private static String getPackage(File f){
		CompilationUnit compilationUnit;
		try {
			compilationUnit = StaticJavaParser.parse(f);
			Optional<PackageDeclaration> packageDeclaration = compilationUnit.getPackageDeclaration();
			if (packageDeclaration.isPresent()) return "";
			return packageDeclaration.get().getNameAsString();
		} catch (FileNotFoundException | ParseProblemException e) {
			// TODO Auto-generated catch block
			return "";
		}
		
		

	}
	
}
