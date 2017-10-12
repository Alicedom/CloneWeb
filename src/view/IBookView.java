package view;

import java.awt.event.ActionListener;

public interface IBookView {
	public void addClickListtenerForButtonChoice(ActionListener listener);

	public void addClickListtenerForButtonPrintToPDF(ActionListener listener);
}
