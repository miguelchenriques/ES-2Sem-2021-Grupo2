package es2sem2021.grupo2.codequalityassessor.xlsx;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class FileGenerator {

	public String fileName;
	
	public FileGenerator(String fileName) {
		super();
		String[] dir = fileName.split("/");
		String lastOne = dir[dir.length-1];
		this.fileName = lastOne;
	}

	private static String[] columns = { "MethodID", "Package", "Class", "Method", "NOM_class", "LOC_class", "WMC_class",
			"is_God_Class", "LOC_method", "CYCLO_method", "is_Long_Method" };
	private static List<Method> methods = new ArrayList<Method>();

	public void main() throws IOException, InvalidFormatException {
		System.out.println(fileName);
		methods.add(new Method(1,"abc","Fill","Main",10,11,12,true,14,15,false));

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(fileName);

		Row headerRow = sheet.createRow(0);

		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
		}

		int rowNum = 1;

		for (Method method : methods) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(method.methodID);
			row.createCell(1).setCellValue(method.m_package);
			row.createCell(2).setCellValue(method.m_class);
			row.createCell(3).setCellValue(method.m_method);
			row.createCell(4).setCellValue(method.nom_class);
			row.createCell(5).setCellValue(method.loc_class);
			row.createCell(6).setCellValue(method.wmc_class);
			row.createCell(7).setCellValue(method.is_God_Class);
			row.createCell(8).setCellValue(method.loc_method);
			row.createCell(9).setCellValue(method.cyclo_method);
			row.createCell(10).setCellValue(method.is_Long_Method);
		}

		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}

		FileOutputStream fileOut = new FileOutputStream(fileName.concat("_metrics.xlsx"));
		System.out.println(fileName.concat("_metrics.xlsx"));
		workbook.write(fileOut);
		fileOut.close();
	}
}
