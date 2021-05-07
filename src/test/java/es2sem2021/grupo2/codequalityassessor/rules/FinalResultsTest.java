package es2sem2021.grupo2.codequalityassessor.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
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
			RulesSet.getRules().clear();
			CodeSmells.getCodeSmells().clear();
			CodeSmells.importMandatoryCodeSmells();
			RulesSet.addRule("Teste", "LOC_Method >= 5");
			CodeSmells.addRuleToCodeSmell("is_Long_Method", RulesSet.getRules().get("Teste"));
			HashMap<String,HashMap<String,Boolean>> ruleResults = FinalResults.getRulesResults(methods);
			assertEquals(2,ruleResults.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
