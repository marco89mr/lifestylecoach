package lsc.localdatabase.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;



/**
 * The persistent class for the "record" database table.
 * 
 */
@Entity
@Table(name="\"record\"")
@NamedQuery(name = "Record.findAll", query = "SELECT r FROM Record r")
@XmlType(name = "record", propOrder = { "selfLink", "userLink", "type", "date", "datasLink" })
@XmlRootElement(name="record")
public class Record extends Base implements Serializable {
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
	public Link getSelfLink() { return getLink("self"); }
	
	public void setSelfLink(Link link) { putLink(link); }
	
	
	@XmlElement(name="link", required=true)
	public Link getUserLink() { return getLink("user"); }
	
	public void setUserLink(Link link) { putLink(link); }
	 
	
	@XmlElement(name="link")
	public Link getDatasLink() { return getLink("datas"); }
	
	public void setDatasLink(Link link) { putLink(link); }
	
	
	
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
		return _getBaseUrl()+"record/"+this.id;
	}
	
	
	
}

