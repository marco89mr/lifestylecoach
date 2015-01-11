package lsc.localdatabase.model;

import lsc.localdatabase.App;
import lsc.localdatabase.dao.LifeCoachDao;

import java.io.Serializable;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


@Entity
@Table(name="\"data\"")
@NamedQuery(name = "Data.findAll", query = "SELECT d FROM Data d")
@XmlType(name = "data", propOrder = { "selfLink", "name", "value", "recordLink" })
@XmlRootElement(name="data")
public class Data implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite_data")
	@TableGenerator(name="sqlite_data", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="data")
	@Column(name = "\"id\"")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="\"record_id\"",referencedColumnName="\"id\"", insertable = true, updatable = true)
	private Record record;
	
	@Column(name = "\"name\"")
	private String name;
	
	@Column(name = "\"value\"")
	private String value;
	
	
	
	
	
	
	public Data() { }
	
	
	
	
	
	@XmlTransient
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlTransient // to avoid infinite loop on serialization
	public Record getRecord() {	return record; }
	
	public void setRecord(Record record) { this.record = record;	}
	
	
	@XmlElement(name="name")
	public String getName() { return this.name; }
	
	public void setName(String name) { this.name = name; }
	
	
	@XmlElement(name="value")
	public String getValue() { return this.value; }
	
	public void setValue(String value) { this.value = value; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link getSelfLink() { return Link.create("data", this._getUrl() ); }
	
	public void setSelfLink(Link link) {
		if(link.getRel().equals("record"))
			this.record = Record.getByUrl( link.getHref() );
	}
	
	
	@XmlElement(name="link")
	public Link getRecordLink() { return Link.create("record", this.record._getUrl() ); }
	
	public void setRecordLink(Link link) { }
	
	
	
	// Accessory methods
	// 
	
	public String _getUrl() {
		return App.getBASE_URI()+"data/"+this.id;
	}
	
	
	
	
	
	
	
	
	
	
	// Database operations
	// 
	
	public static Data getByUrl(String url) {
		URI uri = URI.create( url );
		String path = uri.getPath();
		int slash = path.lastIndexOf("/");
		String id = path.substring(slash+1);
		Data entry = Data.getById( Integer.parseInt(id) );
		// check
		if(entry!=null && entry._getUrl().equals( url ) )
			return entry;
		else
			return null;
	}
	
	public static Data getById(int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Data m = em.find(Data.class, id);
		LifeCoachDao.instance.closeConnections(em);
		return m;
	}
	
	public static DataList getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		DataList list = new DataList( em.createNamedQuery("Measure.findAll", Data.class).getResultList() );
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static DataList getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.record.getAll(filters)");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		DataList list = new DataList();
		// building query
		String where = " WHERE d.id > 0";
		
		if(param.containsKey("user_id"))
			where+=" and d.record.user.id = "+param.getFirst("user_id");
		if(param.containsKey("record_id"))
			where+=" and d.record.id LIKE "+param.getFirst("record_id");
		if(param.containsKey("record_type"))
			where+=" and d.record.type LIKE "+param.getFirst("record_type");
		if(param.containsKey("data_name"))
			where+=" and s.name LIKE "+param.getFirst("data_name");
		if(param.containsKey("last"))
			where+=" ORDER BY id DESC LIMIT 0, "+param.getFirst("last");
		
		System.out.println("--> "+"SELECT d FROM Data d"+where);
		TypedQuery<Data> query = em.createQuery("SELECT d FROM Data d"+where, Data.class);
		// querying
		list.setList( query.getResultList() );
		LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Data save(Data m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static Data update(Data m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		m=em.merge(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static void remove(Data m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    m=em.merge(m);
	    em.remove(m);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
	
}

