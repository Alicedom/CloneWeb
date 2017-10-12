package control;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import com.itextpdf.text.DocumentException;

import model.Content;
import model.Tool;
import view.BookView;

public class BookViewController {
	BookView bookview;
	Content content;

	public BookViewController(LinkedList<Content> listContent) {
		bookview = new BookView();
		loadBookView(bookview.getPanelContent(), listContent);


		bookview.addClickListtenerForButtonChoice(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (content != null) {
					Tool.showPagefromHTMLString(content.getElement().html());
				}else {
					System.out.println("Book View Controlller - Content null");
					JOptionPane.showMessageDialog(null, "File "+content.getPath().toString()+"not found");
				}
			}
		});

		bookview.addClickListtenerForButtonPrintToPDF(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(content != null) {

					File file = Tool.openJChooseFile(bookview, "Save");
					try {
						Tool.convertFilePDFfromHTMLStringUseCrowdAPI(file, content.getElement().html());
//						Tool.createPdf(file, content.getPath());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}else {
					System.out.println("Book View Controlller - Content null");
					JOptionPane.showMessageDialog(null, "File "+content.getPath().toString()+"not found");
				}

			}
		});

	}

	private void loadBookView(JPanel panelContent, LinkedList<Content> listContent) {

		panelContent.setLayout(new GridLayout(0, 1));
		for (int i=0;i< listContent.size();i++) {
			Content content = listContent.get(i);
			JLabel label = new JLabel(content.getAttribute());
			panelContent.add(label);

			//Day content ra ngoai listenner,
			//su dung 1 phuong thuc ben ngoai
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					super.mousePressed(e);
					loadInfo(content);
				}
			} );
		}

	}
	protected void loadInfo(Content content) {
		this.content = content;
		if(bookview.getCheckBoxChangeToHTML()) {
			bookview.setTextArea(content.getElement().html());
		} else {
			bookview.setTextArea(getText(content.getElement()));
		}

	}


	private String getText(Element parentElement) {
		String working = "";

		String descrizione = Jsoup.parse(parentElement.html().replaceAll("(?i)<br[^>]*>", "br2n")).text();
		descrizione = Jsoup.parse(descrizione.replaceAll("<p*>", "br2n")).text();
		working = descrizione.replaceAll("br2n", "\n");

		return working;
	}
}
