package es2sem2021.grupo2.codequalityassessor.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import es2sem2021.grupo2.codequalityassessor.rules.CodeSmells;
import es2sem2021.grupo2.codequalityassessor.rules.Rule;

public class CreateCodeSmellsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField CodeSmellsText;
	private  DefaultTableModel model;
	private JLabel errorLabel;

	public CreateCodeSmellsPanel() {
		setBounds(0, 0, 650, 483);
		setLayout(null);
		setVisible(false);

		CodeSmellsText = new JTextField();
		CodeSmellsText.setColumns(10);
		CodeSmellsText.setBounds(69, 67, 232, 40);
		add(CodeSmellsText);

		JLabel CodeSmellsLabel = new JLabel("Code Smells Name");
		CodeSmellsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CodeSmellsLabel.setBounds(69, 40, 232, 16);
		add(CodeSmellsLabel);

		JButton addCodeSmell = new JButton("Add Code Smell");
		addCodeSmell.setBounds(376, 61, 184, 46);
		addCodeSmell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				errorLabel.setVisible(false);
				CodeSmells.addCodeSmell(CodeSmellsText.getText());
				updateCodeSmells();
			}
		});
		add(addCodeSmell);

		JTable table = new JTable();
		String[] columnNames = { "CodeSmell Name", "Rule Name", "" };
		model = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if(column < 2) return false;
				return true;
			}
		};
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(256);
		table.getColumnModel().getColumn(1).setPreferredWidth(256);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		scrollPane.setBounds(6, 167, 638, 288);
		add(scrollPane);
		
		
		errorLabel = new JLabel("You can't delete Code Smell \"is_Long_Method\" or \"is_God_Class\" ");
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(79, 128, 466, 16);
		errorLabel.setVisible(false);
		add(errorLabel);
		
		updateCodeSmells();

		Action delete = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				errorLabel.setVisible(false);
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				String codeSmell = table.getModel().getValueAt(modelRow, 0).toString();
				if(codeSmell.equals("is_Long_Method") || codeSmell.equals("is_God_Class") )
					errorLabel.setVisible(true);
				else errorLabel.setVisible(false);
				CodeSmells.deleteRuleToCodeSmell(codeSmell);
				updateCodeSmells();
			}
		};

		ButtonColumn deleteButton = new ButtonColumn(table, delete, 2);
		deleteButton.setMnemonic(KeyEvent.VK_D);
	}

	public void updateCodeSmells() {
		HashMap<String, Rule> codeSmells = CodeSmells.getCodeSmells();
		model.getDataVector().removeAllElements();
		revalidate();
		
		for (Map.Entry<String, Rule> entry : codeSmells.entrySet()) {
			setCodeSmellsRows(entry);
			}
	}

	private void setCodeSmellsRows(Map.Entry<String, Rule> entry) {
		String key = entry.getKey();

			if (entry.getValue() == null) {
				model.addRow(new Object[] { key, "", "Delete" });
			} else {
				model.addRow(new Object[] { key, entry.getValue().getName(), "Delete" });
			}
	}
}
