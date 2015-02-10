package lsc.rest.model;

import java.net.URI;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name="link")
@XmlType(name = "link", propOrder = { "rel", "href" })
public class Link<L extends RelationInterface> {

	
	private String rel;
	private String href;
	
	
	

	public Link() {	}
	public Link(String rel, String href) {
		this.rel = rel;
		this.href = href;
	}
	public Link(L rel, String href) {
		this.rel = rel.toString();
		this.href = href;
	}
	
	
	
	
	
	@XmlAttribute
	public String getRel() { return this.rel;	}
	public Link<L> setRel(String rel) {
		this.rel = rel;
		return this;
	}
	
	@XmlAttribute
	public String getHref() { return this.href; }
	public Link<L> setHref(String href) {
		this.href = href;
		return this;
	}
	
	
	
	
	
	// utils

	public int _parseId(){
		URI uri = URI.create( this.href );
		String path = uri.getPath();
		int slash = path.lastIndexOf("/");
		return Integer.parseInt( path.substring(slash+1) );
	}
	
	
	
	
	// builders
	
	public static <L extends RelationInterface> Link<L> create() {
		return new Link<L>("","");
	}
	public static <L extends RelationInterface> Link<L> create(String rel, String href) {
		return new Link<L>(rel, href);
	}
	public static <L extends RelationInterface> Link<L> create(L rel, String href) {
		return new Link<L>(rel, href);
	}
	
}