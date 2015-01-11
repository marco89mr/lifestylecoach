package lsc.localdatabase.model;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lsc.localdatabase.dao.LifeCoachDao;


@XmlRootElement(name="linkcontainer")
@XmlType(name = "linkcontainer", propOrder = { "link" })
public class LinkContainer implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Link link;
	
	
	public LinkContainer() { }
	public LinkContainer(Link link) {
		this.link = link;
	}
	public LinkContainer(String rel, String href) {
		this.link = Link.create(rel, href);
	}
	
	@XmlElement(name="link")
	public Link getLink() { return this.link; }
	public void setLink(Link link) { this.link = link; }
	
	public static LinkContainer create(String rel, String href) {
		return new LinkContainer(rel, href);
	}
	public static LinkContainer create(Link link) {
		return new LinkContainer(link);
	}

}