package lsc.localdatabase.client;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="deadlines")
public class DeadlineCollection implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Deadline> list;	
	
	public DeadlineCollection() { }
	
	public DeadlineCollection(List<Deadline> list) {
		this.list = list;
	}

	@XmlElement(name="deadline")
	public List<Deadline> getList() { return this.list; }
	public void setList(List<Deadline> param) { this.list = param; }
	
	
	

	
	
	
	
	
	
	
}
