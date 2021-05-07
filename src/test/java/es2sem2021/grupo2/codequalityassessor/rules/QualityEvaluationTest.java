package es2sem2021.grupo2.codequalityassessor.rules;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QualityEvaluationTest {

	@Test
	void qualityEvaluationTest() { 
		RulesSet.getRules().clear();
		CodeSmells.loadFromFile();
		RulesSet.addRule("GCTest", "WMC_Class > 50 OR NOM_Class > 10");
		RulesSet.addRule("LMTest", "LOC_Method > 50 AND CYCLO_Method > 10");
		CodeSmells.getCodeSmells().put("is_Long_Method",RulesSet.getRules().get("LMTest"));
		CodeSmells.getCodeSmells().put("is_God_Class",RulesSet.getRules().get("GCTest"));
		QualityEvaluation qe = new QualityEvaluation();
		assertEquals(0,qe.getGodClassTruePositives());
		assertEquals(44,qe.getGodClassTrueNegatives());
		assertEquals(0,qe.getGodClassFalsePositives());
		assertEquals(2,qe.getGodClassFalseNegatives());
		assertEquals(0,qe.getLongMethodTruePositives());
		assertEquals(216,qe.getLongMethodTrueNegatives());
		assertEquals(0,qe.getLongMethodFalsePositives());
		assertEquals(24,qe.getLongMethodFalseNegatives());
	}

}
