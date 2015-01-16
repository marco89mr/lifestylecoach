package lsc.localdatabase.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name="linkcontainer")
@XmlType(name = "linkcontainer", propOrder = { "link" })
public class LinkCollection implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Link link;
	
	
	public LinkCollection() { }
	public LinkCollection(Link link) {
		this.link = link;
	}
	public LinkCollection(String rel, String href) {
		this.link = Link.create(rel, href);
	}
	
	@XmlElement(name="link")
	public Link getLink() { return this.link; }
	public void setLink(Link link) { this.link = link; }
	
	public static LinkCollection create(String rel, String href) {
		return new LinkCollection(rel, href);
	}
	public static LinkCollection create(Link link) {
		return new LinkCollection(link);
	}

}