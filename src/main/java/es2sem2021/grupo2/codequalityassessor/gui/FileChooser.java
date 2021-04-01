package es2sem2021.grupo2.codequalityassessor.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import es2sem2021.grupo2.codequalityassessor.xlsx.FileGenerator;
   
public class FileChooser extends JFrame implements ActionListener{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	FileChooser(){
		
	}
	
	static JLabel label = new JLabel();
	
	public static void main(String args[]){
        JFrame frame = new JFrame("File chooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        JButton openButton = new JButton("open");
        FileChooser f = new FileChooser();
        
        openButton.addActionListener(f);
       
        JPanel panel = new JPanel();
        panel.add(openButton);
        
        panel.add(label);
        frame.add(panel);
     
        frame.setVisible(true);
     }

	@Override
	public void actionPerformed(ActionEvent evt)
    {
        // if the user presses the save button show the save dialog
        String com = evt.getActionCommand();
  
        // if the user presses the open dialog show the open dialog
        if(com.equals("open")) {
        	// create an object of JFileChooser class
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
				} catch (InvalidFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            // if the user cancelled the operation
            else
                label.setText("the user cancelled the operation");
        }
    }
}