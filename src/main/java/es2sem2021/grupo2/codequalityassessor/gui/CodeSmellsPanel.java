package es2sem2021.grupo2.codequalityassessor.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import es2sem2021.grupo2.codequalityassessor.rules.CodeSmells;
import es2sem2021.grupo2.codequalityassessor.rules.FinalResults;
import es2sem2021.grupo2.codequalityassessor.rules.Rule;
import es2sem2021.grupo2.codequalityassessor.rules.RulesSet;
import es2sem2021.grupo2.codequalityassessor.xlsx.Method;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class CodeSmellsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	// private JTable table;
	private static DefaultTableModel model;
	private String[] columnNames;
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
		scrollPane.setBounds(0, 0, 650, 483);
		add(scrollPane);
		
		

//		JScrollPane scrollPane = new JScrollPane(table);
//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPane.setBounds(0, 0, 650, 483);
//		add(scrollPane);

//		JButton RefreshButton = new JButton("Refresh");
//		RefreshButton.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				//updateCollumns
//			}
//		});

//		RefreshButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//			}
//		});
//		RefreshButton.setBounds(0, 0, 115, 56);
//		add(RefreshButton);
//		table = new JTable();
//		table.setBounds(0, 73, 631, 392);
//		add(table);
//		table.setModel(model);
//		table.setFillsViewportHeight(true);

	}

	public static void updateColunms( List<Method> methods) {
		model.getDataVector().removeAllElements();

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
