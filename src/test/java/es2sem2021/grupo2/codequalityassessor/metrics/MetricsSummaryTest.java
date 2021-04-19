package es2sem2021.grupo2.codequalityassessor.metrics;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Test;

class MetricsSummaryTest {

	@Test
	void testCompilerMetrics() throws InvalidFormatException, IOException {
		File f = new File("compiler_metrics.xlsx");
		Workbook workbook = WorkbookFactory.create(f);
		MetricsSummary metricsSummary = new MetricsSummary(workbook);
		assertEquals(metricsSummary.getNumberOfClasses(),9);
		assertEquals(metricsSummary.getNumberOfPackages(),1);
		assertEquals(metricsSummary.getTotalLinesOfCode(),2585);
		assertEquals(metricsSummary.getTotalNumberOfMethods(),110);
		assertEquals(metricsSummary.getMethods().size(),110);
	}
	
	@Test
	void testJasmlMetrics() throws InvalidFormatException, IOException {
		File f = new File("jasml_metrics.xlsx");
		Workbook workbook = WorkbookFactory.create(f);
		MetricsSummary metricsSummary = new MetricsSummary(workbook);
		assertEquals(metricsSummary.getNumberOfClasses(),49);
		assertEquals(metricsSummary.getNumberOfPackages(),4);
	}
	
	@Test
	void testError() throws InvalidFormatException, IOException {
	    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			File f = new File("excel_errado.xlsx");
			Workbook workbook = WorkbookFactory.create(f);
			MetricsSummary metricsSummary = new MetricsSummary(workbook);
	    });

	    String expectedMessage = "An error has occured, your rows don't have the correct metrics' name, please check the syntax again.";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.equals(expectedMessage));
	}
	
	@Test
	void testEmpty() throws InvalidFormatException, IOException {
	    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			File f = new File("excel_vazio.xlsx");
			Workbook workbook = WorkbookFactory.create(f);
			MetricsSummary metricsSummary = new MetricsSummary(workbook);
	    });

	    String expectedMessage = "An error has occured, xlsx file doesn't have any rows, please try again.";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.equals(expectedMessage));

				
	}
}