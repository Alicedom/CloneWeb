package model;

public class Content {

	private String title;
	private String date;
	private String category;
	private String shortContent;
	private String mainContent;
	
	public Content() {
		// TODO Auto-generated constructor stub
	}
	
	public Content(String initTitle, String initDate, String initCategory, String initShortContent, String initMainContent) {
		title = initTitle;
		date = initDate;
		category = initCategory;
		shortContent = initShortContent;
		mainContent = initMainContent;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getShortContent() {
		return shortContent;
	}
	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
	}
	public String getMainContent() {
		return mainContent;
	}
	public void setMainContent(String mainContent) {
		this.mainContent = mainContent;
	}
}
