package control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import view.IMainView;
import view.MainView;

public class CloneWebProgram {
	
	private IMainView main;
	
	public CloneWebProgram() {
		main = new MainView();
		
		main.addClickListtenerForFindButtun(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selection = main.getSelection();
				String element = main.getElement();
				
				System.out.println("Selection: "+ selection + " Element: "+ element);
			}
		});
		
		main.addClickListtenerForOpenItemMenu(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = openJChooseFile("Open");
				System.out.println(file);
				
			}
		});
		
		main.addClickListtenerForSaveItemMenu(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = openJChooseFile("Save");
				System.out.println(file);
			}
		});
		main.addClickListtenerForImportItemMenu(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = openJChooseFile("Import");
				System.out.println(file);
			}
		});
		main.addClickListtenerForExportItemMenu(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = openJChooseFile("Export");
				System.out.println(file);
			}
		});
	}

	private File openJChooseFile(String string) {
		File file = null;
		
		JFileChooser fc= new JFileChooser();
		int returnVal = fc.showDialog((Component) main, string);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            System.out.println(file.getPath());
        }
        
        return file;
	}


}
