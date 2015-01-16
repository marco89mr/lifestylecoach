package lsc.localdatabase.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


@Entity
@Table(name="\"data\"")
@NamedQuery(name = "Data.findAll", query = "SELECT d FROM Data d")
@XmlType(name = "data", propOrder = { "selfLink", "name", "value", "recordLink" })
@XmlRootElement(name="data")
public class Data extends Base implements Serializable {
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
	public Link getSelfLink() {	return getLink("self"); }
	
	public void setSelfLink(Link link) { putLink(link);	}
	
	
	@XmlElement(name="link")
	public Link getRecordLink() { return getLink("record"); }
	
	public void setRecordLink(Link link) { putLink(link); }
	
	
	
	// Accessory methods
	// 
	
	public String _getUrl() {
		return _getBaseUrl()+"data/"+this.id;
	}
	
	
	
	
}

