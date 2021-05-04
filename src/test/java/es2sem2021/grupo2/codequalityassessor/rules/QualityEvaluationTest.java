package es2sem2021.grupo2.codequalityassessor.rules;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QualityEvaluationTest {

	@Test
	void qualityEvaluationTest() {
		RulesSet.rules.clear();
		CodeSmells.codesmells.clear();
		CodeSmells.importMandatoryCodeSmells();
		RulesSet.addRule("GCTest", "WMC_Class > 50 OR NOM_Class > 10");
		RulesSet.addRule("LMTest", "LOC_Method > 50 AND CYCLO_Method > 10");
		CodeSmells.codesmells.put("is_Long_Method",RulesSet.rules.get("LMTest"));
		CodeSmells.codesmells.put("is_God_Class",RulesSet.rules.get("GCTest"));
		System.out.println(CodeSmells.codesmells.get("is_Long_Method").getName());
		QualityEvaluation qe = new QualityEvaluation();
		System.out.println(qe.getGodClassTruePositives());
		System.out.println(qe.getGodClassTrueNegatives());
		System.out.println(qe.getGodClassFalsePositives());
		System.out.println(qe.getGodClassFalseNegatives());
		System.out.println(qe.getLongMethodTruePositives());
		System.out.println(qe.getLongMethodTrueNegatives());
		System.out.println(qe.getLongMethodFalsePositives());
		System.out.println(qe.getLongMethodFalseNegatives());
		
	}

}
