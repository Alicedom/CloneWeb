package model;

import java.util.LinkedList;

import javax.swing.text.html.HTML;

public class TreeHTML {

	private LinkedList<Content> listContent;
	public TreeHTML() {
		if(listContent ==null) {
			listContent = new LinkedList<Content>();
		}
	}
	public String createHTMLfromList() {
		HTML html = new HTML();
		for(Content content: listContent) {
			if(content.get)
		}
		
		return null;
	}
	public void addNewcontent(Content content) {
		listContent.add(content); 
	}
	public void resetList() {
		listContent.remove();
	}
	
}
