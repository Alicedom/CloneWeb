package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.BookView;

public class BookViewController {
	BookView bookview;
	public BookViewController() {
		 bookview = new BookView();
		 bookview.addClickListtenerForButtonChangeText(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
	}

}
