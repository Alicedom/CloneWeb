package model;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GenCode {

	Elements listElement;
	public GenCode() {
		
	}
	
	public LinkedList<String> getAllLink(){
		LinkedList<String> listLink = null;
		
		if(listElement == null) {
			System.out.println("null");
		}else {
			listLink = new LinkedList<>();			
			
			for(Element item: listElement) {
				listLink.addAll(getAllLink(item));
			}
			
		}
		return listLink;
	}
	public LinkedList<String> getAllLink(Element element) {
		LinkedList<String> listLink = null;
		
		if(element == null) {
			System.out.println("null");
		}else {
			listLink = new LinkedList<>();
			
			Elements list = element.getElementsByTag("a");
			for(Element item: list) {
				listLink.add(item.select("a").attr("href"));
			}
			
		}
		return listLink;
	}
	
	private Document getDocToLink(String link){
		Document doc=null;
		try {
			doc = Jsoup.connect(link).get();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return doc;
	}
	
}
