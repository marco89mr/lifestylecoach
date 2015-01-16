package lsc.localdatabase.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import lsc.localdatabase.App;


public class Base {
	
	
	private int id;
	
	protected Map<String, Link> links = new HashMap<String, Link>();
	
	
	

	@XmlTransient
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlTransient
	public Map<String, Link> getLinks() { return this.links; }
	
	public void setLinks(Map<String, Link> links) { this.links = links; }

	public Link getLink(String link_rel) { return links.get(link_rel); }

	public Link putLink(Link link) { return links.put(link.getRel(), link); }
	
	
	
	
	public String _getBaseUrl() {
		return App.getBASE_URI().toString();
	}
	
	
}

