package es2sem2021.grupo2.codequalityassessor.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import es2sem2021.grupo2.codequalityassessor.metrics.MetricsExtractor;
import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

public class FinalResultsTest {
	
	
	@Test
	void getRulesResultsTest() {
		File f = new File("testFiles\\src\\com\\jasml\\compiler\\ParsingException.java");	
		try {
			List<Method> methods = MetricsExtractor.extract(f);
			assertNotNull(methods);
			RulesSet.rules.clear();
			CodeSmells.codesmells.clear();
			CodeSmells.importMandatoryCodeSmells();
			RulesSet.addRule("Teste", "LOC_Method >= 5");
			boolean add = CodeSmells.addRuleToCodeSmell("is_Long_Method", RulesSet.getRules().get("Teste"));
			RulesSet.addRule("Teste2", "LOC_Class >= 5");
			boolean add2 = CodeSmells.addRuleToCodeSmell("is_God_Class", RulesSet.getRules().get("Teste2")); 
			HashMap<String,HashMap<String,Boolean>> ruleResults = FinalResults.getRulesResults(methods);
//			System.out.println(ruleResults.get(0).getMethodsresults().get(5).getMethod());
//			System.out.println(ruleResults.get(0).getMethodsresults().get(5).getResult());
//			System.out.println(ruleResults.get());
			assertEquals(2,ruleResults.size());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
