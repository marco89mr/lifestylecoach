package lsc.localdatabase.model;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lsc.localdatabase.dao.LifeCoachDao;


@XmlRootElement(name="link")
@XmlType(name = "link", propOrder = { "rel", "href" })
public class Link implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private String rel;
	private String href;
	
	
	public Link() { }
	public Link(String rel, String href) {
		this.rel = rel;
		this.href = href;
	}
	
	@XmlAttribute
	public String getRel() { return this.rel;	}
	public Link setRel(String rel) {
		this.rel = rel;
		return this;
	}
	
	@XmlAttribute
	public String getHref() { return this.href; }
	public Link setHref(String href) {
		this.href = href;
		return this;
	}
	

	public static Link create() {
		Link link = new Link();
		return link;
	}
	public static Link create(String rel, String href) {
		Link link = new Link();
		link.rel = rel;
		link.href = href;
		return link;
	}

}