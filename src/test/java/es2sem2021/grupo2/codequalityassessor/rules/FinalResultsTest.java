package es2sem2021.grupo2.codequalityassessor.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es2sem2021.grupo2.codequalityassessor.metrics.MetricsExtractor;
import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

public class FinalResultsTest {
	
	@BeforeEach
	void setUp() {
		File rules = new File(Constants.RULE_DATA_FILE);
		rules.delete();
		File codeSmells = new File(Constants.CODE_SMELLS_DATA_FILE);
		codeSmells.delete();
	}
	
	@AfterEach
	void tearDown() {
		File rules = new File(Constants.RULE_DATA_FILE);
		rules.delete();
		File codeSmells = new File(Constants.CODE_SMELLS_DATA_FILE);
		codeSmells.delete();
	}
	
	@Test
	void getRulesResultsTest() {
		File f = new File("testFiles\\src\\com\\jasml\\compiler\\ParsingException.java");	
		try {
			List<Method> methods = MetricsExtractor.extract(f);
			assertNotNull(methods);
			RulesSet.getRules().clear();
			CodeSmells.loadFromFile();
			RulesSet.addRule("Teste", "LOC_Method >= 5");
			CodeSmells.addRuleToCodeSmell("is_Long_Method", RulesSet.getRules().get("Teste"));
			HashMap<String,HashMap<String,Boolean>> ruleResults = FinalResults.getRulesResults(methods);
			assertEquals(2,ruleResults.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
