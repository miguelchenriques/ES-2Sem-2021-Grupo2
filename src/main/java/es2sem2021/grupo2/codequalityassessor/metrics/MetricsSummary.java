package es2sem2021.grupo2.codequalityassessor.metrics;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import es2sem2021.grupo2.codequalityassessor.rules.Constants;
import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

public class MetricsSummary {
	private ArrayList<Method> methods = new ArrayList<>();
	private ArrayList<String> packages = new ArrayList<>();
	private ArrayList<String> classes = new ArrayList<>();
	private int totalLOC = 0;
	
	/**
	 * Constructor of the MetricsSummary class, that gets all the metrics from an xlsx file
	 * If the workbook's first sheet doesn't have any rows or the rows don't have all the correct metrics' names it returns an IllegalArgumentException
	 * 
	 * @param workbook workbook that has the metrics to be summarized 
	 * @throws IllegalArgumentException
	 */
	
	public MetricsSummary(Workbook workbook) {
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		if(!rowIterator.hasNext())
			throw new IllegalArgumentException("An error has occured, xlsx file doesn't have any rows, please try again.");
	
		Row firstRow = rowIterator.next();
		int classIndex = -1;
		int packageIndex = -1;
		int methodIndex = -1;
		int lOCClassIndex = -1;
		int nOMClassIndex = -1;
		int wMCClassIndex = -1;
		int lOCMethodIndex = -1;
		int cYCLOMethodIndex = -1;
		for (int i=firstRow.getFirstCellNum(); i<firstRow.getLastCellNum();i++) {
			if(firstRow.getCell(i) == null) 
				throw new IllegalArgumentException("An error has occured, your rows don't have the correct metrics' name, please check the syntax again.");
			if(firstRow.getCell(i).getStringCellValue().equals("Package")) 
				packageIndex = i;
			if(firstRow.getCell(i).getStringCellValue().equals("Class")) 
				classIndex = i;
			if(firstRow.getCell(i).getStringCellValue().equals("Method")) 
				methodIndex = i;
			if(firstRow.getCell(i).getStringCellValue().equals(Constants.NOM_CLASS)) 
				nOMClassIndex = i;
			if(firstRow.getCell(i).getStringCellValue().equals(Constants.LOC_CLASS)) 
				lOCClassIndex = i;
			if(firstRow.getCell(i).getStringCellValue().equals(Constants.WMC_CLASS)) 
				wMCClassIndex = i;
			if(firstRow.getCell(i).getStringCellValue().equals(Constants.LOC_METHOD)) 
				lOCMethodIndex = i;
			if(firstRow.getCell(i).getStringCellValue().equals(Constants.CYCLO_METHOD)) 
				cYCLOMethodIndex = i;	
		}
		if(classIndex == -1 || packageIndex == -1 || methodIndex == -1 || nOMClassIndex == -1 || lOCClassIndex == -1 || wMCClassIndex == -1 || lOCMethodIndex == -1 || cYCLOMethodIndex == -1) 
			throw new IllegalArgumentException("An error has occured, your rows don't have the correct metrics' name, please check the syntax again.");	
		
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			String packageName = row.getCell(packageIndex).getStringCellValue();
			String className = row.getCell(classIndex).getStringCellValue();
			String methodName = row.getCell(methodIndex).getStringCellValue();
			int nOMClass = (int) row.getCell(nOMClassIndex).getNumericCellValue();
			int lOCClass = (int) row.getCell(lOCClassIndex).getNumericCellValue();
			int wMCClass = (int) row.getCell(wMCClassIndex).getNumericCellValue();
			int lOCMethod = (int) row.getCell(lOCMethodIndex).getNumericCellValue();
			int cYCLOMethod = (int) row.getCell(cYCLOMethodIndex).getNumericCellValue();
			methods.add(new Method(packageName,className,methodName,nOMClass,lOCClass,wMCClass,lOCMethod,cYCLOMethod));
			if(!classes.contains(className)) { 
				if(!className.contains("."))
					totalLOC+=lOCClass;
				classes.add(className);
			}
			if(!packages.contains(packageName))
				packages.add(packageName);
		}
	}
		
	/**
	 * @return the number of java packages
	 */
	public int getNumberOfPackages() {
		return packages.size();
	}
	
	/**
	 * @return number of classes, both normal classes as inner classes
	 */
	public int getNumberOfClasses() {
		return classes.size();
	}
	
	/**
	 * @return sum of the total non blanck lines of code in the java project
	 */
	public int getTotalLinesOfCode() {
		return totalLOC;
	}
	
	/**
	 * @return	sum of total methods in the selected java project
	 */
	public int getTotalNumberOfMethods() {
		return methods.size();
	}
	
	/**
	 * @return all the methods and constructors available in the selected project
	 */
	public ArrayList<Method> getMethods() {
		return methods;
	}
	
}
