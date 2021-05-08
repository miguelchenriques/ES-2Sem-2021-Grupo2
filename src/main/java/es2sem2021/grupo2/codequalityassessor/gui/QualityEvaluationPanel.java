package es2sem2021.grupo2.codequalityassessor.gui;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.JLabel;


import org.jfree.chart.plot.PlotOrientation;

import es2sem2021.grupo2.codequalityassessor.rules.QualityEvaluation;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class QualityEvaluationPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	public QualityEvaluationPanel() {

		setBounds(0, 0, 650, 483);
		setLayout(null);     
		setVisible(false);

		JLabel VerdadeirosPositivoLabel = new JLabel("True Positives:");
		VerdadeirosPositivoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		VerdadeirosPositivoLabel.setBounds(31, 60, 96, 14);
		add(VerdadeirosPositivoLabel);

		JLabel VerdadeirosNegativoLabel = new JLabel("True Negatives:");
		VerdadeirosNegativoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		VerdadeirosNegativoLabel.setBounds(31, 77, 96, 14);
		add(VerdadeirosNegativoLabel);

		JLabel FalsoPositivoLabel = new JLabel("False Positives:");
		FalsoPositivoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		FalsoPositivoLabel.setBounds(31, 91, 96, 14);
		add(FalsoPositivoLabel);

		JLabel FalsosNegativosLabel = new JLabel("False Negatives:");
		FalsosNegativosLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		FalsosNegativosLabel.setBounds(31, 108, 96, 14);
		add(FalsosNegativosLabel);

		JLabel n_verdadeiros_positivos = new JLabel("TP");
		n_verdadeiros_positivos.setHorizontalAlignment(SwingConstants.CENTER);
		n_verdadeiros_positivos.setBounds(325, 60, 67, 14);
		add(n_verdadeiros_positivos);

		JLabel n_verdadeiros_negativos = new JLabel("TN");
		n_verdadeiros_negativos.setHorizontalAlignment(SwingConstants.CENTER);
		n_verdadeiros_negativos.setBounds(325, 77, 67, 14);
		add(n_verdadeiros_negativos);

		JLabel n_falsos_positivos = new JLabel("FP");
		n_falsos_positivos.setHorizontalAlignment(SwingConstants.CENTER);
		n_falsos_positivos.setBounds(325, 91, 67, 14);
		add(n_falsos_positivos);

		JLabel n_falsos_negativos = new JLabel("FN");
		n_falsos_negativos.setHorizontalAlignment(SwingConstants.CENTER);
		n_falsos_negativos.setBounds(325, 108, 67, 14);
		add(n_falsos_negativos);
		
		JLabel n_falsos_negativos_1 = new JLabel("FN");
		n_falsos_negativos_1.setHorizontalAlignment(SwingConstants.CENTER);
		n_falsos_negativos_1.setBounds(182, 108, 67, 14);
		add(n_falsos_negativos_1);
		
		JLabel n_falsos_positivos_1 = new JLabel("FP");
		n_falsos_positivos_1.setHorizontalAlignment(SwingConstants.CENTER);
		n_falsos_positivos_1.setBounds(182, 91, 67, 14);
		add(n_falsos_positivos_1);
		
		JLabel n_verdadeiros_negativos_1 = new JLabel("TN");
		n_verdadeiros_negativos_1.setHorizontalAlignment(SwingConstants.CENTER);
		n_verdadeiros_negativos_1.setBounds(182, 77, 67, 14);
		add(n_verdadeiros_negativos_1);
		
		JLabel n_verdadeiros_positivos_1 = new JLabel("TP");
		n_verdadeiros_positivos_1.setHorizontalAlignment(SwingConstants.CENTER);
		n_verdadeiros_positivos_1.setBounds(182, 60, 67, 14);
		add(n_verdadeiros_positivos_1);
		
		JLabel lblNewLabel = new JLabel("is_Long_Method");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(311, 35, 96, 14);
		add(lblNewLabel);
		
		JLabel lblIsgodclass = new JLabel("is_God_Class");
		lblIsgodclass.setHorizontalAlignment(SwingConstants.CENTER);
		lblIsgodclass.setBounds(167, 35, 82, 14);
		add(lblIsgodclass);

		QualityEvalChart qechart = new QualityEvalChart("Codesmells","QualityEvaluation");

		JFreeChart chart = ChartFactory.createBarChart(
				"Quality Evaluation",
				"",
				"",
				qechart.getDataset(),
				PlotOrientation.VERTICAL,
				true, true, false);


		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(10, 157, 630, 289);
		add(chartPanel);

		JButton changeCS = new JButton("Refresh");
		changeCS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QualityEvalChart qechart = new QualityEvalChart("Codesmells","QualityEvaluation");
				QualityEvaluation qe = new QualityEvaluation();
				n_verdadeiros_positivos.setText(Integer.toString(qe.getLongMethodTruePositives()));
				n_verdadeiros_negativos.setText(Integer.toString(qe.getLongMethodTrueNegatives()));
				n_falsos_positivos.setText(Integer.toString(qe.getLongMethodFalsePositives()));
				n_falsos_negativos.setText(Integer.toString(qe.getLongMethodFalseNegatives()));
				n_verdadeiros_positivos_1.setText(Integer.toString(qe.getGodClassTruePositives()));
				n_verdadeiros_negativos_1.setText(Integer.toString(qe.getGodClassTrueNegatives()));
				n_falsos_positivos_1.setText(Integer.toString(qe.getGodClassFalsePositives()));
				n_falsos_negativos_1.setText(Integer.toString(qe.getGodClassFalseNegatives()));
				JFreeChart chart = ChartFactory.createBarChart(
						"Quality Evaluation",
						"",
						"",
						qechart.getDataset(),
						PlotOrientation.VERTICAL,
						true, true, false);

				chartPanel.setChart(chart);

			}
		});
		changeCS.setBounds(462, 61, 153, 47);
		add(changeCS);
		


	}   
}
