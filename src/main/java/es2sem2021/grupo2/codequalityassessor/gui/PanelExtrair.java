package es2sem2021.grupo2.codequalityassessor.gui;

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
import javax.swing.SwingConstants;

public class PanelExtrair extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */

	JLabel label = new JLabel("New label");

	public PanelExtrair() {
		setBounds(0, 0, 449, 405);
		setLayout(null);
		setVisible(false);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 449, 405);
		add(panel);
		panel.setLayout(null);

		JButton openButton = new JButton("Choose File");
		openButton.setBounds(157, 125, 117, 29);
		panel.add(openButton);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(65, 193, 306, 51);
		label.setVisible(false);
		panel.add(label);
		
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
    				label.setText("File: " + j.getSelectedFile().getAbsolutePath() + " generated.");
    				label.setVisible(true);
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

}
