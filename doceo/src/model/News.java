package model;

import java.util.ArrayList;

public class News {
	private String id;
	private String title;
	private String src;
	private String date;
	private String url;
	private String content;
	private ArrayList<Sentence> sentences;
	
	public News(){
		
	}

	public News(String id, String title, String src, String date,
			String content, String url) {
		this.id = id;
		this.title = title;
		this.src = src;
		this.date = date;
		this.url = url;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public String getSrc() {
		return src;
	}

	public String getDate() {
		return date;
	}

	public String getUrl() {
		return url;
	}

	public String getContent() {
		return content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArrayList<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(ArrayList<Sentence> sentences) {
		this.sentences = sentences;
	}

	public String toString() {
		return "title:" + getTitle() + "--src:" + getSrc() + "--date:"
				+ getDate() + "--url:" + getUrl() + "--sentences:"
				+ getSentences() + "--content:" + getContent();
	}
}
