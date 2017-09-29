package model;

import org.jsoup.nodes.Element;

public class Content {
	private String attribute;
	private Element element;
	
	public Content(String attribute) {		
		this.attribute = attribute;
		this.element = null;
	}
	
    public String toString() {
        return attribute;
    }
	
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public Element getElement() {
		return element;
	}
	public void setElement(Element content) {
		this.element = content;
	}
	
	
}
