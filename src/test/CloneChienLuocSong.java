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

public class CloneChienLuocSong {

	private Document doc;
	private String[] categories = {"sach-hay","phat-giao","ky-nang","kien-thuc","thai-do"};
	private String home = "http://chienluocsong.com";
	private LinkedList<String> linkListArticles;
	private final String folderContent = "chienluocsong/content/";
	private final String folderDetail = "chienluocsong/detail/";
	private String logName ="chienluocsong/log.txt";
	private String logLink ="chienluocsong/loglink.txt";
	private Writer logNameWt;
	private Writer logLinkWt;
	private Element info;

	
	public CloneChienLuocSong() throws IOException {
		try{
			File fileLog = new File(logName);
			if(!fileLog.exists())
				fileLog.createNewFile();
			if(logNameWt == null){
				logNameWt = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(fileLog,true), "UTF-8"));
			}
			File fileLogLink = new File(logLink);
			if(!fileLogLink.exists())
				fileLogLink.createNewFile();
			if(logLinkWt == null){
				logLinkWt = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(fileLogLink,true), "UTF-8"));
			}

			long start = System.currentTimeMillis();

			for (String category : categories){
				LinkedList<String> articles = getAllArticlesFromCategory(home+"/category/"+category);
				for(String article: articles){
						try {
							getFile(article);
						} catch (IOException e) {
							logNameWt.write(e.getMessage()+"\n");
						}
				}
			}
			long stop = System.currentTimeMillis();

			
			System.out.println(stop- start);
			logNameWt.write(String.valueOf(stop- start));
		}finally {
			logNameWt.close();	
		}
	}
	

	private void getFile(String linkArticle) throws IOException{

		logNameWt.write(linkArticle);
		String fileName = linkArticle.replace(home, "");
		fileName = linkArticle.replace("/", "");
		System.out.println(fileName);

		System.out.println("get File _ link Article "+linkArticle);
		doc = getDocToLink(linkArticle);
		Element content = doc.getElementsByClass("td-ss-main-content").first();
		
		getContent(fileName, content);
		getDetail(fileName, content);
	}
	

	private void getContent(String fileName, Element content) throws IOException{
		
		File fileContent;
		fileContent= new File(folderContent);
		if(! fileContent.exists())
			fileContent.mkdirs();

		fileContent= new File(folderContent+fileName+".html");
		System.out.println(fileContent.getPath());
		if(!fileContent.exists()){
			fileContent.createNewFile();

			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileContent), "UTF-8"));
			

			try {
				info = content.getElementsByClass("td-post-header").first();
				
				out.write("<html> \n <title> <meta chaset=\"UTF-8\"></title>");

				Element shortContentClass = content.getElementsByClass("td-post-content").first();
				out.write(shortContentClass.html());
				out.write("</html>");
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
					String title = content.getElementsByClass("entry-title").first().text();
					logNameWt.write(title+"\n");
					System.out.println(title);
					out1.write(title+"\n");

					String date = info.getElementsByClass("td-post-date").first().text();
					logNameWt.write(date+"\n");
					System.out.println(date);
					out1.write(date+"\n");

					String author = info.getElementsByClass("td-post-author-name").first().text();
					logNameWt.write(author);
					System.out.println(author);
					out1.write(author);
					
					out1.flush();
					logNameWt.flush();
				}
			} finally {
				out1.close();
			}
		}
	}



	public LinkedList<String> getAllArticlesFromCategory(String categoryLink){
		
		linkListArticles = new LinkedList<String>();
		doc = getDocToLink(categoryLink);
		String currentLink = categoryLink; 
		
		int numberpage = getNumpage(doc.getElementsByClass("page-nav td-pb-padding-side").first());
		int numberpageCurrent = 1;

		while(numberpageCurrent <= numberpage){	
			if(doc != null){
				
//				currentLink = linkNextPage(categoryLink, numberpageCurrent);
				linkListArticles.addAll(getAllArticlesFromPage(doc));
				currentLink = linkNextPage(categoryLink, numberpageCurrent);
				if(currentLink != null)
					doc = getDocToLink(currentLink);
				else
					break;
				numberpageCurrent++;
			}
			else
				break;
		}

		return linkListArticles;
	}

	private String linkNextPage(String categoryLink, int  numberCurrentLink) {
//		numberCurrentLink++;
		return categoryLink+"/page/"+String.valueOf(numberCurrentLink)+"/";
	}


	public LinkedList<String> getAllArticlesFromPage(Document docCurentPage){

		LinkedList<String> linkListArticles = new LinkedList<>();

		Element articles_class = docCurentPage.getElementsByClass("td-ss-main-content").first();
		Elements articles_header_item_class = articles_class.getElementsByClass("td_module_10 td_module_wrap td-animation-stack");

		if(articles_header_item_class != null)
			for(Element article_header_item: articles_header_item_class){
				String article_link = article_header_item.select("a").attr("href");
				//if(!linkListArticles.contains(article_link))
				try {
					logLinkWt.write(article_link);
					logLinkWt.write("\n");
					logLinkWt.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(article_link);
				linkListArticles.add(article_link);
			}

		return linkListArticles;
	}

	
	private int getNumpage(Element e) {
		Elements listNumPage = e.select("a");
		int number = Integer.valueOf(listNumPage.get(listNumPage.size()-2).text());
		System.out.println("numberpage "+number);
		return number;
		
	}

	private Document getDocToLink(String link){
		try {
//			System.out.println(link);
			doc = Jsoup.connect(link).get();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return doc;
	}
	

	public static void main(String[] args) {

		try {
			new CloneChienLuocSong();
			 
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}




}
