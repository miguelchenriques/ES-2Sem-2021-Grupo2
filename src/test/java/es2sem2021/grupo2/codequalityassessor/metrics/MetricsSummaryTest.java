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
		assertEquals(11, metricsSummary.getNumberOfClasses());
		assertEquals(1, metricsSummary.getNumberOfPackages());
		assertEquals(2421, metricsSummary.getTotalLinesOfCode());
		assertEquals(112, metricsSummary.getTotalNumberOfMethods());
		assertEquals(112, metricsSummary.getMethods().size());
	}
	
	@Test
	void testJasmlMetrics() throws InvalidFormatException, IOException {
		File f = new File("jasml_metrics.xlsx");
		Workbook workbook = WorkbookFactory.create(f);
		MetricsSummary metricsSummary = new MetricsSummary(workbook);
		assertEquals(51, metricsSummary.getNumberOfClasses());
		assertEquals(4, metricsSummary.getNumberOfPackages());
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