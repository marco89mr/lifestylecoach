package lsc.localdatabase.rest.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;


public abstract class BaseRest<L extends RelationInterface> {
	
	public abstract LinkRest<L> getSelfLink();
	
	// From Here it's about the Rest Model for this Class
	//
	
	
	// Links
	//
	
	private Map<String, LinkRest<L>> links = new HashMap<String, LinkRest<L>>();
	
	
	// necessary for marshalling ??? boh
	@XmlTransient
	public Map<String, LinkRest<L>> getLinks() { return this.links; }
	protected void setLinks(Map<String, LinkRest<L>> links) { this.links = links; }
	
	
	protected LinkRest<L> getLink(L l) {
		if(links.get(l.toString()) == null)
			return new LinkRest<L>("","");
		return links.get(l.toString());
	}
	
	public LinkRest<L> putLink(LinkRest<L> link) {
		return links.put(link.getRel(), link);
	}
	
	
	
}

