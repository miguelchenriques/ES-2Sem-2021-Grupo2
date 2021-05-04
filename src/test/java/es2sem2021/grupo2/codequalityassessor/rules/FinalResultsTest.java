package es2sem2021.grupo2.codequalityassessor.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
			RulesSet.addRule("Teste", "LOC_Method >= 5");
			ArrayList<RuleResults> ruleResults = FinalResults.getRulesResults(methods);
			System.out.println(ruleResults.get(0).getMethodsresults().get(5).getMethod());
			System.out.println(ruleResults.get(0).getMethodsresults().get(5).getResult());
			assertEquals(1,ruleResults.size());
			System.out.println();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
