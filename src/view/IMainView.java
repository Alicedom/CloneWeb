package view;

import java.awt.event.ActionListener;

import javax.swing.Action;

public interface IMainView {
	
	public void addClickListtenerForOpenItemMenu(ActionListener listener);
	
	public void addClickListtenerForSaveItemMenu(ActionListener listener);
	
	public void addClickListtenerForImportItemMenu(ActionListener listener);
	
	public void addClickListtenerForExportItemMenu(ActionListener listener);
	
	public void addClickListtenerForFindButtun(ActionListener listener);
	
	public void addClickListtenerForItemMenu(Action action);
	
	public String getSelection();
	
	public String getElement();

}
