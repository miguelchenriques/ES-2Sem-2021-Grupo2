package es2sem2021.grupo2.codequalityassessor.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class App {

	private JFrame frame;
	private ExtractPanel extract_panel;
	private VisualizePanel visualize_panel;
	private RulesPanel rules_panel;
	private CodeSmellsPanel codesmells_panel;
	private SetRulesCodeSmellsPanel set_rules_code_smells_panel;
	private QualityEvaluationPanel quality_evaluation_panel;
	private CreateCodeSmellsPanel create_code_smells_panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the main frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 812, 483);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		extract_panel = new ExtractPanel();
		visualize_panel = new VisualizePanel();
		rules_panel = new RulesPanel();
		codesmells_panel = new CodeSmellsPanel();
		set_rules_code_smells_panel = new SetRulesCodeSmellsPanel();
		quality_evaluation_panel = new QualityEvaluationPanel();
		create_code_smells_panel = new CreateCodeSmellsPanel();
		
		JPanel menuPane = new JPanel();
		menuPane.setBackground(Color.GRAY);
		menuPane.setBounds(0, 0, 162, 483);
		frame.getContentPane().add(menuPane);
		menuPane.setLayout(null);
		
		JPanel extractMetricsPane = new JPanel();
		extractMetricsPane.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				menuClicked(extract_panel);
			}
		});
		extractMetricsPane.setBackground(Color.LIGHT_GRAY);
		extractMetricsPane.setBounds(6, 11, 150, 50);
		menuPane.add(extractMetricsPane);
		extractMetricsPane.setLayout(null);
		
		extractMetricsPane.add(createExtractMetricsLbl());
		
		JPanel visualizeMetricsPane = new JPanel();
		visualizeMetricsPane.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				menuClicked(visualize_panel);
			}
		});
		visualizeMetricsPane.setBackground(Color.LIGHT_GRAY);
		visualizeMetricsPane.setBounds(6, 72, 150, 50);
		menuPane.add(visualizeMetricsPane);
		visualizeMetricsPane.setLayout(null);
		
		visualizeMetricsPane.add(createVisualizeMetricsLbl());
		
		JPanel rulesPane = addRulesToMenuPane(menuPane);
		
		JLabel lblAddViewRules = createAddViewRulesLbl();
		rulesPane.add(lblAddViewRules);
		
		JPanel visualizeCodeSmellsPane = new JPanel();
		visualizeCodeSmellsPane.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				menuClicked(codesmells_panel);
			}
		});
		visualizeCodeSmellsPane.setLayout(null);
		visualizeCodeSmellsPane.setBackground(Color.LIGHT_GRAY);
		visualizeCodeSmellsPane.setBounds(6, 316, 150, 50);
		menuPane.add(visualizeCodeSmellsPane);
		
		visualizeCodeSmellsPane.add(createVisualizeCodeSmellLbl());
		
		JPanel codeSmellsPane = new JPanel();
		codeSmellsPane.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				menuClicked(set_rules_code_smells_panel);
				set_rules_code_smells_panel.update();
			}
		});
		codeSmellsPane.setLayout(null);
		codeSmellsPane.setBackground(Color.LIGHT_GRAY);
		codeSmellsPane.setBounds(6, 255, 150, 50);
		menuPane.add(codeSmellsPane);
		
		codeSmellsPane.add(createSetRulesCodeSmellsLabel());
		
		JPanel visualizeQualityEvaluationPane = createQualityEvalPane(menuPane);
		
		visualizeQualityEvaluationPane.add(createQualityEvalLbl());
		
		JPanel CreateCodeSmellsPanel = new JPanel();
		CreateCodeSmellsPanel.setBounds(6, 194, 150, 50);
		menuPane.add(CreateCodeSmellsPanel);
		CreateCodeSmellsPanel.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                menuClicked(create_code_smells_panel);
                create_code_smells_panel.updateCodeSmells();
            }
        });
		CreateCodeSmellsPanel.setLayout(null);
		CreateCodeSmellsPanel.setBackground(Color.LIGHT_GRAY);
		
		;
		CreateCodeSmellsPanel.add(createCreateCodeSmellsLabel());
		
		JPanel contentPane = createContentPane();
		
		contentPane.add(extract_panel);
		contentPane.add(visualize_panel);
		contentPane.add(rules_panel);
		contentPane.add(codesmells_panel);
		contentPane.add(set_rules_code_smells_panel);
		contentPane.add(quality_evaluation_panel);
		contentPane.add(create_code_smells_panel);
		contentPane.add(createWelcomeLbl());
		contentPane.add(createClickMenuLbl());
	}

	/**
	 * @return	centered label with content "Add/View Rules"
	 */
	private JLabel createAddViewRulesLbl() {
		JLabel lblNewLabel_1_1 = new JLabel("Add/View Rules");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(6, 17, 132, 16);
		return lblNewLabel_1_1;
	}

	/**
	 * @return	centered label with content "Visualize Code Smells"
	 */
	private JLabel createVisualizeCodeSmellLbl() {
		JLabel lblVisualizeCodeSmells = new JLabel("Visualize Code Smells");
		lblVisualizeCodeSmells.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualizeCodeSmells.setBounds(0, 16, 150, 16);
		return lblVisualizeCodeSmells;
	}

	private JPanel addRulesToMenuPane(JPanel menuPane) {
		JPanel rulesPane = new JPanel();
		rulesPane.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				menuClicked(rules_panel);
			}
		});
		rulesPane.setLayout(null);
		rulesPane.setBackground(Color.LIGHT_GRAY);
		rulesPane.setBounds(6, 133, 150, 50);
		menuPane.add(rulesPane);
		return rulesPane;
	}

	/**
	 * @return	centered label with content "Visualize Metrics"
	 */
	private JLabel createVisualizeMetricsLbl() {
		JLabel lblVisMetrics = new JLabel("Visualize Metrics");
		lblVisMetrics.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisMetrics.setBounds(6, 17, 132, 16);
		return lblVisMetrics;
	}

	/**
	 * @return	centered label with content "Extract metrics"
	 */
	private JLabel createExtractMetricsLbl() {
		JLabel lblNewLabel = new JLabel("Extract metrics");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(22, 16, 99, 16);
		return lblNewLabel;
	}

	/**
	 * @return	centered label with content "Visualize Quality Evaluation"
	 */
	private JLabel createQualityEvalLbl() {
		JLabel lblVisQualityEvaluation = new JLabel("Visualize Quality Evaluation");
		lblVisQualityEvaluation.setBounds(10, 11, 130, 28);
		lblVisQualityEvaluation.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblVisQualityEvaluation.setHorizontalAlignment(SwingConstants.CENTER);
		return lblVisQualityEvaluation;
	}

	
	private JPanel createQualityEvalPane(JPanel menuPane) {
		JPanel visualizeQualityEvaluationPane_1 = new JPanel();
		visualizeQualityEvaluationPane_1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                menuClicked(quality_evaluation_panel);
            }
        });
		visualizeQualityEvaluationPane_1.setLayout(null);
		visualizeQualityEvaluationPane_1.setBackground(Color.LIGHT_GRAY);
		visualizeQualityEvaluationPane_1.setBounds(6, 377, 150, 50);
		menuPane.add(visualizeQualityEvaluationPane_1);
		return visualizeQualityEvaluationPane_1;
	}
	
	/**
	 * @return	centered label with content "Set Rules for Code Smells"
	 */
	private JLabel createSetRulesCodeSmellsLabel() {
		JLabel lblSetRulesCodeSmmells = new JLabel("Set Rules for Code Smells");
		lblSetRulesCodeSmmells.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblSetRulesCodeSmmells.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetRulesCodeSmmells.setBounds(6, 11, 138, 28);
		return lblSetRulesCodeSmmells;
	}
	
	/**
	 * @return	centered label with content "Welcome to the Code Evaluation App"
	 */
	private JLabel createWelcomeLbl() {
		JLabel lblWelcomeToCEA = new JLabel("Welcome to the Code Evaluation App");
		lblWelcomeToCEA.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToCEA.setFont(new Font("SF Pro", Font.PLAIN, 20));
		lblWelcomeToCEA.setBounds(116, 122, 417, 58);
		return lblWelcomeToCEA;
	}
	
	/**
	 * @return	centered label with content "Click a menu to interact"
	 */
	private JLabel createClickMenuLbl() {
		JLabel lblClickMenu = new JLabel("Click on a menu to interact");
		lblClickMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblClickMenu.setFont(new Font("SF Pro", Font.PLAIN, 16));
		lblClickMenu.setBounds(189, 250, 272, 16);
		return lblClickMenu;
	}

	/**
	 * @return	pane that will hold the content
	 */
	private JPanel createContentPane() {
		JPanel contentPane = new JPanel();
		contentPane.setBounds(161, 0, 650, 455);
		frame.getContentPane().add(contentPane);
		contentPane.setLayout(null);
		return contentPane;
	}

	/**
	 * @return	centered label with content "Create Code Smells"
	 */
	private JLabel createCreateCodeSmellsLabel() {
		JLabel lblCreateCodeSmels = new JLabel("Create Code Smells");
		lblCreateCodeSmels.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateCodeSmels.setFont(new Font("Dialog", Font.PLAIN, 11));
		lblCreateCodeSmels.setBounds(10, 11, 130, 28);
		return lblCreateCodeSmels;
	}
	
	/**
	 * 
	 * Changes the visible JPanel to the one passed as argument to the 
	 * 
	 * @param panel	panel that will be the one visible
	 */
	public void menuClicked(JPanel panel) {
		extract_panel.setVisible(false);
		visualize_panel.setVisible(false);
		rules_panel.setVisible(false);
		codesmells_panel.setVisible(false);
		set_rules_code_smells_panel.setVisible(false);
		quality_evaluation_panel.setVisible(false);
		create_code_smells_panel.setVisible(false);
		
		panel.setVisible(true);
	}
}