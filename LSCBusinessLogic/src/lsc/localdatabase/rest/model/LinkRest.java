package lsc.localdatabase.rest.model;

import java.net.URI;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name="link")
@XmlType(name = "link", propOrder = { "rel", "href" })
public class LinkRest<L extends RelationInterface> {

	
	private String rel;
	private String href;
	
	
	

	public LinkRest() {	}
	public LinkRest(String rel, String href) {
		this.rel = rel;
		this.href = href;
	}
	public LinkRest(L rel, String href) {
		this.rel = rel.toString();
		this.href = href;
	}
	
	
	
	
	
	@XmlAttribute
	public String getRel() { return this.rel;	}
	public LinkRest<L> setRel(String rel) {
		this.rel = rel;
		return this;
	}
	
	@XmlAttribute
	public String getHref() { return this.href; }
	public LinkRest<L> setHref(String href) {
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
	
	public static <L extends RelationInterface> LinkRest<L> create() {
		return new LinkRest<L>("","");
	}
	public static <L extends RelationInterface> LinkRest<L> create(String rel, String href) {
		return new LinkRest<L>(rel, href);
	}
	public static <L extends RelationInterface> LinkRest<L> create(L rel, String href) {
		return new LinkRest<L>(rel, href);
	}

}