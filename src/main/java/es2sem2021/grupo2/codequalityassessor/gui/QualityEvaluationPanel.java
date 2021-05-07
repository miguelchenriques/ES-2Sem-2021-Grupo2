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
		VerdadeirosPositivoLabel.setBounds(21, 57, 116, 14);
		add(VerdadeirosPositivoLabel);

		JLabel VerdadeirosNegativoLabel = new JLabel("True Negatives:");
		VerdadeirosNegativoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		VerdadeirosNegativoLabel.setBounds(21, 94, 117, 14);
		add(VerdadeirosNegativoLabel);

		JLabel FalsoPositivoLabel = new JLabel("False Positives:");
		FalsoPositivoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		FalsoPositivoLabel.setBounds(243, 57, 98, 14);
		add(FalsoPositivoLabel);

		JLabel FalsosNegativosLabel = new JLabel("False Negatives:");
		FalsosNegativosLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		FalsosNegativosLabel.setBounds(225, 94, 116, 14);
		add(FalsosNegativosLabel);

		JLabel n_verdadeiros_positivos = new JLabel("VP");
		n_verdadeiros_positivos.setHorizontalAlignment(SwingConstants.LEFT);
		n_verdadeiros_positivos.setBounds(149, 57, 82, 14);
		add(n_verdadeiros_positivos);

		JLabel n_verdadeiros_negativos = new JLabel("VN");
		n_verdadeiros_negativos.setHorizontalAlignment(SwingConstants.LEFT);
		n_verdadeiros_negativos.setBounds(148, 94, 65, 14);
		add(n_verdadeiros_negativos);

		JLabel n_falsos_positivos = new JLabel("FP");
		n_falsos_positivos.setHorizontalAlignment(SwingConstants.LEFT);
		n_falsos_positivos.setBounds(351, 57, 84, 14);
		add(n_falsos_positivos);

		JLabel n_falsos_negativos = new JLabel("FN");
		n_falsos_negativos.setHorizontalAlignment(SwingConstants.LEFT);
		n_falsos_negativos.setBounds(353, 94, 82, 14);
		add(n_falsos_negativos);

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
				n_verdadeiros_positivos.setText(Integer.toString(qe.getLongMethodTruePositives()) + " / " + Integer.toString(qe.getGodClassTruePositives()));
				n_verdadeiros_negativos.setText(Integer.toString(qe.getLongMethodTrueNegatives()) + " / " + Integer.toString(qe.getGodClassTrueNegatives()));
				n_falsos_positivos.setText(Integer.toString(qe.getLongMethodFalsePositives()) + " / " + Integer.toString(qe.getGodClassFalsePositives()));
				n_falsos_negativos.setText(Integer.toString(qe.getLongMethodFalseNegatives()) + " / " + Integer.toString(qe.getGodClassFalseNegatives()));
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
