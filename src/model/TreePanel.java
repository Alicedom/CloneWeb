package model;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TreePanel extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<Content> listContent;
	private JPanel panel;
	
	public TreePanel() {
		if(listContent ==null) {
			listContent = new LinkedList<Content>();
		}
	}
	
	public JPanel createTreePanel(LinkedList list, int deep) {
		for (Object i: list){
			if ( i instanceof LinkedList){
				createTreePanel((LinkedList)i,deep);
			}
			else{
				JLabel lb = new JLabel(deep+"____File" + (String)i);
				lb.addMouseListener(this);
				panel.add(lb);
			}
		}
		return null;
	}
	
	public StringBuilder createHTMLfromList() {
		StringBuilder html = new StringBuilder();
		html.append("<p>");
		for(Content content: listContent) {
			if(content.isChoose()) {
				html.append(
						"<p id="+content.getAttribute()+"\">"
								+content.getAttribute()+
						"</p>" );
			}
		}
		html.append("</p>");
		
		return html;
	}
	public void addNewcontent(Content content) {
		listContent.add(content); 
	}
	public void resetList() {
		listContent.remove();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
