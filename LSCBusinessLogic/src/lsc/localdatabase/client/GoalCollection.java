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


@XmlRootElement(name="goals")
public class GoalCollection implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Goal> list;	
	
	public GoalCollection() { }
	
	public GoalCollection(List<Goal> list) {
		this.list = list;
	}

	@XmlElement(name="goal")
	public List<Goal> getList() { return this.list; }
	public void setList(List<Goal> param) { this.list = param; }
	

	
	
	
	
	
	
	
	
}
