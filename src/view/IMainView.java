package view;

import java.awt.event.ActionListener;


public interface IMainView {
	/*
	 * Open HTML file 
	 */
	public void addClickListtenerForOpenItemMenu(ActionListener listener);
	/*
	 * Save HMTL file
	 */
	public void addClickListtenerForSaveItemMenu(ActionListener listener);
	
	/*
	 * Import parse code from file
	 */
	public void addClickListtenerForImportItemMenu(ActionListener listener);
	
	/*
	 * Export parse code into file
	 */
	public void addClickListtenerForExportItemMenu(ActionListener listener);
	
	/*
	 *Action parse HTML 
	 */
	public void addClickListtenerForFindButtun(ActionListener listener);
	
}
