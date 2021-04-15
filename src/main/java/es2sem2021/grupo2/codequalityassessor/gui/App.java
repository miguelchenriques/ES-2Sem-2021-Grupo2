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
	private PanelExtrair panel_extrair;
	private PanelVisualizar panel_visualizar;

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
		frame.setBounds(100, 100, 610, 433);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel_extrair = new PanelExtrair();
		panel_visualizar = new PanelVisualizar();
		
		JPanel menuPane = new JPanel();
		menuPane.setBackground(Color.GRAY);
		menuPane.setBounds(0, 0, 162, 405);
		frame.getContentPane().add(menuPane);
		menuPane.setLayout(null);
		
		JPanel extrairMetricasPane = new JPanel();
		extrairMetricasPane.setName("extrair");
		extrairMetricasPane.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				menuClicked(panel_extrair);
			}
		});
		extrairMetricasPane.setBackground(Color.LIGHT_GRAY);
		extrairMetricasPane.setBounds(6, 121, 150, 50);
		menuPane.add(extrairMetricasPane);
		extrairMetricasPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Extrair métricas");
		lblNewLabel.setBounds(22, 16, 99, 16);
		extrairMetricasPane.add(lblNewLabel);
		
		JPanel visualizarMetricasPane = new JPanel();
		visualizarMetricasPane.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				menuClicked(panel_visualizar);
			}
		});
		visualizarMetricasPane.setBackground(Color.LIGHT_GRAY);
		visualizarMetricasPane.setBounds(6, 183, 150, 50);
		menuPane.add(visualizarMetricasPane);
		visualizarMetricasPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Visualizar métricas");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(6, 17, 132, 16);
		visualizarMetricasPane.add(lblNewLabel_1);
		
		JPanel contentPane = new JPanel();
		contentPane.setBounds(161, 0, 449, 405);
		frame.getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		contentPane.add(panel_extrair);
		contentPane.add(panel_visualizar);
	}
	
	public void menuClicked(JPanel panel) {
		panel_extrair.setVisible(false);
		panel_visualizar.setVisible(false);
		
		panel.setVisible(true);
	}
	
}
