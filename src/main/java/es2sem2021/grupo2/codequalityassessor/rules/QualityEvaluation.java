package es2sem2021.grupo2.codequalityassessor.rules;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

public class QualityEvaluation {
	private ArrayList<String> truePositivesGC = new ArrayList<>();
	private ArrayList<String> trueNegativesGC = new ArrayList<>();
	private ArrayList<String> falsePositivesGC = new ArrayList<>();
	private ArrayList<String> falseNegativesGC = new ArrayList<>();
	private ArrayList<String> truePositivesLM = new ArrayList<>();
	private ArrayList<String> trueNegativesLM = new ArrayList<>();
	private ArrayList<String> falsePositivesLM = new ArrayList<>();
	private ArrayList<String> falseNegativesLM = new ArrayList<>();
	
	public QualityEvaluation(FinalResults fr) {
		//Buscar tanto os results do long method como os da god class
		FileInputStream file;
		Workbook workbook=new XSSFWorkbook();
		try {
			file = new FileInputStream(new File("Code_Smells.xlsx"));
			workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			if(!rowIterator.hasNext())
				throw new IllegalArgumentException("An error has occured, xlsx file doesn't have any rows, please try again.");
			
			Row firstRow = rowIterator.next();
			int classIndex = -1;
			int methodIndex = -1;
			int godClassIndex = -1;
			int longMethodIndex = -1;
			for (int i=firstRow.getFirstCellNum(); i<firstRow.getLastCellNum();i++) {
				if(firstRow.getCell(i).getStringCellValue().equals("class")) 
					classIndex = i;
				if(firstRow.getCell(i).getStringCellValue().equals("method")) 
					methodIndex = i;
				if(firstRow.getCell(i).getStringCellValue().equals("is_God_Class")) 
					godClassIndex = i;
				if(firstRow.getCell(i).getStringCellValue().equals("is_Long_Method")) 
					longMethodIndex = i;
			}
			if(classIndex == -1 || methodIndex == -1 || godClassIndex == -1 || longMethodIndex == -1) 
				throw new IllegalArgumentException("An error has occured, your rows don't have the correct metrics' name, please check the syntax again.");	
			
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				String className = row.getCell(classIndex).getStringCellValue();
				String methodName = row.getCell(methodIndex).getStringCellValue();
				String godClassValue = row.getCell(methodIndex).getStringCellValue();
				String longMethodValue = row.getCell(methodIndex).getStringCellValue();
				if(!truePositivesGC.contains(className) && godClassValue.equals("VERDADEIRO") /*&& obter valor do godclass ver se é verdadeiro*/)
					truePositivesGC.add(className);
				if(!trueNegativesGC.contains(className) && godClassValue.equals("FALSO") /*&& obter valor do godclass ver se é falso*/)
					trueNegativesGC.add(className);
				if(!falsePositivesGC.contains(className) && godClassValue.equals("FALSO") /*&& obter valor do godclass ver se é verdadeiro*/)
					falsePositivesGC.add(className);
				if(!falseNegativesGC.contains(className) && godClassValue.equals("VERDADEIRO") /*&& obter valor do godclass ver se é falso*/)
					falseNegativesGC.add(className);
				if(!truePositivesLM.contains(methodName) && longMethodValue.equals("VERDADEIRO") /*&& obter valor do longmethod ver se é verdadeiro*/)
					truePositivesLM.add(methodName);
				if(!trueNegativesLM.contains(methodName) && longMethodValue.equals("FALSO") /*&& obter valor do longmethod ver se é falso*/)
					trueNegativesLM.add(methodName);	
				if(!falsePositivesLM.contains(methodName) && longMethodValue.equals("FALSO") /*&& obter valor do longmethod ver se é verdadeiro*/)
					falsePositivesLM.add(methodName);	
				if(!falseNegativesLM.contains(methodName) && longMethodValue.equals("VERDADEIRO") /*&& obter valor do longmethod ver se é falso*/)
					falseNegativesLM.add(methodName);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getGodClassTruePositives() {
		return truePositivesGC.size();
	}
	
	public int getGodClassTrueNegatives() {
		return trueNegativesGC.size();
	}
	
	public int getGodClassFalsePositives() {
		return falsePositivesGC.size();
	}
	
	public int getGodClassFalseNegatives() {
		return falseNegativesGC.size();
	}
	
	public int getLongMethodTruePositives() {
		return truePositivesLM.size();
	}
	
	public int getLongMethodTrueNegatives() {
		return trueNegativesLM.size();
	}
	
	public int getLongMethodFalsePositives() {
		return falsePositivesLM.size();
	}
	
	public int getLongMethodFalseNegatives() {
		return falseNegativesLM.size();
	}
}