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


@XmlRootElement(name="datas")
public class DataCollection implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Data> list;	
	
	public DataCollection() { }
	
	public DataCollection(List<Data> list) {
		this.list = list;
	}
	
	@XmlElement(name="data")
	public List<Data> getList() { return this.list; }
	public void setList(List<Data> param) { this.list = param; }

	
	
	
	
	
}
