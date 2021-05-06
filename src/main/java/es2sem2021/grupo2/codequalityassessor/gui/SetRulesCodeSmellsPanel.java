package es2sem2021.grupo2.codequalityassessor.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import es2sem2021.grupo2.codequalityassessor.rules.CodeSmells;
import es2sem2021.grupo2.codequalityassessor.rules.Rule;
import es2sem2021.grupo2.codequalityassessor.rules.RulesSet;

public class SetRulesCodeSmellsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	
	private JComboBox<String> codeSmellCB;
	private JComboBox<String> ruleCB;
	private JLabel errorLabel;
	
	public SetRulesCodeSmellsPanel() {
		setBounds(0, 0, 650, 483);
		setLayout(null);
		
		codeSmellCB = new JComboBox<String>();
		codeSmellCB.setBounds(202, 142, 153, 27);
		add(codeSmellCB);
		
		ruleCB = new JComboBox<String>();
		ruleCB.setBounds(202, 268, 153, 27);
		add(ruleCB);
		
		JLabel lblNewLabel = new JLabel("Code Smell");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(224, 114, 107, 16);
		add(lblNewLabel);
		
		JLabel lblRule = new JLabel("Rule");
		lblRule.setHorizontalAlignment(SwingConstants.CENTER);
		lblRule.setBounds(224, 240, 107, 16);
		add(lblRule);
		
		JButton changeCS = new JButton("Change Code Smell");
		changeCS.addMouseListener(new MouseAdapter() {
           @Override
            public void mouseClicked(MouseEvent e) {
        	   errorLabel.setVisible(false);
        	   if(ruleCB.getSelectedItem().toString() == "") {
        		   errorLabel.setVisible(true);
        		   return;
        	   } 
        	   String codeSmell = codeSmellCB.getSelectedItem().toString();
        	   String ruleName = ruleCB.getSelectedItem().toString();
        	   Rule rule = RulesSet.getRules().get(ruleName);
        	   if(CodeSmells.addRuleToCodeSmell(codeSmell, rule) == false) {
        		   errorLabel.setVisible(true);
        	   };
        	   System.out.println(CodeSmells.getCodeSmells());
        	   }
           });
		changeCS.setBounds(188, 384, 187, 29);
		add(changeCS);
		
		errorLabel = new JLabel("You must enter a valid rule for that code smell");
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(128, 355, 305, 16);
		errorLabel.setVisible(false);
		add(errorLabel);
		
		update();
		
	}
	
	public void update(){
		codeSmellCB.removeAllItems();
		ruleCB.removeAllItems();
		CodeSmells.importMandatoryCodeSmells();
		HashMap<String, Rule> codeSmells = CodeSmells.getCodeSmells();
		for (Map.Entry<String, Rule> c : codeSmells.entrySet()) {
			codeSmellCB.addItem(c.getKey());
		}
		HashMap<String, Rule> rules = RulesSet.getRules();
		ruleCB.addItem("");
		for (Map.Entry<String, Rule> rule : rules.entrySet()) {
			ruleCB.addItem(rule.getKey());
		}
	}

}
