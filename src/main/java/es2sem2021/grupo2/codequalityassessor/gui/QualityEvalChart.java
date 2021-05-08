package es2sem2021.grupo2.codequalityassessor.gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import es2sem2021.grupo2.codequalityassessor.rules.QualityEvaluation;

public class QualityEvalChart extends ApplicationFrame{

	private static final long serialVersionUID = 1L;
	private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	
	 public QualityEvalChart( String applicationTitle , String chartTitle ) {
         super( applicationTitle );        
         JFreeChart barChart = ChartFactory.createBarChart(
            chartTitle,           
            "Category",            
            "Score",            
            createDataset(),          
            PlotOrientation.VERTICAL,           
            true, true, false);
            
         ChartPanel chartPanel = new ChartPanel( barChart );        
         chartPanel.setPreferredSize(new java.awt.Dimension( 630, 336 ));
         getContentPane().add(chartPanel);
      }
      
      private CategoryDataset createDataset( ) {
         final String GC = "is_God_Class";        
         final String LM = "is_Long_Method";              
         final String VP = "TP";        
         final String VN = "TN";        
         final String FP = "FP";        
         final String FN = "FN";        
         
         
         try{
        	 QualityEvaluation qualityEval = new QualityEvaluation();
        	 
        	 dataset.addValue( qualityEval.getGodClassTruePositives() , GC , VP);        
             dataset.addValue( qualityEval.getGodClassTrueNegatives() , GC , VN);        
             dataset.addValue( qualityEval.getGodClassFalsePositives() , GC , FP); 
             dataset.addValue( qualityEval.getGodClassFalseNegatives() , GC , FN);   
             
             dataset.addValue( qualityEval.getLongMethodTruePositives() , LM , VP);
             dataset.addValue( qualityEval.getLongMethodTrueNegatives() , LM , VN);
             dataset.addValue( qualityEval.getLongMethodFalsePositives() , LM ,FP);
             dataset.addValue( qualityEval.getLongMethodFalseNegatives() , LM , FN);
         }catch(Exception e){
        	 System.out.println("podre");
        	 
         }

           

         return dataset; 
      }
      
      public DefaultCategoryDataset getDataset() {
          return dataset;
      }
}
