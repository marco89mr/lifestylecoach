package lsc.rest.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;


public abstract class Base<L extends RelationInterface> {

	@XmlTransient
	public abstract Link<L> getSelfLink();
	public abstract void setSelfLink(Link<L> link);

	@XmlTransient
	public abstract Link<L> getParentLink();
	public abstract void setParentLink(Link<L> link);
	
	// From Here it's about the Rest Model for this Class
	//
	
	
	// Links
	//
	
	private Map<String, Link<L>> links = new HashMap<String, Link<L>>();
	
	
	// necessary for marshalling ??? boh
	@XmlTransient
	public Map<String, Link<L>> getLinks() { return this.links; }
	protected void setLinks(Map<String, Link<L>> links) { this.links = links; }
	
	
	protected Link<L> getLink(L l) {
		if(links.get(l.toString()) == null){
			links.put(l.toString(), new Link<L>(l,""));
			return links.get(l.toString());
		}
		return links.get(l.toString());
	}
	
	public Link<L> putLink(Link<L> link) {
		return links.put(link.getRel(), link);
	}
	
	
	
}

