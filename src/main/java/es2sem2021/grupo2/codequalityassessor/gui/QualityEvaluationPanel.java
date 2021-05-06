package es2sem2021.grupo2.codequalityassessor.gui;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.JLabel;


import org.jfree.chart.plot.PlotOrientation;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;




public class QualityEvaluationPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public QualityEvaluationPanel() {
		
		setBounds(0, 0, 650, 483);
        setLayout(null);       
        
        JLabel VerdadeirosPositivoLabel = new JLabel("VerdadeirosPositivo");
        VerdadeirosPositivoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        VerdadeirosPositivoLabel.setBounds(28, 32, 98, 14);
        add(VerdadeirosPositivoLabel);
        
        JLabel VerdadeirosNegativoLabel = new JLabel("VerdadeirosNegativo");
        VerdadeirosNegativoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        VerdadeirosNegativoLabel.setBounds(10, 57, 127, 14);
        add(VerdadeirosNegativoLabel);
        
        JLabel FalsoPositivoLabel = new JLabel("FalsoPositivo");
        FalsoPositivoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        FalsoPositivoLabel.setBounds(206, 32, 98, 14);
        add(FalsoPositivoLabel);
        
        JLabel FalsosNegativosLabel = new JLabel("FalsosNegativos");
        FalsosNegativosLabel.setHorizontalAlignment(SwingConstants.CENTER);
        FalsosNegativosLabel.setBounds(206, 57, 98, 14);
        add(FalsosNegativosLabel);
        
        JLabel n_verdadeiros_positivos = new JLabel("VP");
        n_verdadeiros_positivos.setHorizontalAlignment(SwingConstants.CENTER);
        n_verdadeiros_positivos.setBounds(119, 32, 77, 14);
        add(n_verdadeiros_positivos);
        
        JLabel n_verdadeiros_negativos = new JLabel("VN");
        n_verdadeiros_negativos.setHorizontalAlignment(SwingConstants.CENTER);
        n_verdadeiros_negativos.setBounds(119, 57, 77, 14);
        add(n_verdadeiros_negativos);
        
        JLabel n_falsos_positivos = new JLabel("FP");
        n_falsos_positivos.setHorizontalAlignment(SwingConstants.CENTER);
        n_falsos_positivos.setBounds(314, 32, 68, 14);
        add(n_falsos_positivos);
        
        JLabel n_falsos_negativos = new JLabel("FN");
        n_falsos_negativos.setHorizontalAlignment(SwingConstants.CENTER);
        n_falsos_negativos.setBounds(314, 57, 68, 14);
        add(n_falsos_negativos);
        
        JComboBox<String> codeSmellCB_1 = new JComboBox<String>();
        codeSmellCB_1.setBounds(444, 26, 153, 27);
        add(codeSmellCB_1);
        
        JLabel CodeSmellsLabel = new JLabel("Code Smell");
        CodeSmellsLabel.setBounds(490, 11, 75, 14);
        add(CodeSmellsLabel);
        
        JButton changeCS = new JButton("Change Code Smell");
        changeCS.setBounds(422, 64, 187, 29);
        add(changeCS);
        
        QualityEvalChart qechart = new QualityEvalChart("ya","yo");
        
        JFreeChart chart = ChartFactory.createBarChart(
                "Indicadores",
                "",
                "Numero de indicadores",
                qechart.getDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);
        
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(10, 118, 630, 306);
        add(chartPanel);
	}   
}
