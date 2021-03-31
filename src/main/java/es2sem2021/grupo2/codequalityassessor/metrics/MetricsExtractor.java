package es2sem2021.grupo2.codequalityassessor.metrics;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.javaparser.utils.Pair;

import es2sem2021.grupo2.codequalityassessor.metrics.extractors.CYCLO_method;
import es2sem2021.grupo2.codequalityassessor.metrics.extractors.LOCClass;
import es2sem2021.grupo2.codequalityassessor.metrics.extractors.LOC_method;
import es2sem2021.grupo2.codequalityassessor.metrics.extractors.NOM;
import es2sem2021.grupo2.codequalityassessor.metrics.extractors.WMCClass;
import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

public class MetricsExtractor {
	
	public static List<Method> extract(File f) {
		List<Method> methods = new ArrayList<Method>();
		
		HashMap<String, Integer> locClass = LOCClass.getClassLOC(f);
		HashMap<String, Integer> locMethod = LOC_method.getLOCMethod(f);
		HashMap<String, Integer> cycloMehod = CYCLO_method.getMethodCyclo(f);
		HashMap<String, Integer> nom = NOM.getClassNOM(f);
		HashMap<String, Integer> wmcClass = WMCClass.getClassWMC(f);
		
		String mainClass = getMainClass(locClass.keySet());
		
		for (String method: locMethod.keySet()) {
			Pair<String, String> methodPair = getMethodPair(method);
			String className = methodPair.a;
			String methodDeclaration = methodPair.b;
		}
		
		methods.add(new Method( "abc", "Fill", "Main", 10, 11, 12, 14, 15));
		return methods;
	}
	
	
	private static String getMainClass(Set<String> classes) {
		for (String s: classes) {
			if (!s.contains(".")) {
				return s;
			}
		}
		return null;
	}
	
	private static Pair<String, String> getMethodPair(String method) {
		// Pattern para verificar se existe um ponto entre nomes por ex: Class.Methodo(Tipo, int)
		// Pattern falha se for depois dos parenteses por ex: Construtor(Tipo.OutroTipo, int, String)
		Pattern patternStart = Pattern.compile("^[a-z0-9]*\\.[a-z0-9]*", Pattern.CASE_INSENSITIVE);
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
	
}
