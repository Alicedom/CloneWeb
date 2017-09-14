package model;

import org.jsoup.nodes.Element;

public class Content {
	private boolean choose;
	private String attribute;
	private Element content; 
	public Content() {
		choose = true;
		content = null;
	}
	public boolean isChoose() {
		return choose;
	}
	public void setChoose(boolean choose) {
		this.choose = choose;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public Element getContent() {
		return content;
	}
	public void setContent(Element content) {
		this.content = content;
	}
	
	
}
