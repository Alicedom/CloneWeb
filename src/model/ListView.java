package model;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ListView {
	private Document doc=null;

	public LinkedList<Content> list;
	public ListView(File dir) {
		list = new LinkedList<Content>();
		loadListFromDir(dir);

	}
	private void loadListFromDir(File dir) {
		List<File> files = new ArrayList<File>(Arrays.asList(dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {

				String name = pathname.getName().toLowerCase();
				return name.endsWith(".html") && !("System Volume Information".equalsIgnoreCase(name));

			}
		})));

		Collections.sort(files);

		for (File file : files) {
			if (file.isDirectory()) {
				loadListFromDir(file);
			}
		}
		for (File file : files) {

			if (file.isFile()) {
				Content contentFile = new Content(file.getName());

				if(file.getName().toLowerCase().endsWith(".html")) {
					contentFile.setElement(getDocToFile(file));
				}

				list.add(contentFile);
			}
		}
		
	}
	private Document getDocToFile(File dir){


		//***************Chuyen thread de load********************//
		try {
			doc = Jsoup.parse(dir, "UTF-8");
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return doc;
	}
	public LinkedList<Content> getList(){
		return list;
	}
	public Content getContent(int index) {
		return list.get(index);
	}}
