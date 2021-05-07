package es2sem2021.grupo2.codequalityassessor.gui;

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

import es2sem2021.grupo2.codequalityassessor.rules.Rule;
import es2sem2021.grupo2.codequalityassessor.rules.RulesSet;
import java.awt.Color;

public class RulesPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField ruleName;
	private JTextField ruleCondition;
	private DefaultTableModel model;
	private JLabel errorMsg;

	/**
	 * Create the panel.
	 */
	public RulesPanel() {
		setBounds(0, 0, 650, 483);
		setLayout(null);
		setVisible(false);

		errorMsg = new JLabel("New label");
		errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		errorMsg.setForeground(Color.RED);
		errorMsg.setBounds(117, 152, 417, 16);
		errorMsg.setVisible(false);
		add(errorMsg);

		ruleName = new JTextField();
		ruleName.setBounds(24, 66, 152, 43);
		add(ruleName);
		ruleName.setColumns(10);

		ruleCondition = new JTextField();
		ruleCondition.setBounds(207, 66, 258, 43);
		add(ruleCondition);
		ruleCondition.setColumns(10);

		JLabel ruleNameLabel = new JLabel("Rule Name");
		ruleNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ruleNameLabel.setBounds(50, 44, 98, 16);
		add(ruleNameLabel);

		JLabel ruleConditionLabel = new JLabel("Rule condition");
		ruleConditionLabel.setBounds(288, 44, 96, 16);
		add(ruleConditionLabel);

		JButton addRule = new JButton("Add Rule");
		addRule.setBounds(501, 69, 121, 38);
		addRule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				errorMsg.setVisible(false);
				if (RulesSet.addRule(ruleName.getText(), ruleCondition.getText()) == false) {
					errorMsg.setText("Please enter a valid logic condition!");
					errorMsg.setVisible(true);
				}
				updateRules();
			}
		});
		add(addRule);

		JTable table = new JTable();
		String[] columnNames = { "Rule Name", "Rule Condition", "", "" };
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
		table.getColumnModel().getColumn(1).setPreferredWidth(500);
		// table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor());
		// table.setPreferredScrollableViewportSize(new Dimension(500, 200));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		table.setFillsViewportHeight(true);
		scrollPane.setBounds(6, 192, 638, 263);
		add(scrollPane);

		RulesSet.loadFromFile();
		updateRules();

		Action delete = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				RulesSet.deleteRule(table.getModel().getValueAt(modelRow, 0).toString());
				updateRules();
			}
		};

		Action change = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				errorMsg.setVisible(false);
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				if (RulesSet.changeRule(table.getModel().getValueAt(modelRow, 0).toString(),
						ruleCondition.getText()) == false) {
					errorMsg.setText("Please enter a valid condition!");
					errorMsg.setVisible(true);
				}
				updateRules();
			}
		};

		ButtonColumn deleteButton = new ButtonColumn(table, delete, 2);
		deleteButton.setMnemonic(KeyEvent.VK_D);

		ButtonColumn changeButton = new ButtonColumn(table, change, 3);
		changeButton.setMnemonic(KeyEvent.VK_D);

	}

	private void updateRules() {
		HashMap<String, Rule> rules = RulesSet.getRules();
		model.getDataVector().removeAllElements();
		revalidate();
		for (Map.Entry<String, Rule> entry : rules.entrySet()) {
			addRuleRow(entry);
		}
	}

	private void addRuleRow(Map.Entry<String, Rule> entry) {
		String key = entry.getKey();
		Rule rule = entry.getValue();
		model.addRow(new Object[] { key, rule.getConditions(), "Delete", "Change" });
	}
}
