package es2sem2021.grupo2.codequalityassessor.xlsx;

import java.io.File;
import java.io.FileNotFoundException;
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

import es2sem2021.grupo2.codequalityassessor.metrics.MetricsExtractor;



public class FileGenerator {

	public String fileName;
	public File folder;
	
	public FileGenerator(File folder) {
		this.folder = folder;
		this.fileName = folder.getName();
	}

	private static String[] columns = { "MethodID", "Package", "Class", "Method", "NOM_class", "LOC_class", "WMC_class",
			"is_God_Class", "LOC_method", "CYCLO_method", "is_Long_Method" };

	/**
	 * Creates the xlsx file with all the methods and metrics from the files in the selected folder
	 * 
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public void main() throws IOException, InvalidFormatException {
		
		List<Method> methods = new ArrayList<>();
		
		parseFolders(this.folder,methods);
		
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
			row.createCell(0).setCellValue(rowNum-1);
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
	
	void parseFolders(File folder, List<Method> methods) throws FileNotFoundException {
		for (File f: folder.listFiles()) {
			//System.out.println(f.getName());
			if(f.isDirectory())
				parseFolders(f,methods);
			else
				methods.addAll(MetricsExtractor.extract(f));
		}
	}
}
