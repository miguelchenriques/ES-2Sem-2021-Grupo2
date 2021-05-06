package es2sem2021.grupo2.codequalityassessor.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import es2sem2021.grupo2.codequalityassessor.rules.FinalResults;
import es2sem2021.grupo2.codequalityassessor.xlsx.FileGenerator;
import es2sem2021.grupo2.codequalityassessor.xlsx.Method;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CodeSmellsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static DefaultTableModel model;
	private static JTable table;

	public CodeSmellsPanel() {
		setBounds(0, 0, 650, 483);
		setLayout(null);

		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		
		
		table = new JTable();
		table.setModel(model);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		table.setFillsViewportHeight(true);
		scrollPane.setBounds(0, 49, 650, 434);
		add(scrollPane);
		
		JButton refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateColunms(FileGenerator.getMethods());
			}
		});
		refreshButton.setBounds(0, 0, 130, 50);
		add(refreshButton);
		
	}

	public static void updateColunms( List<Method> methods) {
		model.getDataVector().removeAllElements();
		model.setColumnCount(0);

		HashMap<String, HashMap<String, Boolean>> ruleResults = FinalResults.getRulesResults(methods);

//		for (int i = 0; i < methods.size(); i++) { 
//			Method method = methods.get(i);
//			model.addRow(new Object[] { method.m_class, method.m_method}); 
//			for (int j = 0; j < ruleResults.size(); j++) {
//				model.setValueAt(ruleResults.get(j).getMethodsresults().get(i).getResult(),i ,j+2);
//			}
//			
//		}
		model.addColumn("Class");
		model.addColumn("Method");

		int j = 2;
		for (Map.Entry<String, HashMap<String, Boolean>> entry : ruleResults.entrySet()) {
			int i = 0;

			if (!entry.getValue().isEmpty()) {
				model.addColumn(entry.getKey());
				for (Map.Entry<String, Boolean> entry1 : entry.getValue().entrySet()) {
					if (j == 2) {
						model.addRow(new Object[] { methods.get(i).m_class, methods.get(i).m_method });
					}
					model.setValueAt(entry1.getValue(), i, j);
					i++;
				}
				j++;

			}
		}

		table.setModel(model); 
	}
}
