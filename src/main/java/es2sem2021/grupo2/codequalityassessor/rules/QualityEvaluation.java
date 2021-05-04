package es2sem2021.grupo2.codequalityassessor.rules;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es2sem2021.grupo2.codequalityassessor.metrics.MetricsExtractor;
import es2sem2021.grupo2.codequalityassessor.xlsx.FileGenerator;
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
	
	
	/**
	 * Constructor of the class QualityEvaluation that will evaluate the quality of the codesmells is_God_Class and is_Long_Method according to the default xlsx file given by the teachers
	 * If there is some issue with the creation of the workbook it will throw an IOException and if the workbook's first sheet is empty or the name of the rows are incorrect it will throw an IllegalArgumentException
	 * 
	 * @param fr FinalResults object that contains the detected codesmells
	 * @throws IllegalArgumentException or IOException
	 */
	public QualityEvaluation() {
		File folder;
		FileInputStream file;
		Workbook workbook=new XSSFWorkbook();
		try {
			folder = new File("testFiles" + File.separator + "src" + File.separator + "com" + File.separator + "jasml");
			List<Method> methods = new ArrayList<>();
			FileGenerator.parseFolders(folder,methods);
			HashMap<String,HashMap<String,Boolean>> codeSmells = FinalResults.getRulesResults(methods);
			HashMap<String,Boolean> godClass = codeSmells.get("is_God_Class");
			HashMap<String,Boolean> longMethod = codeSmells.get("is_Long_Method");
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
				String key = className + "." + methodName;
				if(godClass.get(key)!=null && row.getCell(godClassIndex).getCellType() == Cell.CELL_TYPE_BOOLEAN) {
					Boolean godClassValue = row.getCell(godClassIndex).getBooleanCellValue();
					if(!truePositivesGC.contains(className) && godClassValue && godClass.get(key))
						truePositivesGC.add(className);
					if(!trueNegativesGC.contains(className) && !godClassValue && !godClass.get(key))
						trueNegativesGC.add(className);
					if(!falsePositivesGC.contains(className) && !godClassValue && godClass.get(key))
						falsePositivesGC.add(className);
					if(!falseNegativesGC.contains(className) && godClassValue && !godClass.get(key))
						falseNegativesGC.add(className);
				}
				if(longMethod.get(key)!=null && row.getCell(longMethodIndex).getCellType() == Cell.CELL_TYPE_BOOLEAN) {
					Boolean longMethodValue = row.getCell(longMethodIndex).getBooleanCellValue();
					if(longMethodValue && longMethod.get(key))
						truePositivesLM.add(methodName);
					if(!longMethodValue && !longMethod.get(key))
						trueNegativesLM.add(methodName);	
					if(!longMethodValue && longMethod.get(key))
						falsePositivesLM.add(methodName);	
					if(longMethodValue && !longMethod.get(key))
						falseNegativesLM.add(methodName);
				}
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
