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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
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
	 * Initialize the contents of the frame.
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
		extractMetricsPane.setBounds(6, 42, 150, 50);
		menuPane.add(extractMetricsPane);
		extractMetricsPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Extract metrics");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(22, 16, 99, 16);
		extractMetricsPane.add(lblNewLabel);
		
		JPanel visualizeMetricsPane = new JPanel();
		visualizeMetricsPane.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				menuClicked(visualize_panel);
			}
		});
		visualizeMetricsPane.setBackground(Color.LIGHT_GRAY);
		visualizeMetricsPane.setBounds(6, 103, 150, 50);
		menuPane.add(visualizeMetricsPane);
		visualizeMetricsPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Visualize Metrics");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(6, 17, 132, 16);
		visualizeMetricsPane.add(lblNewLabel_1);
		
		JPanel rulesPane = new JPanel();
		rulesPane.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				menuClicked(rules_panel);
			}
		});
		rulesPane.setLayout(null);
		rulesPane.setBackground(Color.LIGHT_GRAY);
		rulesPane.setBounds(6, 164, 150, 50);
		menuPane.add(rulesPane);
		
		JLabel lblNewLabel_1_1 = new JLabel("Add/View Rules");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(6, 17, 132, 16);
		rulesPane.add(lblNewLabel_1_1);
		
		JPanel visualizeCodeSmellsPane = new JPanel();
		visualizeCodeSmellsPane.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				menuClicked(codesmells_panel);
			}
		});
		visualizeCodeSmellsPane.setLayout(null);
		visualizeCodeSmellsPane.setBackground(Color.LIGHT_GRAY);
		visualizeCodeSmellsPane.setBounds(6, 286, 150, 50);
		menuPane.add(visualizeCodeSmellsPane);
		
		JLabel lblVisualizeCodeSmells = new JLabel("Visualize Code Smells");
		lblVisualizeCodeSmells.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualizeCodeSmells.setBounds(0, 16, 150, 16);
		visualizeCodeSmellsPane.add(lblVisualizeCodeSmells);
		
		JPanel codeSmellsPane = new JPanel();
		codeSmellsPane.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				menuClicked(set_rules_code_smells_panel);
				set_rules_code_smells_panel.update();
			}
		});
		codeSmellsPane.setLayout(null);
		codeSmellsPane.setBackground(Color.LIGHT_GRAY);
		codeSmellsPane.setBounds(6, 225, 150, 50);
		menuPane.add(codeSmellsPane);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Set Rules for Code Smells");
		lblNewLabel_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setBounds(6, 6, 138, 38);
		codeSmellsPane.add(lblNewLabel_1_1_1);
		
		JPanel visualizeQualityEvaluationPane_1 = new JPanel();
		visualizeQualityEvaluationPane_1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                menuClicked(quality_evaluation_panel);
            }
        });
		visualizeQualityEvaluationPane_1.setLayout(null);
		visualizeQualityEvaluationPane_1.setBackground(Color.LIGHT_GRAY);
		visualizeQualityEvaluationPane_1.setBounds(6, 347, 150, 50);
		menuPane.add(visualizeQualityEvaluationPane_1);
		
		JLabel lblQualityEvaluation_1 = new JLabel("Visualize Quality Evaluation");
		lblQualityEvaluation_1.setBounds(0, 0, 150, 49);
		visualizeQualityEvaluationPane_1.add(lblQualityEvaluation_1);
		lblQualityEvaluation_1.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblQualityEvaluation_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		JPanel contentPane = new JPanel();
		contentPane.setBounds(161, 0, 650, 455);
		frame.getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		contentPane.add(extract_panel);
		contentPane.add(visualize_panel);
		contentPane.add(rules_panel);
		contentPane.add(codesmells_panel);
		contentPane.add(set_rules_code_smells_panel);
		contentPane.add(quality_evaluation_panel);
		
		JLabel lblNewLabel_2 = new JLabel("Welcome to the Code Evaluation App");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("SF Pro", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(116, 122, 417, 58);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Click a menu to interact");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("SF Pro", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(189, 250, 272, 16);
		contentPane.add(lblNewLabel_3);
	}
	
	public void menuClicked(JPanel panel) {
		extract_panel.setVisible(false);
		visualize_panel.setVisible(false);
		rules_panel.setVisible(false);
		codesmells_panel.setVisible(false);
		set_rules_code_smells_panel.setVisible(false);
		quality_evaluation_panel.setVisible(false);
		
		panel.setVisible(true);
	}
}