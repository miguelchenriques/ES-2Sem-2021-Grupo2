package es2sem2021.grupo2.codequalityassessor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import es2sem2021.grupo2.codequalityassessor.xlsx.FileGenerator;
import javax.swing.JLabel;
import javax.swing.JButton;

public class PanelExtrair extends JPanel {

	/**
	 * Create the panel.
	 */

	JLabel label = new JLabel("New label");

	public PanelExtrair() {
		setBounds(0, 0, 449, 405);
		setLayout(null);
		setVisible(true);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 449, 405);
		add(panel);
		panel.setLayout(null);

		JButton openButton = new JButton("Choose File");
		openButton.setBounds(153, 126, 117, 29);
		panel.add(openButton);
		label.setBounds(183, 193, 61, 16);
		panel.add(label);
		FileChooser f = new FileChooser();
		openButton.addActionListener(f);
		
		openButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
            	JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    			// set the selection mode to directories only
    			j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    			// invoke the showsOpenDialog function to show the save dialog
    			int r = j.showOpenDialog(null);

    			if (r == JFileChooser.APPROVE_OPTION) {
    				// set the label to the path of the selected directory
    				label.setText(j.getSelectedFile().getAbsolutePath());
    				FileGenerator fileGenerator = new FileGenerator(j.getSelectedFile());
    				try {
    					fileGenerator.main();
    				} catch (InvalidFormatException i) {
    					// TODO Auto-generated catch block
    					i.printStackTrace();
    				} catch (IOException i) {
    					// TODO Auto-generated catch block
    					i.printStackTrace();
    				}
    			}
                
            }
		});
	}

//	public void actionPerformed(ActionEvent evt) {
//		// if the user presses the save button show the save dialog
//		String com = evt.getActionCommand();
//
//		// if the user presses the open dialog show the open dialog
//		if (com.equals("open")) {
//			// create an object of JFileChooser class
//			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//
//			// set the selection mode to directories only
//			j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//
//			// invoke the showsOpenDialog function to show the save dialog
//			int r = j.showOpenDialog(null);
//
//			if (r == JFileChooser.APPROVE_OPTION) {
//				// set the label to the path of the selected directory
//				label.setText(j.getSelectedFile().getAbsolutePath());
//				FileGenerator fileGenerator = new FileGenerator(j.getSelectedFile());
//				try {
//					fileGenerator.main();
//				} catch (InvalidFormatException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			// if the user cancelled the operation
//			else
//				label.setText("the user cancelled the operation");
//		}
//
//	}

}
