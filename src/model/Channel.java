package model;

import java.util.List;

public class Channel {
	
	private String title;
	private String description;
	private String link;
	private String lastBuildDate;
	private String pubDate;
	private String ttl;
	List<Item> items;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	
	}
	public String getLastBuildDate() {
		return lastBuildDate;
	
	}
	
	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}
	
	public String getPubDate() {
		return pubDate;
	}
	
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	
	public String getTtl() {
		return ttl;
	}
	
	public void setTtl(String ttl) {
		this.ttl = ttl;
	}
}
