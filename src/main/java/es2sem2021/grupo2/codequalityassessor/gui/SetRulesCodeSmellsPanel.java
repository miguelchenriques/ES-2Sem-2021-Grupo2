package es2sem2021.grupo2.codequalityassessor.gui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import es2sem2021.grupo2.codequalityassessor.rules.CodeSmells;
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
		
		updateCodeSmells();
		
	}
	
	Action change = new AbstractAction()
	{
	    public void actionPerformed(ActionEvent e)
	    {
	    	JTable table = (JTable)e.getSource();
	        int modelRow = Integer.valueOf( e.getActionCommand() );
	        String s = table.getModel().getValueAt(modelRow, 1).toString();
	    }
	};
	
	private void updateCodeSmells() {
		CodeSmells.importMandatoryCodeSmells();
		HashMap<String, Rule> codeSmells = CodeSmells.getCodesmells();
		model.getDataVector().removeAllElements();
		revalidate();
		for (Map.Entry<String, Rule> entry : codeSmells.entrySet()) {
			JComboBox<String> comboBox = new JComboBox<>();
			HashMap<String, Rule> rules = RulesSet.getRules();
			for (Map.Entry<String, Rule> rule : rules.entrySet()) {
				comboBox.addItem(rule.getKey());
				TableColumn column = table.getColumnModel().getColumn(1);
				column.setCellEditor(new DefaultCellEditor(comboBox));
			}
			model.addRow(new Object[] { entry.getKey(), entry.getValue().getConditions()});
		}
	}
}
