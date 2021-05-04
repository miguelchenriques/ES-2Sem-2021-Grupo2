package es2sem2021.grupo2.codequalityassessor.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class SetRulesCodeSmellsPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private static DefaultTableModel model;
	private static JTable table;
	
	public SetRulesCodeSmellsPanel() {
		setBounds(0, 0, 650, 483);
		setLayout(null);
		
		model = new DefaultTableModel();
		table = new JTable();
		table.setModel(model);
		String[] columnNames = { "Code Smell", "Rule for detection" };
		model.setColumnIdentifiers(columnNames);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		table.setFillsViewportHeight(true);
		scrollPane.setBounds(27, 185, 591, 275);
		add(scrollPane);
	}
	
	private void updateRules() {
		HashMap<String, Rule> rules = RulesSet.getRules();
		model.getDataVector().removeAllElements();
		revalidate();
		for (Map.Entry<String, Rule> entry : rules.entrySet()) {
			String key = entry.getKey();
			Rule rule = entry.getValue();
			model.addRow(new Object[] { key, rule.getConditions(), "Delete", "Change" });
		}
	}
}
