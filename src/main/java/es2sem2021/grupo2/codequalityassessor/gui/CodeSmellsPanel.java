package es2sem2021.grupo2.codequalityassessor.gui;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import es2sem2021.grupo2.codequalityassessor.rules.FinalResults;
import es2sem2021.grupo2.codequalityassessor.rules.Rule;
import es2sem2021.grupo2.codequalityassessor.rules.RuleResults;
import es2sem2021.grupo2.codequalityassessor.rules.RulesSet;
import es2sem2021.grupo2.codequalityassessor.xlsx.Method;

public class CodeSmellsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	// private JTable table;
	private static DefaultTableModel model;
	private String[] columnNames;
	private static JTable table;

	public CodeSmellsPanel() {
		setBounds(0, 0, 650, 483);
		setLayout(null);

		model = new DefaultTableModel();
		table = new JTable();
		table.setModel(model);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		table.setFillsViewportHeight(true);
		scrollPane.setBounds(0, 0, 650, 483);
		add(scrollPane);

	}

	public static void updateColunms(String[] colunm, List<Method> methods) {
		model.getDataVector().removeAllElements();
		model.setColumnIdentifiers(colunm);
		table.setModel(model);

		ArrayList<RuleResults> ruleResults = FinalResults.getRulesResults(methods);

		for (int i = 0; i < methods.size(); i++) { 
			Method method = methods.get(i);
			model.addRow(new Object[] { method.m_class, method.m_method});
			for (int j = 0; j < ruleResults.size(); j++) {
				model.setValueAt(ruleResults.get(j).getMethodsresults().get(i).getResult(),i ,j+2);
			}
			
		}
	}

}
