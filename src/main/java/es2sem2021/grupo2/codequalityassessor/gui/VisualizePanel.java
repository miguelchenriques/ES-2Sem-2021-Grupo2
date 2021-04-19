package es2sem2021.grupo2.codequalityassessor.gui;

import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es2sem2021.grupo2.codequalityassessor.metrics.MetricsSummary;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VisualizePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel n_packages;
	JLabel n_classes;
	JLabel n_methods;
	JLabel n_lines;
	
	JLabel fileName;

	/**
	 * Create the panel.
	 */
	public VisualizePanel() {
		setBounds(0, 0, 650, 483);
		setLayout(null);
		setVisible(false);

		n_packages = new JLabel("New label");
		n_packages.setBounds(283, 183, 61, 16);
		add(n_packages);
		n_packages.setVisible(false);

		n_classes = new JLabel("New label");
		n_classes.setBounds(283, 211, 61, 16);
		add(n_classes);
		n_classes.setVisible(false);

		n_methods = new JLabel("New label");
		n_methods.setBounds(283, 239, 61, 16);
		add(n_methods);
		n_methods.setVisible(false);

		n_lines = new JLabel("New label");
		n_lines.setBounds(283, 267, 61, 16);
		add(n_lines);
		n_lines.setVisible(false);
		
		fileName = new JLabel("File ...");
		fileName.setHorizontalAlignment(SwingConstants.CENTER);
		fileName.setBounds(54, 114, 357, 35);
		add(fileName);
		fileName.setVisible(false);

		JButton chooseFile = new JButton("Import File");
		chooseFile.setBounds(133, 56, 169, 29);
		add(chooseFile);

		chooseFile.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				// invoke the showsOpenDialog function to show the save dialog
				int r = j.showOpenDialog(null);

				if (r == JFileChooser.APPROVE_OPTION) {
					FileInputStream file;
					try {
						file = new FileInputStream(j.getSelectedFile());
						Workbook workbook = new XSSFWorkbook(file);
						MetricsSummary metricsSummary = new MetricsSummary(workbook);
						n_packages.setText(String.valueOf(metricsSummary.getNumberOfPackages()));
						n_packages.setVisible(true);

						n_classes.setText(String.valueOf(metricsSummary.getNumberOfClasses()));
						n_classes.setVisible(true);

						n_methods.setText(String.valueOf(metricsSummary.getTotalNumberOfMethods()));
						n_methods.setVisible(true);

						n_lines.setText(String.valueOf(metricsSummary.getTotalLinesOfCode()));
						n_lines.setVisible(true);
						
						fileName.setText("Viewing metrics of file: " + j.getSelectedFile().getName());
						fileName.setVisible(true);

					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});

		JLabel lblNewLabel = new JLabel("Number of Packages:");
		lblNewLabel.setBounds(72, 183, 140, 16);
		add(lblNewLabel);

		JLabel lblNumberOfClasses = new JLabel("Number of Classes:");
		lblNumberOfClasses.setBounds(72, 211, 140, 16);
		add(lblNumberOfClasses);

		JLabel lblNumberOfMethods = new JLabel("Number of Methods:");
		lblNumberOfMethods.setBounds(72, 239, 140, 16);
		add(lblNumberOfMethods);

		JLabel lblNumberOfLines = new JLabel("Number of Lines Of Code:");
		lblNumberOfLines.setBounds(72, 267, 180, 16);
		add(lblNumberOfLines);
		

	}

}
