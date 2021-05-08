package es2sem2021.grupo2.codequalityassessor.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QualityEvaluationTest {

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
	void qualityEvaluationTest() {
		RulesSet.getRules().clear();
		CodeSmells.getCodeSmells().clear();
		CodeSmells.loadFromFile();
		RulesSet.addRule("GCTest", "WMC_Class > 50 OR NOM_Class > 10");
		RulesSet.addRule("LMTest", "LOC_Method > 50 AND CYCLO_Method > 10");
		CodeSmells.getCodeSmells().put("is_Long_Method", RulesSet.getRules().get("LMTest"));
		CodeSmells.getCodeSmells().put("is_God_Class", RulesSet.getRules().get("GCTest"));
		QualityEvaluation qe = new QualityEvaluation();
		assertEquals(0, qe.getGodClassTruePositives());
		assertEquals(44, qe.getGodClassTrueNegatives());
		assertEquals(0, qe.getGodClassFalsePositives());
		assertEquals(2, qe.getGodClassFalseNegatives());
		assertEquals(0, qe.getLongMethodTruePositives());
		assertEquals(216, qe.getLongMethodTrueNegatives());
		assertEquals(0, qe.getLongMethodFalsePositives());
		assertEquals(24, qe.getLongMethodFalseNegatives());
	}

}
