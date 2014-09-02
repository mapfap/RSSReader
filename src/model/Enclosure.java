package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Enclosure data model of rss, hold the multimedia content.
 * 
 * @author Sarun Wongtanakarn 5510546166
 *
 */
@XmlAccessorType (XmlAccessType.FIELD)
public class Enclosure {
	
	@XmlAttribute
	private String url;
	@XmlAttribute
	private int length;
	@XmlAttribute
	private String type;
	
	public Enclosure() {
		
	}
	
	@Override
	public String toString() {
		return getType() + " " + getUrl(); 
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
}
