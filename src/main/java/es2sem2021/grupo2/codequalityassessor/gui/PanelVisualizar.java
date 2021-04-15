package es2sem2021.grupo2.codequalityassessor.gui;

import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import es2sem2021.grupo2.codequalityassessor.xlsx.FileGenerator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class PanelVisualizar extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelVisualizar() {
		setBounds(0, 0, 449, 405);
		setLayout(null);

		JButton chooseFile = new JButton("Importar ficheiro");
		chooseFile.setBounds(123, 73, 169, 29);
		add(chooseFile);

		chooseFile.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				
    			// invoke the showsOpenDialog function to show the save dialog
    			int r = j.showOpenDialog(null);

    			if (r == JFileChooser.APPROVE_OPTION) {
    				
    			}
			}
		});

		JLabel lblNewLabel = new JLabel("Number of Packages:");
		lblNewLabel.setBounds(41, 156, 140, 16);
		add(lblNewLabel);

		JLabel lblNumberOfClasses = new JLabel("Number of Classes:");
		lblNumberOfClasses.setBounds(41, 184, 140, 16);
		add(lblNumberOfClasses);

		JLabel lblNumberOfMethods = new JLabel("Number of Methods:");
		lblNumberOfMethods.setBounds(41, 212, 140, 16);
		add(lblNumberOfMethods);

		JLabel lblNumberOfLines = new JLabel("Number of Lines Of Code:");
		lblNumberOfLines.setBounds(41, 240, 180, 16);
		add(lblNumberOfLines);

		JLabel n_packages = new JLabel("New label");
		n_packages.setBounds(266, 156, 61, 16);
		add(n_packages);

		JLabel n_classes = new JLabel("New label");
		n_classes.setBounds(266, 184, 61, 16);
		add(n_classes);

		JLabel n_methods = new JLabel("New label");
		n_methods.setBounds(266, 212, 61, 16);
		add(n_methods);

		JLabel n_lines = new JLabel("New label");
		n_lines.setBounds(266, 240, 61, 16);
		add(n_lines);
		setVisible(false);

	}

}
