package lsc.andrux.rest.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlRootElement(name="quote")
public class Quote {
	
	
	
	private String quote;
	
	private String author;
	
	private String category;
	
	private String cat;
	
	
	
	public Quote() { }
	
	
	
	public String getQuote() { return this.quote; }
	
	public void setQuote(String x) { this.quote = x; }
	
	
	public String getAuthor() { return this.author; }
	
	public void setAuthor(String x) { this.author = x; }
	
	
	public String getCategory() { return this.category; }
	
	public void setCategory(String x) { this.category = x; }
	
	
	public String getCat() { return this.cat; }
	
	public void setCat(String x) { this.cat = x; }
	
	
	
	
}


