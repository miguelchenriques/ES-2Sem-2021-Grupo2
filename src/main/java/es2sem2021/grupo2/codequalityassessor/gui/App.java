package es2sem2021.grupo2.codequalityassessor.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;

public class App {

	private JFrame frame;
	private ExtractPanel extract_panel;
	private VisualizePanel visualize_panel;
	private RulesPanel rules_panel;

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
		extractMetricsPane.setBounds(6, 121, 150, 50);
		menuPane.add(extractMetricsPane);
		extractMetricsPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Extract metrics");
		lblNewLabel.setBounds(22, 16, 99, 16);
		extractMetricsPane.add(lblNewLabel);
		
		JPanel visualizeMetricsPane = new JPanel();
		visualizeMetricsPane.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				menuClicked(visualize_panel);
			}
		});
		visualizeMetricsPane.setBackground(Color.LIGHT_GRAY);
		visualizeMetricsPane.setBounds(6, 183, 150, 50);
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
		rulesPane.setBounds(6, 245, 150, 50);
		menuPane.add(rulesPane);
		
		JLabel lblNewLabel_1_1 = new JLabel("Use/Set Rules");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(6, 17, 132, 16);
		rulesPane.add(lblNewLabel_1_1);
		
		JPanel contentPane = new JPanel();
		contentPane.setBounds(161, 0, 650, 483);
		frame.getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		contentPane.add(extract_panel);
		contentPane.add(visualize_panel);
		contentPane.add(rules_panel);
	}
	
	public void menuClicked(JPanel panel) {
		extract_panel.setVisible(false);
		visualize_panel.setVisible(false);
		rules_panel.setVisible(false);
		
		panel.setVisible(true);
	}
}
