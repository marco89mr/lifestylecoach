package lsc.localdatabase.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lsc.localdatabase.client.LocalDatabaseClient;


public class EntityModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private Map<String, Link> link = new HashMap<String, Link>();

	
	public int getId() { return this.id; }
	

	public Link getLink(String link_rel) { return this.link.get(link_rel); }
	
	public void setLink(Link link) { this.link.put(link.getRel(), link); }
	
	
	public String _getBaseUrl() {
		return LocalDatabaseClient.base_url;
	}
	


}

