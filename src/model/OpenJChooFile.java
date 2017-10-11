package model;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;

public class OpenJChooFile {

	public static File open(Component main, String string) {
		File file = null;
		
		JFileChooser fc= new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = fc.showDialog(main, string);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            System.out.println("Choose: "+file.getPath());
        }
        
        return file;
	}
}
