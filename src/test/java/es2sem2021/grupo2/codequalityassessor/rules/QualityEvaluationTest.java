package es2sem2021.grupo2.codequalityassessor.rules;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QualityEvaluationTest {

	@Test
	void qualityEvaluationTest() { 
		RulesSet.getRules().clear();
		CodeSmells.getCodeSmells().clear();
		CodeSmells.importMandatoryCodeSmells();
		RulesSet.addRule("GCTest", "WMC_Class > 50 OR NOM_Class > 10");
		RulesSet.addRule("LMTest", "LOC_Method > 50 AND CYCLO_Method > 10");
		CodeSmells.getCodeSmells().put("is_Long_Method",RulesSet.getRules().get("LMTest"));
		CodeSmells.getCodeSmells().put("is_God_Class",RulesSet.getRules().get("GCTest"));
		QualityEvaluation qe = new QualityEvaluation();
		assertEquals(qe.getGodClassTruePositives(),2);
		assertEquals(qe.getGodClassTrueNegatives(),40);
		assertEquals(qe.getGodClassFalsePositives(),4);
		assertEquals(qe.getGodClassFalseNegatives(),0);
		assertEquals(qe.getLongMethodTruePositives(),18);
		assertEquals(qe.getLongMethodTrueNegatives(),215);
		assertEquals(qe.getLongMethodFalsePositives(),1);
		assertEquals(qe.getLongMethodFalseNegatives(),6);
	}

}
