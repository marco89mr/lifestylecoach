package lsc.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "data", propOrder = { "id", "recordId", "name", "value" })
@XmlRootElement(name="data")
public class Data extends Base {

	
	private int id;
	
	private int record_id;
	
	private String name;
	
	private String value;
	
	
	
	
	
	
	public Data() { }
	
	
	

	
	
	@XmlElement(name="id")
	public int getId() { return this.id; }
	
	public void setId(int id) { this.id = id; }
	
	
	@XmlElement
	public int getRecordId() { return this.record_id; }
	
	public void setRecordId(int id) { this.record_id = id; }
	
	
	@XmlElement(name="name")
	public String getName() { return this.name; }
	
	public void setName(String name) { this.name = name; }
	
	
	@XmlElement(name="value")
	public String getValue() { return this.value; }
	
	public void setValue(String value) { this.value = value; }
	
	
	
	
	
	
}

