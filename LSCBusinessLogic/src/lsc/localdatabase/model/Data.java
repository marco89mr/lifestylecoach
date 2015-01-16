package lsc.localdatabase.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "data", propOrder = { "selfLink", "name", "value", "recordLink" })
@XmlRootElement(name="data")
public class Data extends EntityModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private Record record;
	
	private String name;
	
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
			this.record = RecordCollection.getByUrl( link.getHref() );
	}
	
	
	@XmlElement(name="link")
	public Link getRecordLink() { return Link.create("record", this.record._getUrl() ); }
	
	public void setRecordLink(Link link) { }
	
	
	
	// Accessory methods
	// 
	
	public String _getUrl() {
		return _getBaseUrl()+"data/"+this.id;
	}
	
	
	
	
}

