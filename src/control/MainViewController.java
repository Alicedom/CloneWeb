package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import model.DynamicTreeDemo;
import model.OpenJChooFile;
import view.MainView;

public class MainViewController {
	
	private static MainView main;
	public static DynamicTreeDemo treeview;
	public MainViewController() {
		main = new MainView();
		treeview = new DynamicTreeDemo(new File("."));
		MainView.panelTreeView.add(treeview);		
		main.addClickListtenerForFindButtun(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selection = MainView.cbSelection.getSelectedItem().toString();
				String element = MainView.tfInput.getText();

				System.out.println("Selection: "+ selection + " Element: "+ element);
			}
		});
		
		main.addClickListtenerForOpenItemMenu(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = OpenJChooFile.open(main,"Open");
				treeview = new DynamicTreeDemo(file);
			}
		});
		
		main.addClickListtenerForSaveItemMenu(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = OpenJChooFile.open(main, "Save");
				System.out.println(file);
			}
		});
		
		main.addClickListtenerForImportItemMenu(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = OpenJChooFile.open(main, "Import");
				System.out.println(file);
			}
		});
		main.addClickListtenerForExportItemMenu(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = OpenJChooFile.open(main,"Export");
				System.out.println(file);
			}
		});
		
	}


}
