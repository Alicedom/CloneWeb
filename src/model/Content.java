package model;

import java.io.File;

import org.jsoup.nodes.Element;

public class Content {
	private String attribute;
	private File path;
	private Element element;
	
	public Content(String attribute) {		
		this.attribute = attribute;
		this.element = null;
		this.path = null;
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
	
	public void setPath(File path) {
		this.path = path;
	}
	public File getPath() {
		return path;
	}
}
