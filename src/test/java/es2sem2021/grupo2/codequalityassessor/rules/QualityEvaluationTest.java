package es2sem2021.grupo2.codequalityassessor.rules;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QualityEvaluationTest {

	@Test
	void qualityEvaluationTest() { 
		RulesSet.getRules().clear();
		CodeSmells.getCodeSmells().clear();
		CodeSmells.loadFromFile();
		RulesSet.addRule("GCTest", "WMC_Class > 50 OR NOM_Class > 10");
		RulesSet.addRule("LMTest", "LOC_Method > 50 AND CYCLO_Method > 10");
		CodeSmells.getCodeSmells().put("is_Long_Method",RulesSet.getRules().get("LMTest"));
		CodeSmells.getCodeSmells().put("is_God_Class",RulesSet.getRules().get("GCTest"));
		QualityEvaluation qe = new QualityEvaluation();
		assertEquals(46,qe.getGodClassTruePositives()+qe.getGodClassTrueNegatives()+qe.getGodClassFalsePositives()+qe.getGodClassFalseNegatives());
		assertEquals(240,qe.getLongMethodTruePositives()+qe.getLongMethodTrueNegatives()+qe.getLongMethodFalsePositives()+qe.getLongMethodFalseNegatives());
	}

}
