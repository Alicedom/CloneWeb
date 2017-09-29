package control;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import org.jsoup.nodes.Document;

import model.GenCode;
import model.TreeView;
import view.IMainView;
import view.MainView;

public class CloneWebProgram {
	
	private IMainView main;
	private Document doc;
	private GenCode gencode;
	private Container cp; 
	private TreeView treeview;
	public CloneWebProgram() {
		main = new MainView();
		gencode = new GenCode(); 
//		JFrame frame = new JFrame();
		cp= main.getPanelLeft_Result();
		treeview =new TreeView(new File(".")); 
		cp.add(treeview);
				
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
				if(file != null) 
					if (file.isDirectory() || file.getPath().endsWith(".html")) {
						treeview = new TreeView(file);
					cp.add(treeview);
					
					cp.validate();
//					/cp.repaint();
				}
					
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
		
		main.addFindAllLinkButtonListenner(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(doc == null) {
					System.out.println("null");
				}else {
					gencode.getAllLink();
				}
				
			}
		});
	}

	private File openJChooseFile(String string) {
		File file = null;
		
		JFileChooser fc= new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = fc.showDialog((Component) main, string);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            System.out.println("Choose: "+file.getPath());
        }
        
        return file;
	}


}
