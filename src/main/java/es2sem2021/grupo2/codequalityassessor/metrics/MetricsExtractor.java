package es2sem2021.grupo2.codequalityassessor.metrics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

public class MetricsExtractor {
	
	public static List<Method> extract(File f) {
		List<Method> methods = new ArrayList<Method>();
		methods.add(new Method(1,"abc","Fill","Main",10,11,12,true,14,15,false));
		return methods;
	}
	
}
