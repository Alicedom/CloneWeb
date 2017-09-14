package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CloneTamlyhoc {

	private static Document doc;
	private LinkedList<String> linkListCategories;
	private LinkedList<String> linkListArticles;

	private final String homeLink ="http://tamlyhoctoipham.com/";
	private final String folderContent = "tam ly hoc toi pham\\content\\";
	private final String folderDetail = "tam ly hoc toi pham\\detail\\";
	private final String logName = "E:\\tam ly hoc toi pham\\log.txt";
	private Writer log;
	private Element info;

	public CloneTamlyhoc() throws IOException {
		try{
			File fileLog = new File(logName);
			if(!fileLog.exists())
				fileLog.createNewFile();
			if(log == null){
				log = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(fileLog,true), "UTF-8"));
			}

			long start = System.currentTimeMillis();

			LinkedList<String> categories = getCategories();
			for (String category : categories){
				LinkedList<String> articles = getAllArticlesFromCategory(category);
				for(String article: articles){
						try {
							getFile(article);
						} catch (IOException e) {
							log.write(e.getMessage()+"\n");
						}
				}
			}
			long stop = System.currentTimeMillis();

			
			System.out.println(stop- start);
			log.write(String.valueOf(stop- start));
		}finally {
			log.close();
			
		}
	}

	public LinkedList<String> getCategories(){
		linkListCategories = new LinkedList<String>();
		doc = getDocToLink(homeLink);

		if(doc != null){
			Element main_menu_id = doc.getElementById("main-menu").children().first();
			Elements main_menu_list_item = main_menu_id.getElementsByTag("li");
			for (Element main_menu_list_item_elem : main_menu_list_item){
				String link = main_menu_list_item_elem.select("a").attr("href");
				if (link.contains("category") && !linkListCategories.contains(link)){
					linkListCategories.add(link);
					System.out.println(link);
				}
			}
		}

		return linkListCategories;
	}

	public LinkedList<String> getAllArticlesFromCategory(String categoryLink){
		linkListArticles = new LinkedList<String>();
		doc = getDocToLink(categoryLink);
		String currentLink = categoryLink; 

		while(true){	
			if(doc != null){		
				linkListArticles.addAll(getAllArticlesFromPage(doc));
				currentLink = linkNextPage(categoryLink, doc);
				if(currentLink != null)
					doc = getDocToLink(currentLink);
				else
					break;
			}
			else
				break;
		}

		return linkListArticles;
	}

	public LinkedList<String> getAllArticlesFromPage(Document docCurentPage){

		LinkedList<String> linkListArticles = new LinkedList<>();

		Element articles_class = docCurentPage.getElementsByClass("composs-blog-list lets-do-1").first();
		Elements articles_header_item_class = articles_class.getElementsByClass("item-header");

		if(articles_header_item_class != null)
			for(Element article_header_item: articles_header_item_class){
				String article_link = article_header_item.select("a").attr("href");
				//if(!linkListArticles.contains(article_link))
				System.out.println(article_link);
				linkListArticles.add(article_link);
			}

		return linkListArticles;
	}

	private String linkNextPage(String curentCategory, Document curentDoc){

		Element pageIndex = curentDoc.getElementsByClass("composs-panel-pager").first();
		boolean hasNextPage = pageIndex.getElementsByClass("next page-numbers").first() != null;

		if (hasNextPage){
			String currentPage =pageIndex.getElementsByClass("page-numbers current").text();
			System.out.println("finish page"+currentPage);
			String nextPage = curentCategory+"?page="+String.valueOf((Integer.valueOf(currentPage)+1));
			return nextPage;
		}
		else
			return null;
	}

	public void getFile(String linkArticle) throws IOException{

		log.write(linkArticle);
		String fileName = linkArticle.replace(homeLink, "");

		doc = getDocToLink(linkArticle);
		Element content = doc.getElementsByClass("composs-main-article-content").first();
		
		getContent(fileName, content);
		getDetail(fileName, content);
	}

	private void getContent(String fileName, Element content) throws IOException{
		
		File fileContent;
		fileContent= new File(folderContent);
		if(! fileContent.exists())
			fileContent.mkdirs();

		fileContent= new File(folderContent+fileName+".html");
		if(!fileContent.exists()){
			fileContent.createNewFile();

			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileContent), "UTF-8"));
			

			try {
				info = content.getElementsByClass("composs-main-article-meta").first();

				Element shortContentClass = content.getElementsByClass("shortcode-content").first();
				out.write(shortContentClass.html());
				System.out.println("write sucess: "+fileName);
			} finally {
				out.close();
			}
		}
	}
	
	private void getDetail(String fileName, Element content) throws IOException{
		File fileContent= new File(folderDetail);
		if(! fileContent.exists())
			fileContent.mkdirs();
		fileContent= new File(folderDetail+fileName+".txt");
		if(!fileContent.exists()){

			fileContent.createNewFile();
			Writer out1 = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileContent), "UTF-8"));

			try {
				if(info != null){
					String title = content.getElementsByTag("h1").text();
					log.write(title+"\n");
					System.out.println(title);
					out1.write(title+"\n");

					String date = info.getElementsByTag("span").first().text();
					if(date.contains("access_time"))
						date = date.replace("access_time", "");
					log.write(date+"\n");
					System.out.println(date);
					out1.write(date+"\n");

					Elements categories = info.select("a[href]");
					for(Element e:categories){
						System.out.println(e.text());
						log.write("#"+e.text()+"\n");
						out1.write("#"+e.text()+"\n");
					}
				}
			} finally {
				out1.close();
			}
		}
	}

	private Document getDocToLink(String link){
		try {
			doc = Jsoup.connect(link).get();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return doc;
	}

	public static void main(String[] args) {

		try {
			new CloneTamlyhoc();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
