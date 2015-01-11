package lsc.localdatabase.model;

import lsc.localdatabase.App;
import lsc.localdatabase.dao.LifeCoachDao;

import java.io.File;
import java.io.Serializable;
import java.net.URI;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.persistence.oxm.annotations.XmlPath;


/**
 * The persistent class for the "record" database table.
 * 
 */
@Entity
@Table(name="\"record\"")
@NamedQuery(name = "Record.findAll", query = "SELECT r FROM Record r")
@XmlType(name = "record", propOrder = { "selfLink", "userLink", "type", "date", "datasLink" })
@XmlRootElement(name="record")
public class Record implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite_record")
	@TableGenerator(name="sqlite_record", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="record")
	@Column(name = "\"id\"")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="\"user_id\"",referencedColumnName="\"id\"", insertable = true, updatable = true)
	private User user;
	
	@Column(name = "\"type\"")
	private String type;

    @Column(name = "\"date\"")
	private String date;
	
	// mappedBy must be equal to the name of the attribute in Notification that maps this relation
	@OneToMany(mappedBy="record",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Data> data;
	
	
	
	
	public Record() { }
	
	
	

	@XmlTransient
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlTransient // to avoid infinite loop on serialization
	public User getUser() {	return user; }
	
	public void setUser(User user) { this.user = user;	}
	
	
	@XmlElement
	public String getType() { return this.type; }
	
	public void setType(String value) { this.type = value; }
	
	
	@XmlElement
	public String getDate() { return this.date; }
	
	public void setDate(String date) throws ParseException { this.date = date; }
	
	
	@XmlTransient
	public List<Data> getData() { return this.data; }
	
	public void setData(List<Data> param) { this.data = param; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link getSelfLink() { return Link.create("self", this._getUrl() ); }
	
	public void setSelfLink(Link link) {
		System.out.println("DEBUG: selfLink");
		if(link.getRel().equals("user"))
			this.user = User.getByUrl( link.getHref() );
	}
	
	
	@XmlElement(name="link", required=true)
	public Link getUserLink() { return Link.create("user", this.getUser()._getUrl() ); }
	
	public void setUserLink(Link link) { setSelfLink(link); }
	 
	
	@XmlElement(name="link")
	public Link getDatasLink() { return Link.create("datas", this._getUrl()+"/data"); }
	
	public void setDatasLink(Link link) { setSelfLink(link); }
	
	
	
	// Accessory methods
	// 
	
	public Date _getDate() throws ParseException { 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
	    return sdf.parse(this.date);
	}
	
	public void _setDate(Date date) { 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
	    this.date = sdf.format(date);
	}
	
	public String _getUrl() {
		return App.getBASE_URI()+"record/"+this.id;
	}
	
	
	
	
	
	
	
	
	
	
	
	// Database operations
	// 
	
	public static Record getByUrl(String url) {
		URI uri = URI.create( url );
		String path = uri.getPath();
		int slash = path.lastIndexOf("/");
		String id = path.substring(slash+1);
		Record entry = Record.getById( Integer.parseInt(id) );
		// check
		if(entry!=null && entry._getUrl().equals( url ) )
			return entry;
		else
			return null;
	}
	
	public static Record getById(int id) {
		System.out.println("--> model.record.getById("+id+")");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Record m = em.find(Record.class, id);
		LifeCoachDao.instance.closeConnections(em);
		return m;
	}
	
	public static RecordList getAll() {
		System.out.println("--> model.record.getAll()");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		RecordList list = new RecordList( em.createNamedQuery("Record.findAll", Record.class).getResultList() );
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static RecordList getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.record.getAll(filters)");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		RecordList list = new RecordList();
		// building query
		String where = " WHERE r.id > 0";
		
		if(param.containsKey("user_id"))
			where+=" and r.user.id = "+param.getFirst("user_id")+"";
		if(param.containsKey("type"))
			where+=" and r.type LIKE \""+param.getFirst("type")+"\"";
		if(param.containsKey("last"))
			where+=" and r.mail LIKE \""+param.getFirst("last")+"\"";
		if(param.containsKey("fromdate"))
			where+=" and r.date > \""+param.getFirst("fromdate")+"\"";
		if(param.containsKey("todate"))
			where+=" and r.date < \""+param.getFirst("todate")+"\"";
		
		System.out.println("--> "+"SELECT r FROM Record r"+where);
		TypedQuery<Record> query = em.createQuery("SELECT r FROM Record r"+where, Record.class);
		// querying
		list.setList( query.getResultList() );
		LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Record save(Record r) {
		System.out.println("--> model.record.save("+r.getId()+")");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(r);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return r;
	}
	
	public static Record update(Record r) {
		System.out.println("--> model.record.update("+r.getId()+")");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		r=em.merge(r);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return r;
	}
	
	public static void remove(Record r) {
		System.out.println("--> model.record.remove("+r.getId()+")");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    r=em.merge(r);
	    em.remove(r);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
	
}

