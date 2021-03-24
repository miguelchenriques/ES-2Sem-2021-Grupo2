package es2sem2021.grupo2.codequalityassessor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
   
public class FileChooser extends JFrame implements ActionListener{


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
            }
            // if the user cancelled the operation
            else
                label.setText("the user cancelled the operation");
        }
    }
}